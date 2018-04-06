/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sachovnica.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;

/**
 *
 * @author Unlink
 */
public class SachovnicaVykreslovac extends AVykreslovacFigurka {

    private int aSirkaRamu;

    public SachovnicaVykreslovac(Sachovnica paSachovnica, IFigurkaDraw paFigurkaDrawer) {
        super(paSachovnica, paFigurkaDrawer);
        this.aSirkaRamu = 0;
    }
    
    

    @Override
    protected void vykresliPolicko(Graphics2D paGd, Point paPozicia, PolickoSachovnice paPolicko) {
        paGd.setColor(paPolicko.getFarba().getColor());
        paGd.fillRect(paPozicia.x, paPozicia.y, aRozmerPolicka.width, aRozmerPolicka.height);
        vykresliFigurku(paGd, paPozicia.x, paPozicia.y, paPolicko.dajFigurku());
    }
    
    /**
     * Spocita aRozmerPolicka, aRozmerPlochyPolicok, aOffsetPolicok
     */
    @Override
    protected void spocitajRozmery() {
        aSirkaRamu = (Math.min(aCanvasSize.width, aCanvasSize.height) / (aHraciaPlocha.getDlzka()*2));
        aRozmerPlochyPolicok.setSize(aCanvasSize.width-aSirkaRamu-6, aCanvasSize.height-aSirkaRamu-6);
        double hrana = Math.min(aRozmerPlochyPolicok.getWidth()/aHraciaPlocha.getDlzka(), aRozmerPlochyPolicok.getHeight()/aHraciaPlocha.getSirka());
        aRozmerPolicka.setSize((int)hrana, (int)hrana);
        aOffsetPolicok.setLocation(aSirkaRamu+6, 6);
    }

    @Override
    protected void vykresliPozadie(Graphics2D paGd) {
        super.vykresliPozadie(paGd);
        vykresliLegendu(paGd);
    }
    
    private void vykresliLegendu(Graphics2D paGd) {
        paGd.setColor(Color.BLACK);
        paGd.setStroke(new BasicStroke(2));
        paGd.drawRect(aSirkaRamu+3, 3, aRozmerPolicka.width*aHraciaPlocha.getDlzka() + 5, aRozmerPolicka.height*aHraciaPlocha.getSirka()+5);
    
        Font font = new Font("Tahoma", Font.BOLD, (int) (aRozmerPolicka.height*0.35));
        paGd.setFont(font);
        for (int i=0; i<aHraciaPlocha.getSirka(); i++) {
            paGd.drawString(""+(aHraciaPlocha.getSirka()-i), 10, aOffsetPolicok.y+i*aRozmerPolicka.height+aRozmerPolicka.height*0.7f);
        }
        
        for (int i=0; i<aHraciaPlocha.getDlzka(); i++) {
            paGd.drawString(""+((char) ('A'+i)), aOffsetPolicok.x+i*aRozmerPolicka.width+aRozmerPolicka.width*0.3f, aOffsetPolicok.y+aHraciaPlocha.getSirka()*aRozmerPolicka.height+(aRozmerPolicka.height*0.35f));
        }
    }
}
