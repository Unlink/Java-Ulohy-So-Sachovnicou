/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.vykreslovac;

import java.awt.event.MouseEvent;

/**
 * Znackovací interfejs
 * @author Unlink
 */
public interface IListenable {
    public void sendEvent(MouseEvent e);
}
