import java.awt.*;

public class Food {
    public double pos_x;
    public double pos_y;
    public double size;
    public Color color = new Color(68,151,0);

    public Food(double x, double y, double size) {
        this.size = size;
        pos_x = x;
        pos_y = y;
    }

    public Food(double x, double y) {
        size = 5.0;
        pos_x = x;
        pos_y = y;
    }

    public void draw(Graphics g) { // dessine la nourriture
        g.setColor(color);
        g.fillOval((int) (pos_x), (int) (pos_y), (int) size, (int) size);
    }
}
