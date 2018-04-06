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
public class JackSparrow implements IFigurka {

    @Override
    public boolean mozeSaPosunut(PolickoSachovnice paPolicko1, PolickoSachovnice paPolicko2, Sachovnica aSachovnica) {
        return paPolicko2.dajFigurku() == null;
    }

    @Override
    public String meno() {
        return "Jack Sparrow";
    }

    @Override
    public EFarba farba() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
