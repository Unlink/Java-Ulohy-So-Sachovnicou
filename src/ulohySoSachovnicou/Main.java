/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Unlink
 */
public class Main {
    public static void main(String[] args) {
        IUloha uloha = new ulohySoSachovnicou.ulohy.kon.Uloha();
        //IUloha uloha = new ulohySoSachovnicou.ulohy.jackSparrow.Uloha();
        //IUloha uloha = new ulohySoSachovnicou.ulohy.osemDam.Uloha();
        JFrame j = new JFrame("Hra");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.add(uloha.dajPlatno());
        j.setMinimumSize(new Dimension(500,500));
        j.setVisible(true);
        uloha.run();
    }
}
