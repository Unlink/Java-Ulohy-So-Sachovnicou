/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sachovnica;

import java.awt.Color;

/**
 *
 * @author Unlink
 */
public enum EFarba {
    Cierna(new Color(44, 62, 80)), Biela(new Color(236, 240, 241));
    
    private final Color aFarba;
    
    EFarba(Color paFarba) {
        aFarba = paFarba;
    }

    public Color getColor() {
        return aFarba;
    }
}
