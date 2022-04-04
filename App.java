import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App extends JFrame implements ActionListener, ChangeListener {

    // temps
    public Timer timer;
    public int day = 1;
    public int minute = 0;
    public int dayDuration = 500;

    // map
    public Map map;

    // IHM
    JButton startButton;
    JLabel daysCount;

    //Stats
    Stats stat;

    // Valeurs MIN,MAX,INIT slliders
    static final int speed_MIN = 0;
    static final int speed_MAX = 30;
    static final int speed_INIT = 20; // initial speed
    static final int Energy_MIN = 0;
    static final int Energy_MAX = 2000;
    static final int Energy_INIT = 500;
    static final int qntfood_MAX = 100;
    static final int qntFood_MIN = 0;
    static final int qntfood_INIT = 5;
    static final int detection_MIN = 10;
    static final int detection_MAX = 50;
    static final int detection_INIT = 50;
    static final int mapSize_MIN = 10;
    static final int mapSize_MAX = 50;
    static final int mapSize_INIT = 30;
    static final int BlobSize_INIT = 10;
    static final int BlobSize_MIN = 5;
    static final int BlobSize_MAX = 40;

    // Declaration Sliders
    JSlider speedSlider;
    JSlider FoodSlider;
    JSlider EnergySlider;
    JSlider MapSizeSlider;
    JSlider DetectionSlider;
    JSlider BlobSizeSlider;

    // Labels
    JLabel speedLabel;
    JLabel FoodLabel;
    JLabel EnergyLabel;
    JLabel MapSizeLabel;
    JLabel BlobSizeLabel;
    JLabel DetectionLabel;

    public App(Map aMap) {
        map = aMap;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        System.out.println(width + " " + height);

        setBounds(0, 0, (int) width, (int) height - 50);
        setTitle("Les Blobs c'est cool");
        setLayout(null);

        System.out.println(map.blobIniSize);

        // JPanel qui contient juste la map
        JPanel mapBounds = new JPanel();
        mapBounds.setBounds((1000 - map.width) / 2, (1000 - map.height) / 2, map.width, map.height);
        mapBounds.setLayout(null);

        JPanel statBounds = new JPanel();
        statBounds.setBounds(300,50, 400,300);
        statBounds.setLayout(null);


        // JPanel qui contient tt le côté gauche qui est celui de la map
        JPanel affichageMap = new JPanel();
        affichageMap.setBounds(0, 0, 1000, 1000);
        affichageMap.setLayout(null);
        affichageMap.setBackground(Color.pink);

        // JPanel qui contient tout le côté droit avec les sliders
        JPanel affichageSliders = new JPanel();
        affichageSliders.setBounds(1000, 0, 920, 1000);
        affichageSliders.setLayout(null);
        affichageSliders.setBackground(Color.yellow);

        // JPanel conteneur qui contient affichageSliders et affichageMap
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setLayout(null);

        // Bouton START
        startButton = new JButton("START");
        startButton.setBounds(700, 900, 200, 80);
        startButton.addActionListener(this);

        daysCount = new JLabel("day");

        int lengthSlider = 200;
        int widthSlider = 40;
        speedSlider = new JSlider(JSlider.HORIZONTAL, speed_MIN, speed_MAX, speed_INIT);
        // Turn on labels at major tick marks.
        int xSpeedSlider = 20;
        int ySpeedSlider = 200;
        speedSlider.setMajorTickSpacing(10);// espace minimal affiché sous le slider entre les valeurs de vitesse
        speedSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(this);
        speedLabel = new JLabel("Vitesse : " + speed_INIT);
        speedLabel.setBounds(xSpeedSlider, ySpeedSlider - 70, 200, 60);
        FoodSlider = new JSlider(JSlider.HORIZONTAL, qntFood_MIN, qntfood_MAX, qntfood_INIT);
        // Turn on labels at major tick marks.
        int xFoodSlider = xSpeedSlider;
        int yFoodSlider = ySpeedSlider + 140;
        FoodSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les valeurs de vitesse
        FoodSlider.setMinorTickSpacing(5);// espace minimal entre les valeurs de vitesse
        FoodSlider.setPaintTicks(true);
        FoodSlider.setPaintLabels(true);
        FoodSlider.addChangeListener(this);
        FoodLabel = new JLabel("quantite nourriture : " + qntfood_INIT);
        FoodLabel.setBounds(xFoodSlider, yFoodSlider - 70, 200, 60);

        MapSizeSlider = new JSlider(JSlider.HORIZONTAL, mapSize_MIN, mapSize_MAX, mapSize_INIT);
        int xMapSizeSlider = xFoodSlider;
        int yMapSizeSlider = yFoodSlider + 140;
        MapSizeSlider.setMajorTickSpacing(10);// espace minimal affiché sous le slider entre les valeurs de vitesse
        MapSizeSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        MapSizeSlider.setPaintTicks(true);
        MapSizeSlider.setPaintLabels(true);
        MapSizeSlider.addChangeListener(this);
        MapSizeLabel = new JLabel("Taille de la carte: " + mapSize_INIT);
        MapSizeLabel.setBounds(xMapSizeSlider, yMapSizeSlider - 70, 200, 60);

        BlobSizeSlider = new JSlider(JSlider.HORIZONTAL, BlobSize_MIN, BlobSize_MAX, BlobSize_INIT);
        // Turn on labels at major tick marks.
        int xBlobSizeSlider = xSpeedSlider;
        int yBlobSizeSlider = yMapSizeSlider + 140;
        BlobSizeSlider.setMajorTickSpacing(5);// espace minimal affiché sous le slider entre les valeurs de vitesse
        BlobSizeSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        BlobSizeSlider.setPaintTicks(true);
        BlobSizeSlider.setPaintLabels(true);
        BlobSizeSlider.addChangeListener(this);
        BlobSizeLabel = new JLabel("Taille du blob : " + BlobSize_INIT);
        BlobSizeSlider.setBounds(xBlobSizeSlider, yBlobSizeSlider - 70, 200, 60);
        BlobSizeLabel.setBounds(xBlobSizeSlider, yBlobSizeSlider - 70, 200, 60);

        EnergySlider = new JSlider(JSlider.HORIZONTAL, Energy_MIN, Energy_MAX, Energy_INIT);
        // Turn on labels at major tick marks.
        int xEnergySlider = xSpeedSlider + 250;
        int yEnergySlider = ySpeedSlider;
        EnergySlider.setMajorTickSpacing(500);// espace minimal affiché sous le slider entre les valeurs de vitesse
        EnergySlider.setMinorTickSpacing(100);// espace minimal entre les valeurs de vitesse
        EnergySlider.setPaintTicks(true);
        EnergySlider.setPaintLabels(true);
        EnergySlider.addChangeListener(this);
        EnergyLabel = new JLabel("Energie des blobs : " + EnergySlider.getValue());
        EnergySlider.setBounds(xEnergySlider, yEnergySlider - 70, 500, 60);
        EnergyLabel.setBounds(xEnergySlider, yEnergySlider - 70, 200, 60);

        // Slider modifiant la distance de détection des blobs
        DetectionSlider = new JSlider(JSlider.HORIZONTAL, detection_MIN, detection_MAX, detection_INIT);
        int xDetectionSlider = xSpeedSlider;
        int yDetectionSlider = yBlobSizeSlider + 140;
        DetectionSlider.setMajorTickSpacing(10);
        DetectionSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        DetectionSlider.setPaintTicks(true);
        DetectionSlider.setPaintLabels(true);
        DetectionSlider.addChangeListener(this);
        DetectionLabel = new JLabel("Champ de vision : " + detection_INIT);
        DetectionLabel.setBounds(xDetectionSlider, yDetectionSlider - 70, 200, 60);

        affichageSliders.add(speedSlider);
        affichageSliders.add(speedLabel);
        affichageSliders.add(MapSizeSlider);
        affichageSliders.add(MapSizeLabel);
        affichageSliders.add(FoodLabel);
        affichageSliders.add(FoodSlider);
        affichageSliders.add(EnergyLabel);
        affichageSliders.add(EnergySlider);
        affichageSliders.add(BlobSizeSlider);
        affichageSliders.add(BlobSizeLabel);
        affichageSliders.add(DetectionSlider);
        affichageSliders.add(DetectionLabel);
        speedSlider.setBounds(xSpeedSlider, ySpeedSlider, lengthSlider, widthSlider);
        FoodSlider.setBounds(xFoodSlider, yFoodSlider, lengthSlider, widthSlider);
        EnergySlider.setBounds(xEnergySlider, yEnergySlider, lengthSlider, widthSlider);
        MapSizeSlider.setBounds(xMapSizeSlider, yMapSizeSlider, lengthSlider, widthSlider);
        BlobSizeSlider.setBounds(xBlobSizeSlider, yBlobSizeSlider, lengthSlider, widthSlider);
        DetectionSlider.setBounds(xDetectionSlider, yDetectionSlider, lengthSlider, widthSlider);
        affichageSliders.setLayout(null);

        mapBounds.add(map);
        statBounds.add(stat);
        affichageMap.add(mapBounds);
        contentPane.add(affichageSliders);
        contentPane.add(affichageMap);

        affichageSliders.add(startButton);
        affichageSliders.add(statBounds);

        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        map.repaint(); // actualise l'IDH
        stat.repaint();

        timer = new Timer(10, this);

    }

    public void actionPerformed(java.awt.event.ActionEvent e) { // tout ce qui se passe chaque x ms
        if (e.getSource() == startButton) {

            map.iniBlob(); // initialise un tableau de blob chacun placés
            // aléatoirement sur les bords de la map
            map.iniFood(); // initialise un tableau de food chacun placés
            // aléatoirement sur la map
            timer.start();

        }

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
            stat = new Stats(map.blobs);
            map.whipeBlobs();
            map.resetFood();
            map.newGeneration();
            day++;
            System.out.println("day " + day);
        }
        map.repaint();
        stat.repaint();

    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (source == speedSlider) {
            speedLabel.setText("Vitesse : " + speedSlider.getValue());
            map.blobIniSpeed =speedSlider.getValue() ;

        } else if (source == FoodSlider) {
            FoodLabel.setText("quantite nourriture : " + FoodSlider.getValue());
            map.initFoodNumber = FoodSlider.getValue();
        } else if (source == MapSizeSlider) {
            MapSizeLabel.setText("Taille de la carte : " + MapSizeSlider.getValue());
        } else if (source == BlobSizeSlider) {
            BlobSizeLabel.setText("Taille du blob : " + BlobSizeSlider.getValue());
            map.blobIniSize = BlobSizeSlider.getValue();
        } else if (source == DetectionSlider) {
            DetectionLabel.setText("Champ de vision : " + DetectionSlider.getValue());
        } else if (source == EnergySlider) {
            EnergyLabel.setText("Energie des blobs : " + EnergySlider.getValue());
        }
    }

}
