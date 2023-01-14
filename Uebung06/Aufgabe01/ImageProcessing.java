/**
 *
 * @author KC
 */

import java.awt.image.*;//BufferedImage
import javax.swing.*;


public class ImageProcessing {

    public BufferedImage img=null;
    public BufferedImage img_out=null;
    public BufferedImage img_out2=null;
    public GUI gui;

    public ImageProcessing(){

        gui=new GUI(this);
    }

    public void process(){

    if(img!=null){
        int N=img.getWidth();
        int M=img.getHeight();

        //complex embedding of grey values

        Complex[][] z = new Complex[N][M];

                        for(int x=0;x<img.getWidth();x++){
                            for(int y=0; y<img.getHeight();y++){

                                    int argb_in=img.getRGB(x, y);
                                    int r=getRed(argb_in);
                                    int g=getGreen(argb_in);
                                    int b=getBlue(argb_in);

                                    double gray = (double) (r+g+b)/3.0;
                                    z[x][y] = new Complex(gray,0.0);

                                    /*
                                         int argb_out=setARGB(255,r,g,0);
                                        img_out.setRGB(x , y ,argb_out);

                                        int argb_out2=setARGB(255,r,0,b);
                                        img_out2.setRGB(x, y ,argb_out2);
                                    */

                                }
                            }
                            
          DFT_2 dft_2 = new DFT_2(N,M);
          Complex[][] c = dft_2.transform(z);




        //introduce filter values
        Complex[][]  c_filtered = new Complex[N][M];

        //
        int threshold = 20;

        for (int k=0; k<N;k++) {
            for (int l = 0; l < M; l++) {
                c_filtered[k][l] = new Complex(0.0,0.0);

                if (!((k<threshold && l<threshold) || (k>N-threshold && l<threshold) || (k<threshold && l>M-threshold) || (k>N-threshold && l>M-threshold))){
                    c_filtered[k][l] = c[k][l];
                }

            }
        }

        //Display the strength of all coefficient
        int [][] c_display = new int[N][M];

        for (int k=0; k<N;k++){
            for (int l=0;l<M;l++){
                c_display[k][l] = (int)  c_filtered[k][l].getAbs();

                int argb_out = setARGB(255,c_display[k][l],c_display[k][l],c_display[k][l]);
                img_out.setRGB(k , l ,argb_out);
            }
        }

        //reconstruction (normally at the decoder)
        Complex[][] z_reconstruction = dft_2.invtrans(c_filtered);
        for (int x=0; x<N;x++){
            for (int y=0;y<M;y++){
                double z_display = 128.0 + z_reconstruction[x][y].getReal();

                int argb_out2=setARGB(255,(int)z_display,(int)z_display,(int)z_display);
                img_out2.setRGB(x, y ,argb_out2);
            }
        }




        gui.pack();  //Fensterabmae neu berechnen
                            
                            

    }//end of if

    }//end of process function

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
