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

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author Unlink
 * @param <T>
 */
public abstract class AHraciaPlocha<T extends APolicko> {

    /**
     * Pole, ktoré reprezentuje plochu
     */
    protected T[][] aPlocha;

    /**
     * 
     * @param paSirka Zvislá os (y)
     * @param paDlzka Horizontalna os (x)
     * @param paClass Trieda poličok
     */
    public AHraciaPlocha(int paDlzka, int paSirka, Class<T> paClass) {
        aPlocha = (T[][]) Array.newInstance(paClass, paSirka, paDlzka);
    }
    
    /**
     * Vráti políčko na zadaných suradiciach
     * @param x
     * @param y
     * @return 
     */
    public T getPolicko(int x, int y) {
        try {
            return aPlocha[y][x];
        }
        catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    /**
     * Vráti políčka v riadku, v ktorom je zvolené políčko
     * @param paPolicko
     * @return 
     */
    public Iterable<T> dajRiadok(T paPolicko) {
        ArrayList<T> list = new ArrayList<>();
        for (int i=0; i<aPlocha[0].length; i++){
            list.add(aPlocha[paPolicko.getY()][i]);
        }
        return list;
    }
    
    /**
     * Vráti políčka vo stlpci, v ktorom je zvolené políčko
     * @param paPolicko
     * @return 
     */
    public Iterable<T> dajStlpec(T paPolicko) {
        ArrayList<T> list = new ArrayList<>();
        for (int i=0; i<aPlocha.length; i++){
            list.add(aPlocha[i][paPolicko.getX()]);
        }
        return list;
    }
    
    /**
     * Vráti políčka v diagonále, zadaného polička z ľava do prava
     * @param paPolicko
     * @return 
     */
    public Iterable<T> dajDiagonaluPravu(T paPolicko) {
        ArrayList<T> list = new ArrayList<>();
        int start_x = Math.max(paPolicko.getX()-paPolicko.getY(), 0); 
        int start_y = Math.max(paPolicko.getY()-paPolicko.getX(), 0);
        for (int i=start_y; i<aPlocha.length; i++) {
            if ((start_x + (i - start_y)) >= aPlocha[0].length)
                break;
            list.add(aPlocha[i][(start_x + (i - start_y))]);
        }
        return list;
    }
    
    /**
     * Vráti políčka v diagonále, zadaného polička z prava do lava
     * @param paPolicko
     * @return 
     */
    public Iterable<T> dajDiagonaluLavu(T paPolicko) {
        ArrayList<T> list = new ArrayList<>();
        int start_x = Math.min(paPolicko.getY() + paPolicko.getX(), aPlocha[0].length - 1);
        int start_y = Math.max(0, (paPolicko.getY() - ((aPlocha[0].length - 1) - paPolicko.getX())));
        
        for (int i=start_y; i<aPlocha.length; i++) {
            if ((start_x - (i-start_y)) < 0)
                break;
            list.add(aPlocha[i][start_x - (i-start_y)]);
        }
        return list;
    }
    
    /**
     * Y psylonova os
     * @return 
     */
    public int getSirka() {
        return aPlocha.length;
    }
    
    /**
     * Xová os
     * @return 
     */
    public int getDlzka() {
        return aPlocha[0].length;
    }
}
