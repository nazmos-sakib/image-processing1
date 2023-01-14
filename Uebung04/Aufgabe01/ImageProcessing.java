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

                       int[] werte=new int[img.getWidth()];

                        for(int x=0;x<img.getWidth();x++){
                        for(int y=0;y<img.getHeight();y++){
                                int argb_in=img.getRGB(x, y);
                                int r=getRed(argb_in);
                                int g=getGreen(argb_in);
                                int b=getBlue(argb_in);
                                int grey=(int)((0.33F) *( (float)r+(float)g+(float)b ));
                                werte[x]=grey;
                                int argb_out=setARGB(255,grey,grey,grey);
                                img_out.setRGB(x , y ,argb_out);
                                }
                            }

              // Fouriertransformation------------------------------------------
                       DFT dft=new DFT(img.getWidth());

                       Complex[] c=new Complex[img.getWidth()];
                       Complex[] z=new Complex[img.getWidth()];

                               for(int x=0;x<img.getWidth();x++)
                               {
                                    z[x]=new Complex(werte[x],0);  // Werte im komplexen einbetten
                               }

                               //DFT-Transformation des komplexen Eingangsvektors

                               c=dft.transform(z);

                               // Werte zur Anzeige aufbereiten
                               
                               int[] betraege= new int[img.getWidth()];
                               
                               for(int x=0;x<img.getWidth();x++)
                               {
                                    betraege[x]= (int)c[x].getAbs();
                                    betraege[0]=0;
                               }

                                /*DFT-R�cktransformation des Eingangsvektors
                               Complex[] z_new=dct.invtrans(c);
                                */
                            
          gui.diagramm_01.setParam(werte,werte);//Werte Bildzeile �bergeben  == Values handed over a picture
          gui.diagramm_01.repaint();//Diagramm neu zeichnen  == New draw diagram
          gui.diagramm_02.setParam(betraege,betraege);//Koeffizienten �bergeben  == Hand over coefficients
          gui.diagramm_02.repaint();//Diagramm neu zeichnen  == New draw diagram

          gui.pack();  //Fensterabma�e neu berechnen
                            
                            

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
