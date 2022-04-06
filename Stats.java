import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class Stats extends JPanel{
    Color c = new Color(200,100,100);
    ArrayList<Blob> blobs = new ArrayList<Blob>();
    int[] size_range = {20,30,40,80};
    int[] size_value = {0,0,0,0,0};
    int[] view_range = {20,40,80,100};
    int[] view_value = {0,0,0,0,0};
    int[] speed_range = {20,40,80,100};
    int[] speed_value = {0,0,0,0,0};
    int[] energy_range = {500,500,501,505};
    int[] energy_value = {0,0,0,0,0};
    private Image dbImage;
    private Graphics dbg;
    public int width = 800;
    public int height = 800;

    public Stats(ArrayList<Blob> bl){
        setBounds(0, 0, 800, 800);
        setLayout(null);
        blobs = bl;
        fetch(bl);
    }
    public void fetch(ArrayList<Blob> blobs){
        for(int j=0;j<=4;j++){
            size_value[j]=0;
            view_value[j]=0;
            speed_value[j]=0;
            energy_value[j]=0;
        }
        for(Blob el : blobs){
            for(int i=0;i<size_range.length;i++){
                if(el.size<size_range[i]){
                    size_value[i]+=1;
                    break;
                }
            }
            if(el.size>=size_range[size_range.length-1]){
                size_value[4]+=1;
            }
            for(int i=0;i<view_range.length;i++){
                if(el.viewRange<view_range[i]){
                    view_value[i]+=1;
                    break;
                }
            }
            if(el.viewRange>=view_range[view_range.length-1]){
                view_value[4]+=1;
            }
            for(int i=0;i<speed_range.length;i++){
                if(el.speed<speed_range[i]){
                    speed_value[i]+=1;
                    break;
                }
            }
            if(el.speed>=speed_range[speed_range.length-1]){
                speed_value[4]+=1;
            }
            for(int i=0;i<energy_range.length;i++){
                if(el.energyIni<energy_range[i]){
                    energy_value[i]+=1;
                    break;
                }
            }
            if(el.energyIni>=energy_range[energy_range.length-1]){
                energy_value[4]+=1;
            }
        }
    }
    public void paintComponent(Graphics g){
        for(int i=0;i<size_value.length;i++){
            g.setColor(c);
            g.fillRect(20*i+10,200-size_value[i]*10,10,size_value[i]*10);
        }
        for(int i=0;i<view_value.length;i++){
            g.setColor(c);
            g.fillRect(20*i+120,200-view_value[i]*10,10,view_value[i]*10);
        }
        for(int i=0;i<speed_value.length;i++){
            g.setColor(c);
            g.fillRect(20*i+220,200-speed_value[i]*10,10,speed_value[i]*10);
        }
        for(int i=0;i<energy_value.length;i++){
            g.setColor(c);
            g.fillRect(20*i+320,200-energy_value[i]*10,10,energy_value[i]*10);
        }
    }
    public void paint(Graphics g) {
        dbImage = createImage(920, 1000);
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
}
