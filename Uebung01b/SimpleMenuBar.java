/**
 *
 * @author KC
 */
import javax.swing.*;
import java.awt.*;
import javax.imageio.*; //ImageIO
import java.awt.image.*;
import java.io.*;


public class SimpleMenuBar extends JMenuBar{

    private JMenu FileMenu;
    private JMenuItem OpenMenuItem1;
    private JMenuItem OpenMenuItem2;
    private JMenuItem OpenMenuItem3;
    private GUI g;


    public SimpleMenuBar(GUI g){

    this.g=g;

    FileMenu = new JMenu();
    OpenMenuItem1 = new JMenuItem();
    OpenMenuItem2 = new JMenuItem();
    OpenMenuItem3 = new JMenuItem();
    FileMenu.setText("File");
    OpenMenuItem1.setText("Open1");
    OpenMenuItem2.setText("Open2");
    OpenMenuItem3.setText("Classification Image");


    add(FileMenu);
    FileMenu.add(OpenMenuItem1);
    FileMenu.add(OpenMenuItem2);
    FileMenu.add(OpenMenuItem3);

    OpenMenuItem1.addActionListener(new java.awt.event.ActionListener()
    {
          public void actionPerformed(java.awt.event.ActionEvent evt)
                         { OpenMenuAction1(evt);
                                      }
     });
     
    OpenMenuItem2.addActionListener(new java.awt.event.ActionListener()
    {
          public void actionPerformed(java.awt.event.ActionEvent evt)
                         { OpenMenuAction2(evt);
                                      }
     });



    OpenMenuItem3.addActionListener(new java.awt.event.ActionListener()
    {
          public void actionPerformed(java.awt.event.ActionEvent evt)
                         { OpenMenuAction3(evt);
                                      }
     });

    }//Konstructor

    private void OpenMenuAction1(java.awt.event.ActionEvent evt)
    {

    FileDialog fd = new FileDialog(g);
    fd.setVisible(true);
                //fd.show();


    g.setTitle("Dir: " +fd.getDirectory() + " File: " + fd.getFile());
    if (fd.getFile() == null) return;



    try{
    g.img_pro.img= ImageIO.read( new File(fd.getDirectory() + fd.getFile()));             // load the image
                }catch(Exception e){};


    g.ic=new ImageIcon(g.img_pro.img);

              g.input.setIcon(g.ic);
              g.pack();

    }//OpenMenuAction1

    private void OpenMenuAction2(java.awt.event.ActionEvent evt)
    {

    FileDialog fd = new FileDialog(g);
    fd.setVisible(true);
                //fd.show();


    g.setTitle("Dir: " +fd.getDirectory() + " File: " + fd.getFile());
    if (fd.getFile() == null) return;



    try{
    g.img_pro.img_out= ImageIO.read( new File(fd.getDirectory() + fd.getFile()));             // load the image
                }catch(Exception e){};


    g.ic_out=new ImageIcon(g.img_pro.img_out);

              g.output.setIcon(g.ic_out);
              g.pack();

  }

  private void OpenMenuAction3(java.awt.event.ActionEvent evt)
    {

    FileDialog fd = new FileDialog(g);
    fd.setVisible(true);
                //fd.show();


    g.setTitle("Dir: " +fd.getDirectory() + " File: " + fd.getFile());
    if (fd.getFile() == null) return;



    try{
    g.img_pro.classificationImage= ImageIO.read( new File(fd.getDirectory() + fd.getFile()));             // load the image
                }catch(Exception e){};

    g.img_pro.classifiedImage=  new BufferedImage(g.img_pro.classificationImage.getWidth(),g.img_pro.classificationImage.getHeight(),BufferedImage.TYPE_INT_ARGB);

    g.ic_classified=new ImageIcon(g.img_pro.classifiedImage);

              g.classification.setIcon(g.ic_classified);
              g.pack();

  }

 }
