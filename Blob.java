
import java.awt.*;

public class Blob {
    // Caractéristiques du blob
    public double speed;
    public double size;
    public double viewRange;
    public double energy;
    public double energyIni = 1000;
    public boolean alive = true;
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
    public double steeringStrength = 10;
    public int foodAttrationForce;
    public int predatorRepulsionForce;
    public int targetAttrationForce;

    public Blob(double sp, double si, double vr) {
        speed = sp;
        size = si;
        viewRange = vr;
        color = definedColor();
    }

    public void VectSpeed() { // calcul la nouvelle direction et vitesse du blob
        movement();
        computeNewVitesse();
        speedV.x = newSpeedV.x;
        speedV.y = newSpeedV.y;
        orientation = speedV.angle();

    }

    public void VectSpeed(Vect newForce, int newForceStrength) { // calcul la nouvelle direction et vitesse du blob
        movement();
        computeNewVitesse(newForce, newForceStrength);
        speedV.x = newSpeedV.x;
        speedV.y = newSpeedV.y;
        orientation = speedV.angle();

    }

    public void movement() { // fait se déplacer le blob
        pos_x += speedV.x;
        pos_y += speedV.y;
    }

    public void computeNewVitesse() { // calcul la nouvelle vitesse du blob
        computeWanderingForce();
        newSpeedV = (speedV.times(steeringStrength)).vectAdd(wanderingForceV.times(wanderingStrength));
        newSpeedV.normalize();
        newSpeedV = newSpeedV.times(Math.log(speed));

    }

    public void computeNewVitesse(Vect newForce, int newForceStrength) {
        computeWanderingForce();
        newSpeedV = (speedV.times(steeringStrength))
                .vectAdd(wanderingForceV.times(wanderingStrength).vectAdd(newForce.times(newForceStrength)));
        newSpeedV.normalize();
        newSpeedV = newSpeedV.times(Math.log(speed));

    }

    public void computeWanderingForce() { // calcul la force aléatoire qui s'aplique au blob pour le faire se déplacer
                                          // aléatoirement dans l'espace
        orientation += Math.PI / 2;
        wanderingForceV.x += (Math.random() - 0.5) * Math.cos(orientation);
        wanderingForceV.y += (Math.random() - 0.5) * Math.sin(orientation);
        wanderingForceV.normalize();

    }

    public void draw(Graphics g, Color c) { // dessine un blob
        g.setColor(c);
        //definedColor();
        g.fillOval((int) (pos_x), (int) (pos_y), (int) size, (int) size);
    }

    public Color definedColor() {
        int r = (int) Math.round(speed) * 30;
        int g = (int) Math.round(size) * 10;
        int b = (int) Math.round(viewRange) * 5;
        if (r >= 255) {
            r = 255;
        } else if (g >= 255) {
            g = 255;
        } else if (b >= 255) {
            b = 255;
        }
        return new Color(r, g, b);
    }

}
