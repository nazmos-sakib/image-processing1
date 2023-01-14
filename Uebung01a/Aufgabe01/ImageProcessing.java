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

    if(img!=null){
        //Histogram- data--------------------------------
        int[] histogram = new int[256];
        int Max=0;

        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){

                //getting RGB values for each pixel
                int argb_in=img.getRGB(x, y);
                int r=getRed(argb_in);
                int g=getGreen(argb_in);
                int b=getBlue(argb_in);

                //setting gray value for each pixel
                int grey=(int)((0.33F) *( (float)r+(float)g+(float)b ));

                //creating new image with gray value
                int argb_out=setARGB(255,grey,grey,grey);
                img_out.setRGB(x , y ,argb_out);

                //Histogram calculation
                if (grey> -1 && grey<256){
                    histogram[grey]=histogram[grey]+1;
                    if(Max<histogram[grey]) Max=histogram[grey];
                }//end of if

            }//end of height for
        } //end of weight for


    
    
    gui.dia.setParam(histogram, Max); //to plot histogram
    gui.dia.repaint();
    
   //------------------------------------------------                     
                        
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
