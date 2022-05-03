import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * App contient le main, l’IHM et le actionPerformed qui fait appel aux autres classes pour que le programme se déroule.
 */
public class App extends JFrame implements ActionListener, ChangeListener {

    // temps
    public Timer timer;
    public int day = 1;
    public int minute = 0;
    public int DAY_DURATION = 500;

    // map
    public Map map;
    public int mapWidth;
    public int mapHeight;

    // IHM sauf sliders
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
    public JLabel texteCreaMap;
    public JLabel fondDroit;
    public JLabel fondGauche;
    public JLabel daysCount;
    public JLabel bloby;
    public JLabel foodLogo;
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

    // Boutons d'information sur les sliders associé
    public JButton iSpeed;
    public JButton iFood;
    public JButton iBlobSize;
    public JButton iDetection;
    public JButton iEnergy;
    public JButton iBlobNumber;
    public JButton iamplitudeVariationSize;
    public JButton iamplitudeVariationSpeed;
    public JButton iamplitudeVariationEnergy;
    public JButton iamplitudeVariationView;
    public JButton ivariationChance;

    // Stats
    public Stats stat;

    // Valeurs MIN,MAX,INIT slliders
    static final int SPEED_MIN = 0;
    static final int SPEED_MAX = 30;
    static final int SPEED_INI = 20; // initial speed

    static final int ENERGY_MIN = 0;
    static final int ENERGY_MAX = 2000;
    static final int ENERGY_INIT = 500;

    static final int QUANT_FOOD_MIN = 0;
    static final int QUANT_FOOD_MAX = 100;
    static final int QUANT_FOOD_INI = 20;

    static final int DETECTION_MIN = 10;
    static final int DETECTION_MAX = 50;
    static final int DETECTION_INI = 30;

    static final int BLOB_SIZE_MIN = 5;
    static final int BLOB_SIZE_MAX = 40;
    static final int BLOB_SIZE_INI = 10;

    static final int QUANT_BLOB_MIN = 5;
    static final int QUANT_BLOB_MAX = 50;
    static final int QUANT_BLOB_INI = 10;

    static final int AMPLITUDE_VARIATION_SIZE_MIN = 0;
    static final int AMPLITUDE_VARIATION_SIZE_MAX = 100;
    static final int AMPLITUDE_VARIATION_SIZE_INIT = 50;

    static final int AMPLITUDE_VARIATION_SPEED_MIN = 0;
    static final int AMPLITUDE_VARIATION_SPEED_MAX = 1000;
    static final int AMPLITUDE_VARIATION_SPEED_INI = 100;

    static final int AMPLITUDE_VARIATION_ENERGY_MIN = 0;
    static final int AMPLITUDE_VARIATION_ENERGY_MAX = 200;
    static final int AMPLITUDE_VARIATION_ENERGY_INI = 100;

    static final int AMPLITUDE_VARIATION_VIEW_RANGE_MIN = 0;
    static final int AMPLITUDE_VARIATION_VIEW_RANGE_MAX = 50;
    static final int AMPLITUDE_VARIATION_VIEW_RANGE_INI = 15;

    static final int VARIATION_CHANCE_MIN = 0;
    static final int VARIATION_CHANCE_MAX = 100;
    static final int VARIATION_CHANCE_INI = 80;

    // Declaration Sliders
    public int WIDTH_SLIDER = 800;
    public int HEIGHT_SLIDER = 40;
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

    // Labels des slidders
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

    /**
     * Constructeur 
     */
    public App() {
        width = 1920;
        height = 1080;

        setBounds(0, 0, (int) width, (int) height - 50);
        setTitle("Les Blobs c'est cool");
        setLayout(null);

        ecranCreateMap();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(10, this);

    }

