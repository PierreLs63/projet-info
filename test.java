import javax.swing.*;
import java.awt.*;

public class test extends JFrame {
    
    private JPanel fenetre;
    private JLabel image;

    public test (){
        this.setTitle("salut");
        this.setBounds(0,0,800,800);
        this.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fenetre = new JPanel();
        fenetre.setBounds(0,0,800,800);
        fenetre.setLayout(null);
        fenetre.setBackground(Color.blue);

        image = new JLabel(new ImageIcon ("./Images/Logo300.png"));
        image.setBounds(20,20,600,600);

        this.add (fenetre);
        fenetre.add(image);

        this.setVisible(true);
    }

    public static void main (String [] args){
        test t = new test();
    }
}
