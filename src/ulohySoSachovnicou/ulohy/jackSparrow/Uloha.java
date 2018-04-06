/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.ulohy.jackSparrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import ulohySoSachovnicou.IUloha;
import ulohySoSachovnicou.sachovnica.IFigurka;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;
import ulohySoSachovnicou.vykreslovac.JHraciaPlocha;
import javax.swing.JComponent;

/**
 * Úloha: nájdi cestu z bodu A do bodu B, tak, aby si prešiel všetky políčka na šachovnici
 * @author Unlink
 */
public class Uloha implements IUloha {
    private Stack<PolickoSachovnice> cesta;
    private Sachovnica ostrov;
    private JHraciaPlocha platno;
    private List<PolickoSachovnice> cesta2;
    private PolickoSachovnice start;
    private PolickoSachovnice ciel;
    
    private int[][] aMapaPristupu;
    private int aCounter;
    
    public Uloha() {
        cesta = new Stack<>();
        cesta2 = new ArrayList<>();
        ostrov = new Sachovnica(8, 8);
        platno = new JHraciaPlocha(new OstrovVykreslovac(ostrov, new FigurkyPainter(), cesta2));
        
        start = ostrov.getPolicko(5, 2);
        ciel = ostrov.getPolicko(3, 5);
        
        //Umiestnime jacka
        ostrov.poloz(start, new JackSparrow());
        //Umiestnime poklad
        ostrov.poloz(ciel, new Poklad());
        
        cesta.push(start);
        cesta2.add(start);
        chod();
    }

    private boolean chod() {
        /*platno.repaint();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }*/
        PolickoSachovnice jack = cesta.lastElement();
        
        /*for (PolickoSachovnice p:dajMoznostiPosunu(jack)){
            if (dajMoznostiPosunu(p).size() < 1)
                return false;
        }*/
        
        /*if (!jeToDobryTah())
            return false;*/
        
        List<PolickoSachovnice> moznosti = dajMoznostiPosunu(jack);
        
        if (moznosti.size() > 0){
            for (PolickoSachovnice dalsie:moznosti){
                cesta.push(dalsie);
                ostrov.presun(jack, dalsie);
                ostrov.poloz(jack, new Znacka());
                if (chod()){
                    return true;
                }
                else {
                    ostrov.zodvihni(jack);
                    ostrov.presun(dalsie, jack);
                    cesta.pop();
                }
            }
            return false;
        }
        else if (cesta.size() == ostrov.getDlzka()*ostrov.getSirka()-1) {
            for (PolickoSachovnice p:dajSusediacePolicka(jack)){
                if (p.dajFigurku() instanceof Poklad)
                    return true;
            }
            return false;
        }
        else {
            
            return false;
        }
    }
    
    private List<PolickoSachovnice> dajSusediacePolicka(PolickoSachovnice p) {
        List<PolickoSachovnice> a = new ArrayList<>(4);
        //Hore
        if (p.getY() > 0){
            a.add(ostrov.getPolicko(p.getX(), p.getY()-1));
        }
        //Dolava
        if (p.getX() > 0){
            a.add(ostrov.getPolicko(p.getX()-1, p.getY()));
        }
        //Dole
        if (p.getY() < ostrov.getSirka()-1){
            a.add(ostrov.getPolicko(p.getX(), p.getY()+1));
        }
        //Doprava
        if (p.getX() < ostrov.getDlzka()-1){
            a.add(ostrov.getPolicko(p.getX()+1, p.getY()));
        }
        return a;
    }
    
    private List<PolickoSachovnice> dajMoznostiPosunu(PolickoSachovnice jack) {
        return dajMoznostiPosunu(jack, false);
    }
    
    private List<PolickoSachovnice> dajMoznostiPosunu(PolickoSachovnice jack, boolean ajJacka) {
        List<PolickoSachovnice> a = new ArrayList<>(4);
        for (PolickoSachovnice p:dajSusediacePolicka(jack)){
            if (p.dajFigurku() == null || (ajJacka && p.dajFigurku() instanceof JackSparrow))
                a.add(p);
        }
        return a;
    }

    private boolean jeToDobryTah() {
        class Farba {
            public Farba(int c) {
                this.c = c;
            }
            int c = 0;
        }
        
        Farba[][] a = new Farba[ostrov.getDlzka()][ostrov.getSirka()];
        
        int oblasti = 0;
        int x = 1;
        for (int i=0; i<ostrov.getDlzka(); i++){
            for (int j=0; j<ostrov.getSirka(); j++){
                PolickoSachovnice p = ostrov.getPolicko(i, j);
                if (p.dajFigurku() == null || p.dajFigurku() instanceof Poklad) {
                    boolean isFarbene = false;
                    List<PolickoSachovnice> mp = dajMoznostiPosunu(p, true);
                    if (mp.size() == 1)
                        return false;
                    for (PolickoSachovnice n:mp){
                        if (a[n.getX()][n.getY()] != null) {
                            if (a[p.getX()][p.getY()] == null) {
                                a[p.getX()][p.getY()] = a[n.getX()][n.getY()];
                            }
                            else if (a[p.getX()][p.getY()].c != a[n.getX()][n.getY()].c){
                                a[p.getX()][p.getY()].c = a[n.getX()][n.getY()].c;
                                oblasti--;
                            }
                            isFarbene = true;
                        }
                    }
                    if (!isFarbene) {
                        a[p.getX()][p.getY()] = new Farba(x++);
                        oblasti++;
                    }
                }
            }
        }
        return oblasti < 2;
    }
    
    @Override
    public void run() { 
        //chod();
        IFigurka j = ostrov.zodvihni(cesta.lastElement());
        ostrov.zodvihni(start);
        ostrov.poloz(start, j);
        platno.repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        //cesta.remove(0);
        ostrov.zodvihni(start);
        PolickoSachovnice px = start;
        cesta.add(ciel);
        for (PolickoSachovnice p:cesta) {
            IFigurka m = ostrov.zodvihni(p);
            ostrov.poloz(p, j);
            cesta2.add(px);
            px = p;
            platno.repaint();
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
            }
            ostrov.zodvihni(p);
            ostrov.poloz(p, m);
        }
        platno.repaint();
    }

    @Override
    public JComponent dajPlatno() {
        return platno;
    }
}
