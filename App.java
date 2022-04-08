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

public class App extends JFrame implements ActionListener, ChangeListener {

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
    JButton backButton;
    JButton createMapButton300;
    JButton createMapButton400;
    JButton createMapButton500;
    JButton createMapButton600;
    JButton createMapButton700;
    JButton createMapButton800;
    JButton createMapButton900;
    JButton createMapButton1000;
    JLabel texteCreaMap;
    JLabel fondDroit;
    // File file = new
    // File("131-1310596_open-a-trading-account-with-investorseurope-free-getting.png");
    // BufferedImage MapButtonIcon = ImageIO.read(file);
    JLabel daysCount;
    double height;
    double width;
    JPanel contentPane;
    JPanel affichageStats;
    JPanel affichageBoutons;
    JPanel affichageStart;
    JPanel affichageSliders;
    JPanel affichageMap;
    JPanel mapBounds;
    JPanel statBounds;

    // Stats
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
    // sliders
    int widthSlider = 800;
    int heightSlider = 40;
    JSlider speedSlider;
    JSlider foodSlider;
    JSlider energySlider;
    JSlider detectionSlider;
    JSlider blobSizeSlider;

    // Labels
    JLabel speedLabel;
    JLabel FoodLabel;
    JLabel EnergyLabel;
    JLabel BlobSizeLabel;
    JLabel DetectionLabel;

    public App() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        System.out.println(width + " " + height);

        setBounds(0, 0, (int) width, (int) height - 50);
        setTitle("Les Blobs c'est cool");
        setLayout(null);

