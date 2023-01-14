import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUI extends JFrame{

    private Container con;
    private SimpleMenuBar sb;
    private JButton start;

    public JButton input;
    public JButton output;
    public JButton output2;
    public ImageProcessing img_pro;
    public ImageIcon ic;
    public ImageIcon ic_out;
    public ImageIcon ic_out2;

    public GUI(ImageProcessing i_pro){

    img_pro=i_pro;

    sb=new SimpleMenuBar(this);
    setJMenuBar(sb);

    con=getContentPane();
    con.setLayout(new FlowLayout(FlowLayout.LEFT));

    start= new JButton ("Start");
    input= new JButton ("Input Image");
    output= new JButton ("Output Image");
    output2= new JButton ("Reconstruction");


    start.addActionListener(

      new ActionListener(){
      public void actionPerformed(ActionEvent e){

        img_pro.process();

        output.repaint();
        

        
        pack();

      }});

    con.add(start);
    con.add(input);
    con.add(output);
    con.add(output2);

    setVisible(true);
    pack();
    }


}
