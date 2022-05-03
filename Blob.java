
import java.awt.*;

public class Blob {
    // Caractéristiques du blob
    public double speed;
    public double size;
    public double viewRange;
    public double energy;
    public double energyIni;
    public Color color = new Color(0, 0, 0);
    public int foodB = 0;

    // position du blob
    public double pos_x = 0;
    public double pos_y = 0;
    public boolean wallBounce = false;;

    // liste des vecteurs vitesse
    public Vect speedV = new Vect(0, 0);
    public Vect newSpeedV = new Vect(0, 0);
    public Vect wanderingForceV = new Vect(0, 0);

    // parametres de deplacement
    public double wanderingStrength;
    public double orientation = 0;
    public double STEERING_STRENGTH = 10;
    public int foodAttrationForce;
    public int predatorRepulsionForce;
    public int targetAttrationForce;

    /**
     * Constructeur
     */
    public Blob(double sp, double si, double vr, double en) {
        speed = sp;
        size = si;
        viewRange = vr;
        energyIni = en;
        color = definedColor();
    }

    /**
     * calcul la nouvelle direction et vitesse du blob
     */
    public void VectSpeed() {
        movement();
        computeNewVitesse();
        speedV.x = newSpeedV.x;
        speedV.y = newSpeedV.y;
        orientation = speedV.angle();

    }

    /**
     * calcul la nouvelle direction et vitesse du blob
     */
    public void VectSpeed(Vect newForce, int newForceStrength) {
        movement();
        computeNewVitesse(newForce, newForceStrength);
        speedV.x = newSpeedV.x;
        speedV.y = newSpeedV.y;
        orientation = speedV.angle();

    }

    /**
     * fait se déplacer le blob
     */
    public void movement() {
        pos_x += speedV.x;
        pos_y += speedV.y;
    }

    /**
     * calcul la nouvelle vitesse du blob si il n'a pas de force spécifiques qui lui
     * sont aplliquées
     */
    public void computeNewVitesse() {
        computeWanderingForce();
        newSpeedV = (speedV.times(STEERING_STRENGTH)).vectAdd(wanderingForceV.times(wanderingStrength));
        newSpeedV.normalize();
        newSpeedV = newSpeedV.times(Math.log(speed));

    }

    /**
     * calcul la nouvelle vitesse du blob si il a une force spécifiques qui lui est
     * aplliquée
     */
    public void computeNewVitesse(Vect newForce, int newForceStrength) {
        computeWanderingForce();
        newSpeedV = (speedV.times(STEERING_STRENGTH))
                .vectAdd(wanderingForceV.times(wanderingStrength).vectAdd(newForce.times(newForceStrength)));
        newSpeedV.normalize();
        newSpeedV = newSpeedV.times(Math.log(speed));

    }

    /**
     * calcul la force aléatoire qui s'aplique au blob pour le faire se déplacer
     * aléatoirement dans l'espace
     */
    public void computeWanderingForce() {
        orientation += Math.PI / 2;
        wanderingForceV.x += (Math.random() - 0.5) * Math.cos(orientation);
        wanderingForceV.y += (Math.random() - 0.5) * Math.sin(orientation);
        wanderingForceV.normalize();

    }

    /**
     * dessine un blob
     */
    public void draw(Graphics g, Color c) { 
        color = definedColor();
        g.setColor(c);
        // definedColor();
        g.fillOval((int) (pos_x), (int) (pos_y), (int) (size * (Math.sin(energy * 0.025) + 7) / 8),
                (int) (size * (Math.sin(energy * 0.025) + 7) / 8));
    }

    /**
     * définie la couleur du blob en fonction de ses caractéristiques (vitesse, taille, rayon de vision)
     */
    public Color definedColor() {
        int r = (int) Math.round(speed) * 10;
        int g = (int) Math.round(size) * 15;
        int b = (int) Math.round(viewRange) * 10;
        if (r >= 254) {
            r = 254;
        }
        if (g >= 254) {
            g = 254;
        }
        if (b >= 254) {
            b = 254;
        }
        if (r < 0) {
            r = 0;
        }
        if (g < 0) {
            g = 0;
        }
        if (b < 0) {
            b = 0;
        }
        return new Color(r, g, b);
    }

}
