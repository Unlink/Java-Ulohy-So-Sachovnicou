/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.vykreslovac;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author Unlink
 */
public interface IVykreslovac {
    /**
     * Vola sa vždy pri prekleslovaní plátna, a vykreslí hraciu plochu
     * @param paSize rozmer komponentu do krorého kreslíme
     * @param paGd 
     */
    public void vykresli(Dimension paSize, Graphics2D paGd);
}
