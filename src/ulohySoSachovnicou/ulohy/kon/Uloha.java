/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.ulohy.kon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import ulohySoSachovnicou.IUloha;
import ulohySoSachovnicou.hraciaPlocha.APolicko;
import ulohySoSachovnicou.ulohy.jackSparrow.Znacka;
import ulohySoSachovnicou.sach.figurky.Jazdec;
import ulohySoSachovnicou.sach.figurky.SachovnicoveFigurky2D;
import ulohySoSachovnicou.sachovnica.EFarba;
import ulohySoSachovnicou.sachovnica.IFigurka;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;
import ulohySoSachovnicou.vykreslovac.JHraciaPlocha;
import javax.swing.JComponent;

/**
 * Úloaha: prejdite s koňom po šachovnici tak, aby ste prešli každé jedno políčko, a na žiadne ste nestúpili 2x
 * @author Unlink
 */
public class Uloha implements IUloha {

    private Sachovnica aSachovnica;
    private JHraciaPlocha aHraciaPlocha;
    
    private PolickoSachovnice aStart;
    
    private Stack<PolickoSachovnice> aCesta;
    private List<APolicko> aCesta2;
    
    public Uloha() {
        aSachovnica = new Sachovnica(24, 24);
        aCesta = new Stack();
        aCesta2 = new Stack();
        aHraciaPlocha = new JHraciaPlocha(new VykreslovacCestyNaSachovnici(aSachovnica, new SachovnicoveFigurky2D(), aCesta2));
        
        aStart = aSachovnica.getPolicko(0, 0);
        aSachovnica.poloz(aStart, new Jazdec(EFarba.Cierna));
        long milis = System.currentTimeMillis();
        ries();
        System.out.println("Vygenerované za:"+(System.currentTimeMillis()-milis)+"ms");
    }
    
    private void ries() {
        Set<PolickoSachovnice>[][] moznostiPohybu = new Set[aSachovnica.getDlzka()][aSachovnica.getSirka()];
        for (int i=0; i<moznostiPohybu.length; i++)
            for (int j=0; j<moznostiPohybu[0].length; j++)
                moznostiPohybu[i][j] = generujMoznostiPohybu(aSachovnica.getPolicko(i, j));
        
        PolickoSachovnice aktual = aStart;
        int kk=0;
        while (true) {
            if (aCesta.size() == (aSachovnica.getDlzka()*aSachovnica.getSirka() -1)){
                System.out.println("Najdena cesta");
                break;
            }
            if (aCesta.isEmpty() && moznostiPohybu[aktual.getX()][aktual.getY()].isEmpty()){
                System.out.println("Nenajdena cesta");
                break;
            }
            if (moznostiPohybu[aktual.getX()][aktual.getY()].isEmpty()) {
                moznostiPohybu[aktual.getX()][aktual.getY()] = generujMoznostiPohybu(aktual);
                IFigurka f = aSachovnica.zodvihni(aktual);
                aCesta.pop();
                if (aCesta.isEmpty())
                    aktual = aStart;
                else 
                    aktual = aCesta.lastElement();
                aSachovnica.zodvihni(aktual);
                aSachovnica.poloz(aktual, f);
            }
            else {
                PolickoSachovnice dalsie = null;
                int min = Integer.MAX_VALUE;
                for (PolickoSachovnice p:moznostiPohybu[aktual.getX()][aktual.getY()]){
                    if (p.dajFigurku() == null) {
                        int m = pocetMoznosti(moznostiPohybu, p);
                        if (m < min){
                            min = m;
                            dalsie = p;
                        }
                    }
                }
                if (dalsie == null) {
                    moznostiPohybu[aktual.getX()][aktual.getY()] = generujMoznostiPohybu(aktual);
                    IFigurka f = aSachovnica.zodvihni(aktual);
                    aCesta.pop();
                    if (aCesta.isEmpty())
                        aktual = aStart;
                    else 
                        aktual = aCesta.lastElement();
                    aSachovnica.zodvihni(aktual);
                    aSachovnica.poloz(aktual, f);
                }
                else {
                    moznostiPohybu[aktual.getX()][aktual.getY()].remove(dalsie);
                    aCesta.push(dalsie);
                    aSachovnica.presun(aktual, dalsie);
                    aSachovnica.poloz(aktual, new Znacka());
                    aktual = dalsie;
                }
            }
            
        }
        
    }
    
    private int pocetMoznosti(Set<PolickoSachovnice>[][] moznostiPohybu, PolickoSachovnice aktual){
        int sum = 0;
        for (PolickoSachovnice p:moznostiPohybu[aktual.getX()][aktual.getY()])
            if (p.dajFigurku() == null)
                sum++;
        return sum;
    }
    
    private Set<PolickoSachovnice> generujMoznostiPohybu(PolickoSachovnice p) {
        Set<PolickoSachovnice> mp = new HashSet<>();
        mp.add(aSachovnica.getPolicko(p.getX()-1, p.getY()-2));
        mp.add(aSachovnica.getPolicko(p.getX()-2, p.getY()-1));
        mp.add(aSachovnica.getPolicko(p.getX()-2, p.getY()+1));
        mp.add(aSachovnica.getPolicko(p.getX()-1, p.getY()+2));
        mp.add(aSachovnica.getPolicko(p.getX()+1, p.getY()+2));
        mp.add(aSachovnica.getPolicko(p.getX()+2, p.getY()+1));
        mp.add(aSachovnica.getPolicko(p.getX()+2, p.getY()-1));
        mp.add(aSachovnica.getPolicko(p.getX()+1, p.getY()-2));
        mp.remove(null);
        return mp;
    }
    
    @Override
    public void run() {
        //Img saver
        /*VykreslovacCestyNaSachovnici v = new VykreslovacCestyNaSachovnici(aSachovnica, new SachovnicoveFigurky2D(), aCesta2);
        BufferedImage bi = new BufferedImage(aSachovnica.getDlzka()*50, aSachovnica.getSirka()*50, BufferedImage.TYPE_INT_ARGB);
        */
        
        
        //Vyčistime sachovnicu
        aSachovnica.vycistiSachovnicu();
        aSachovnica.poloz(aStart, new Jazdec(EFarba.Cierna));
        PolickoSachovnice akt = aStart;
        aCesta2.add(aStart);
        if (!aCesta.isEmpty())
            aCesta2.add(aCesta.firstElement());
            int ix = 0;
            for (PolickoSachovnice p:aCesta) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
            }
                aSachovnica.presun(akt, p);
                aSachovnica.poloz(akt, new Znacka());
                aCesta2.add(p);
                akt = p;
                aHraciaPlocha.repaint();
                
                
                /*v.vykresli(new Dimension(bi.getWidth(), bi.getHeight()), bi.createGraphics());
                File outputfile = new File("C:\\Users\\Unlink\\Desktop\\kon-sach\\"+(++ix)+".png");
                try {
                ImageIO.write(bi, "png", outputfile);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }*/
            }
    }

    @Override
    public JComponent dajPlatno() {
        return aHraciaPlocha;
    }
}
