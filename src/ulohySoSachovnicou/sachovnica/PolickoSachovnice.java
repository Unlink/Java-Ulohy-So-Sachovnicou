/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulohySoSachovnicou.sachovnica;

import ulohySoSachovnicou.hraciaPlocha.APolicko;

/**
 *
 * @author Unlink
 */
public class PolickoSachovnice extends APolicko {

    private IFigurka aFigurka;
    private final EFarba aFarba;
    
    PolickoSachovnice(int x, int y, EFarba paFarba) {
        super(x, y);
        aFigurka = null;
        aFarba = paFarba;
    }

    /**
     * Package private - môže manipulovať len pomocou šachovnice
     * 
     * Položí figúrku, ak je to možné
     * @param paFigurka
     * @return 
     */
    boolean polz(IFigurka paFigurka) {
        if (aFigurka == null) {
            aFigurka = paFigurka;
            return true;
        }
        return false;
    }

    public IFigurka dajFigurku() {
        return aFigurka;
    }
    
    /**
     * Package private - môže manipulovať len pomocou šachovnice
     * 
     * Zodvihne figúrku, ak je to možné
     * @return 
     */
    IFigurka zodvihni() {
        if (aFigurka != null) {
            IFigurka f = aFigurka;
            aFigurka = null;
            return f;
        }
        return null;
    }

    public EFarba getFarba() {
        return aFarba;
    } 
}
