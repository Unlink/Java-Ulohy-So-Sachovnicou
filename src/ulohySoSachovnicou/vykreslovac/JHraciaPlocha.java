/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.vykreslovac;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;


/**
 * Swing komponent, do ktorého sa dokáže vykresliť hracia plocha
 * @author Unlink
 */
public class JHraciaPlocha extends JComponent {
    private final IVykreslovac aVykreslovac;

    /**
     * 
     * @param aVykreslovac Vykreslovač, ktorý vykreslí príslušnú hraciu plochu
     */
    public JHraciaPlocha(final IVykreslovac aVykreslovac) {
        this.aVykreslovac = aVykreslovac;
        
        if (aVykreslovac instanceof IListenable) {
            final JHraciaPlocha clousure = this;
            this.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    ((IListenable) aVykreslovac).sendEvent(e);
                    clousure.repaint();
                }
                
            });
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        aVykreslovac.vykresli(this.getSize(), (Graphics2D) g);
    }
}
