import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    // variables du terrain
    public int width;
    public int height;
    public int wallWidth;
    public int wallHeight;
    public int WALL_REPULSION_FORCE = 1000;

    // blobs
    public int initBlobNumber;
    public double blobIniSpeed;
    public double blobIniSize;
    public double blobIniView;
    public double energyIni;
    public ArrayList<Blob> blobs = new ArrayList<Blob>();
    public double WANDERING_STRENGTH_FORCE = 2;

    // Coefs de variation
    public double amplitudeVariationSize ;
    public double amplitudeVariationSpeed;
    public double amplitudeVariationEnergy;
    public double amplitudeVariationView;
    public double chanceVariation;// entre 0 est 1

    // foods
    public int initFoodNumber = 20;
    public ArrayList<Food> foods = new ArrayList<Food>();

    // IHM
    private Image dbImage;
    private Graphics dbg;

    public Map(int w, int h) {
        width = w; // largeur de la map
        height = h; // hauteur de la map
        wallWidth = width / 20; // largeur des bords map
        wallHeight = height / 20; // hauteur des bords map

        setBounds(0, 0, width, height);
        setLayout(null);

    }

    public void iniFood() {
        for (int i = 0; i < initFoodNumber; i++) {
            foods.add(new Food(Math.random() * (width - 2.5 * wallWidth) + wallWidth,
                    Math.random() * (height - 2.5 * wallHeight) + wallHeight));
        }
    }

    public void iniBlob() {
        for (int i = 0; i < initBlobNumber; i++) {
            blobs.add(new Blob(blobIniSpeed, blobIniSize, blobIniView, energyIni));
            blobs.get(i).energy = blobs.get(i).energyIni;
            double rand = Math.random();
            if (rand < 0.25) {
                blobs.get(i).pos_x = 0 + blobs.get(i).size;
                blobs.get(i).pos_y = Math.random() * (height - 2 * wallHeight) + wallHeight;
                blobs.get(i).speedV = new Vect(1, 0);
            } else if (rand > 0.25 && rand < 0.5) {
                blobs.get(i).pos_x = width - blobs.get(i).size;
                blobs.get(i).pos_y = Math.random() * (height - 2 * wallHeight) + wallHeight;
                blobs.get(i).speedV = new Vect(-1, 0);
            } else if (rand > 0.5 && rand < 0.75) {
                blobs.get(i).pos_x = Math.random() * (width - 2 * wallWidth) + wallWidth;
                blobs.get(i).pos_y = 0 + blobs.get(i).size;
                blobs.get(i).speedV = new Vect(0, 1);
            } else {
                blobs.get(i).pos_x = Math.random() * (width - 2 * wallWidth) + wallWidth;
                blobs.get(i).pos_y = height - blobs.get(i).size;
                blobs.get(i).speedV = new Vect(0, -1);
            }
        }
    }

    public int testBord(Blob unBlob) { // vérifie si le blob détecte les murs de la map
        if (new Vect(wallWidth, unBlob.pos_y).distance(unBlob.pos_x,
                unBlob.pos_y) <= 10 && unBlob.wallBounce == true) {
            return -1; /// mur de gauche
        } else if (new Vect(width - wallWidth, unBlob.pos_y).distance(unBlob.pos_x,
                unBlob.pos_y) <= 10 && unBlob.wallBounce == true) {
            return 1; // mur de droite
        } else if (new Vect(unBlob.pos_x, wallHeight).distance(unBlob.pos_x,
                unBlob.pos_y) <= 10 && unBlob.wallBounce == true) {
            return 2; // mur du haut
        } else if (new Vect(unBlob.pos_x, height - wallHeight).distance(unBlob.pos_x,
                unBlob.pos_y) <= 10 && unBlob.wallBounce == true) {
            return -2; // mur du bas
        } else {
            return 0; // pas de mur
        }
    }

    public Vect attractFood(Blob unBlob) {

        int index = -1;
        double distMin = -1;
        for (int i = 0; i < foods.size(); i++) {
            double dist = new Vect(unBlob.pos_x, unBlob.pos_y).distance(foods.get(i).pos_x,
                    foods.get(i).pos_y);

            if (dist < unBlob.size) {
                foods.remove(i);
                unBlob.foodB++;
            }

            else if (dist < unBlob.viewRange) {
                if (distMin == -1 || dist < distMin) {
                    index = i;
                    distMin = dist;
                }
            }
        }
        if (index >= 0) {
            return new Vect(foods.get(index).pos_x - unBlob.pos_x,
                    foods.get(index).pos_y - unBlob.pos_y);
        } else {
            return new Vect(0, 0);
        }
    }

    public Vect testBlobTarget(Blob unBlob) { // vérifie si le blob détecte un blob plus petit
        ArrayList<Blob> blobTarget = new ArrayList<Blob>();
        for (Blob e : blobs) {
            if (new Vect(unBlob.pos_x, unBlob.pos_y).distance(e.pos_x,
                    e.pos_y) <= unBlob.viewRange
                    && unBlob != e && unBlob.size < (0.8 * e.size)) {
                blobTarget.add(e);

            }

        }
        if (blobTarget.size() == 0) {
            return new Vect(0, 0);
        } else {
            Blob target = blobTarget.get(0);
            for (Blob e : blobTarget) {
                if (new Vect(unBlob.pos_x, unBlob.pos_y).distance(e.pos_x,
                        e.pos_y) < new Vect(unBlob.pos_x, unBlob.pos_y).distance(target.pos_x,
                                target.pos_y)) {
                    target = e;
                }
            }
            return new Vect(unBlob.pos_x - target.pos_x, unBlob.pos_y - target.pos_y);
        }
    }

    public Vect testBlobPredator(Blob unBlob) { // vérifie si le blob détecte un blob plus grand
        ArrayList<Blob> blobPredator = new ArrayList<Blob>();
        boolean test = false;
        for (Blob e : blobs) {
            if (new Vect(unBlob.pos_x, unBlob.pos_y).distance(e.pos_x,
                    e.pos_y) <= unBlob.viewRange
                    && unBlob != e && 0.8 * unBlob.size > e.size && !isSafe(e)) {
                blobPredator.add(e);
            }
        }
        if (blobPredator.size() == 0 || test == true) {
            return new Vect(0, 0);
        } else {
            Blob predator = blobPredator.get(0);
            for (Blob e : blobPredator) {
                if (new Vect(unBlob.pos_x, unBlob.pos_y).distance(e.pos_x,
                        e.pos_y) < new Vect(unBlob.pos_x, unBlob.pos_y).distance(predator.pos_x,
                                predator.pos_y)) {
                    predator = e;
                }
            }
            return new Vect(predator.pos_x - unBlob.pos_x, predator.pos_y - unBlob.pos_y);

        }
    }

    public void eatBlob() {
        ArrayList<Blob> blobsEaten = new ArrayList<>();

        for (Blob unBlob : blobs) {
            for (Blob e : blobs) {
                if (e != unBlob && new Vect(unBlob.pos_x, unBlob.pos_y).distance(e.pos_x, e.pos_y) <= unBlob.size
                        && unBlob.size * 0.8 > e.size && !isSafe(unBlob) && !isSafe(e)) {
                    unBlob.foodB++;
                    unBlob.energy = unBlob.energy + 500;
                    blobsEaten.add(e);

                }

            }

        }
        while (blobsEaten.size() > 0) {
            blobs.remove(blobsEaten.get(0));
            blobsEaten.remove(0);
        }
    }

    public void whipeBlobs() {

        ArrayList<Blob> blobsToRemove = new ArrayList<>();

        for (Blob unBlob : blobs) {
            unBlob.wallBounce = false;
            unBlob.energy = unBlob.energyIni;
            if (unBlob.pos_x >= wallWidth && unBlob.pos_x <= (width - wallWidth)
                    && unBlob.pos_y >= wallHeight && unBlob.pos_y <= (height - wallHeight)) {
                blobsToRemove.add(unBlob);
            }
        }
        while (blobsToRemove.size() > 0) {
            blobs.remove(blobsToRemove.get(0));
            blobsToRemove.remove(0);
        }
    }

    public void resetFood() {
        ArrayList<Food> foodsToRemove = new ArrayList<>();

        for (Food miam : foods) {
            foodsToRemove.add(miam);
        }
        while (foodsToRemove.size() > 0) {
            foods.remove(foodsToRemove.get(0));
            foodsToRemove.remove(0);
        }
        iniFood();
    }

    public boolean isSafe(Blob unBlob) {
        return (unBlob.pos_x < wallWidth || unBlob.pos_x > (width - wallWidth)
                || unBlob.pos_y < wallHeight || unBlob.pos_y > (height - wallHeight));
    }

    public void moveBlobs(Blob unBlob) {
        if (unBlob.foodB == 1 && unBlob.energy >= unBlob.energyIni / 3 || unBlob.foodB == 0) { // déplacement des blobs
                                                                                               // qui ont moins de 2
                                                                                               // npurriture et encore
                                                                                               // 2/3 de leur énergie
            if (isSafe(unBlob)) {
                unBlob.wanderingStrength = 0;
                unBlob.wallBounce = false;
                unBlob.foodAttrationForce = 0;
                unBlob.targetAttrationForce = 0;
                unBlob.predatorRepulsionForce = 0;
                unBlob.VectSpeed(new Vect(unBlob.pos_x - width / 2, unBlob.pos_y - height / 2), -5); // recalcule les
                                                                                                     // forces
                                                                                                     // appliquées au
                                                                                                     // blob et son
                                                                                                     // déplacement

            } else if (testBord(unBlob) == -2) { // faire que les blobs soient repoussés par le mur du bas
                unBlob.wanderingStrength = 0;
                unBlob.VectSpeed(new Vect(0, -1), WALL_REPULSION_FORCE); // applique une force qui les repousse du mur
                unBlob.wallBounce = false;

            } else if (testBord(unBlob) == 2) { // faire que les blobs soient repoussés par le mur du haut
                unBlob.wanderingStrength = 0;
                unBlob.VectSpeed(new Vect(0, 1), WALL_REPULSION_FORCE); // applique une force qui les repousse du mur
                unBlob.wallBounce = false;

            } else if (testBord(unBlob) == -1) { // faire que les blobs soient repoussés par le mur de gauche
                unBlob.wanderingStrength = 0;
                unBlob.VectSpeed(new Vect(1, 0), WALL_REPULSION_FORCE); // applique une force qui les repousse du mur
                unBlob.wallBounce = false;

            } else if (testBord(unBlob) == 1) { // faire que les blobs soient repoussés par le mur de droite
                unBlob.wanderingStrength = 0;
                unBlob.VectSpeed(new Vect(-1, 0), WALL_REPULSION_FORCE); // applique une force qui les repousse du mur
                unBlob.wallBounce = false;

            } else if (!attractFood(unBlob).equals(new Vect(0, 0))) {
                unBlob.wanderingStrength = WANDERING_STRENGTH_FORCE;
                unBlob.VectSpeed(attractFood(unBlob), unBlob.foodAttrationForce);
                unBlob.wallBounce = true;

            } else if (!testBlobPredator(unBlob).equals(new Vect(0, 0))) { // faire que les blobs fuient ceux plus gros
                unBlob.wanderingStrength = WANDERING_STRENGTH_FORCE;
                unBlob.VectSpeed(testBlobPredator(unBlob), unBlob.predatorRepulsionForce);
                unBlob.wallBounce = true;

            } else if (!testBlobTarget(unBlob).equals(new Vect(0, 0))) {// faire que les blobs poursuivent ceux plus
                                                                        // petits
                unBlob.wanderingStrength = WANDERING_STRENGTH_FORCE;
                unBlob.VectSpeed(testBlobTarget(unBlob), unBlob.targetAttrationForce);
                unBlob.wallBounce = true;

            } else { //// déplacement des blobs qui ont dépassé les murs
                unBlob.wanderingStrength = 5;
                unBlob.wallBounce = true;
                unBlob.foodAttrationForce = 20;
                unBlob.targetAttrationForce = 30;
                unBlob.predatorRepulsionForce = 30;
                unBlob.VectSpeed(); // recalcule les forces appliquées au blob et son déplacement

            }
        } else {
            if (!isSafe(unBlob)) {
                List<Double> test = Arrays
                        .asList(new Double[] { unBlob.pos_x, width - unBlob.pos_x, unBlob.pos_y,
                                height - unBlob.pos_y });
                int index = test.indexOf(Collections.min(test));
                switch (index) {
                    case 0:
                        unBlob.VectSpeed(new Vect(-1, 0), 20);
                        break;
                    case 1:
                        unBlob.VectSpeed(new Vect(1, 0), 20);
                        break;
                    case 2:
                        unBlob.VectSpeed(new Vect(0, -1), 20);
                        break;
                    case 3:
                        unBlob.VectSpeed(new Vect(0, 1), 20);
                        break;
                }
            }

        }

    }

    public Blob newBlob(Blob parent) {
        double size = parent.size;
        if (Math.random() < chanceVariation) {
            size += (amplitudeVariationSize / size) * (Math.random() - 0.5) * 2;
        }
        double speed = parent.speed;
        if (Math.random() < chanceVariation) {
            speed += (amplitudeVariationSpeed / speed) * (Math.random() - 0.5) * 2;
        }
        double viewRange = parent.viewRange;
        if (Math.random() < chanceVariation) {
            viewRange += (amplitudeVariationView / viewRange) * (Math.random() - 0.5) * 2;
        }
        double energyIni = parent.energyIni;
        if (Math.random() < chanceVariation) {
            energyIni += (amplitudeVariationEnergy / energyIni) * (Math.random() - 0.5) * 20;
        }
        return new Blob(speed, size, viewRange, energyIni);
    }

    public void newGeneration() {
        ArrayList<Blob> blobsTemp = new ArrayList<Blob>();
        for (Blob el : blobs) {
            if (el.foodB == 2) {
                Blob blobC = newBlob(el);
                blobC.pos_x = el.pos_x;
                blobC.pos_y = el.pos_y;
                blobC.energy = blobC.energyIni;
                blobsTemp.add(blobC);
            }
            el.foodB = 0;
        }
        for (Blob el : blobsTemp) {
            blobs.add(el);
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color (40,155,93)); // la map ext
        g.fillRect(0, 0, width, height);
        g.setColor(Color.green); // la map int
        g.fillRect(wallWidth, wallHeight, width - 2 * wallWidth, height - 2 * wallWidth);

        for (Blob unBlob : blobs) { // les blobs
            unBlob.draw(g, unBlob.color);
        }

        for (Food miam : foods) { // la nourriture
            miam.draw(g, Color.yellow);
        }
    }

    public void paint(Graphics g) {
        dbImage = createImage(width, height);
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
}
