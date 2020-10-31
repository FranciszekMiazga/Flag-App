package zad1;

import javax.swing.*;
import java.awt.*;

public class Flags {
    private ImageIcon polishFlag;
    private ImageIcon spainFlag;
    private ImageIcon ukFlag;
    private ImageIcon germanyFlag;
    private ImageIcon czechFlag;
    private ImageIcon brasilFlag;
    private ImageIcon franceFlag;
    private ImageIcon japanFlag;
    private ImageIcon rpaFlag;
    private ImageIcon chinaFlag;
    private ImageIcon portugalFlag;





    public Flags() {
        polishFlag=new ImageIcon("src/zad1/flags/poland-2.png");
        germanyFlag=new ImageIcon("src/zad1/flags/germany.png");
        spainFlag=new ImageIcon("src/zad1/flags/spain.png");
        ukFlag=new ImageIcon("src/zad1/flags/united-kingdom.png");
        czechFlag=new ImageIcon("src/zad1/flags/czech-republic.png");
        brasilFlag=new ImageIcon("src/zad1/flags/brazil.png");
        japanFlag=new ImageIcon("src/zad1/flags/japan.png");
        rpaFlag=new ImageIcon("src/zad1/flags/south-africa.png");
        franceFlag=new ImageIcon("src/zad1/flags/france.png");
        chinaFlag=new ImageIcon("src/zad1/flags/china.png");
        portugalFlag=new ImageIcon("src/zad1/flags/portugal.png");

    }
    public Icon getPortugalFlag(){
        return portugalFlag;
    }

    public Icon getPolishFlag(){
        return polishFlag;
    }

    public ImageIcon getGermanyFlag() {
        return germanyFlag;
    }

    public ImageIcon getSpainFlag() {
        return spainFlag;
    }

    public ImageIcon getUkFlag() {
        return ukFlag;
    }

    public ImageIcon getCzechFlag() {
        return czechFlag;
    }

    public ImageIcon getBrasilFlag() {
        return brasilFlag;
    }

    public ImageIcon getFranceFlag() {
        return franceFlag;
    }

    public ImageIcon getJapanFlag() {
        return japanFlag;
    }

    public ImageIcon getRpaFlag() {
        return rpaFlag;
    }

    public ImageIcon getChinaFlag() {
        return chinaFlag;
    }
}
