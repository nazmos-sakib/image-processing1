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

                        for(int x=0;x<img.getWidth();x++){
                        for(int y=0;y<img.getHeight();y++){
                                int argb_in=img.getRGB(x, y);
                                int r=getRed(argb_in);
                                int b=getBlue(argb_in);
                                int argb_out=setARGB(255,r,0,b);
                                img_out.setRGB(x , y ,argb_out);
                                }
                            }

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
