import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;//BufferedImage

public class GUI extends JFrame{

    private Container con;
    private SimpleMenuBar sb;
    private JButton start;

    public JButton input;
    public JButton output;
    public JButton classification;
    public JButton uvHistogramm;
    
    public BufferedImage uvH_Image;

    public ImageProcessing img_pro;
    public ImageIcon ic;
    public ImageIcon ic_out;
    public ImageIcon ic_classified;


    public GUI(ImageProcessing i_pro){

    img_pro=i_pro;

    sb=new SimpleMenuBar(this);
    setJMenuBar(sb);

    con=getContentPane();
    con.setLayout(new FlowLayout(FlowLayout.LEFT));

    uvH_Image = new BufferedImage(360,256,BufferedImage.TYPE_INT_RGB);
        float two_pi=2.0F*(float)Math.PI;

        for(int h=0;h<360;h++){
            for(int s=0;s<256;s++){

                float[] hsv = new float[3];
                hsv[0] = ((float)h)/360.0F;
                hsv[1] = ((float)s)/256.0F;
                hsv[2] =  1.0F;

                float[] rgb = new float[3];

                ColorConverter.hsv_to_rgb(hsv,rgb);


                int color=setARGB(255,(int)rgb[0],(int)rgb[1],(int)rgb[2]);

                uvH_Image.setRGB(h,s,color);

            }
        }
   
    
     



    start= new JButton ("Start");
    input= new JButton ("Input Image");
    output= new JButton ("Output Image");
    classification= new JButton ("Classification Image");
    uvHistogramm= new JButton ("Histogramm Image");


    ImageIcon ic_uvH=new ImageIcon(uvH_Image);
    uvHistogramm.setIcon(ic_uvH);

    start.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        img_pro.process();

        output.repaint();
        uvHistogramm.repaint();

      }});

    con.add(start);
    con.add(input);
    con.add(output);

//    con.add(uvDiagramm);
    con.add(uvHistogramm);
    con.add(classification);

    setVisible(true);
    pack();
    }

    public int setARGB(int al,int red,int green, int blue){
    int val= al<<24 | red<<16 | green<< 8 | blue;
    return val;
    }
}
