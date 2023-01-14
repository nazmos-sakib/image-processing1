/**
 *
 * @author KC
 */

import java.awt.image.*;//BufferedImage
import javax.swing.*;


public class ImageProcessing {

    public BufferedImage img=null;
    public BufferedImage img_out=null;
    public BufferedImage classificationImage=null;
    public BufferedImage classifiedImage=null;

    int[][] index; //Klasseninformation



    public GUI gui;

    public ImageProcessing(){

        gui=new GUI(this);

        index=new int[360][256];
      

    }

    public void process(){

        if(img!=null && img_out!=null && classificationImage!=null){

            int SE=0;


            float[] rgb1=new float[3];
            float[] rgb2=new float[3];
            float[] hsv1=new float[3];
            float[] hsv2=new float[3];

            for(int x=0;x<img.getWidth();x++){
                for(int y=0;y<img.getHeight();y++){
                    int argb_in=img.getRGB(x, y);


                    rgb1[0]=(float)getRed(argb_in);
                    rgb1[1]=(float)getGreen(argb_in);
                    rgb1[2]=(float)getBlue(argb_in);

                    int argb_out=img_out.getRGB(x, y);

                    rgb2[0]=(float)getRed(argb_out);
                    rgb2[1]=(float)getGreen(argb_out);
                    rgb2[2]=(float)getBlue(argb_out);

                    //Farbraumtransformation
                    ColorConverter.rgb_to_hsv(rgb1,hsv1);
                    ColorConverter.rgb_to_hsv(rgb2,hsv2);

                    //Klassen-Information speichern und  Klassen-Information visualisieren
                    //Save class information and visualize class information
                    int u1=(int)(hsv1[0]*360.0F);
                    int v1=(int)(hsv1[1]*256.0F);
                    int u2=(int)(hsv2[0]*360.0F);
                    int v2=(int)(hsv2[1]*256.0F);

                    if(u1<360 && v1 <256 && hsv1[2]<0.95F){

                        index[u1][v1]=1;

                        //Klasse 1: magenta(rgb(255,0,255))
                        gui.uvH_Image.setRGB(u1,v1,setARGB(255,255,0,255));

                    }

                    if(u2<360 && v2 <256 && hsv2[2]<0.95F){
                        index[u2][v2]=-1;
                        //Klasse 2: dark green(rgb(0,100,0)
                        gui.uvH_Image.setRGB(u2,v2,setARGB(255,0,100,0));
                    }



                }
            }


            //Linear -Klassifikator auf classificationImage anwenden
            for(int x=0;x<classificationImage.getWidth();x++){
                for(int y=0;y<classificationImage.getHeight();y++){

                    int argb_in=classificationImage.getRGB(x, y);

                    rgb1[0]=(float)getRed(argb_in);
                    rgb1[1]=(float)getGreen(argb_in);
                    rgb1[2]=(float)getBlue(argb_in);


                    //Farbraumtransformation
                    ColorConverter.rgb_to_hsv(rgb1,hsv1);
                    int u1=(int)(hsv1[0]*360.0F);
                    int v1=(int)(hsv1[1]*256.0F);
                    //57,134  69,165
                    if(!(u1>57 && u1<69 && v1>134 && v1<165)){

                        //Klassifikations-Ergebnis im AusgabeBild eintragen

                        classifiedImage.setRGB(x,y,argb_in);

                    }



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
