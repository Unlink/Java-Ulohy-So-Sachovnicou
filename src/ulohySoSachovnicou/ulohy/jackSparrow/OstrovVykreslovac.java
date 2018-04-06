/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.ulohy.jackSparrow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;
import ulohySoSachovnicou.sachovnica.graphics.AVykreslovacFigurka;
import ulohySoSachovnicou.sachovnica.graphics.IFigurkaDraw;
import javax.imageio.ImageIO;

/**
 *
 * @author Unlink
 */
public class OstrovVykreslovac extends AVykreslovacFigurka {

    private final List<PolickoSachovnice> aCesta;
    
    public OstrovVykreslovac(Sachovnica paSachovnica, IFigurkaDraw aFigurkaDrawer, List<PolickoSachovnice> aCesta) {
        super(paSachovnica, aFigurkaDrawer);
        this.aCesta = aCesta;
    }

    @Override
    public void vykresli(Dimension paSize, Graphics2D paGd) {
        super.vykresli(paSize, paGd);
        Point p = new Point(aCesta.get(0).getX()*aRozmerPolicka.width, aCesta.get(0).getY()*aRozmerPolicka.height);
        for (PolickoSachovnice pl:aCesta) {
            paGd.setColor(Color.BLACK);
            Point p2 = new Point(pl.getX()*aRozmerPolicka.width, pl.getY()*aRozmerPolicka.height);
            paGd.drawLine(p.x+aRozmerPolicka.width/2, p.y+aRozmerPolicka.height/2, p2.x+aRozmerPolicka.width/2, p2.y+aRozmerPolicka.height/2);
            p = p2;
        }
    }

    
    
    @Override
    protected void vykresliPozadie(Graphics2D paGd) {
        super.vykresliPozadie(paGd);
        try {
            BufferedImage bg = ImageIO.read(getClass().getResource("/java_cv2/jackSparrow/sand_texture.png"));
            paGd.drawImage(bg, 0, 0, aCanvasSize.width, aCanvasSize.height, null);
        } catch (IOException ex) {
        }
    }

    @Override
    protected void vykresliPolicko(Graphics2D paGd, Point paPozicia, PolickoSachovnice paPolicko) {
        paGd.setColor(new Color(127, 140, 141, 150));
        paGd.drawOval(paPozicia.x+1, paPozicia.y+1, aRozmerPolicka.width-2, aRozmerPolicka.height-2);
        vykresliFigurku(paGd, paPozicia.x, paPozicia.y, paPolicko.dajFigurku());
    }
}