    /**
     * Le premier affichage permettant de choisir la taille de la carte
     */
    public void ecranCreateMap() {

        // JPanel conteneur qui contient tout les autres JPanel
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setLayout(null);

        // JPanel qui contient tt le côté gauche avec l'image de start du jeu
        affichageStart = new JPanel();
        affichageStart.setBounds(0, 0, 1000, 1000);
        affichageStart.setLayout(null);
        affichageStart.setBackground(Color.green);

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

        // Fond du côté droit de l'IHM
        fondGauche = new JLabel(new ImageIcon("./Images/FondHerbe.jpg"));
        fondGauche.setLayout(null);
        fondGauche.setBounds(0, 0, 1000, 1000);

        // Image accueil bloby mascotte
        bloby = new JLabel(new ImageIcon("./Images/Gif06.png"));
        bloby.setLayout(null);
        bloby.setBounds(affichageStart.getWidth() / 2 - 100, affichageStart.getHeight() / 2 - 400, 600, 600);

        // Image accueil nourriture
        foodLogo = new JLabel(new ImageIcon("./Images/Food23.png"));
        foodLogo.setLayout(null);
        foodLogo.setBounds(affichageStart.getWidth() / 2 - 450, affichageStart.getHeight() / 2 - 150, 300, 300);

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
        affichageStart.add(bloby);
        affichageStart.add(foodLogo);
        affichageStart.add(fondGauche);
        contentPane.add(affichageBoutons);
        contentPane.add(affichageStart);
        setContentPane(contentPane);
        setVisible(true);

    }

