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
    public Diagramm diagramm_01;
    public Diagramm diagramm_02;

    public GUI(ImageProcessing i_pro){

    img_pro=i_pro;

    sb=new SimpleMenuBar(this);
    setJMenuBar(sb);

    con=getContentPane();
    con.setLayout(new FlowLayout(FlowLayout.LEFT));

    start= new JButton ("Start");
    input= new JButton ("Input Image");
    output= new JButton ("Output Image");

    diagramm_01=new Diagramm();
    diagramm_02=new Diagramm();

    start.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        img_pro.process();

        output.repaint();
        
        diagramm_01.repaint();
        diagramm_02.repaint();
        
        pack();

      }});

    con.add(start);
    con.add(input);
    con.add(output);
    con.add(diagramm_01);
    con.add(diagramm_02);

    setVisible(true);
    pack();
    }


}
