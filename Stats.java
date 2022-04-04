import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;
public class Stats extends JPanel{
    ArrayList<Blob> blobs = new ArrayList<Blob>();
    int[] size_range = {20,40,80,100};
    int[] size_value = {0,0,0,0,0};
    int[] view_range = {20,40,80,100};
    int[] view_value = {0,0,0,0,0};
    int[] speed_range = {20,40,80,100};
    int[] speed_value = {0,0,0,0,0};
    int[] energy_range = {20,40,80,100};
    int[] energy_value = {0,0,0,0,0};
    public Stats(ArrayList<Blob> blobs){
        this.blobs = blobs;
    }
    public void fetch(){
        for(Blob el : blobs){
            for(int i=0;i<size_range.length;i++){
                if(el.size<size_range[i]){
                    size_value[i]+=1;
                }
                else{
                    size_value[4]+=1;
                }
            }
            for(int i=0;i<view_range.length;i++){
                if(el.viewRange<view_range[i]){
                    view_value[i]+=1;
                }
                else{
                    view_value[4]+=1;
                }
            }
            for(int i=0;i<speed_range.length;i++){
                if(el.speed<speed_range[i]){
                    speed_value[i]+=1;
                }
                else{
                    speed_value[4]+=1;
                }
            }
            for(int i=0;i<energy_range.length;i++){
                if(el.energyIni<energy_range[i]){
                    energy_value[i]+=1;
                }
                else{
                    energy_value[4]+=1;
                }
            }
        }
    }
    public void draw(Graphics g, Color c){
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
}