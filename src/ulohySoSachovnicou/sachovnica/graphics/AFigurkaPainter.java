/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ulohySoSachovnicou.sachovnica.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import ulohySoSachovnicou.sachovnica.IFigurka;
import javax.imageio.ImageIO;

/**
 * Pomocná trieda na vykreslovanie figúrok
 * @author Unlink
 */
public abstract class AFigurkaPainter implements IFigurkaDraw {
    
    private final List<Method> aMetody;
    protected String aRecources_Path;
    protected String aExtension;

    public AFigurkaPainter(String paRecources_Path) {
        this(paRecources_Path, "png");
    }
    
    public AFigurkaPainter(String paRecources_Path, String paExtension) {
        aRecources_Path = paRecources_Path;
        aExtension = paExtension;
        
        //Reflect object
        aMetody = new ArrayList<>();
        for (Method m:getClass().getDeclaredMethods()){
            if ("paint".equals(m.getName())) {
                aMetody.add(m);
                m.setAccessible(true);
            }
        }
    }
    
    @Override
    public void draw(IFigurka paFigurka, BufferedImage paImg) {
        if (paFigurka == null)
            return;
        for (Method m:aMetody){
            Class cls = m.getParameterTypes()[0];
            if (cls.isInstance(paFigurka)){
                 try {
                    m.invoke(this, paFigurka, paImg);
                    return;
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    drawDeflaut(paImg);
                    return;
                }
            }
        }
        drawDeflaut(paImg);
    }

    /**
     * Pokial nenájde metódu na vykreslenie konkretnej figúrky
     * Nakresli deflaut obrázok
     * @param paImg 
     */
    protected void drawDeflaut(BufferedImage paImg) {
        Graphics2D g2 = (Graphics2D) paImg.getGraphics();
        g2.setColor(Color.RED);
        int r = (int) (paImg.getWidth()*0.4);
        g2.fillOval(r, r, paImg.getWidth()-2*r, paImg.getHeight()-2*r);
    }
    
    
    protected void drawImage(BufferedImage paImg, String paFigurkaImg) throws IOException {
        Graphics2D g2 = (Graphics2D) paImg.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        try {
            BufferedImage im = ImageIO.read(getClass().getResource(aRecources_Path+paFigurkaImg+"."+aExtension));
            g2.drawImage(im, 0, 0, paImg.getWidth(), paImg.getHeight(), null);
        }
        //Aj keď nenánjde resource, aj keď sa ho nepodarí načítať vrátime IOE
        catch (IOException | IllegalArgumentException ex){
            throw new IOException(ex.getMessage());
        }
    }
}
