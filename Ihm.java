import java.awt.*;
import javax.swing.*;

public class IHM extends JFrame {

    public IHM (Map map){
        JPanel affichageGlobal = new JPanel();
        affichageGlobal.setBounds(0,50,2000,1000);
        affichageGlobal.setBackground(Color.white);
        affichageGlobal.add(map);
        System.out.println("pute");
        add(affichageGlobal);
        setVisible(true);
    }
    
}
