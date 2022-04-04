import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.event.ActionEvent; 


public class IHMControle extends JFrame implements ChangeListener, ActionListener  {

  //Variables nécessaires à la création des sliders
  static final int speed_MIN = 0;
  static final int speed_MAX = 30;
  static final int speed_INIT =20;   
  static final int Energy_MIN = 0;
  static final int Energy_MAX = 2000;
  static final int Energy_INIT =500; 
  static final int qntfood_MAX= 100; 
  static final int qntFood_MIN=0;
  static final int qntfood_INIT=5;
  static final int detection_MIN=10;
  static final int detection_MAX=50;
  static final int detection_INIT=50;
  static final int mapSize_MIN=10;
  static final int mapSize_MAX=50;
  static final int mapSize_INIT=30;
  static final int BlobSize_INIT=10;
  static final int BlobSize_MIN=5;
  static final int BlobSize_MAX=40;
  
  JSlider speedSlider;
  JSlider FoodSlider;
  JSlider EnergySlider;
  JSlider MapSizeSlider;
  JSlider DetectionSlider;
  JSlider BlobSizeSlider;
  
  JLabel speedLabel;
  JLabel FoodLabel;
  JLabel EnergyLabel;
  JLabel MapSizeLabel;
  JLabel BlobSizeLabel;
  JLabel DetectionLabel;
  
  JButton startButton;
  JButton resetButton;


