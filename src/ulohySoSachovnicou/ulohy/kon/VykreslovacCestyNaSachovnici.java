/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.ulohy.kon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import ulohySoSachovnicou.hraciaPlocha.APolicko;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;
import ulohySoSachovnicou.sachovnica.graphics.IFigurkaDraw;
import ulohySoSachovnicou.sachovnica.graphics.SachovnicaVykreslovac;

/**
 *
 * @author Unlink
 */
public class VykreslovacCestyNaSachovnici extends SachovnicaVykreslovac {

    private List<APolicko> aCesta;

    public VykreslovacCestyNaSachovnici(Sachovnica paSachovnica, IFigurkaDraw paFigurkaDrawer, List<APolicko> paCesta) {
        super(paSachovnica, paFigurkaDrawer);
        aCesta = paCesta;
    }

    @Override
    public void vykresli(Dimension paSize, Graphics2D paGd) {
        super.vykresli(paSize, paGd);
        if (!aCesta.isEmpty()){
            Point p = new Point(aCesta.get(0).getX()*aRozmerPolicka.width+aOffsetPolicok.x, aCesta.get(0).getY()*aRozmerPolicka.height+aOffsetPolicok.y);
            for (APolicko pl:aCesta) {
                paGd.setColor(Color.BLACK);
                Point p2 = new Point(pl.getX()*aRozmerPolicka.width+aOffsetPolicok.x, pl.getY()*aRozmerPolicka.height+aOffsetPolicok.y);
                paGd.drawLine(p.x+aRozmerPolicka.width/2, p.y+aRozmerPolicka.height/2, p2.x+aRozmerPolicka.width/2, p2.y+aRozmerPolicka.height/2);
                p = p2;
            }
        }
    }

    
}
