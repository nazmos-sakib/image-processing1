/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import javax.swing.*;

public class Diagramm extends JPanel
{
int[] hist;
int Max;

public Diagramm()

{
  setLayout(null);
  setPreferredSize(new Dimension(256, 133));
}

  void setParam(int[] hist,int Max)
  {
    this.hist=hist;
    this.Max=Max;
  }

  public void paintComponent(Graphics g)
  {
    //Lila Hintergrund zeichen
    g.setColor(new Color(123,12,135,167));
    g.fillRect(0,0,256,100);
    
      for(int i=0;i<256;i++)
      {

        int wert=0;
        if(Max>0)wert=(int)(100.0F*(float)hist[i]/(float)Max);
        
        //Grautöne in Balken zeichnen
        g.setColor(new Color(i,i,i));
        g.fillRect(i,100-wert,1,wert);
        g.fillRect(i,100,1,33);
        g.fillRect(0,100,256,5);

      }
  }
}

