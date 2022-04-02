import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class App extends JFrame implements ActionListener {

    // temps
    public Timer timer;
    public int day = 1;
    public int minute = 0;
    public int dayDuration = 500;

    // map
    public Map map;
    private Image dbImage;
    private Graphics dbg;
    JPanel mapBounds;

    public App (Map aMap){
        map = aMap;
        mapBounds = new JPanel();
        setBounds(0, 0, 1920, 1000);
        setLayout(null);

        JPanel affichageGlobal = new JPanel();
        affichageGlobal.setBounds(0,50,1920,1000);
        affichageGlobal.setBackground(Color.white);
        affichageGlobal.setLayout(null);

        mapBounds.setBounds(500, 100, map.width, map.height);
        mapBounds.setLayout(null);
        mapBounds.setBackground(Color.green);


        affichageGlobal.add(mapBounds);
        add(affichageGlobal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        repaint(); // actualise l'IDH

        timer = new Timer(10, this);
        timer.start();

    }
    

    public void actionPerformed(java.awt.event.ActionEvent e) { // tout ce qui se passe chaque x ms
        if (e.getSource() == timer) {
            minute++;
            for (Blob unBlob : map.blobs) {
                if (unBlob.energy > 0) {
                    map.moveBlobs(unBlob);
                }
                unBlob.energy = unBlob.energy - 0.05 * unBlob.size - 0.05 * Math.log(unBlob.speed)
                        - 0.002 * unBlob.viewRange;
                        System.out.println(map.blobs.get(1).energy);  
            }

            map.eatBlob();

        }
        if (minute == day * dayDuration) { // ce qui se passe à la fin de la journée

            map.whipeBlobs();
            map.resetFood();
            map.newGeneration();
            day++;
            System.out.println("day " + day);
        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.pink); // la map ext
        g.fillRect(0, 0, map.width, map.height);
        g.setColor(Color.blue); // la map int
        g.fillRect(map.wallWidth, map.wallHeight, map.width - 2 * map.wallWidth, map.height - 2 * map.wallWidth);

        for (Blob unBlob : map.blobs) { // les blobs
            unBlob.draw(g, unBlob.color);
        }

        for (Food miam : map.foods) { // la nourriture
            miam.draw(g, Color.green);
        }
    }

    public void paint(Graphics g) {
        dbImage = createImage(map.width, map.height);
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 50, mapBounds);
    }
}
