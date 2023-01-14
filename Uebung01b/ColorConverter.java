import java.awt.*;

public class ColorConverter {



    public static void rgb_to_hsv(float[] rgb, float[] hsv) {
        float r1, g1, b1;        
        if ((rgb.length != 3) && (hsv.length != 3)) {
            System.out.println("Falsche Argumente");
            return;
        }
        hsv[2] = Math.max(Math.max(rgb[0], rgb[1]), rgb[2]);
        float min = Math.min(Math.min(rgb[0], rgb[1]), rgb[2]);        
        float x = (hsv[2] - min);
        if (hsv[2] == 0.0F) {
            hsv[0] = hsv[1] = 0.0F;   // color = black
            return;
        }
        hsv[1] = x/hsv[2];
        if (x == 0.0F) {
            hsv[2] /= 256.0F;
            hsv[0] = 0.0F;   // color = gray
            return;
        }
        r1 = (hsv[2] - rgb[0])/x;
        g1 = (hsv[2] - rgb[1])/x;
        b1 = (hsv[2] - rgb[2])/x;
        if ((rgb[0] == hsv[2]) && (rgb[1] == min))
            hsv[0] = (5.0F + b1)/6.0F;
        else if ((rgb[0] == hsv[2]) && (rgb[1] != min))
            hsv[0] = (1.0F - g1)/6.0F;
        else if ((rgb[1] == hsv[2]) && (rgb[2] == min))
            hsv[0] = (1.0F + r1)/6.0F;
        else if ((rgb[1] == hsv[2]) && (rgb[2] != min))
            hsv[0] = (3.0F - b1)/6.0F;
        else if ((rgb[2] == hsv[2]) && (rgb[0] == min))
            hsv[0] = (3.0F + g1)/6.0F;
        else hsv[0] = (5.0F - r1)/6.0F;
        hsv[2] /= 256.0F;
     }
    
    
    public static void hsv_to_rgb(float[] hsv, float[] rgb) {
        float f, w1, w2, w3;
        int i;
        if ((rgb.length != 3) && (hsv.length != 3)) {
            System.out.println("Falsche Argumente");
            return;
        }
        if (hsv[1] == 0.0F) {
            float value = hsv[2]*256.0F;
            if (value >=256.0F) value = 255.0F;
            rgb[0] = rgb[1] = rgb[2] = value;
            return;
        }
        i  = (int)(6.0F * hsv[0]); 
        if (i == 6) i=5; 
        f  = 6.0F*hsv[0] - (float)i;
        w1 = hsv[2] * (1.0F - hsv[1]);
        w2 = hsv[2] * (1.0F - hsv[1]*f);
        w3 = hsv[2] * (1.0F - (hsv[1]*(1.0F - f)));
        switch (i) {
            case 0: rgb[0] = hsv[2]; rgb[1] = w3;      rgb[2] = w1; break;
            case 1: rgb[0] = w2;     rgb[1] = hsv[2];  rgb[2] = w1; break;
            case 2: rgb[0] = w1;     rgb[1] = hsv[2];  rgb[2] = w3; break;
            case 3: rgb[0] = w1;     rgb[1] = w2;      rgb[2] = hsv[2]; break;
            case 4: rgb[0] = w3;     rgb[1] = w1;      rgb[2] = hsv[2]; break;
            case 5: rgb[0] = hsv[2]; rgb[1] = w1;      rgb[2] = w2; break;
        }
        for (int j=0; j<3; j++)  {
            rgb[j] *= 256.0F;
            if (rgb[j] >= 256.0F) rgb[j] = 255.0F;
        }
     }
     
    
}