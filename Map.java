import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Map extends JFrame implements ActionListener {
    // variables du terrain
    int width;
    int height;
    int wallWidth;
    int wallHeight;
    int wallRepulsionForce = 1000;

    // temps
    Timer timer;
    int day = 1;
    int minute = 0;

    // blobs
    int initBlobNumber = 10;
    double blobIniSpeed = 3;
    double blobIniSize = 10;
    double blobIniView = 100;
    ArrayList<Blob> blobs = new ArrayList<Blob>();

    // foods
    int initFoodNumber = 20;
    ArrayList<Food> foods = new ArrayList<Food>();

    // IHM
    private Image dbImage;
    private Graphics dbg;

    public Map(int w, int h) {
        width = w; // largeur de la map
        height = h; // hauteur de la map
        wallWidth = width / 20; // largeur des bords map
        wallHeight = height / 20; // hauteur des bords map
        setBounds(0, 0, width, height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initBlob(); // initialise un tableau de blob chacun placés
                    // aléatoirement sur les bords de la map
        initFood(); // initialise un tableau de food chacun placés
                    // aléatoirement sur la map

        blobs.get(2).size = 20;
        timer = new Timer(50, this); // ttes les actions se feront les x ms
        timer.start(); // commence la partie
        repaint(); // actualise l'IDH
    }

    public void initFood() {
        for (int i = 0; i < initFoodNumber; i++) {
            foods.add(new Food(Math.random() * (width - 2.5 * wallWidth) + wallWidth,
                    Math.random() * (height - 2.5 * wallHeight) + wallHeight));
        }
    }

    public void initBlob() {
        for (int i = 0; i < initBlobNumber; i++) {
            blobs.add(new Blob(blobIniSpeed, blobIniSize, blobIniView));
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
        blobs.get(2).speed = 10.1;

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

            else if (dist < unBlob.view_range) {
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
                    e.pos_y) <= unBlob.view_range
                    && unBlob != e && unBlob.size < (e.size - 0.2 * e.size)) {
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
                    e.pos_y) <= unBlob.view_range
                    && unBlob != e && 0.8 * unBlob.size > e.size) {
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

    public Blob eatBlob(Blob unBlob) {
        for (Blob e : blobs) {
            if (e != unBlob && new Vect(unBlob.pos_x, unBlob.pos_y).distance(e.pos_x, e.pos_y) <= e.size
                    && unBlob.size * 0.8 > e.size) {
                unBlob.foodB++;
                unBlob.energy = unBlob.energy + 500;
                return e;
            }
        }
        return null;
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
                unBlob.VectSpeed(); // recalcule les forces appliquées au blob et son déplacement

            } else if (testBord(unBlob) == -2) { // faire que les blobs soient repoussés par le mur du bas
                unBlob.wanderingStrength = 0;
                unBlob.VectSpeed(new Vect(0, -1), wallRepulsionForce); // applique une force qui les repousse du mur
                unBlob.wallBounce = false;

            } else if (testBord(unBlob) == 2) { // faire que les blobs soient repoussés par le mur du haut
                unBlob.wanderingStrength = 0;
                unBlob.VectSpeed(new Vect(0, 1), wallRepulsionForce); // applique une force qui les repousse du mur
                unBlob.wallBounce = false;

            } else if (testBord(unBlob) == -1) { // faire que les blobs soient repoussés par le mur de gauche
                unBlob.wanderingStrength = 0;
                unBlob.VectSpeed(new Vect(1, 0), wallRepulsionForce); // applique une force qui les repousse du mur
                unBlob.wallBounce = false;

            } else if (testBord(unBlob) == 1) { // faire que les blobs soient repoussés par le mur de droite
                unBlob.wanderingStrength = 0;
                unBlob.VectSpeed(new Vect(-1, 0), wallRepulsionForce); // applique une force qui les repousse du mur
                unBlob.wallBounce = false;

            } else if (!attractFood(unBlob).equals(new Vect(0, 0))) {
                unBlob.wanderingStrength = 2;
                unBlob.VectSpeed(attractFood(unBlob), unBlob.foodAttrationForce);
                unBlob.wallBounce = true;

            } else if (!testBlobPredator(unBlob).equals(new Vect(0, 0))) { // faire que les blobs fuient ceux plus gros
                unBlob.wanderingStrength = 2;
                unBlob.VectSpeed(testBlobPredator(unBlob), unBlob.predatorRepulsionForce);
                unBlob.wallBounce = true;

            } else if (!testBlobTarget(unBlob).equals(new Vect(0, 0))) {// faire que les blobs poursuivent ceux plus
                                                                        // petits
                unBlob.wanderingStrength = 2;
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
                    .asList(new Double[] { unBlob.pos_x, width - unBlob.pos_x, unBlob.pos_y, height - unBlob.pos_y });
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

    public void paintComponent(Graphics g) {
        g.setColor(Color.pink); // la map ext
        g.fillRect(0, 0, width, height);
        g.setColor(Color.blue); // la map int
        g.fillRect(wallWidth, wallHeight, width - 2 * wallWidth, height - 2 * wallWidth);

        for (Blob unBlob : blobs) { // les blobs
            unBlob.draw(g, Color.yellow);
        }
        blobs.get(2).draw(g, Color.red); // un certain blob pour les tests

        for (Food miam : foods) { // la nourriture
            miam.draw(g, Color.green);
        }
    }

    public void paint(Graphics g) {
        dbImage = createImage(width, height);
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) { // tout ce qui se passe chaque x ms
        if (e.getSource() == timer) {
            minute++;
            ArrayList<Blob> blobsEaten = new ArrayList<>();
            for (Blob unBlob : blobs) {
                if (unBlob.energy > 0) {
                    moveBlobs(unBlob);
                }
                unBlob.energy = unBlob.energy - 0.05 * unBlob.size - 0.05 * unBlob.speed
                        - 0.05 * unBlob.view_range;
                System.out.println(blobs.get(2).energy);
                System.out.println(blobs.get(2).foodB);

                Blob blobEaten = eatBlob(unBlob);
                if (blobEaten != null)
                    blobsEaten.add(eatBlob(unBlob));
            }

            while (blobsEaten.size() > 0) {
                blobs.remove(blobsEaten.get(0));
                blobsEaten.remove(0);
            }
        }
        if (minute == day * 200) { // ce qui se passe à la fin de la journée

            ArrayList<Blob> blobsToRemove = new ArrayList<>();
            for (Blob unBlob : blobs) {
                unBlob.foodB = 0;
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
            day++;
        }
        repaint();
    }
}
