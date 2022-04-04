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

    public App (Map aMap){
        map = aMap;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        setBounds(0, 0, (int)width, (int)height-50);
        setTitle("Les Blobs c'est cool");
        setLayout(null);

        JPanel mapBounds = new JPanel();
        mapBounds.setBounds((1000-map.width)/2, (1000-map.height)/2, map.width, map.height);
        mapBounds.setLayout(null);

        
        JPanel affichageMap = new JPanel();
        affichageMap.setBounds(0,0,1000,1000);
        affichageMap.setLayout(null);
        affichageMap.setBackground(Color.pink);

        JPanel contentPane = new JPanel();
        contentPane.setBounds(0,0,getWidth(),getHeight());
        contentPane.setLayout(null);
        contentPane.setBackground(Color.blue);

        mapBounds.add(map);
        affichageMap.add(mapBounds);
        contentPane.add(affichageMap);

        

        //JButton f = new JButton("appuyez");
        // f.setBounds(500,500,50,50);


        //contentPane.add(f);

        setContentPane(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        map.repaint(); // actualise l'IDH

        timer = new Timer(10, this);
        //timer.start();

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