        EcranCreateMap();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(10, this);

    }

    public void EcranCreateMap() {

        // JPanel conteneur qui contient tout les autres JPanel
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setLayout(null);

        // JPanel qui contient tt le côté gauche avec l'image de start du jeu
        affichageStart = new JPanel();
        affichageStart.setBounds(0, 0, 1000, 1000);
        affichageStart.setLayout(null);
        affichageStart.setBackground(Color.pink);

        // JPanel qui contient tout le côté droit avec les boutons
        affichageBoutons = new JPanel();
        affichageBoutons.setBounds(1000, 0, 920, 1000);
        affichageBoutons.setLayout(null);
        affichageBoutons.setBackground(Color.yellow);

        // Texte de création de la map
        texteCreaMap = new JLabel(new ImageIcon("./Images/TexteCréaCarte.png"));
        texteCreaMap.setLayout(null);
        texteCreaMap.setBounds(103, 200, 713, 160);

        // Fond du côté droit de l'IHM
        fondDroit = new JLabel(new ImageIcon("./Images/FondDroit.jpeg"));
        fondDroit.setLayout(null);
        fondDroit.setBounds(0, 0, 920, 1000);

        // Bouton createMapButton300
        createMapButton300 = new JButton(new ImageIcon("./Images/Logo300.png"));
        createMapButton300.setBounds(affichageBoutons.getWidth() / 6 - 100, affichageBoutons.getHeight() / 2, 150, 80);
        createMapButton300.setLayout(null);
        createMapButton300.addActionListener(this);
        createMapButton300.setBorder(BorderFactory.createEmptyBorder());
        createMapButton300.setContentAreaFilled(false);
        createMapButton300.setBorderPainted(false);
        createMapButton300.setFocusPainted(false);
        createMapButton300.setContentAreaFilled(false);

        // Bouton createMapButton400
        createMapButton400 = new JButton(new ImageIcon("./Images/Logo400.png"));
        createMapButton400.setBounds(createMapButton300.getX() + 200, affichageBoutons.getHeight() / 2, 150, 80);
        createMapButton400.setLayout(null);
        createMapButton400.addActionListener(this);
        createMapButton400.setBorder(BorderFactory.createEmptyBorder());
        createMapButton400.setContentAreaFilled(false);
        createMapButton400.setBorderPainted(false);
        createMapButton400.setFocusPainted(false);
        createMapButton400.setContentAreaFilled(false);

        // Bouton createMapButton500
        createMapButton500 = new JButton(new ImageIcon("./Images/Logo500.png"));
        createMapButton500.setBounds(createMapButton400.getX() + 200, affichageBoutons.getHeight() / 2, 150, 80);
        createMapButton500.setLayout(null);
        createMapButton500.addActionListener(this);
        createMapButton500.setBorder(BorderFactory.createEmptyBorder());
        createMapButton500.setContentAreaFilled(false);
        createMapButton500.setBorderPainted(false);
        createMapButton500.setFocusPainted(false);
        createMapButton500.setContentAreaFilled(false);

        // Bouton createMapButton600
        createMapButton600 = new JButton(new ImageIcon("./Images/Logo600.png"));
        createMapButton600.setBounds(createMapButton500.getX() + 200, affichageBoutons.getHeight() / 2, 150, 80);
        createMapButton600.setLayout(null);
        createMapButton600.addActionListener(this);
        createMapButton600.setBorder(BorderFactory.createEmptyBorder());
        createMapButton600.setContentAreaFilled(false);
        createMapButton600.setBorderPainted(false);
        createMapButton600.setFocusPainted(false);
        createMapButton600.setContentAreaFilled(false);

        // Bouton createMapButton700
        createMapButton700 = new JButton(new ImageIcon("./Images/Logo700.png"));
        createMapButton700.setBounds(affichageBoutons.getWidth() / 6 - 100, affichageBoutons.getHeight() / 2 + 200, 150,
                80);
        createMapButton700.setLayout(null);
        createMapButton700.addActionListener(this);
        createMapButton700.setBorder(BorderFactory.createEmptyBorder());
        createMapButton700.setContentAreaFilled(false);
        createMapButton700.setBorderPainted(false);
        createMapButton700.setFocusPainted(false);
        createMapButton700.setContentAreaFilled(false);

        // Bouton createMapButton800
        createMapButton800 = new JButton(new ImageIcon("./Images/Logo800.png"));
        createMapButton800.setBounds(createMapButton700.getX() + 200, affichageBoutons.getHeight() / 2 + 200, 150, 80);
        createMapButton800.setLayout(null);
        createMapButton800.addActionListener(this);
        createMapButton800.setBorder(BorderFactory.createEmptyBorder());
        createMapButton800.setContentAreaFilled(false);
        createMapButton800.setBorderPainted(false);
        createMapButton800.setFocusPainted(false);
        createMapButton800.setContentAreaFilled(false);

        // Bouton createMapButton900
        createMapButton900 = new JButton(new ImageIcon("./Images/Logo900.png"));
        createMapButton900.setBounds(createMapButton800.getX() + 200, affichageBoutons.getHeight() / 2 + 200, 150, 80);
        createMapButton900.setLayout(null);
        createMapButton900.addActionListener(this);
        createMapButton900.setBorder(BorderFactory.createEmptyBorder());
        createMapButton900.setContentAreaFilled(false);
        createMapButton900.setBorderPainted(false);
        createMapButton900.setFocusPainted(false);
        createMapButton900.setContentAreaFilled(false);

        // Bouton createMapButton1000
        createMapButton1000 = new JButton(new ImageIcon("./Images/Logo1000.png"));
        createMapButton1000.setBounds(createMapButton900.getX() + 200, affichageBoutons.getHeight() / 2 + 200, 177, 80);
        createMapButton1000.setLayout(null);
        createMapButton1000.addActionListener(this);
        createMapButton1000.setBorder(BorderFactory.createEmptyBorder());
        createMapButton1000.setContentAreaFilled(false);
        createMapButton1000.setBorderPainted(false);
        createMapButton1000.setFocusPainted(false);
        createMapButton1000.setContentAreaFilled(false);

        contentPane.removeAll();
        affichageBoutons.add(createMapButton300);
        affichageBoutons.add(createMapButton400);
        affichageBoutons.add(createMapButton500);
        affichageBoutons.add(createMapButton600);
        affichageBoutons.add(createMapButton700);
        affichageBoutons.add(createMapButton800);
        affichageBoutons.add(createMapButton900);
        affichageBoutons.add(createMapButton1000);
        affichageBoutons.add(texteCreaMap);
        affichageBoutons.add(fondDroit);
        contentPane.add(affichageBoutons);
        contentPane.add(affichageStart);
        setContentPane(contentPane);
        setVisible(true);

    }

    public void EcranSet() {

        // JPanel qui contient tout le côté droit avec les sliders
        affichageSliders = new JPanel();
        affichageSliders.setBounds(1000, 0, 920, 1000);
        affichageSliders.setLayout(null);
        affichageSliders.setBackground(Color.yellow);

        // Bouton START
        startButton = new JButton(new ImageIcon("./Images/Start.png"));
        startButton.setBounds(affichageSliders.getWidth()/3 - 100, affichageSliders.getHeight()-100, 200, 80);
        startButton.setLayout(null);
        startButton.addActionListener(this);

        // Bouton BACK
        backButton = new JButton(new ImageIcon("./Images/Back.png"));
        backButton.setBounds(2*affichageSliders.getWidth()/3 - 100, affichageSliders.getHeight()-100, 200, 80);
        backButton.setLayout(null);
        backButton.addActionListener(this);

        // SpeedSlider
        speedSlider = new JSlider(JSlider.HORIZONTAL, speed_MIN, speed_MAX, speed_INIT);
        // Turn on labels at major tick marks.
        speedSlider.setMajorTickSpacing(10);// espace minimal affiché sous le slider entre les valeurs de vitesse
        speedSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(this);
        speedSlider.setOpaque(false);
        //speedSlider.setBackground(new ImageIcon("./Images/FondDroit.jpeg"));
        speedSlider.setBounds(affichageSliders.getWidth()/2 - widthSlider/2, affichageSliders.getHeight()/6 - 100, widthSlider, heightSlider);
        speedLabel = new JLabel("Vitesse : " + speed_INIT);
        speedLabel.setBounds(speedSlider.getX()+5 + 5, speedSlider.getY() - 50, 200, 60);

        // foodSlider
        foodSlider = new JSlider(JSlider.HORIZONTAL, qntFood_MIN, qntfood_MAX, qntfood_INIT);
        // Turn on labels at major tick marks.
        foodSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les valeurs de vitesse
        foodSlider.setMinorTickSpacing(5);// espace minimal entre les valeurs de vitesse
        foodSlider.setPaintTicks(true);
        foodSlider.setPaintLabels(true);
        foodSlider.addChangeListener(this);
        foodSlider.setBounds(affichageSliders.getWidth()/2 - widthSlider/2, 2*affichageSliders.getHeight()/6 - 100, widthSlider, heightSlider);
        foodSlider.setOpaque(false);
        FoodLabel = new JLabel("Quantité de nourriture : " + qntfood_INIT);
        FoodLabel.setBounds(foodSlider.getX() + 5 , foodSlider.getY() - 50, 200, 60);

        // BlobsSize Slider
        blobSizeSlider = new JSlider(JSlider.HORIZONTAL, BlobSize_MIN, BlobSize_MAX, BlobSize_INIT);
        // Turn on labels at major tick marks.
        blobSizeSlider.setMajorTickSpacing(5);// espace minimal affiché sous le slider entre les valeurs de vitesse
        blobSizeSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        blobSizeSlider.setPaintTicks(true);
        blobSizeSlider.setPaintLabels(true);
        blobSizeSlider.addChangeListener(this);
        blobSizeSlider.setBounds(affichageSliders.getWidth()/2 - widthSlider/2, 3*affichageSliders.getHeight()/6 - 100, widthSlider, heightSlider);
        blobSizeSlider.setOpaque(false);
        BlobSizeLabel = new JLabel("Taille du blob : " + BlobSize_INIT);
        BlobSizeLabel.setBounds(blobSizeSlider.getX() + 5 , blobSizeSlider.getY() - 50, 200, 60);

        // EnergyIni Slider
        energySlider = new JSlider(JSlider.HORIZONTAL, Energy_MIN, Energy_MAX, Energy_INIT);
        // Turn on labels at major tick marks.
        energySlider.setMajorTickSpacing(500);// espace minimal affiché sous le slider entre les valeurs de vitesse
        energySlider.setMinorTickSpacing(100);// espace minimal entre les valeurs de vitesse
        energySlider.setPaintTicks(true);
        energySlider.setPaintLabels(true);
        energySlider.addChangeListener(this);
        energySlider.setBounds(affichageSliders.getWidth()/2 - widthSlider/2, 4*affichageSliders.getHeight()/6 - 100, widthSlider, heightSlider);
        energySlider.setOpaque(false);
        EnergyLabel = new JLabel("Energie des blobs : " + energySlider.getValue());
        EnergyLabel.setBounds(energySlider.getX() + 5 , energySlider.getY() - 50, 200, 60);

        // DetectionRange Slider
        detectionSlider = new JSlider(JSlider.HORIZONTAL, detection_MIN, detection_MAX, detection_INIT);
        detectionSlider.setMajorTickSpacing(10);
        detectionSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        detectionSlider.setPaintTicks(true);
        detectionSlider.setPaintLabels(true);
        detectionSlider.addChangeListener(this);
        detectionSlider.setBounds(affichageSliders.getWidth()/2 - widthSlider/2, 5*affichageSliders.getHeight()/6 - 100, widthSlider, heightSlider);
        detectionSlider.setOpaque(false);
        DetectionLabel = new JLabel("Champ de vision : " + detection_INIT);
        DetectionLabel.setBounds(detectionSlider.getX() + 5 , detectionSlider.getY() - 50, 200, 60);

        // add
        contentPane.removeAll();
        affichageSliders.add(startButton);
        affichageSliders.add(backButton);
        affichageSliders.add(speedSlider);
        affichageSliders.add(speedLabel);
        affichageSliders.add(FoodLabel);
        affichageSliders.add(foodSlider);
        affichageSliders.add(EnergyLabel);
        affichageSliders.add(energySlider);
        affichageSliders.add(blobSizeSlider);
        affichageSliders.add(BlobSizeLabel);
        affichageSliders.add(detectionSlider);
        affichageSliders.add(DetectionLabel);
        affichageSliders.add(fondDroit);
        contentPane.add(affichageSliders);
        contentPane.add(affichageStart);
        setContentPane(contentPane);
        setVisible(true);

    }

    public void EcranJeu() {

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
        statBounds.setBounds(0, (int) height - stat.height, stat.width, stat.height);
        statBounds.setLayout(null);

        // add et remove
        contentPane.removeAll();
        mapBounds.add(map);
        statBounds.add(stat);
        affichageMap.add(mapBounds);
        affichageSliders.add(daysCount);
        affichageSliders.add(statBounds);
        contentPane.add(affichageSliders);
        contentPane.add(affichageMap);
        setContentPane(contentPane);
        setVisible(true);

        map.repaint(); // actualise la map
        stat.repaint();

    }

    public void actionPerformed(java.awt.event.ActionEvent e) { // tout ce qui se passe chaque x ms

        if (e.getSource() == createMapButton300) {
            map = new Map(300, 300);
            EcranSet();
        }

        if (e.getSource() == backButton) {
            EcranCreateMap();
        }

        if (e.getSource() == createMapButton400) {
            map = new Map(400, 400);
            EcranSet();
        }

        if (e.getSource() == createMapButton500) {
            map = new Map(500, 500);
            EcranSet();
        }

        if (e.getSource() == createMapButton600) {
            map = new Map(600, 600);
            EcranSet();
        }

        if (e.getSource() == createMapButton700) {
            map = new Map(700, 700);
            EcranSet();
        }

        if (e.getSource() == createMapButton800) {
            map = new Map(800, 800);
            EcranSet();
        }

        if (e.getSource() == createMapButton900) {
            map = new Map(900, 900);
            EcranSet();
        }

        if (e.getSource() == createMapButton1000) {
            map = new Map(1000, 1000);
            EcranSet();
        }

        if (e.getSource() == startButton) {

            stat = new Stats(map.blobs);
            stat.fetch(map.blobs);
            stat.repaint();
            EcranJeu();

            map.iniBlob(); // initialise un tableau de blob chacun placés
            // aléatoirement sur les bords de la map
            map.iniFood(); // initialise un tableau de food chacun placés
            // aléatoirement sur la map
            timer.start();
            // stat.repaint(); // actualise les stats

        }

        if (e.getSource() == timer) {

            minute++;
            for (Blob unBlob : map.blobs) {
                if (unBlob.energy > 0) {
                    map.moveBlobs(unBlob);
                }
                unBlob.energy = unBlob.energy - 0.05 * unBlob.size - 0.05 * Math.log(unBlob.speed)
                        - 0.002 * unBlob.viewRange;
                // System.out.println(map.blobs.get(1).energy);
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
            map.blobIniSpeed = speedSlider.getValue();

        } else if (source == foodSlider) {
            FoodLabel.setText("quantite nourriture : " + foodSlider.getValue());
            map.initFoodNumber = foodSlider.getValue();
        } else if (source == blobSizeSlider) {
            BlobSizeLabel.setText("Taille du blob : " + blobSizeSlider.getValue());
            map.blobIniSize = blobSizeSlider.getValue();
        } else if (source == detectionSlider) {
            DetectionLabel.setText("Champ de vision : " + detectionSlider.getValue());
            map.blobIniView = detectionSlider.getValue();
        } else if (source == energySlider) {
            EnergyLabel.setText("Energie des blobs : " + energySlider.getValue());
            map.energyIni = energySlider.getValue();

        }
    }

}
