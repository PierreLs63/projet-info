
import java.awt.*;

public class Blob {
    // Caractéristiques du blob
    public double speed;
    public double size;
    public double viewRange;
    public double energy;
    public double energyIni;
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

    public Blob(double sp, double si, double vr,double en) {
        speed = sp;
        size = si;
        viewRange = vr;
        energyIni = en;
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
        color=definedColor();
        g.setColor(c);
        //definedColor();
        g.fillOval((int) (pos_x), (int) (pos_y), (int) (size*(Math.sin(energy*0.025)+7)/8), (int) (size*(Math.sin(energy*0.025)+7)/8));
    }

    public Color definedColor() {
        int r = (int) Math.round(speed) * 10;
        int g = (int) Math.round(size) * 15;
        int b = (int) Math.round(viewRange) * 10;
        if (r >= 254) {
            r = 254;
        }if (g >= 254) {
            g = 254;
        }if (b >= 254) {
            b = 254;
        }
        if (r < 0) {
            r = 0;
        }if (g <0) {
            g = 0;
        }if (b < 0) {
            b = 0;
        }
        return new Color(r, g, b);
    }

}