    /**
     * Le seconde affichage où l'on sélectionne les paramètres initiaux de la simulation
     */
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
        speedSlider = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INI);
        // Turn on labels at major tick marks.
        speedSlider.setMajorTickSpacing(10);// espace minimal affiché sous le slider entre les valeurs de vitesse
        speedSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(this);
        speedSlider.setOpaque(false);
        // speedSlider.setBackground(new ImageIcon("./Images/FondDroit.jpeg"));
        speedSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2, affichageSliders.getHeight() / 7 - 75,
                WIDTH_SLIDER, HEIGHT_SLIDER);
        speedLabel = new JLabel("Vitesse : " + SPEED_INI);
        speedLabel.setBounds(speedSlider.getX() + 5 + 5, speedSlider.getY() - 50, 200, 60);

        // foodSlider
        foodSlider = new JSlider(JSlider.HORIZONTAL, QUANT_FOOD_MIN, QUANT_FOOD_MAX, QUANT_FOOD_INI);
        // Turn on labels at major tick marks.
        foodSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les valeurs de vitesse
        foodSlider.setMinorTickSpacing(5);// espace minimal entre les valeurs de vitesse
        foodSlider.setPaintTicks(true);
        foodSlider.setPaintLabels(true);
        foodSlider.addChangeListener(this);
        foodSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                2 * affichageSliders.getHeight() / 7 - 75, WIDTH_SLIDER, HEIGHT_SLIDER);
        foodSlider.setOpaque(false);
        foodLabel = new JLabel("Quantité de nourriture : " + QUANT_FOOD_INI);
        foodLabel.setBounds(foodSlider.getX() + 5, foodSlider.getY() - 50, 200, 60);

        // BlobsSize Slider
        blobSizeSlider = new JSlider(JSlider.HORIZONTAL, BLOB_SIZE_MIN, BLOB_SIZE_MAX, BLOB_SIZE_INI);
        // Turn on labels at major tick marks.
        blobSizeSlider.setMajorTickSpacing(5);// espace minimal affiché sous le slider entre les valeurs de vitesse
        blobSizeSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        blobSizeSlider.setPaintTicks(true);
        blobSizeSlider.setPaintLabels(true);
        blobSizeSlider.addChangeListener(this);
        blobSizeSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                3 * affichageSliders.getHeight() / 7 - 75, WIDTH_SLIDER, HEIGHT_SLIDER);
        blobSizeSlider.setOpaque(false);
        blobSizeLabel = new JLabel("Taille du blob : " + BLOB_SIZE_INI);
        blobSizeLabel.setBounds(blobSizeSlider.getX() + 5, blobSizeSlider.getY() - 50, 200, 60);

        // EnergyIni Slider
        energySlider = new JSlider(JSlider.HORIZONTAL, ENERGY_MIN, ENERGY_MAX, ENERGY_INIT);
        // Turn on labels at major tick marks.
        energySlider.setMajorTickSpacing(500);// espace minimal affiché sous le slider entre les valeurs de vitesse
        energySlider.setMinorTickSpacing(100);// espace minimal entre les valeurs de vitesse
        energySlider.setPaintTicks(true);
        energySlider.setPaintLabels(true);
        energySlider.addChangeListener(this);
        energySlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                4 * affichageSliders.getHeight() / 7 - 75, WIDTH_SLIDER, HEIGHT_SLIDER);
        energySlider.setOpaque(false);
        energyLabel = new JLabel("Energie des blobs : " + energySlider.getValue());
        energyLabel.setBounds(energySlider.getX() + 5, energySlider.getY() - 50, 200, 60);

        // DetectionRange Slider
        detectionSlider = new JSlider(JSlider.HORIZONTAL, DETECTION_MIN, DETECTION_MAX, DETECTION_INI);
        detectionSlider.setMajorTickSpacing(10);
        detectionSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        detectionSlider.setPaintTicks(true);
        detectionSlider.setPaintLabels(true);
        detectionSlider.addChangeListener(this);
        detectionSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                5 * affichageSliders.getHeight() / 7 - 75, WIDTH_SLIDER, HEIGHT_SLIDER);
        detectionSlider.setOpaque(false);
        detectionLabel = new JLabel("Champ de vision : " + DETECTION_INI);
        detectionLabel.setBounds(detectionSlider.getX() + 5, detectionSlider.getY() - 50, 200, 60);

        // BlobsNumber Slider
        blobNumberSlider = new JSlider(JSlider.HORIZONTAL, QUANT_BLOB_MIN, QUANT_BLOB_MAX, QUANT_BLOB_INI);
        // Turn on labels at major tick marks.
        blobNumberSlider.setMajorTickSpacing(5);// espace minimal affiché sous le slider entre les valeurs de vitesse
        blobNumberSlider.setMinorTickSpacing(1);// espace minimal entre les valeurs de vitesse
        blobNumberSlider.setPaintTicks(true);
        blobNumberSlider.setPaintLabels(true);
        blobNumberSlider.addChangeListener(this);
        blobNumberSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                6 * affichageSliders.getHeight() / 7 - 75, WIDTH_SLIDER, HEIGHT_SLIDER);
        blobNumberSlider.setOpaque(false);
        numberLabel = new JLabel("Nombre de blobs : " + QUANT_BLOB_INI);
        numberLabel.setBounds(blobNumberSlider.getX() + 5, blobNumberSlider.getY() - 50, 200, 60);

        // Paramètrages des boutons d'informations sous chaque sliders

        iSpeed = new JButton(new ImageIcon("./Images/info.png"));
        iSpeed.setBounds(speedLabel.getX(), speedLabel.getY() + 100, 30, 30);
        iSpeed.addActionListener(this);

        iBlobSize = new JButton(new ImageIcon("./Images/info.png"));
        iBlobSize.setBounds(blobSizeLabel.getX(), blobSizeLabel.getY() + 100, 30, 30);
        iBlobSize.addActionListener(this);

        iDetection = new JButton(new ImageIcon("./Images/info.png"));
        iDetection.setBounds(detectionLabel.getX(), detectionLabel.getY() + 100, 30, 30);
        iDetection.addActionListener(this);

        iFood = new JButton(new ImageIcon("./Images/info.png"));
        iFood.setBounds(foodLabel.getX(), foodLabel.getY() + 100, 30, 30);
        iFood.addActionListener(this);

        iEnergy = new JButton(new ImageIcon("./Images/info.png"));
        iEnergy.setBounds(energyLabel.getX(), energyLabel.getY() + 110, 30, 30);
        iEnergy.addActionListener(this);

        iBlobNumber = new JButton(new ImageIcon("./Images/info.png"));
        iBlobNumber.setBounds(numberLabel.getX(), numberLabel.getY() + 100, 30, 30);
        iBlobNumber.addActionListener(this);

        // add
        contentPane.removeAll();
        affichageSliders.add(iBlobSize);
        affichageSliders.add(iDetection);
        affichageSliders.add(iEnergy);
        affichageSliders.add(iFood);
        affichageSliders.add(iSpeed);
        affichageSliders.add(iBlobNumber);
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
        contentPane.add(affichageSliders);
        contentPane.add(affichageStart);
        setContentPane(contentPane);
        setVisible(true);

    }

    /**
     * Le troisième affichage où la simulation se déroule et l'on peut changer des paramètres
     */
    public void EcranJeu() {

        // JPanel conteneur qui contient affichageSliders et affichageMap
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, getWidth(), getHeight());
        contentPane.setLayout(null);

        // JPanel qui contient tt le côté gauche avec l'affichage de la partie
        affichageMap = new JPanel();
        affichageMap.setBounds(0, 0, 1000, 1000);
        affichageMap.setLayout(null);
        affichageMap.setBackground(Color.gray);

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
        foodSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                2 * affichageSliders.getHeight() / 10 - 100, WIDTH_SLIDER, HEIGHT_SLIDER);
        foodLabel.setBounds(foodSlider.getX() + 5, foodSlider.getY() - 50, 150, 60);

        // Mutation Chance Slider
        variationChanceSlider = new JSlider(JSlider.HORIZONTAL, VARIATION_CHANCE_MIN, VARIATION_CHANCE_MAX,
                VARIATION_CHANCE_INI);
        // Turn on labels at major tick marks.
        variationChanceSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les valeurs
        variationChanceSlider.setMinorTickSpacing(10);// espace minimal entre les valeurs de vitesse
        variationChanceSlider.setPaintTicks(true);
        variationChanceSlider.setPaintLabels(true);
        variationChanceSlider.addChangeListener(this);
        variationChanceSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                3 * affichageSliders.getHeight() / 10 - 100, WIDTH_SLIDER, HEIGHT_SLIDER);
        variationChanceSlider.setOpaque(false);
        variationChanceLabel = new JLabel("Chances de mutations : " + variationChanceSlider.getValue() + "%");
        variationChanceLabel.setBounds(variationChanceSlider.getX() + 5, variationChanceSlider.getY() - 50, 210, 60);

        // Mutation energy Slider
        amplitudeVariationEnergySlider = new JSlider(JSlider.HORIZONTAL, AMPLITUDE_VARIATION_ENERGY_MIN,
                AMPLITUDE_VARIATION_ENERGY_MAX,
                AMPLITUDE_VARIATION_ENERGY_INI);
        // Turn on labels at major tick marks.
        amplitudeVariationEnergySlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les
                                                               // valeurs
        amplitudeVariationEnergySlider.setMinorTickSpacing(10);// espace minimal entre les valeurs de vitesse
        amplitudeVariationEnergySlider.setPaintTicks(true);
        amplitudeVariationEnergySlider.setPaintLabels(true);
        amplitudeVariationEnergySlider.addChangeListener(this);
        amplitudeVariationEnergySlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                4 * affichageSliders.getHeight() / 10 - 100, WIDTH_SLIDER, HEIGHT_SLIDER);
        amplitudeVariationEnergySlider.setOpaque(false);
        amplitudeVariationEnergyLabel = new JLabel(
                "Amplitude de variation de l'énergie à la mutation : " + amplitudeVariationEnergySlider.getValue());
        amplitudeVariationEnergyLabel.setBounds(amplitudeVariationEnergySlider.getX() + 5,
                amplitudeVariationEnergySlider.getY() - 50, 500, 60);

        // Mutation Size Slider
        amplitudeVariationSizeSlider = new JSlider(JSlider.HORIZONTAL, AMPLITUDE_VARIATION_SIZE_MIN,
                AMPLITUDE_VARIATION_SIZE_MAX,
                AMPLITUDE_VARIATION_SIZE_INIT);
        // Turn on labels at major tick marks.
        amplitudeVariationSizeSlider.setMajorTickSpacing(20);// espace minimal affiché sous le slider entre les
                                                             // valeurs

        amplitudeVariationSizeSlider.setMinorTickSpacing(10);// espace minimal entre les valeurs de vitesse
        amplitudeVariationSizeSlider.setPaintTicks(true);
        amplitudeVariationSizeSlider.setPaintLabels(true);
        amplitudeVariationSizeSlider.addChangeListener(this);
        amplitudeVariationSizeSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                5 * affichageSliders.getHeight() / 10 - 100, WIDTH_SLIDER, HEIGHT_SLIDER);
        amplitudeVariationSizeSlider.setOpaque(false);
        amplitudeVariationSizeLabel = new JLabel(
                "Amplitude de variation de la taille des blobs à la mutation : "
                        + amplitudeVariationSizeSlider.getValue());
        amplitudeVariationSizeLabel.setBounds(amplitudeVariationSizeSlider.getX() + 5,
                amplitudeVariationSizeSlider.getY() - 50, 500, 60);

        // Mutation Size Slider
        amplitudeVariationSpeedSlider = new JSlider(JSlider.HORIZONTAL, AMPLITUDE_VARIATION_SPEED_MIN,
                AMPLITUDE_VARIATION_SPEED_MAX,
                AMPLITUDE_VARIATION_SPEED_INI);
        // Turn on labels at major tick marks.
        amplitudeVariationSpeedSlider.setMajorTickSpacing(200);// espace minimal affiché sous le slider entre les
                                                               // valeurs
        amplitudeVariationSpeedSlider.setMinorTickSpacing(50);// espace minimal entre les valeurs de vitesse
        amplitudeVariationSpeedSlider.setPaintTicks(true);
        amplitudeVariationSpeedSlider.setPaintLabels(true);
        amplitudeVariationSpeedSlider.addChangeListener(this);
        amplitudeVariationSpeedSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                6 * affichageSliders.getHeight() / 10 - 100, WIDTH_SLIDER, HEIGHT_SLIDER);
        amplitudeVariationSpeedSlider.setOpaque(false);
        amplitudeVariationSpeedLabel = new JLabel(
                "Amplitude de variation de la vitesse des blobs à la mutation : "
                        + amplitudeVariationSpeedSlider.getValue());
        amplitudeVariationSpeedLabel.setBounds(amplitudeVariationSpeedSlider.getX() + 5,
                amplitudeVariationSpeedSlider.getY() - 50, 500, 60);

        // Mutation Size Slider
        amplitudeVariationViewSlider = new JSlider(JSlider.HORIZONTAL, AMPLITUDE_VARIATION_VIEW_RANGE_MIN,
                AMPLITUDE_VARIATION_VIEW_RANGE_MAX,
                AMPLITUDE_VARIATION_VIEW_RANGE_INI);
        // Turn on labels at major tick marks.
        amplitudeVariationViewSlider.setMajorTickSpacing(10);// espace minimal affiché sous le slider entre les
        // valeurs
        amplitudeVariationViewSlider.setMinorTickSpacing(5);// espace minimal entre les valeurs de vitesse
        amplitudeVariationViewSlider.setPaintTicks(true);
        amplitudeVariationViewSlider.setPaintLabels(true);
        amplitudeVariationViewSlider.addChangeListener(this);
        amplitudeVariationViewSlider.setBounds(affichageSliders.getWidth() / 2 - WIDTH_SLIDER / 2,
                7 * affichageSliders.getHeight() / 10 - 100, WIDTH_SLIDER, HEIGHT_SLIDER);
        amplitudeVariationViewSlider.setOpaque(false);
        amplitudeVariationViewLabel = new JLabel(
                "Amplitude de variation du rayon de vison des blobs à la mutation : "
                        + amplitudeVariationViewSlider.getValue());
        amplitudeVariationViewLabel.setBounds(amplitudeVariationViewSlider.getX() + 5,
                amplitudeVariationViewSlider.getY() - 50, 500, 60);

        iamplitudeVariationEnergy = new JButton(new ImageIcon("./Images/info.png"));
        iamplitudeVariationEnergy.setBounds(amplitudeVariationEnergyLabel.getX() - 50,
                amplitudeVariationEnergyLabel.getY() + 25, 30, 30);
        iamplitudeVariationEnergy.addActionListener(this);

        iamplitudeVariationSize = new JButton(new ImageIcon("./Images/info.png"));
        iamplitudeVariationSize.setBounds(amplitudeVariationSizeLabel.getX() - 50,
                amplitudeVariationSizeLabel.getY() + 25, 30, 30);
        iamplitudeVariationSize.addActionListener(this);

        iamplitudeVariationSpeed = new JButton(new ImageIcon("./Images/info.png"));
        iamplitudeVariationSpeed.setBounds(amplitudeVariationSpeedLabel.getX() - 50,
                amplitudeVariationSpeedLabel.getY() + 25, 30, 30);
        iamplitudeVariationSpeed.addActionListener(this);

        iamplitudeVariationView = new JButton(new ImageIcon("./Images/info.png"));
        iamplitudeVariationView.setBounds(amplitudeVariationViewLabel.getX() - 50,
                amplitudeVariationViewLabel.getY() + 25, 30, 30);
        iamplitudeVariationView.addActionListener(this);

        ivariationChance = new JButton(new ImageIcon("./Images/info.png"));
        ivariationChance.setBounds(variationChanceLabel.getX() - 50, variationChanceLabel.getY() + 25, 30, 30);
        ivariationChance.addActionListener(this);

        iFood = new JButton(new ImageIcon("./Images/info.png"));
        iFood.setBounds(foodLabel.getX() - 50, foodLabel.getY() + 25, 30, 30);
        iFood.addActionListener(this);

        // add et remove
        contentPane.removeAll();
        mapBounds.add(map);
        statBounds.add(stat);
        affichageMap.add(mapBounds);
        affichageSliders.add(iFood);
        affichageSliders.add(iamplitudeVariationEnergy);
        affichageSliders.add(iamplitudeVariationSize);
        affichageSliders.add(iamplitudeVariationSpeed);
        affichageSliders.add(iamplitudeVariationView);
        affichageSliders.add(ivariationChance);
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

    /**
     * Initialisation des paramètres de la carte créé sur ceux initiaux des sliders au cas où ils ne soient pas modifiés par l'utilisateur
     */
    public void setMapIni() {
        map.blobIniSpeed = speedSlider.getValue();
        map.blobIniSize = blobSizeSlider.getValue();
        map.blobIniView = detectionSlider.getValue();
        map.initBlobNumber = blobNumberSlider.getValue();
        map.energyIni = energySlider.getValue();
        ecranSet();
    }

   @Override
    public void actionPerformed(java.awt.event.ActionEvent e) { 

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

            stat = new Stats(map.blobs, blobSizeSlider.getValue(), detectionSlider.getValue(), speedSlider.getValue(),
                    energySlider.getValue());
            stat.fetch(map.blobs, blobSizeSlider.getValue(), detectionSlider.getValue(), speedSlider.getValue(),
                    energySlider.getValue());
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
        
        if (minute == day * DAY_DURATION) { // ce qui se passe à la fin de la daynée
            map.whipeBlobs();
            map.resetFood();
            map.newGeneration();
            stat.fetch(map.blobs, blobSizeSlider.getValue(), detectionSlider.getValue(), speedSlider.getValue(),
                    energySlider.getValue());
            stat.repaint();
            day++;
            daysCount.setText("Day " + day);
        }

        if (e.getSource() == iSpeed) {
            JOptionPane.showMessageDialog(null, "Permet d'ajuster la vitesse à laquelle se déplacent les blobs");// Réutilisation
                                                                                                                 // d'une
                                                                                                                 // ligne
                                                                                                                 // de
                                                                                                                 // code
                                                                                                                 // donnée
                                                                                                                 // dans
                                                                                                                 // une
                                                                                                                 // IE
                                                                                                                 // de
                                                                                                                 // 2ème
                                                                                                                 // année
                                                                                                                 // (Exo
                                                                                                                 // Pendu)
        }
        if (e.getSource() == iBlobNumber) {
            JOptionPane.showMessageDialog(null,
                    "Permet d'ajuster le nombre de blobs initialement présents sur la carte");
        }
        if (e.getSource() == iBlobSize) {
            JOptionPane.showMessageDialog(null, "Permet d'ajuster la taille initiale des blobs sur la carte");
        }
        if (e.getSource() == iDetection) {
            JOptionPane.showMessageDialog(null, "Permet d'ajuster le champ de vision initial des blobs");
        }
        if (e.getSource() == iEnergy) {
            JOptionPane.showMessageDialog(null, "Permet d'ajuster l'énergie initiale des blobs au début du jour");
        }
        if (e.getSource() == iFood) {
            JOptionPane.showMessageDialog(null,
                    "Permet d'ajuster la quantité de nourriture présente sur la carte au début du jour");
        }
        if (e.getSource() == iamplitudeVariationEnergy) {
            JOptionPane.showMessageDialog(null,
                    "Permet d'ajuster à quel point l'énergie du blob dupliqué différera de celle du blob initial");
        }
        if (e.getSource() == iamplitudeVariationSize) {
            JOptionPane.showMessageDialog(null,
                    "Permet d'ajuster à quel point la taille du blob dupliqué différera de celle du blob initial");
        }
        if (e.getSource() == iamplitudeVariationSpeed) {
            JOptionPane.showMessageDialog(null,
                    "Permet d'ajuster à quel point la vitesse du blob dupliqué différera de celle du blob initial");
        }
        if (e.getSource() == iamplitudeVariationView) {
            JOptionPane.showMessageDialog(null,
                    "Permet d'ajuster à quel point la distance de vision du blob dupliqué différera de celle du blob initial");
        }
        if (e.getSource() == ivariationChance) {
            JOptionPane.showMessageDialog(null,
                    "Permet de modifier le pourcentage de chance que le blob créé à la duplication soit différent du blob initial");
        }
        map.repaint();

    }

    @Override
    public void stateChanged(ChangeEvent e) {

        JSlider source = (JSlider) e.getSource();
        if (source == speedSlider) {
            speedLabel.setText("Vitesse : " + speedSlider.getValue());
            map.blobIniSpeed = speedSlider.getValue();
        } else if (source == foodSlider) {
            foodLabel.setText("quantite nourriture : " + foodSlider.getValue());
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
            variationChanceLabel.setText("Chances de mutations : " + variationChanceSlider.getValue() + "%");
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

    public static void main(String[] args) {

        App app = new App();
    }
}
