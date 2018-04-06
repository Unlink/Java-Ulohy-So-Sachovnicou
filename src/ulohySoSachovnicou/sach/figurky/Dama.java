/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sach.figurky;

import ulohySoSachovnicou.sachovnica.EFarba;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;

/**
 *
 * @author Unlink
 */
public class Dama extends ASachovaFigurka {

    public Dama() {
        this(EFarba.Cierna);
    }
    
    public Dama(EFarba aFarba) {
        super("Dáma", aFarba);
    }

    /**
     * Treba implementovať, pre moje potreby to nieje potrebné
     * @param paPolicko1
     * @param paPolicko2
     * @param aSachovnica
     * @return 
     */
    @Override
    public boolean mozeSaPosunut(PolickoSachovnice paPolicko1, PolickoSachovnice paPolicko2, Sachovnica aSachovnica) {
        return true;
        
    }

    
    
    
}
