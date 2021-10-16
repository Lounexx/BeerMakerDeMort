package com.example.beermakerdemort.Objects;

import java.io.Serializable;

public class Recette implements Serializable {
    private Integer id;
    private double  volumeBiere, degreAlcool,ebc;

    public Recette(){

    }

    public Recette(Integer id,double volumeBiere,double degreAlcool,double ebc){
        this.id = id;
        this.volumeBiere = volumeBiere;
        this.degreAlcool = degreAlcool;
        this.ebc = ebc;
    }

    public Integer getId() {
        return id;
    }

    public double getVolumeBiere() {
        return volumeBiere;
    }

    public void setVolumeBiere(double volumeBiere) {
        this.volumeBiere = volumeBiere;
    }

    public double getDegreAlcool() {
        return degreAlcool;
    }

    public void setDegreAlcool(double degreAlcool) {
        this.degreAlcool = degreAlcool;
    }

    public double getEbc() {
        return ebc;
    }

    public void setEbc(double ebc) {
        this.ebc = ebc;
    }

    public static double calculateMalt(double volumeBiere, double degreAlcool){
       return  (volumeBiere * degreAlcool) / 20;
    }

    public static double calculateEauBrassage(double quantitéMalt){
        return  (quantitéMalt * 2.8);
    }

    public static double calculateEauRincage(double volumeBiere,double eauBrassage){
        return  ((volumeBiere * 1.25)-(eauBrassage * 0.7));
    }

    public static double calculateMCU(double ebcGrain,double quantiteMalt,double volumeBiere){
        return ((4.23*(ebcGrain * quantiteMalt))/volumeBiere);
    }

    public static double calculateAmerisant(double volumeBiere){
        return (volumeBiere * 3);
    }

    public static double calculateAromatique(double volumeBiere){
        return (volumeBiere * 1);
    }

    public static double calculateLevure(double volumeBiere){
        return (volumeBiere / 2);
    }


    public static String srmToRGB(double srm) {
// Returns an RGB value based on SRM
        Double r, g, b;
        r= g= b= (double) 0;
        if (srm>=0 && srm<=1) {
            r = (double) 240; g = (double) 239; b = (double) 181;
        } else if (srm>1 && srm<=2) {
            r = (double) 233; g = (double) 215; b = (double) 108;
        } else if (srm>2) {
// Set red decimal
            if (srm<70.6843) {           r = 243.8327-(6.4040*srm)+(0.0453*srm*srm);
            } else {           r = 17.5014; }
// Set green decimal
            if (srm<35.0674) {           g = 230.929-12.484*srm+0.178*srm*srm;
            } else {           g = 12.0382; }
// Set blue decimal
            if (srm<4) {           b = (double) -54*srm+216;
            } else if (srm>=4 && srm<7) {           b = (double) 0;
            } else if (srm>=7 && srm<9) {           b = (double) 13*srm-91;
            } else if (srm>=9 && srm<13) {           b = (double) 2*srm+8;
            } else if (srm>=13 && srm<17) {           b = -1.5*srm+53.5;
            } else if (srm>=17 && srm<22) {           b = 0.6*srm+17.8;
            } else if (srm>=22 && srm<27) {           b = -2.2*srm+79.4;
            } else if (srm>=27 && srm<34) {           b = -0.4285*srm + 31.5714;
            } else {           b = (double) 17; }
        }
        Integer red = r.intValue(); Integer green = g.intValue(); Integer blue = b.intValue();
        String rr = red.toHexString(red);
        String gg = green.toHexString(green);
        String bb = blue.toHexString(blue);
        String rgb = "#";
        if (rr.length()<2){       rr="0"+rr;
        }else if (gg.length()<2){       gg="0"+gg;
        }else if (bb.length()<2){       bb="0"+bb; }
        rgb = rgb+rr+gg+bb;
        return rgb;
    }
}
