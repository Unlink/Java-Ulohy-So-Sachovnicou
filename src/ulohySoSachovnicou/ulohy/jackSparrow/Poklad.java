/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.ulohy.jackSparrow;

import ulohySoSachovnicou.sachovnica.EFarba;
import ulohySoSachovnicou.sachovnica.IFigurka;
import ulohySoSachovnicou.sachovnica.PolickoSachovnice;
import ulohySoSachovnicou.sachovnica.Sachovnica;

/**
 *
 * @author Unlink
 */
public class Poklad implements IFigurka {

    @Override
    public boolean mozeSaPosunut(PolickoSachovnice paPolicko1, PolickoSachovnice paPolicko2, Sachovnica aSachovnica) {
        return false;
    }

    @Override
    public String meno() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EFarba farba() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
