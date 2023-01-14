/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
        int threshold = 200;

    if(img!=null){  //Liner Classification.

                        for(int x=0;x<img.getWidth();x++){
                        for(int y=0;y<img.getHeight();y++){


                            //getting RGB values for each pixel
                                int argb_in=img.getRGB(x, y);
                                int r=getRed(argb_in);
                                int g=getGreen(argb_in);
                                int b=getBlue(argb_in);

                                //Linearer Klassifikator with gray scale
                            //works good with screw
                                int gray = (int) (0.33f*((float) r+(float) g+ (float) b));

                                int a = 0;
                                if (gray> threshold) {a = 255;}

                                //setting new alpha
                                //setting the value of A=0 means extracting background
                            // and A=255 means extracting foreground
                                int A=0;       //Histogram values of the background
                                if( r >80 && r< 110 && g>107 && g<140 && b>130 && b<170 ) { //photoshop histogram value for RGB
                                    A=255;
                                }

                                //creating new image with removing background.
                                int argb_out=setARGB(A,r,g,b);
                                img_out.setRGB(x , y ,argb_out);

                                }
                            }

    /*
    int[] histogramm=new int[256];                   
    int Max=255;
    
    for(int i=0;i<256;i++){
    
    histogramm[i]=i;
    
    }
    
    
    gui.dia.setParam(histogramm, Max);
    gui.dia.repaint();
    
   */
                        
    }

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
