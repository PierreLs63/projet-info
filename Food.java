import java.awt.*;

/**
 * Objet nourriture, stocke une position et une taille
 */
public class Food {
    public double pos_x;
    public double pos_y;
    public final int SIZE = 5;

    /**
     * Constructeur
     */
    public Food(double x, double y) {
        pos_x = x;
        pos_y = y;
    }

    /**
     * Dessine des cercles pour l'affichage de la nourriture
     */
    public void draw(Graphics g, Color c) { 
        g.setColor(c);
        g.fillOval((int) (pos_x), (int) (pos_y), SIZE, SIZE);
    }
}
