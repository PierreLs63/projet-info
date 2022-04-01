import java.awt.*;
import javax.swing.*;

public class IHM extends JFrame {

    public IHM (Map map){
        JPanel affichageGlobal = new JPanel();
        affichageGlobal.setBounds(0,50,2500,1000);
        affichageGlobal.setLayout(null);
		affichageGlobal.setVisible(true);
        affichageGlobal.setBackground(Color.magenta);
        affichageGlobal.add(map);
        add(affichageGlobal);
        setVisible(true);
    }
    
}
