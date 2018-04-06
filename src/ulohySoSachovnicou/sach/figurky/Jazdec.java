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
public class Jazdec extends ASachovaFigurka {

    public Jazdec(EFarba aFarba) {
        super("Jazdec", aFarba);
    }

    @Override
    public boolean mozeSaPosunut(PolickoSachovnice paPolicko1, PolickoSachovnice paPolicko2, Sachovnica aSachovnica) {
        return paPolicko2.dajFigurku() == null;
    }
    
}
