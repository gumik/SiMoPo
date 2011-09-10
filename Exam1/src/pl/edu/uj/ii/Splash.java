/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author gumik
 */
public class Splash extends Canvas implements Runnable {

    public Splash(SplashTimedOutListener listener, int timeout)
    {
        this.listener = listener;
        this.timeout = timeout;
    }
    
    protected void paint(Graphics grphcs) {
                int width = getWidth();
                int height = getHeight();
        grphcs.drawImage(getImage(), width / 2, height / 2, Graphics.HCENTER | Graphics.VCENTER);
        
        runIfNeccessary();
    }
    
    private Image getImage()
    {
        if (image == null) {
            try {
                image = Image.createImage("/smile.png");
            }
            catch (Exception e) {
                Logger.getInstance().error(e);
            }
        }
        
        return image;
    }
    
    synchronized private void runIfNeccessary() {
        if (!isRunning) {
            isRunning = true;
            new Thread(this).start();
        }
    }

    public void run() {    
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ex) {
            Logger.getInstance().error(ex);
        }
        
        if (listener != null) {
            listener.timedOut();
        }
    }
    
    private Image image = null;
    private SplashTimedOutListener listener;
    private int timeout;
    private boolean isRunning;
}
