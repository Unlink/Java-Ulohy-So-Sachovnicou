/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sach.figurky;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import ulohySoSachovnicou.sachovnica.graphics.AFigurkaPainter;

/**
 *import java_cv2.sachovnica.graphics.AFigurkaPainter;
 * @author Unlink
 */
public class SachovnicoveFigurky2D extends AFigurkaPainter {

    public SachovnicoveFigurky2D() {
        super("/java_cv2/sach/figurky/resources/");
    }
    
    
    
    /**
     * Grafická podoba dámy
     * @param paFigurka
     * @param paImg 
     */
    public void paint(Dama paFigurka, BufferedImage paImg) {
        Graphics2D g2 = (Graphics2D) paImg.getGraphics();
        try {
            drawImage(paImg, paFigurka.farba().toString().toLowerCase()+"_dama");
        } catch (IOException ex) {
            drawDeflaut(paImg);
        }
    }
    
    public void paint(Jazdec paFigurka, BufferedImage paImg) {
        Graphics2D g2 = (Graphics2D) paImg.getGraphics();
        try {
            drawImage(paImg, paFigurka.farba().toString().toLowerCase()+"_jazdec");
        } catch (IOException ex) {
            drawDeflaut(paImg);
        }
    }
}
