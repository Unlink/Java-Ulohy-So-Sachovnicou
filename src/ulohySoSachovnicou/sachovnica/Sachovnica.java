/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sachovnica;

import ulohySoSachovnicou.hraciaPlocha.AHraciaPlocha;

/**
 * Šachovnica, je hracia plocha, ktorá sa skladá z políčok šachovnice, a obsahuje metody na manipuláciu z figúrkami
 * @author Unlink
 */
public class Sachovnica extends AHraciaPlocha<PolickoSachovnice> {

    public Sachovnica() {
        this(8, 8);
    }
    
    /**
     * 
     * @param paDlzka Horizontalna os (x)
     * @param paSirka Zvislá os (y)
     */
    public Sachovnica(int paDlzka, int paSirka) {
        super(paDlzka, paSirka, PolickoSachovnice.class);
        int k = 0;
        for (int i=0; i<paSirka; i++) {
            for (int j=0; j<paDlzka; j++) {
                k++;
                aPlocha[i][j] = new PolickoSachovnice(j,i,((k%2) == 1) ? EFarba.Biela : EFarba.Cierna);
            }
            k++;
        }
    }
    
    /**
     * Vráti políčko na danej pozícii, parametre vo formáte 
     * A,7
     * H,3
     * @param paX Písmeno 
     * @param paY Cislo
     * @return 
     */
    public PolickoSachovnice getPolicko(char paX, char paY) {
        int x = (int) (paX-'A');
        int y = (int) (('0' + aPlocha.length) - paY);
        return getPolicko(x, y);
    }
    
    /**
     * Zodvihne figúrku z políčka
     * @param paPolicko
     * @return 
     */
    public IFigurka zodvihni(PolickoSachovnice paPolicko) {
        return paPolicko.zodvihni();
    }
    
    /**
     * Položí figurku na poličko
     * @param paPolicko
     * @param paFigurka
     * @return 
     */
    public boolean poloz(PolickoSachovnice paPolicko, IFigurka paFigurka) {
        return paPolicko.polz(paFigurka);
    }
    
    /**
     * Presunie figúrku z políčka 1 do políčka 2
     * A vráti fogúrku z polička 2
     * @param paPolicko1
     * @param paPolicko2
     * @return Figurku z polička 2 alebo null ak sa to podarilo, a figurku z polička 1 ak sa to nepodarilo
     */
    public IFigurka presun(PolickoSachovnice paPolicko1, PolickoSachovnice paPolicko2) {
        if (paPolicko1.dajFigurku() == null) 
            throw new FigurkaPresunException("Na pozícii "+paPolicko1.getX()+","+paPolicko1.getY()+" sa nenachádza žiadna figúrka");
        
        if (paPolicko1 == paPolicko2) {
            return paPolicko1.dajFigurku();
        }
        
        //Môže sa figúrka takto posunúť?
        if (paPolicko1.dajFigurku().mozeSaPosunut(paPolicko1, paPolicko2, this)){
            IFigurka staraFigurka = zodvihni(paPolicko2);
            paPolicko2.polz(paPolicko1.zodvihni());
            return staraFigurka;
        }
        else {
            return paPolicko1.dajFigurku();
        }
    }
    
    /**
     * Vyhádže všetky fugúrky zo šachovnice
     */
    public void vycistiSachovnicu() {
        for (int i=0; i<aPlocha.length; i++)
            for (int j=0; j<aPlocha[0].length; j++)
                this.zodvihni(this.getPolicko(j, i));
    }
}
