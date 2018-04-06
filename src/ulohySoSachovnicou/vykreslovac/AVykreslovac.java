/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.vykreslovac;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import ulohySoSachovnicou.hraciaPlocha.AHraciaPlocha;
import ulohySoSachovnicou.hraciaPlocha.APolicko;

/**
 *
 * @author Unlink
 * @param <T> Typ hracej plochy
 * @param <E> Typ policok hracej plochy
 */
public abstract class AVykreslovac<T extends AHraciaPlocha<? extends APolicko>, E extends APolicko> implements IVykreslovac, IListenable {
    protected final T aHraciaPlocha;
    protected final Dimension aCanvasSize;
    protected final Dimension aRozmerPolicka;
    protected final Dimension aRozmerPlochyPolicok;
    protected final Point aOffsetPolicok;
    
    private final List<IHraciaPlochaListener> aListeners;
    
    private BufferedImage aPozadieCache;

    public AVykreslovac(T paHraciaPlocha) {
        this.aHraciaPlocha = paHraciaPlocha;
        this.aCanvasSize = new Dimension();
        this.aRozmerPolicka = new Dimension();
        this.aRozmerPlochyPolicok = new Dimension();
        this.aOffsetPolicok = new Point();
        this.aPozadieCache = null;
        
        this.aListeners = new ArrayList<>();
    }

    @Override
    public void vykresli(Dimension paSize, Graphics2D paGd) {
        paGd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paGd.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        //Nastavíme velkost platna
        if (!paSize.equals(aCanvasSize)) {
            aCanvasSize.setSize(paSize);
            spocitajRozmery();
            aPozadieCache = new BufferedImage(aCanvasSize.width, aCanvasSize.height, BufferedImage.TYPE_INT_ARGB);
            vykresliPozadie(aPozadieCache.createGraphics());
        }
        else if (aPozadieCache == null) {
            //Generuje pozadie
            vykresliPozadie(aPozadieCache.createGraphics());
        }
        paGd.drawImage(aPozadieCache, 0, 0, null);
        
        for (int j=0; j<aHraciaPlocha.getDlzka(); j++) {
            for (int i=0; i<aHraciaPlocha.getSirka(); i++) {
                Point pozicia = new Point();
                pozicia.x = aOffsetPolicok.x + aRozmerPolicka.width*j;
                pozicia.y = aOffsetPolicok.y + aRozmerPolicka.height*i;
                vykresliPolicko(paGd, pozicia, (E) aHraciaPlocha.getPolicko(j, i));
            }
        }
    }
    
    protected void vykresliPozadie(Graphics2D paGd) {
        paGd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paGd.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        paGd.setColor(new Color(52, 152, 219));
        paGd.fillRect(0, 0, aCanvasSize.width, aCanvasSize.height);
    }
    
    /**
     * Spocita aRozmerPolicka, aRozmerPlochyPolicok, aOffsetPolicok
     */
    protected void spocitajRozmery() {
        aRozmerPlochyPolicok.setSize(aCanvasSize);
        double hrana = Math.min(aRozmerPlochyPolicok.getWidth()/aHraciaPlocha.getDlzka(), aRozmerPlochyPolicok.getHeight()/aHraciaPlocha.getSirka());
        aRozmerPolicka.setSize(hrana, hrana);
        aOffsetPolicok.setLocation(0, 0);
    }

    protected abstract void vykresliPolicko(Graphics2D paGd, Point paPozicia, E paPolicko);

    @Override
    public final void sendEvent(MouseEvent e) {
        //Ked nema kto pocuvať tak zahadzujeme
        if (aListeners.isEmpty())
            return;
        //Len ak sme v oblasti s poličkami, inak zahadzujeme event
        Point clckd = e.getPoint();
        clckd.x -= aOffsetPolicok.x;
        clckd.y -= aOffsetPolicok.y;
        if (clckd.x > 0 
                && clckd.y > 0
                && clckd.x < (aRozmerPolicka.width*aHraciaPlocha.getDlzka())
                && clckd.x < (aRozmerPolicka.height*aHraciaPlocha.getSirka())
                ) {
            int x,y;
            x = clckd.x / aRozmerPolicka.width;
            y = clckd.y / aRozmerPolicka.height;
            
            E policko = (E) aHraciaPlocha.getPolicko(x, y);
            if (policko != null){
                for (IHraciaPlochaListener l:aListeners){
                    l.actionPerformed(policko);
                }
            }
        }
    }
}
