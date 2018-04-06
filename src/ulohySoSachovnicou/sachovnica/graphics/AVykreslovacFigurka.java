/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sachovnica.graphics;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import ulohySoSachovnicou.sachovnica.IFigurka;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;
import ulohySoSachovnicou.vykreslovac.AVykreslovac;

/**
 *
 * @author Unlink
 */
public abstract class AVykreslovacFigurka extends AVykreslovac<Sachovnica, PolickoSachovnice> {

    private final IFigurkaDraw aFigurkaDrawer;

    public AVykreslovacFigurka(Sachovnica paSachovnica, IFigurkaDraw paFigurkaDrawer) {
        super(paSachovnica);
        this.aFigurkaDrawer = paFigurkaDrawer;
    }

    protected void vykresliFigurku(Graphics2D g2, int x, int y, IFigurka figurka) {
        BufferedImage bi = new BufferedImage(aRozmerPolicka.width, aRozmerPolicka.height, BufferedImage.TYPE_INT_ARGB);
        aFigurkaDrawer.draw(figurka, bi);
        g2.drawImage(bi, x, y, null);
    }
    
}
