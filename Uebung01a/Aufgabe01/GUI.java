/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUI extends JFrame{

    private Container con;
    private SimpleMenuBar sb;
    private JButton start;

    public JButton input;
    public JButton output;
    public ImageProcessing img_pro;
    public ImageIcon ic;
    public ImageIcon ic_out;

    public Diagramm dia;
    
    

    public GUI(ImageProcessing i_pro){

    img_pro=i_pro;

    sb=new SimpleMenuBar(this);
    setJMenuBar(sb);

    con=getContentPane();
    con.setLayout(new FlowLayout(FlowLayout.LEFT));

    start= new JButton ("Start");
    input= new JButton ("Input Image");
    output= new JButton ("Output Image");

    dia=new Diagramm();
    
    start.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        img_pro.process();

        output.repaint();

      }});

    con.add(start);
    con.add(input);
    con.add(output);

    con.add(dia);
    
    setVisible(true);
    pack();
    }

}
