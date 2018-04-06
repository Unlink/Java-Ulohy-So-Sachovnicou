/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sachovnica;

/**
 *
 * @author Unlink
 */
public interface IFigurka {

    public boolean mozeSaPosunut(PolickoSachovnice paPolicko1, PolickoSachovnice paPolicko2, Sachovnica aSachovnica);
    public String meno();
    public EFarba farba();
    
}