  public IHMControle(String nom, int width, int height) {
    super(nom);
    setSize(width, height);
    setLocation(0,0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    int lengthSlider =200;
    int widthSlider = 40;
    //Slider permettant de régler la vitesse des blobs
    speedSlider = new JSlider(JSlider.HORIZONTAL,speed_MIN, speed_MAX, speed_INIT);
    int xSpeedSlider=1000;
    int ySpeedSlider=50;
	  speedSlider.setMajorTickSpacing(10);//espace minimal affiché sous le slider entre les valeurs de vitesse
    speedSlider.setMinorTickSpacing(1);//espace minimal entre les valeurs de vitesse
    speedSlider.setPaintTicks(true);
    speedSlider.setPaintLabels(true);
    speedSlider.addChangeListener(this);
	  speedLabel = new JLabel ("Vitesse : "+speed_INIT);
	  speedLabel.setBounds(xSpeedSlider,ySpeedSlider-70,200,60);
    FoodSlider = new JSlider(JSlider.HORIZONTAL,qntFood_MIN, qntfood_MAX, qntfood_INIT);
// Slider permettant de régler la quantité de nourriture
    int xFoodSlider=xSpeedSlider;
    int yFoodSlider=ySpeedSlider+140;
	  FoodSlider.setMajorTickSpacing(20);//espace minimal affiché sous le slider entre les valeurs de vitesse
    FoodSlider.setMinorTickSpacing(5);//espace minimal entre les valeurs de vitesse
    FoodSlider.setPaintTicks(true);
    FoodSlider.setPaintLabels(true);
    FoodSlider.addChangeListener(this);
    FoodLabel = new JLabel ("quantite nourriture : "+qntfood_INIT);
	  FoodLabel.setBounds( xFoodSlider,yFoodSlider-70,200,60);
//Slider permettant de régler la taille de la carte
    MapSizeSlider = new JSlider(JSlider.HORIZONTAL,mapSize_MIN, mapSize_MAX, mapSize_INIT);
    int xMapSizeSlider=xFoodSlider;
    int yMapSizeSlider=yFoodSlider+140;
	  MapSizeSlider.setMajorTickSpacing(10);
    MapSizeSlider.setMinorTickSpacing(1);
    MapSizeSlider.setPaintTicks(true);
    MapSizeSlider.setPaintLabels(true);
    MapSizeSlider.addChangeListener(this);
    MapSizeLabel = new JLabel ("Taille de la carte: "+mapSize_INIT);
	  MapSizeLabel.setBounds(xMapSizeSlider,yMapSizeSlider-70,200,60);
// Slider permettant de choisir la taille des blobs
    BlobSizeSlider = new JSlider(JSlider.HORIZONTAL,BlobSize_MIN,BlobSize_MAX,BlobSize_INIT);
    int xBlobSizeSlider=xSpeedSlider;
    int yBlobSizeSlider=yMapSizeSlider+140;
	  BlobSizeSlider.setMajorTickSpacing(5);
    BlobSizeSlider.setMinorTickSpacing(1);
    BlobSizeSlider.setPaintTicks(true);
    BlobSizeSlider.setPaintLabels(true);
    BlobSizeSlider.addChangeListener(this);
	  BlobSizeLabel = new JLabel ("Taille du blob : "+BlobSize_INIT);
	  BlobSizeSlider.setBounds(xBlobSizeSlider,yBlobSizeSlider-70,200,60);
    BlobSizeLabel.setBounds(xBlobSizeSlider,yBlobSizeSlider-70,200,60);


//Slider modifiant la distance de détection des blobs
    DetectionSlider = new JSlider(JSlider.HORIZONTAL,detection_MIN,detection_MAX,detection_INIT);
    int xDetectionSlider=xSpeedSlider;
    int yDetectionSlider=yBlobSizeSlider+140;
    DetectionSlider.setMajorTickSpacing(10);
    DetectionSlider.setMinorTickSpacing(1);//espace minimal entre les valeurs de vitesse
    DetectionSlider.setPaintTicks(true);
    DetectionSlider.setPaintLabels(true);
    DetectionSlider.addChangeListener(this);
    DetectionLabel = new JLabel ("Champ de vision : "+detection_INIT);
	  DetectionLabel.setBounds(xDetectionSlider,yDetectionSlider-70,200,60);
    EnergySlider = new JSlider(JSlider.HORIZONTAL,Energy_MIN,Energy_MAX,Energy_INIT);
    int xEnergySlider=xSpeedSlider;
    int yEnergySlider=yDetectionSlider+140;
	  EnergySlider.setMajorTickSpacing(500);//espace minimal affiché sous le slider entre les valeurs de vitesse
    EnergySlider.setMinorTickSpacing(100);//espace minimal entre les valeurs de vitesse
    EnergySlider.setPaintTicks(true);
    EnergySlider.setPaintLabels(true);
    EnergySlider.addChangeListener(this);
	  EnergyLabel = new JLabel ("Energie des blobs : "+EnergySlider.getValue());
    EnergyLabel.setBounds(xEnergySlider,yEnergySlider-70,200,60);
//Boutons 
    startButton = new JButton("START");
    startButton.setBackground(Color.GREEN);
    startButton.setBounds(xSpeedSlider+250,yDetectionSlider-135,200,130);
    resetButton = new JButton("RESET");
    resetButton.setBackground(Color.RED);
    resetButton.setBounds(xSpeedSlider+250,yDetectionSlider,200,130);
    //Panneau
    JPanel panel=new JPanel();
    panel.setBounds(10,10,200,400);
    // ajout des Sliders des Labels et des bouttons au panel
    panel.add(speedSlider);
    panel.add(speedLabel);
    panel.add(MapSizeSlider);
    panel.add(MapSizeLabel);
    panel.add(FoodLabel);
    panel.add(FoodSlider);
    panel.add(EnergyLabel);
    panel.add(EnergySlider);
    panel.add(BlobSizeSlider);
    panel.add(BlobSizeLabel);
    panel.add(DetectionSlider);
    panel.add(DetectionLabel);
    panel.add(startButton);
    panel.add(resetButton);
    add(panel);
    //Placement et taille des sliders
	  speedSlider.setBounds(xSpeedSlider,ySpeedSlider,lengthSlider,widthSlider);
    FoodSlider.setBounds(xFoodSlider,yFoodSlider,lengthSlider,widthSlider);
    EnergySlider.setBounds(xEnergySlider,yEnergySlider,lengthSlider,widthSlider);
    MapSizeSlider.setBounds(xMapSizeSlider,yMapSizeSlider,lengthSlider,widthSlider);
    BlobSizeSlider.setBounds(xBlobSizeSlider,yBlobSizeSlider,lengthSlider,widthSlider);
    DetectionSlider.setBounds(xDetectionSlider,yDetectionSlider,lengthSlider,widthSlider);
    
	  panel.setLayout(null);
	  setVisible(true);

  }

  public void stateChanged (ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (source ==speedSlider) {
      speedLabel.setText("Vitesse : "+speedSlider.getValue());
     // Map.blobIniSpeed =speedSlider.getValue() ;
        
    } else if (source ==FoodSlider) {
      FoodLabel.setText("quantite nourriture : "+FoodSlider.getValue());
      //Map.initFoodNumber = FoodSlider.getValue();
    } else if(source== MapSizeSlider){
      MapSizeLabel.setText("Taille de la carte : "+MapSizeSlider.getValue());
    } else if (source == BlobSizeSlider){
      BlobSizeLabel.setText("Taille du blob : "+BlobSizeSlider.getValue());
      //Map.blobIniSize = BlobSizeSlider.getValue();
    }  else if (source == DetectionSlider){
      DetectionLabel.setText("Champ de vision : "+DetectionSlider.getValue());
      }
      else if (source == EnergySlider){
        EnergyLabel.setText("Energie des blobs : "+EnergySlider.getValue());}
  }
  public void actionperformed(ActionEvent e){
    if(e.getSource()==startButton){

    }
    if(e.getSource()==resetButton){
      
    }
  }

  public static void main(String [] args) {  
    IHMControle fenetre =new IHMControle("Sliders",1600,900);  
    fenetre.getContentPane().setBackground(Color.ORANGE);
  }
}
