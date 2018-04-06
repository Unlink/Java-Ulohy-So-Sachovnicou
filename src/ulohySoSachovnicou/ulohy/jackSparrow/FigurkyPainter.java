/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.ulohy.jackSparrow;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import ulohySoSachovnicou.sachovnica.graphics.AFigurkaPainter;

/**
 *
 * @author Unlink
 */
public class FigurkyPainter extends AFigurkaPainter {

    public FigurkyPainter() {
        super("/java_cv2/jackSparrow/");
    }
    
    public void paint(JackSparrow paFigurka, BufferedImage paImg) {
        Graphics2D g2 = (Graphics2D) paImg.getGraphics();
        try {
            drawImage(paImg, "jack-sparrow");
        } catch (IOException ex) {
            drawDeflaut(paImg);
        }
    }
    
    public void paint(Poklad paFigurka, BufferedImage paImg) {
        Graphics2D g2 = (Graphics2D) paImg.getGraphics();
        try {
            drawImage(paImg, "poklad");
        } catch (IOException ex) {
            drawDeflaut(paImg);
        }
    }
    
    public void paint(Znacka paFigurka, BufferedImage paImg) {
        //Not painted
    }
}
