/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sach.figurky;

import ulohySoSachovnicou.sachovnica.EFarba;
import ulohySoSachovnicou.sachovnica.IFigurka;

/**
 *
 * @author Unlink
 */
public abstract class ASachovaFigurka implements IFigurka {
    
    private final String aMeno;
    private final EFarba aFarba;

    public ASachovaFigurka(String aMeno, EFarba aFarba) {
        this.aMeno = aMeno;
        this.aFarba = aFarba;
    }
    
    
    @Override
    public String meno() {
        return aMeno;
    }

    @Override
    public EFarba farba() {
        return aFarba;
    }
    
}
