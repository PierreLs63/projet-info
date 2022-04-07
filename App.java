import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class App extends JFrame implements ActionListener, ChangeListener  {

    // temps
    public Timer timer;
    public int day = 1;
    public int minute = 0;
    public int dayDuration = 500;

    // map
    public Map map;
    public int mapWidth = 500;
    public int mapHeight = 500;


    // IHM
    JButton startButton;
    JButton createMapButton;
    File file = new File("131-1310596_open-a-trading-account-with-investorseurope-free-getting.png");
    BufferedImage MapButtonIcon = ImageIO.read(file);

    JLabel daysCount;
    double height;
    double width;
    JPanel contentPane;
    JPanel affichageStats;
    JPanel affichageStart;
    JPanel affichageSliders;
    JPanel affichageMap;
    JPanel mapBounds;
    JPanel statBounds;

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

    static final int mapSize_MIN = 100;
    static final int mapSize_MAX = 1000;
    static final int mapSize_INIT = 500;

    static final int BlobSize_INIT = 10;
    static final int BlobSize_MIN = 5;
    static final int BlobSize_MAX = 40;

    // Declaration Sliders
    JSlider speedSlider;
    JSlider foodSlider;
    JSlider energySlider;
    JSlider mapSizeSlider;
    JSlider detectionSlider;
    JSlider blobSizeSlider;

    // Labels
    JLabel speedLabel;
    JLabel FoodLabel;
    JLabel EnergyLabel;
    JLabel MapSizeLabel;
    JLabel BlobSizeLabel;
    JLabel DetectionLabel;

    public App() throws IOException{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        System.out.println(width + " " + height);

        setBounds(0, 0, (int) width, (int) height - 50);
        setTitle("Les Blobs c'est cool");
        setLayout(null);

        EcrancreateMapButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(10, this);

    }

    public void EcrancreateMapButton(){

        // JPanel conteneur qui contient tout les autres JPanel
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setLayout(null);

        // JPanel qui contient tt le côté gauche avec l'image de start du jeu
        affichageStart = new JPanel();
        affichageStart.setBounds(0, 0, 1000, 1000);
        affichageStart.setLayout(null);
        affichageStart.setBackground(Color.pink);

        // JPanel qui contient tout le côté droit avec les sliders
        affichageSliders = new JPanel();
        affichageSliders.setBounds(1000, 0, 920, 1000);
        affichageSliders.setLayout(null);
        affichageSliders.setBackground(Color.yellow);

        // Bouton createMapButton
        createMapButton = new JButton(new ImageIcon(MapButtonIcon));
        createMapButton.setBounds(affichageSliders.getWidth()/2-200, affichageSliders.getHeight()/2, 400, 300);
        createMapButton.setLayout(null);
        createMapButton.addActionListener(this);
        createMapButton.setBorder(BorderFactory.createEmptyBorder());
        createMapButton.setContentAreaFilled(false);
        createMapButton.setBorderPainted(false);
        createMapButton.setFocusPainted(false);
        createMapButton.setContentAreaFilled(false);

        affichageSliders.add(createMapButton);
        contentPane.add(affichageSliders);
        contentPane.add(affichageStart);
        setContentPane(contentPane);
        setVisible(true);

        


    }


    public void EcranSet(){

        //Affichage stat
        affichageStats = new JPanel();
        affichageStats.setBounds(1000, 700, 1000, 300);
        affichageStats.setLayout(null);
        affichageStats.setBackground(Color.red);

        // Bouton START
        startButton = new JButton("START");
        startButton.setBounds(0, 0, 200, 80);
        startButton.setLayout(null);
        startButton.addActionListener(this);

        //sliders
        int lengthSlider = 200;
        int widthSlider = 40;

        //SpeedSlider
        speedSlider = new JSlider(JSlider.HORIZONTAL, speed_MIN, speed_MAX, speed_INIT);
        // Turn on labels at major tick marks.
        int xSpeedSlider = 20;
        int ySpeedSlider = 200;
        speedSlider.setMajorTickSpacing(10);// espace minimal affiché sous le slider entre les valeurs de vitesse
        speedSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(this);
        speedSlider.setBackground(Color.yellow);
        speedSlider.setBounds(xSpeedSlider, ySpeedSlider, lengthSlider, widthSlider);
        speedLabel = new JLabel("Vitesse : " + speed_INIT);
        speedLabel.setBounds(xSpeedSlider + 5, ySpeedSlider - 50, 200, 60);

        //foodSlider
        foodSlider = new JSlider(JSlider.HORIZONTAL, qntFood_MIN, qntfood_MAX, qntfood_INIT);
        // Turn on labels at major tick marks.
        int xfoodSlider = xSpeedSlider;
        int yfoodSlider = ySpeedSlider + 140;
        foodSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les valeurs de vitesse
        foodSlider.setMinorTickSpacing(5);// espace minimal entre les valeurs de vitesse
        foodSlider.setPaintTicks(true);
        foodSlider.setPaintLabels(true);
        foodSlider.addChangeListener(this);
        foodSlider.setBounds(xfoodSlider, yfoodSlider, lengthSlider, widthSlider);
        foodSlider.setBackground(Color.yellow);
        FoodLabel = new JLabel("Quantité de nourriture : " + qntfood_INIT);
        FoodLabel.setBounds(xfoodSlider + 5, yfoodSlider - 50, 200, 60);

        //MapSize Slider
        mapSizeSlider = new JSlider(JSlider.HORIZONTAL, mapSize_MIN, mapSize_MAX, mapSize_INIT);
        int xmapSizeSlider = xfoodSlider;
        int ymapSizeSlider = yfoodSlider + 140;
        mapSizeSlider.setMajorTickSpacing(180);// espace minimal affiché sous le slider entre les valeurs de vitesse
        mapSizeSlider.setMinorTickSpacing(50);// espace minimal entre les valeurs de vitesse
        mapSizeSlider.setPaintTicks(true);
        mapSizeSlider.setPaintLabels(true);
        mapSizeSlider.addChangeListener(this);
        mapSizeSlider.setBounds(xmapSizeSlider, ymapSizeSlider, lengthSlider, widthSlider);
        mapSizeSlider.setBackground(Color.yellow);
        MapSizeLabel = new JLabel("Taille de la carte: " + mapSize_INIT);
        MapSizeLabel.setBounds(xmapSizeSlider + 5, ymapSizeSlider - 50, 200, 60);

        //BlobsNumber Slider
        blobSizeSlider = new JSlider(JSlider.HORIZONTAL, BlobSize_MIN, BlobSize_MAX, BlobSize_INIT);
        // Turn on labels at major tick marks.
        int xblobSizeSlider = xSpeedSlider;
        int yblobSizeSlider = ymapSizeSlider + 140;
        blobSizeSlider.setMajorTickSpacing(5);// espace minimal affiché sous le slider entre les valeurs de vitesse
        blobSizeSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        blobSizeSlider.setPaintTicks(true);
        blobSizeSlider.setPaintLabels(true);
        blobSizeSlider.addChangeListener(this);
        blobSizeSlider.setBounds(xblobSizeSlider, yblobSizeSlider, lengthSlider, widthSlider);
        blobSizeSlider.setBackground(Color.yellow);
        BlobSizeLabel = new JLabel("Taille du blob : " + BlobSize_INIT);
        BlobSizeLabel.setBounds(xblobSizeSlider + 5, yblobSizeSlider - 50, 200, 60);

        //EnergyIni Slider
        energySlider = new JSlider(JSlider.HORIZONTAL, Energy_MIN, Energy_MAX, Energy_INIT);
        // Turn on labels at major tick marks.
        int xenergySlider = xSpeedSlider + 250;
        int yenergySlider = ySpeedSlider;
        energySlider.setMajorTickSpacing(500);// espace minimal affiché sous le slider entre les valeurs de vitesse
        energySlider.setMinorTickSpacing(100);// espace minimal entre les valeurs de vitesse
        energySlider.setPaintTicks(true);
        energySlider.setPaintLabels(true);
        energySlider.addChangeListener(this);
        energySlider.setBounds(xenergySlider, yenergySlider, lengthSlider, widthSlider);
        energySlider.setBackground(Color.yellow);
        EnergyLabel = new JLabel("Energie des blobs : " + energySlider.getValue());
        EnergyLabel.setBounds(xenergySlider + 5, yenergySlider - 50, 200, 60);

        // DetectionRange Slider
        detectionSlider = new JSlider(JSlider.HORIZONTAL, detection_MIN, detection_MAX, detection_INIT);
        int xdetectionSlider = xenergySlider;
        int ydetectionSlider = yenergySlider + 140;
        detectionSlider.setMajorTickSpacing(10);
        detectionSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        detectionSlider.setPaintTicks(true);
        detectionSlider.setPaintLabels(true);
        detectionSlider.addChangeListener(this);
        detectionSlider.setBounds(xdetectionSlider, ydetectionSlider, lengthSlider, widthSlider);
        detectionSlider.setBackground(Color.yellow);
        DetectionLabel = new JLabel("Champ de vision : " + detection_INIT);
        DetectionLabel.setBounds(xdetectionSlider + 5, ydetectionSlider - 50, 200, 60);

        //add
        affichageSliders.remove(createMapButton);
        affichageSliders.add(startButton);
        affichageSliders.add(speedSlider);
        affichageSliders.add(speedLabel);
        affichageSliders.add(mapSizeSlider);
        affichageSliders.add(MapSizeLabel);
        affichageSliders.add(FoodLabel);
        affichageSliders.add(foodSlider);
        affichageSliders.add(EnergyLabel);
        affichageSliders.add(energySlider);
        affichageSliders.add(blobSizeSlider);
        affichageSliders.add(BlobSizeLabel);
        affichageSliders.add(detectionSlider);
        affichageSliders.add(DetectionLabel);
        contentPane.add(affichageSliders);
        contentPane.add(affichageStart);
        setContentPane(contentPane);
        setVisible(true);

    }

    public void EcranJeu(){

        // JPanel conteneur qui contient affichageSliders et affichageMap
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setLayout(null);

        // JPanel qui contient tt le côté gauche avec l'affichage de la partie
        affichageMap = new JPanel();
        affichageMap.setBounds(0, 0, 1000, 1000);
        affichageMap.setLayout(null);
        affichageMap.setBackground(Color.pink);

        // JPanel qui contient tout le côté droit avec les sliders
        affichageSliders = new JPanel();
        affichageSliders.setBounds(1000, 0, 920, 1000);
        affichageSliders.setLayout(null);
        affichageSliders.setBackground(Color.yellow);

        // JPanel qui contient juste la map
        mapBounds = new JPanel();
        mapBounds.setBounds((1000 - map.width) / 2, (1000 - map.height) / 2, map.width, map.height);
        mapBounds.setLayout(null);

        // JLabel qui affiche le nb de jours passés
        daysCount = new JLabel("day");
        daysCount.setBounds(800, 20, 80, 20);
        daysCount.setLayout(null);

        statBounds = new JPanel();
        statBounds.setBounds(0,(int) height-stat.height, stat.width, stat.height);
        statBounds.setLayout(null);

        affichageSliders.remove(startButton);
        affichageSliders.remove(speedSlider);
        affichageSliders.remove(speedLabel);
        affichageSliders.remove(mapSizeSlider);
        affichageSliders.remove(MapSizeLabel);
        affichageSliders.remove(FoodLabel);
        affichageSliders.remove(foodSlider);
        affichageSliders.remove(EnergyLabel);
        affichageSliders.remove(energySlider);
        affichageSliders.remove(blobSizeSlider);
        affichageSliders.remove(BlobSizeLabel);
        affichageSliders.remove(detectionSlider);
        affichageSliders.remove(DetectionLabel);
        mapBounds.add(map);
        statBounds.add(stat);
        affichageMap.add(mapBounds);
        affichageSliders.add(daysCount);
        affichageSliders.add(startButton);
        affichageSliders.add(statBounds);
        contentPane.add(affichageSliders);
        contentPane.add(affichageMap);
        setContentPane(contentPane);        
        setVisible(true);

        map.repaint(); // actualise la map
        stat.repaint();

    }

    public void actionPerformed(java.awt.event.ActionEvent e) { // tout ce qui se passe chaque x ms

        if (e.getSource() == createMapButton){
            EcranSet();
        }

        if (e.getSource() == startButton) {

            map = new Map(mapWidth, mapHeight);
            stat = new Stats(map.blobs);
            stat.fetch(map.blobs);
            stat.repaint();
            EcranJeu();

            map.iniBlob(); // initialise un tableau de blob chacun placés
            // aléatoirement sur les bords de la map
            map.iniFood(); // initialise un tableau de food chacun placés
            // aléatoirement sur la map
            timer.start();
            //stat.repaint(); // actualise les stats

        }

        if (e.getSource() == timer) {

            minute++;
            for (Blob unBlob : map.blobs) {
                if (unBlob.energy > 0) {
                    map.moveBlobs(unBlob);
                }
                unBlob.energy = unBlob.energy - 0.05 * unBlob.size - 0.05 * Math.log(unBlob.speed)
                        - 0.002 * unBlob.viewRange;
                //System.out.println(map.blobs.get(1).energy);
            }

            map.eatBlob();

        }
        if (minute == day * dayDuration) { // ce qui se passe à la fin de la journée
            map.whipeBlobs();
            map.resetFood();
            map.newGeneration();
            stat.fetch(map.blobs);
            stat.repaint();
            day++;
            System.out.println("day " + day);
        }
        map.repaint();

    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (source == speedSlider) {
            speedLabel.setText("Vitesse : " + speedSlider.getValue());
            map.blobIniSpeed =speedSlider.getValue() ;

        } else if (source == foodSlider) {
            FoodLabel.setText("quantite nourriture : " + foodSlider.getValue());
            map.initFoodNumber = foodSlider.getValue();
        } else if (source == mapSizeSlider) {
            MapSizeLabel.setText("Taille de la carte : " + mapSizeSlider.getValue());
            mapWidth = mapSizeSlider.getValue();
            mapHeight = mapSizeSlider.getValue();
        } else if (source == blobSizeSlider) {
            BlobSizeLabel.setText("Taille du blob : " + blobSizeSlider.getValue());
            map.blobIniSize = blobSizeSlider.getValue();
        } else if (source == detectionSlider) {
            DetectionLabel.setText("Champ de vision : " + detectionSlider.getValue());
        } else if (source == energySlider) {
            EnergyLabel.setText("Energie des blobs : " + energySlider.getValue());
        }
    }

}
