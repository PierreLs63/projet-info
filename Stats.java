import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class Stats extends JPanel{
    Color c = new Color(200,100,100);
    ArrayList<Blob> blobs = new ArrayList<Blob>();
    int[] size_range = {20,30,40,80};
    int[] size_value = {0,0,0,0,0};
    int[] view_range = {30,35,40,45};
    int[] view_value = {0,0,0,0,0};
    int[] speed_range = {20,50,80,110};
    int[] speed_value = {0,0,0,0,0};
    int[] energy_range = {485,495,505,515};
    int[] energy_value = {0,0,0,0,0};
    private Image dbImage;
    private Graphics dbg;
    public int width = 920;
    public int height = 400;

    public Stats(ArrayList<Blob> bl,int size,int view,int speed,int energy){
        setBounds(0, 0, width, height);
        setLayout(null);
        blobs = bl;
        fetch(bl,size,view,speed,energy);
    }
    public void setRange(int size,int view,int speed,int energy){
        for(int i=0;i<4;i++){
            size_range[i]=size-10+i*10;
            view_range[i]=view-5+i*5;
            speed_range[i]=speed-20+i*30;
            energy_range[i]=energy-10+i*10;
        }
    }
    public void fetch(ArrayList<Blob> blobs,int size,int view,int speed,int energy){
        setRange(size, view,speed,energy);
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
        g.setColor(new Color(200,200,200));
        Font font = new Font("Serif", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics();
        g.fillRect(0,50,700,2);
        g.fillRect(0,100,700,2);
        g.fillRect(0,150,700,2);
        g.fillRect(0,200,700,2);
        g.setColor(c);
        for(int i=0;i<size_value.length-1;i++){
            g.fillRect(30*i+10,250-size_value[i]*(int)200/(blobs.size()),10,size_value[i]*(int)200/(blobs.size()));
            g.drawString(String.valueOf(size_range[i]), 30*i+20, 250+fontMetrics.getAscent());
        }
        g.fillRect(30*4+10,250-size_value[4]*(int)200/(blobs.size()),10,size_value[4]*(int)200/(blobs.size()));
        for(int i=0;i<view_value.length-1;i++){
            g.fillRect(30*i+180,250-view_value[i]*(int)200/(blobs.size()),10,view_value[i]*(int)200/(blobs.size()));
            g.drawString(String.valueOf(view_range[i]), 30*i+190, 250+fontMetrics.getAscent());
        }
        g.fillRect(30*4+180,250-view_value[4]*(int)200/(blobs.size()),10,view_value[4]*(int)200/(blobs.size()));
        for(int i=0;i<speed_value.length-1;i++){
            g.fillRect(30*i+350,250-speed_value[i]*(int)200/(blobs.size()),10,speed_value[i]*(int)200/(blobs.size()));
            g.drawString(String.valueOf(speed_range[i]), 30*i+360, 250+fontMetrics.getAscent());
        }
        g.fillRect(30*4+350,250-speed_value[4]*(int)200/(blobs.size()),10,speed_value[4]*(int)200/(blobs.size()));
        for(int i=0;i<energy_value.length-1;i++){
            g.fillRect(30*i+520,250-energy_value[i]*(int)200/(blobs.size()),10,energy_value[i]*(int)200/(blobs.size()));
            g.drawString(String.valueOf(energy_range[i]), 30*i+530, 250+fontMetrics.getAscent());
        }
        g.fillRect(30*4+520,250-energy_value[4]*(int)200/(blobs.size()),10,energy_value[4]*(int)200/(blobs.size()));
        g.drawString(String.valueOf((int)(blobs.size())), 700, 50+fontMetrics.getAscent());
        g.drawString(String.valueOf((int)(blobs.size()*0.75)), 700, 100+fontMetrics.getAscent());
        g.drawString(String.valueOf((int)(blobs.size()/2)), 700, 150+fontMetrics.getAscent());
        g.drawString(String.valueOf((int)(blobs.size()*0.25)), 700, 200+fontMetrics.getAscent());
        g.drawString("Size", 60, 270+fontMetrics.getAscent());
        g.drawString("viewrange", 230, 270+fontMetrics.getAscent());
        g.drawString("speed", 400, 270+fontMetrics.getAscent());
        g.drawString("energy", 570, 270+fontMetrics.getAscent());

    }
    public void paint(Graphics g) {
        dbImage = createImage(920, 1000);
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
}
