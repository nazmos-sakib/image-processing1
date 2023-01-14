/**
 *
 * @author KC
 */

import java.awt.image.*;//BufferedImage
import javax.swing.*;


public class ImageProcessing {

    public BufferedImage img=null;
    public BufferedImage img_out=null;
    public GUI gui;

    public ImageProcessing(){

        gui=new GUI(this);
    }

    public void process(){

    if(img!=null){

                        //Histogramm-Array f�r Originaldaten

                        int[] hist=new int[256];
                        int[] hist_R_1=new int[511];

                        for(int x=0;x<img.getWidth()-1;x++){
                        for(int y=0;y<img.getHeight();y++){

                        // Aufgabe 1a
                                // xvorhersage(n)=x(n)
                                //x forecast
                                int argb_in=img.getRGB(x, y);
                                int grey=getRed(argb_in);

                                // x(n+1)
                                int argb_in_1=img.getRGB(x+1, y);
                                int grey_1=getRed(argb_in_1);

                                // R(n+1)=x(n+1) - xvorhersage(n)
                                int R_1=grey_1-grey;

                                int R_1_scaled= (int)((((double)R_1)+255.0)/2);

                                int argb_out=setARGB(255, R_1_scaled, R_1_scaled, R_1_scaled);

                                //Bilddaten eintragen
                            //Enter image data
                                img_out.setRGB(x+1 , y ,argb_out);
                                
                            // Aufgabe 1b Histogramme berechnen
                            
                                hist[grey_1]++;

                                int R_1_Shift=R_1+255;
                                hist_R_1[R_1_Shift]++;
                                
                                }
                            }

                            // Aufgabe 1b
                            //Aufgabe 1b / 1. Wahrscheinlichkeiten f�r hist berechnen
                            //Calculate probabilities for Hist
                            
                            double[] p_x=new double[256];
                            for(int i=0;i<256;i++){

                            p_x[i]=((double)hist[i])/((double)((img.getWidth()-1)*img.getHeight()));

                            }

                            System.out.println(entropie(p_x));

                            //Aufgabe 1b / 2. Wahrscheinlichkeiten f�r hist_R_1 berechnen

                            double[] p_R_1_x=new double[511];
                            for(int i=0;i<511;i++){

                            p_R_1_x[i]=((double)hist_R_1[i])/((double)((img.getWidth()-1)*img.getHeight()));

                            }

                            System.out.println(entropie(p_R_1_x));


    }

    }

    public double entropie(double[] p){
    double H=0.0;
    for(int i=0;i<p.length;i++){


      if ( p[i]> 0.0) H=H - ((p[i]* Math.log(p[i]))/Math.log(2));

      }
      return H;
    }

    public int setARGB(int al,int red,int green, int blue){
    int val= al<<24 | red<<16 | green<< 8 | blue;
    return val;
    }

    public int getAlpha(int argb){

    return (argb >> 24) & 0xFF;
    }

  public int getRed(int argb){

    return (argb >> 16) & 0xFF;
    }

  public int getGreen(int argb){

    return (argb >> 8) & 0xFF;
    }

  public int getBlue(int argb){

    return argb & 0xFF;
    }

}
