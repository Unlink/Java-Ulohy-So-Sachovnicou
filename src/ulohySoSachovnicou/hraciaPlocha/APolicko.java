/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.hraciaPlocha;

/**
 *
 * @author Unlink
 */
public abstract class APolicko {
    
    private final int aX;
    private final int aY;
    
    public APolicko(int x, int y) {
        aX = x;
        aY = y;
    }

    /**
     * Vráti X ovú súradnicu polička
     * Začiatok je 0
     * @return 
     */
    public int getX() {
        return aX;
    }

    /**
     * Vráti Y ovú súradnicu polička
     * Začiatok je 0
     * @return 
     */
    public int getY() {
        return aY;
    }
}
