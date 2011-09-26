package pl.edu.uj.ii;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;
import pl.edu.uj.ii.psm.images.model.Config;
import pl.edu.uj.ii.psm.images.controller.GigaController;
import pl.edu.uj.ii.psm.images.model.Browser;
import pl.edu.uj.ii.psm.images.model.PhotoThread;
import pl.edu.uj.ii.psm.images.view.ConfigForm;
import pl.edu.uj.ii.psm.images.view.BrowserView;
import pl.edu.uj.ii.psm.images.view.PhotoView;

/**
 * @author gumik
 */
public class Midlet extends MIDlet {

    public void startApp() {
        MidletManager.getInstance().setMidlet(this);
        
        Config config = Config.getInstance();
        ConfigForm configForm = new ConfigForm();
        
        Browser fileSystemBrowser = new Browser();
        BrowserView fileSystemBrowserView = 
                new BrowserView();
        
        PhotoThread photoThread = new PhotoThread();
        PhotoView photoView = new PhotoView();
        
        GigaController gigaController = new GigaController(this);
        gigaController.setConfig(configForm, config);
        gigaController.setBrowser(fileSystemBrowserView, 
                fileSystemBrowser);
        gigaController.setPhoto(photoView, photoThread);
        
        Display.getDisplay(this).setCurrent(photoView);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
