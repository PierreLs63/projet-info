import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    public JButton startButton;
    public JButton backButton;
    public JButton exitButton;
    public JButton createMapButton300;
    public JButton createMapButton400;
    public JButton createMapButton500;
    public JButton createMapButton600;
    public JButton createMapButton700;
    public JButton createMapButton800;
    public JButton createMapButton900;
    public JButton createMapButton1000;
    public JButton iSpeed;// Bouton d'information sur le slider associé
    public JButton iFood;// Bouton d'information sur le slider associé
    public JButton iBlobSize;// Bouton d'information sur le slider associé
    public JButton iDetection;// Bouton d'information sur le slider associé
    public JButton iEnergy;// Bouton d'information sur le slider associé
    public JButton iBlobNumber;
    public JLabel texteCreaMap;
    public JLabel fondDroit;
    public JLabel daysCount;
    public double height;
    public double width;
    public JPanel contentPane;
    public JPanel affichageStats;
    public JPanel affichageBoutons;
    public JPanel affichageStart;
    public JPanel affichageSliders;
    public JPanel affichageMap;
    public JPanel mapBounds;
    public JPanel statBounds;

    // Stats
    public Stats stat;

    // Valeurs MIN,MAX,INIT slliders
    static final int speed_MIN = 0;
    static final int speed_MAX = 30;
    static final int speed_INIT = 20; // initial speed

    static final int energy_MIN = 0;
    static final int energy_MAX = 2000;
    static final int energy_INIT = 500;

    static final int qntfood_MAX = 100;
    static final int qntFood_MIN = 0;
    static final int qntfood_INIT = 20;

    static final int detection_MIN = 10;
    static final int detection_MAX = 50;
    static final int detection_INIT = 30;

    static final int mapSize_MIN = 100;
    static final int mapSize_MAX = 1000;
    static final int mapSize_INIT = 500;

    static final int blobSize_INIT = 10;
    static final int blobSize_MIN = 5;
    static final int blobSize_MAX = 40;

    static final int blobNumber_INIT = 10;
    static final int blobNumber_MIN = 5;
    static final int blobNumber_MAX = 50;

    static final int amplitudeVariationSize_INIT = 50;
    static final int amplitudeVariationSize_MIN = 0;
    static final int amplitudeVariationSize_MAX = 100;

    static final int amplitudeVariationSpeed_INIT = 100;
    static final int amplitudeVariationSpeed_MIN = 0;
    static final int amplitudeVariationSpeed_MAX = 1000;

    static final int amplitudeVariationEnergy_INIT = 100;
    static final int amplitudeVariationEnergy_MIN = 0;
    static final int amplitudeVariationEnergy_MAX = 200;

    static final int amplitudeVariationView_INIT = 15;
    static final int amplitudeVariationView_MIN = 0;
    static final int amplitudeVariationView_MAX = 50;

    static final int variationChance_INIT = 80;
    static final int variationChance_MIN = 0;
    static final int variationChance_MAX = 100;

    // Declaration Sliders
    // sliders
    public int widthSlider = 800;
    public int heightSlider = 40;
    public JSlider speedSlider;
    public JSlider foodSlider;
    public JSlider energySlider;
    public JSlider detectionSlider;
    public JSlider blobSizeSlider;
    public JSlider blobNumberSlider;
    public JSlider amplitudeVariationSizeSlider;
    public JSlider amplitudeVariationSpeedSlider;
    public JSlider amplitudeVariationEnergySlider;
    public JSlider amplitudeVariationViewSlider;
    public JSlider variationChanceSlider;

    // Labels
    public JLabel speedLabel;
    public JLabel foodLabel;
    public JLabel energyLabel;
    public JLabel blobSizeLabel;
    public JLabel detectionLabel;
    public JLabel numberLabel;
    public JLabel amplitudeVariationSizeLabel;
    public JLabel amplitudeVariationSpeedLabel;
    public JLabel amplitudeVariationEnergyLabel;
    public JLabel amplitudeVariationViewLabel;
    public JLabel variationChanceLabel;

    public App() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();

        setBounds(0, 0, (int) width, (int) height - 50);
        setTitle("Les Blobs c'est cool");
        setLayout(null);

        ecranCreateMap();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(10, this);

    }

    public void ecranCreateMap() {

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

    public void ecranSet() {

        // JPanel qui contient tout le côté droit avec les sliders
        affichageSliders = new JPanel();
        affichageSliders.setBounds(1000, 0, 920, 1000);
        affichageSliders.setLayout(null);
        affichageSliders.setBackground(Color.yellow);

        // Bouton START
        startButton = new JButton(new ImageIcon("./Images/Start.png"));
        startButton.setBounds(affichageSliders.getWidth() / 3 - 100, affichageSliders.getHeight() - 100, 200, 80);
        startButton.setLayout(null);
        startButton.addActionListener(this);

        // Bouton BACK
        backButton = new JButton(new ImageIcon("./Images/Back.png"));
        backButton.setBounds(2 * affichageSliders.getWidth() / 3 - 100, affichageSliders.getHeight() - 100, 200, 80);
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
        // speedSlider.setBackground(new ImageIcon("./Images/FondDroit.jpeg"));
        speedSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2, affichageSliders.getHeight() / 7 - 75,
                widthSlider, heightSlider);
        speedLabel = new JLabel("Vitesse : " + speed_INIT);
        speedLabel.setBounds(speedSlider.getX() + 5 + 5, speedSlider.getY() - 50, 200, 60);

        // foodSlider
        foodSlider = new JSlider(JSlider.HORIZONTAL, qntFood_MIN, qntfood_MAX, qntfood_INIT);
        // Turn on labels at major tick marks.
        foodSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les valeurs de vitesse
        foodSlider.setMinorTickSpacing(5);// espace minimal entre les valeurs de vitesse
        foodSlider.setPaintTicks(true);
        foodSlider.setPaintLabels(true);
        foodSlider.addChangeListener(this);
        foodSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                2 * affichageSliders.getHeight() / 7 - 75, widthSlider, heightSlider);
        foodSlider.setOpaque(false);
        foodLabel = new JLabel("Quantité de nourriture : " + qntfood_INIT);
        foodLabel.setBounds(foodSlider.getX() + 5, foodSlider.getY() - 50, 200, 60);

        // BlobsSize Slider
        blobSizeSlider = new JSlider(JSlider.HORIZONTAL, blobSize_MIN, blobSize_MAX, blobSize_INIT);
        // Turn on labels at major tick marks.
        blobSizeSlider.setMajorTickSpacing(5);// espace minimal affiché sous le slider entre les valeurs de vitesse
        blobSizeSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        blobSizeSlider.setPaintTicks(true);
        blobSizeSlider.setPaintLabels(true);
        blobSizeSlider.addChangeListener(this);
        blobSizeSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                3 * affichageSliders.getHeight() / 7 - 75, widthSlider, heightSlider);
        blobSizeSlider.setOpaque(false);
        blobSizeLabel = new JLabel("Taille du blob : " + blobSize_INIT);
        blobSizeLabel.setBounds(blobSizeSlider.getX() + 5, blobSizeSlider.getY() - 50, 200, 60);

        // EnergyIni Slider
        energySlider = new JSlider(JSlider.HORIZONTAL, energy_MIN, energy_MAX, energy_INIT);
        // Turn on labels at major tick marks.
        energySlider.setMajorTickSpacing(500);// espace minimal affiché sous le slider entre les valeurs de vitesse
        energySlider.setMinorTickSpacing(100);// espace minimal entre les valeurs de vitesse
        energySlider.setPaintTicks(true);
        energySlider.setPaintLabels(true);
        energySlider.addChangeListener(this);
        energySlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                4 * affichageSliders.getHeight() / 7 - 75, widthSlider, heightSlider);
        energySlider.setOpaque(false);
        energyLabel = new JLabel("Energie des blobs : " + energySlider.getValue());
        energyLabel.setBounds(energySlider.getX() + 5, energySlider.getY() - 50, 200, 60);

        // DetectionRange Slider
        detectionSlider = new JSlider(JSlider.HORIZONTAL, detection_MIN, detection_MAX, detection_INIT);
        detectionSlider.setMajorTickSpacing(10);
        detectionSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        detectionSlider.setPaintTicks(true);
        detectionSlider.setPaintLabels(true);
        detectionSlider.addChangeListener(this);
        detectionSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                5 * affichageSliders.getHeight() / 7 - 75, widthSlider, heightSlider);
        detectionSlider.setOpaque(false);
        detectionLabel = new JLabel("Champ de vision : " + detection_INIT);
        detectionLabel.setBounds(detectionSlider.getX() + 5, detectionSlider.getY() - 50, 200, 60);

        // BlobsNumber Slider
        blobNumberSlider = new JSlider(JSlider.HORIZONTAL, blobNumber_MIN, blobNumber_MAX, blobNumber_INIT);
        // Turn on labels at major tick marks.
        blobNumberSlider.setMajorTickSpacing(5);// espace minimal affiché sous le slider entre les valeurs de vitesse
        blobNumberSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        blobNumberSlider.setPaintTicks(true);
        blobNumberSlider.setPaintLabels(true);
        blobNumberSlider.addChangeListener(this);
        blobNumberSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                6 * affichageSliders.getHeight() / 7 - 75, widthSlider, heightSlider);
        blobNumberSlider.setOpaque(false);
        numberLabel = new JLabel("Nombre de blobs : " + blobNumber_INIT);
        numberLabel.setBounds(blobNumberSlider.getX() + 5, blobNumberSlider.getY() - 50, 200, 60);
        //Paramètrages des boutons d'informations sous chaque sliders
        iSpeed = new JButton("?");
        iBlobSize = new JButton("?");
        iDetection = new JButton("?");
        iFood = new JButton("?");
        iEnergy = new JButton("?");
        iBlobNumber = new JButton("?");
        iSpeed.setBounds(speedLabel.getX(),speedLabel.getY()+110,30,30);
        iBlobSize.setBounds(blobSizeLabel.getX(),blobSizeLabel.getY()+110,30,30);
        iDetection.setBounds(detectionLabel.getX(),detectionLabel.getY()+110,30,30);
        iFood.setBounds(foodLabel.getX(),foodLabel.getY()+110,30,30);
        iEnergy.setBounds(energyLabel.getX(),energyLabel.getY()+110,30,30);
        iBlobNumber.setBounds(numberLabel.getX(),numberLabel.getY()+90,30,30);
        iSpeed.addActionListener(this);
        iEnergy.addActionListener(this);
        iFood.addActionListener(this);
        iBlobSize.addActionListener(this);
        iDetection.addActionListener(this);
        iBlobNumber.addActionListener(this);
        // add
        contentPane.removeAll();
        affichageSliders.add(startButton);
        affichageSliders.add(backButton);
        affichageSliders.add(speedSlider);
        affichageSliders.add(speedLabel);
        affichageSliders.add(foodLabel);
        affichageSliders.add(foodSlider);
        affichageSliders.add(energyLabel);
        affichageSliders.add(energySlider);
        affichageSliders.add(blobSizeSlider);
        affichageSliders.add(blobSizeLabel);
        affichageSliders.add(detectionSlider);
        affichageSliders.add(detectionLabel);
        affichageSliders.add(blobNumberSlider);
        affichageSliders.add(numberLabel);
        affichageSliders.add(fondDroit);
        affichageSliders.add(iBlobSize);
        affichageSliders.add(iDetection);
        affichageSliders.add(iEnergy);
        affichageSliders.add(iFood);
        affichageSliders.add(iSpeed);
        affichageSliders.add(iBlobNumber);
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

        // JPanel qui contient les stats
        statBounds = new JPanel();
        statBounds.setBounds(0, (int) height - stat.height, stat.width, stat.height);
        statBounds.setLayout(null);

        // JLabel qui affiche le nb de jours passés
        daysCount = new JLabel("Day " + day);
        daysCount.setBounds(60, 0, 300, 80);
        daysCount.setLayout(null);
        daysCount.setFont(new Font("Ravie", Font.PLAIN, 30));

        // Bouton START
        exitButton = new JButton(new ImageIcon("./Images/Exit.png"));
        exitButton.setBounds(affichageSliders.getWidth() - 225, 0, 200, 80);
        exitButton.setLayout(null);
        exitButton.addActionListener(this);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setContentAreaFilled(false);

        // Slider nombre de nourriture par jour
        foodSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                2 * affichageSliders.getHeight() / 10 - 100, widthSlider, heightSlider);
        foodLabel.setBounds(foodSlider.getX() + 5, foodSlider.getY() - 50, 150, 60);

        // Mutation Chance Slider
        variationChanceSlider = new JSlider(JSlider.HORIZONTAL, variationChance_MIN, variationChance_MAX,
                variationChance_INIT);
        // Turn on labels at major tick marks.
        variationChanceSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les valeurs
        variationChanceSlider.setMinorTickSpacing(10);// espace minimal entre les valeurs de vitesse
        variationChanceSlider.setPaintTicks(true);
        variationChanceSlider.setPaintLabels(true);
        variationChanceSlider.addChangeListener(this);
        variationChanceSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                3 * affichageSliders.getHeight() / 10 - 100, widthSlider, heightSlider);
        variationChanceSlider.setOpaque(false);
        variationChanceLabel = new JLabel("Chances de mutations : " + variationChanceSlider.getValue() + "%");
        variationChanceLabel.setBounds(variationChanceSlider.getX() + 5, variationChanceSlider.getY() - 50, 210, 60);

        // Mutation energy Slider
        amplitudeVariationEnergySlider = new JSlider(JSlider.HORIZONTAL, amplitudeVariationEnergy_MIN,
                amplitudeVariationEnergy_MAX,
                amplitudeVariationEnergy_INIT);
        // Turn on labels at major tick marks.
        amplitudeVariationEnergySlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les
                                                               // valeurs
        amplitudeVariationEnergySlider.setMinorTickSpacing(10);// espace minimal entre les valeurs de vitesse
        amplitudeVariationEnergySlider.setPaintTicks(true);
        amplitudeVariationEnergySlider.setPaintLabels(true);
        amplitudeVariationEnergySlider.addChangeListener(this);
        amplitudeVariationEnergySlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                4 * affichageSliders.getHeight() / 10 - 100, widthSlider, heightSlider);
        amplitudeVariationEnergySlider.setOpaque(false);
        amplitudeVariationEnergyLabel = new JLabel(
                "Amplitude de variation de l'énergie à la mutation : " + amplitudeVariationEnergySlider.getValue());
        amplitudeVariationEnergyLabel.setBounds(amplitudeVariationEnergySlider.getX() + 5,
                amplitudeVariationEnergySlider.getY() - 50, 500, 60);

        // Mutation Size Slider
        amplitudeVariationSizeSlider = new JSlider(JSlider.HORIZONTAL, amplitudeVariationSize_MIN,
                amplitudeVariationSize_MAX,
                amplitudeVariationSize_INIT);
        // Turn on labels at major tick marks.
        amplitudeVariationSizeSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les
                                                             // valeurs

        amplitudeVariationSizeSlider.setMinorTickSpacing(10);// espace minimal entre les valeurs de vitesse
        amplitudeVariationSizeSlider.setPaintTicks(true);
        amplitudeVariationSizeSlider.setPaintLabels(true);
        amplitudeVariationSizeSlider.addChangeListener(this);
        amplitudeVariationSizeSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                5 * affichageSliders.getHeight() / 10 - 100, widthSlider, heightSlider);
        amplitudeVariationSizeSlider.setOpaque(false);
        amplitudeVariationSizeLabel = new JLabel(
                "Amplitude de variation de la taille des blobs à la mutation : "
                        + amplitudeVariationSizeSlider.getValue());
        amplitudeVariationSizeLabel.setBounds(amplitudeVariationSizeSlider.getX() + 5,
                amplitudeVariationSizeSlider.getY() - 50, 500, 60);

        // Mutation Size Slider
        amplitudeVariationSpeedSlider = new JSlider(JSlider.HORIZONTAL, amplitudeVariationSpeed_MIN,
                amplitudeVariationSpeed_MAX,
                amplitudeVariationSpeed_INIT);
        // Turn on labels at major tick marks.
        amplitudeVariationSpeedSlider.setMajorTickSpacing(200);// espace minimal affiché sous le slider entre les
                                                               // valeurs
        amplitudeVariationSpeedSlider.setMinorTickSpacing(50);// espace minimal entre les valeurs de vitesse
        amplitudeVariationSpeedSlider.setPaintTicks(true);
        amplitudeVariationSpeedSlider.setPaintLabels(true);
        amplitudeVariationSpeedSlider.addChangeListener(this);
        amplitudeVariationSpeedSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                6 * affichageSliders.getHeight() / 10 - 100, widthSlider, heightSlider);
        amplitudeVariationSpeedSlider.setOpaque(false);
        amplitudeVariationSpeedLabel = new JLabel(
                "Amplitude de variation de la vitesse des blobs à la mutation : "
                        + amplitudeVariationSpeedSlider.getValue());
        amplitudeVariationSpeedLabel.setBounds(amplitudeVariationSpeedSlider.getX() + 5,
                amplitudeVariationSpeedSlider.getY() - 50, 500, 60);

        // Mutation Size Slider
        amplitudeVariationViewSlider = new JSlider(JSlider.HORIZONTAL, amplitudeVariationView_MIN,
                amplitudeVariationView_MAX,
                amplitudeVariationView_INIT);
        // Turn on labels at major tick marks.
        amplitudeVariationViewSlider.setMajorTickSpacing(10);// espace minimal affiché sous le slider entre les
        // valeurs
        amplitudeVariationViewSlider.setMinorTickSpacing(5);// espace minimal entre les valeurs de vitesse
        amplitudeVariationViewSlider.setPaintTicks(true);
        amplitudeVariationViewSlider.setPaintLabels(true);
        amplitudeVariationViewSlider.addChangeListener(this);
        amplitudeVariationViewSlider.setBounds(affichageSliders.getWidth() / 2 - widthSlider / 2,
                7 * affichageSliders.getHeight() / 10 - 100, widthSlider, heightSlider);
        amplitudeVariationViewSlider.setOpaque(false);
        amplitudeVariationViewLabel = new JLabel(
                "Amplitude de variation du rayon de vison des blobs à la mutation : "
                        + amplitudeVariationViewSlider.getValue());
        amplitudeVariationViewLabel.setBounds(amplitudeVariationViewSlider.getX() + 5,
                amplitudeVariationViewSlider.getY() - 50, 500, 60);

        // add et remove
        contentPane.removeAll();
        mapBounds.add(map);
        statBounds.add(stat);
        affichageMap.add(mapBounds);
        affichageSliders.add(exitButton);
        affichageSliders.add(daysCount);
        affichageSliders.add(statBounds);
        affichageSliders.add(foodLabel);
        affichageSliders.add(foodSlider);
        affichageSliders.add(variationChanceSlider);
        affichageSliders.add(variationChanceLabel);
        affichageSliders.add(amplitudeVariationEnergySlider);
        affichageSliders.add(amplitudeVariationEnergyLabel);
        affichageSliders.add(amplitudeVariationSizeSlider);
        affichageSliders.add(amplitudeVariationSizeLabel);
        affichageSliders.add(amplitudeVariationSpeedSlider);
        affichageSliders.add(amplitudeVariationSpeedLabel);
        affichageSliders.add(amplitudeVariationViewSlider);
        affichageSliders.add(amplitudeVariationViewLabel);
        affichageSliders.add(fondDroit);
        contentPane.add(affichageSliders);
        contentPane.add(affichageMap);
        setContentPane(contentPane);
        setVisible(true);

        map.repaint(); // actualise la map
        stat.repaint();

    }

    public void setMapIni() {
        map.blobIniSpeed = speedSlider.getValue();
        map.blobIniSize = blobSizeSlider.getValue();
        map.blobIniView = detectionSlider.getValue();
        map.initBlobNumber = blobNumberSlider.getValue();
        map.energyIni = energySlider.getValue();
        ecranSet();
    }

    public void actionPerformed(java.awt.event.ActionEvent e) { // tout ce qui se passe chaque x ms

        if (e.getSource() == backButton) {
            ecranCreateMap();
        }

        if (e.getSource() == exitButton) {
            day = 1;
            ecranCreateMap();
        }

        if (e.getSource() == createMapButton300) {
            map = new Map(300, 300);
            ecranSet();
            setMapIni();
        }

        if (e.getSource() == createMapButton400) {
            map = new Map(400, 400);
            ecranSet();
            setMapIni();
        }

        if (e.getSource() == createMapButton500) {
            map = new Map(500, 500);
            ecranSet();
            setMapIni();
        }

        if (e.getSource() == createMapButton600) {
            map = new Map(600, 600);
            ecranSet();
            setMapIni();
        }

        if (e.getSource() == createMapButton700) {
            map = new Map(700, 700);
            ecranSet();
            setMapIni();
        }

        if (e.getSource() == createMapButton800) {
            map = new Map(800, 800);
            ecranSet();
            setMapIni();
        }

        if (e.getSource() == createMapButton900) {
            map = new Map(900, 900);
            ecranSet();
            setMapIni();
        }

        if (e.getSource() == createMapButton1000) {
            map = new Map(1000, 1000);
            ecranSet();
            setMapIni();
        }

        if (e.getSource() == startButton) {

            stat = new Stats(map.blobs, blobSizeSlider.getValue(),detectionSlider.getValue(),speedSlider.getValue(),energySlider.getValue());
            stat.fetch(map.blobs, blobSizeSlider.getValue(),detectionSlider.getValue(),speedSlider.getValue(),energySlider.getValue());
            stat.repaint();

            map.iniBlob(); // initialise un tableau de blob chacun placés
            // aléatoirement sur les bords de la map
            map.iniFood(); // initialise un tableau de food chacun placés
            // aléatoirement sur la map
            EcranJeu();
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
            }

            map.eatBlob();

        }
        if (minute == day * dayDuration) { // ce qui se passe à la fin de la daynée
            map.whipeBlobs();
            map.resetFood();
            map.newGeneration();
            stat.fetch(map.blobs, blobSizeSlider.getValue(),detectionSlider.getValue(),speedSlider.getValue(),energySlider.getValue());
            stat.repaint();
            day++;
            daysCount.setText("Day " + day);
        }
        if(e.getSource()==iSpeed){
            JOptionPane.showMessageDialog(null, "Permet d'ajuster la vitesse à laquelle se déplacent les blobs");// Réutilisation d'une ligne de code donnée dans une IE de 2ème année (Exo Pendu)
        }
        if(e.getSource()==iBlobNumber){
            JOptionPane.showMessageDialog(null, "Permet d'ajuster le nombre de blobs présents sur la carte");
        }
        if(e.getSource()==iBlobSize){
            JOptionPane.showMessageDialog(null, "Permet d'ajuster la taille des blobs sur la carte");
        }
        if(e.getSource()==iDetection){
            JOptionPane.showMessageDialog(null, "Permet d'ajuster le champ de vision des blobs");
        }
        if(e.getSource()==iEnergy){
            JOptionPane.showMessageDialog(null, "Permet d'ajuster l'énergie des blobs au début du jour");
        }
        if(e.getSource()==iFood){
            JOptionPane.showMessageDialog(null, "Permet d'ajuster la quantité de nourriture présente sur la carte au début du jour");
        }
        
        map.repaint();

    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (source == speedSlider) {
            speedLabel.setText("Vitesse : " + speedSlider.getValue());
            map.blobIniSpeed = speedSlider.getValue();
        } else if (source == foodSlider) {
            foodLabel.setText("Quantite nourriture : " + foodSlider.getValue());
            map.initFoodNumber = foodSlider.getValue();
        } else if (source == blobSizeSlider) {
            blobSizeLabel.setText("Taille du blob : " + blobSizeSlider.getValue());
            map.blobIniSize = blobSizeSlider.getValue();
        } else if (source == detectionSlider) {
            detectionLabel.setText("Champ de vision : " + detectionSlider.getValue());
            map.blobIniView = detectionSlider.getValue();
        } else if (source == energySlider) {
            energyLabel.setText("Energie des blobs : " + energySlider.getValue());
            map.energyIni = energySlider.getValue();
        } else if (source == blobNumberSlider) {
            numberLabel.setText("Nombre de blobs : " + blobNumberSlider.getValue());
            map.initBlobNumber = blobNumberSlider.getValue();

        } else if (source == variationChanceSlider) {
            variationChanceLabel.setText("Chances de mutations : " + variationChanceSlider.getValue()+"%");
            map.chanceVariation = variationChanceSlider.getValue() / 100;
        } else if (source == amplitudeVariationEnergySlider) {
            amplitudeVariationEnergyLabel.setText(
                    "Amplitude de variation de l'énergie à la mutation : " + amplitudeVariationEnergySlider.getValue());
            map.amplitudeVariationEnergy = amplitudeVariationEnergySlider.getValue();
        } else if (source == amplitudeVariationSizeSlider) {
            amplitudeVariationSizeLabel.setText("Amplitude de variation de la taille des blobs à la mutation : "
                    + amplitudeVariationSizeSlider.getValue());
            map.amplitudeVariationSize = amplitudeVariationSizeSlider.getValue();
        } else if (source == amplitudeVariationSpeedSlider) {
            amplitudeVariationSpeedLabel.setText("Amplitude de variation de la vitesse des blobs à la mutation : "
                    + amplitudeVariationSpeedSlider.getValue());
            map.amplitudeVariationSpeed = amplitudeVariationSpeedSlider.getValue();
        } else if (source == amplitudeVariationViewSlider) {
            amplitudeVariationViewLabel.setText("Amplitude de variation du rayon de vison des blobs à la mutation : "
                    + amplitudeVariationViewSlider.getValue());
            map.amplitudeVariationView = amplitudeVariationViewSlider.getValue();
        }
    }
}
