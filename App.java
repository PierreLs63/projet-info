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
    JPanel mapBounds;
    JPanel affichageMap;

    public App (Map aMap){
        this.setTitle("Le jeu des blobs");
        this.setResizable(true);

        map = aMap;

        setBounds(0, 0, 1920, 1000);
        setLayout(null);

<<<<<<< HEAD
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0,0,getWidth(),getHeight());
        contentPane.setLayout(null);

        JPanel affichageGlobal = new JPanel();
        affichageGlobal.setBounds(0,50,1000,1000);
        affichageGlobal.setBackground(Color.white);
        affichageGlobal.setLayout(null);
=======
        JPanel affichageMap = new JPanel();
        affichageMap.setBounds(0,50,1000,500);
        affichageMap.setBackground(Color.green);
        affichageMap.setLayout(null);
>>>>>>> 17a671158c8a085ef2139e0c084f5153b56389b1

        mapBounds = new JPanel();
        mapBounds.setBounds(500, 100, map.width, map.height);
        mapBounds.setLayout(null);
        mapBounds.setBackground(Color.yellow);

        JButton f = new JButton("appuyez");
        f.setBounds(500,500,50,50);


        affichageGlobal.add(map);
        contentPane.add(affichageGlobal);
        contentPane.add(f);

<<<<<<< HEAD
        setContentPane(contentPane);
=======
        affichageMap.add(mapBounds);
        add(affichageMap);
>>>>>>> 17a671158c8a085ef2139e0c084f5153b56389b1

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        map.repaint(); // actualise l'IDH

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
        map.repaint();
    }


}
