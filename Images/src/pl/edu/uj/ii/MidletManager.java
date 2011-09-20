/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author gumik
 */
public class MidletManager {
    private static MidletManager instance;
    private Display display;
    private Midlet midlet;
    
    private MidletManager() {
        
    }
    
    public static MidletManager getInstance() {
        if (instance == null) {
            instance = new MidletManager();
        }
        
        return instance;
    }
    
    public void setMidlet(Midlet midlet) {
        this.midlet = midlet;
        this.display = Display.getDisplay(midlet);
    }
    
    public void exitApp() {
        midlet.destroyApp(false);
        midlet.notifyDestroyed();
    }
    
    public void showAlert(Displayable displayable, Alert alert) {
        display.setCurrent(alert, displayable);
    }
}
