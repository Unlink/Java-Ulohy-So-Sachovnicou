/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.ulohy.osemDam;

import java.util.Stack;
import ulohySoSachovnicou.IUloha;
import ulohySoSachovnicou.sach.figurky.Dama;
import ulohySoSachovnicou.sach.figurky.SachovnicoveFigurky2D;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;
import ulohySoSachovnicou.sachovnica.graphics.SachovnicaVykreslovac;
import ulohySoSachovnicou.vykreslovac.JHraciaPlocha;
import javax.swing.JComponent;

/**
 * Problém: 
 * Rozmiestnite na sachovnicu 8 dám, tak aby sa neohrozovali
 * @author Unlink
 */
public class Uloha implements IUloha {
    private final Sachovnica aSachovnica;
    private final JHraciaPlocha aHraciaPlocha;
    
    private final Stack<PolickoSachovnice> aDamy;

    public Uloha() {
        aSachovnica = new Sachovnica();
        aHraciaPlocha = new JHraciaPlocha(new SachovnicaVykreslovac(aSachovnica, new SachovnicoveFigurky2D()));
        aDamy = new Stack<>();
    }

    @Override
    public void run() {
        polozDamu();
        prekresli();
    }
    
    private boolean polozDamu() {
        if (aDamy.size() == 8)
            return true;
        
        for (int i=0; i<aSachovnica.getDlzka(); i++)
                for (int j=0; j<aSachovnica.getSirka(); j++)
                    if (mozemPolozit(aSachovnica.getPolicko(i, j))){
                        aSachovnica.poloz(aSachovnica.getPolicko(i, j), new Dama());
                        //prekresli();
                        
                        aDamy.push(aSachovnica.getPolicko(i, j));
                        if (!polozDamu()) {
                            aDamy.pop();
                            aSachovnica.zodvihni(aSachovnica.getPolicko(i, j));
                            //prekresli();
                        }
                        else {
                            return true;
                        }
                    }
        return false;
    }
    
    private void prekresli() {
        aHraciaPlocha.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
        }
    }
    
    private boolean mozemPolozit(PolickoSachovnice paPolicko) {
        for(PolickoSachovnice p:aSachovnica.dajRiadok(paPolicko))
            if (p.dajFigurku() != null)
                return false;
        for(PolickoSachovnice p:aSachovnica.dajStlpec(paPolicko))
            if (p.dajFigurku() != null)
                return false;
        for(PolickoSachovnice p:aSachovnica.dajDiagonaluLavu(paPolicko))
            if (p.dajFigurku() != null)
                return false;
        for(PolickoSachovnice p:aSachovnica.dajDiagonaluPravu(paPolicko))
            if (p.dajFigurku() != null)
                return false;
        return true;
    }

    @Override
    public JComponent dajPlatno() {
        return aHraciaPlocha;
    }
}
