package pl.edu.uj.ii;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;
import pl.edu.uj.ii.config.Config;
import pl.edu.uj.ii.controller.GigaController;
import pl.edu.uj.ii.gui.ConfigForm;

/**
 * @author gumik
 */
public class Midlet extends MIDlet {

    public void startApp() {
        MidletManager.getInstance().setMidlet(this);
        
        Config config = Config.getInstance();
        ConfigForm configForm = new ConfigForm();
        
        GigaController gigaController = new GigaController();
        gigaController.setConfig(configForm, config);
        
        Display.getDisplay(this).setCurrent(configForm);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
