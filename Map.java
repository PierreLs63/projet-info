import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;


public class Map extends JFrame implements ActionListener{
    //variables du terrain
    int width;
    int height;
    int wallWidth;
    int wallHeight;

    //temps
    Timer timer;
    int day = 1;
    int minute = 0;

    //blobs
    int initBlobNumber = 10;
    double blobIniSpeed = 3;
    double blobIniSize = 10;
    double blobIniView = 40;
    ArrayList<Blob> blobs = new ArrayList<Blob>();
    ArrayList<Food> foods = new ArrayList<Food>();  



    public Map (int w, int h){
        width = w; //largeur de la map
        height = h; //hauteur de la map
        wallWidth = width/20; //largeur des bords map
        wallHeight = height/20; //hauteur des bords map
        setBounds(0, 0, width, height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        for (int i = 0; i < initBlobNumber; i++){ //initialise un tableau de initBlobNumber blobs chacun placés aléatoirement sur les bords de la map
            blobs.add(new Blob (blobIniSpeed, blobIniSize, blobIniView)) ;
            double rand = Math.random();
            if(rand<0.25){
                blobs.get(i).pos_x = 0+blobs.get(i).size;
                blobs.get(i).pos_y = Math.random()*(height-2*wallHeight)+wallHeight;
                blobs.get(i).speedV = new Vect(1,0); 
            }
            else if(rand>0.25 && rand <0.5){
                blobs.get(i).pos_x = width-blobs.get(i).size;
                blobs.get(i).pos_y = Math.random()*(height-2*wallHeight)+wallHeight;
                blobs.get(i).speedV = new Vect(-1,0); 
            }
            else if(rand>0.5 && rand<0.75){
                blobs.get(i).pos_x = Math.random()*(width-2*wallWidth)+wallWidth;
                blobs.get(i).pos_y = 0+blobs.get(i).size;
                blobs.get(i).speedV = new Vect(0,1); 
            }
            else {
                blobs.get(i).pos_x = Math.random()*(width-2*wallWidth)+wallWidth;
                blobs.get(i).pos_y = height-blobs.get(i).size;
                blobs.get(i).speedV = new Vect(0,-1); 
            }
        } 
        timer = new Timer(100, this); // ttes les actions se feront les x ms
        timer.start(); //commence la partie
        repaint(); //actualise l'IDH
    }
    public Vect attractFood(){
        for(int j = 0;j<blobs.size();j++){
            int index= -1;
            float distMin= -1;
            for(int i=0;i<foods.size();i++){
                double dist = new Vect(blobs.get(j).pos_x,blobs.get(j).pos_y).distance(foods.get(i).pos_x,foods.get(i).pos_y);
                if(dist<blobs.get(j).view_range){
                    if(distMin== -1 || dist<distMin){
                        index = i;
                    }
               } 
            }
        }
        return new Vect(1,1);
    }
    public void paint (Graphics g){
        g.setColor(Color.pink); //la map ext
        g.fillRect(0,0,width,height);
        g.setColor(Color.blue); //la map int
        g.fillRect(wallWidth,wallHeight,width-2*wallWidth,height-2*wallWidth);


        for (int j = 0; j < blobs.size(); j++){ //les blobs
            blobs.get(j).draw(g, Color.yellow);
        }
        blobs.get(2).draw (g, Color.red); //un certain blob pour les tests

    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) { //tout ce qui se passe chaque x ms
        if (e.getSource() == timer){
            minute ++;
            
            for (int j = 0; j < blobs.size(); j++){ //déplacement des blobs qui n'ont pas dépassé les murs
                if ( blobs.get(j).pos_x < wallWidth || blobs.get(j).pos_x > (width-wallWidth) || blobs.get(j).pos_y < wallHeight || blobs.get(j).pos_y > (height-wallHeight)){
                    blobs.get(j).wanderingStrength =0;
                    blobs.get(j).wallBounce = false;
                    blobs.get(j).VectSpeed(); //recalcule les forces appliquées au blob et son déplacement

                }else if(testBord(j) == -2){ //faire que les blobs soient repoussés par le mur du bas
                    blobs.get(j).wanderingStrength =0;
                    blobs.get(j).VectSpeed(new Vect(0,-1), 100); //applique une force qui les repousse du mur
                    blobs.get(j).wallBounce = false;

                }else if(testBord(j) == 2){ //faire que les blobs soient repoussés par le mur du haut
                    blobs.get(j).wanderingStrength =0;
                    blobs.get(j).VectSpeed(new Vect(0,1), 100); //applique une force qui les repousse du mur
                    blobs.get(j).wallBounce = false;

                }else if(testBord(j) == -1){ //faire que les blobs soient repoussés par le mur de gauche
                    blobs.get(j).wanderingStrength =0;
                    blobs.get(j).VectSpeed(new Vect(1,0), 100); //applique une force qui les repousse du mur
                    blobs.get(j).wallBounce = false;

                }else if(testBord(j) == 1){ //faire que les blobs soient repoussés par le mur de droite
                    blobs.get(j).wanderingStrength =0;
                    blobs.get(j).VectSpeed(new Vect(-1,0), 100); //applique une force qui les repousse du mur
                    blobs.get(j).wallBounce = false;


                }else{ ////déplacement des blobs qui ont dépassé les murs
                    blobs.get(j).wanderingStrength =5;
                    blobs.get(j).wallBounce = true;
                    blobs.get(j).VectSpeed(); //recalcule les forces appliquées au blob et son déplacement

                }
                System.out.println(blobs.get(2).wallBounce +" "+new Vect(blobs.get(j).pos_x, wallHeight).distance(blobs.get(j).pos_x, blobs.get(j).pos_y)); //pour des tests
            }
        }
        if (minute == day*500){ // ce qui se passe à la fin de la journée
            
            for (int j =  blobs.size() - 1 ; j >= 0; j--){
                if (blobs.get(j).pos_x < wallWidth || blobs.get(j).pos_x > (width-wallWidth) || blobs.get(j).pos_y < wallHeight || blobs.get(j).pos_y > (height-wallHeight)){
                }
                else{
                    blobs.remove(j);
                }
            }
            day ++;
            
        }
        repaint();
    }

    public int testBord(int j){ //vérifie si le blob détecte les murs de la map
        if(new Vect(wallWidth, blobs.get(j).pos_y).distance(blobs.get(j).pos_x, blobs.get(j).pos_y) <= blobs.get(j).view_range && blobs.get(j).wallBounce == true){
            return -1; /// mur de gauche
        }
        else if(new Vect(width-wallWidth, blobs.get(j).pos_y).distance(blobs.get(j).pos_x, blobs.get(j).pos_y) <= blobs.get(j).view_range && blobs.get(j).wallBounce == true){
            return 1; //mur de droite
        }
        else if(new Vect(blobs.get(j).pos_x, wallHeight).distance(blobs.get(j).pos_x, blobs.get(j).pos_y) <= blobs.get(j).view_range && blobs.get(j).wallBounce == true){
            return 2; //mur du haut
        }
        else if (new Vect(blobs.get(j).pos_x, height-wallHeight).distance(blobs.get(j).pos_x, blobs.get(j).pos_y) <= blobs.get(j).view_range && blobs.get(j).wallBounce == true){
            return -2; //mur du bas
        }
        else{
            return 0; //pas de mur
        }    
    }
}
