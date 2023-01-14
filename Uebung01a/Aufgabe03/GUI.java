/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
    public ImageProcessing img_pro;
    public ImageIcon ic;
    public ImageIcon ic_out;

//    public Diagramm dia;
    
    public JButton uvHistogramm;
    public BufferedImage uvH_Image;

    

    public GUI(ImageProcessing i_pro){

    img_pro=i_pro;

    sb=new SimpleMenuBar(this);
    setJMenuBar(sb);

    con=getContentPane();
    con.setLayout(new FlowLayout(FlowLayout.LEFT));

    uvH_Image = new BufferedImage(512,512,BufferedImage.TYPE_INT_RGB);

    start= new JButton ("Start");
    input= new JButton ("Input Image");
    output= new JButton ("Output Image");

  //  dia=new Diagramm();
    uvHistogramm= new JButton ("UV-Histogramm");
    ImageIcon ic_uvH=new ImageIcon(uvH_Image);
    uvHistogramm.setIcon(ic_uvH);

    start.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        img_pro.process();

        output.repaint();

      }});

    con.add(start);
    con.add(input);
    con.add(output);

    // con.add(dia);

    con.add(uvHistogramm);

    
    setVisible(true);
    pack();
    }

}
