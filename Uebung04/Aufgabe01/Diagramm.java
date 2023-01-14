import java.awt.*;
import javax.swing.*;

public class Diagramm extends JPanel
{
private int[] werte1;
private int[] werte2;
private int N;

public Diagramm()

{
  this.N=100;
  werte1=new int[100];
  werte2=new int[100];
  setLayout(null);
  setPreferredSize(new Dimension(100, 256));
}


  void setParam(int[] werte1,int[] werte2)
  {
    this.N=werte1.length;
    this.setPreferredSize(new Dimension(werte1.length, 256));
    this.werte1=werte1;
    this.werte2=werte2;
  }

  public void paintComponent(Graphics g)
  {
    //Lila Hintergrund zeichen
    g.setColor(new Color(135,12,135));
    if(werte1 !=null){

    g.fillRect(0,0,N,256);

      // Daten werte1 zeichnen
      g.setColor(new Color(12,255,167));
      for(int i=0;i<N;i++)
      {
        int wert=0;
        wert=werte1[i];
        
        g.fillRect(i,256-wert,1,wert);

      }
      
      // Daten werte2 zeichnen
      g.setColor(new Color(100,12,255,100));
      for(int i=0;i<N;i++)
      {
        int wert=0;
        wert=werte2[i];

        g.fillRect(i,256-wert,1,wert);

      }
      
      
      }
  }
}
