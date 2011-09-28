package pl.edu.uj.ii;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import pl.edu.uj.ii.psm.images.model.ImageComparer;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;
import pl.edu.uj.ii.psm.images.model.Config;
import pl.edu.uj.ii.psm.images.controller.GigaController;
import pl.edu.uj.ii.psm.images.model.Browser;
import pl.edu.uj.ii.psm.images.model.PhotoSaver;
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
        final PhotoView photoView = new PhotoView();
        ImageComparer imageComparer = new ImageComparer(0);
        PhotoSaver photoSaver = new PhotoSaver(imageComparer);
        
        GigaController gigaController = new GigaController(this);
        gigaController.setConfig(configForm, config);
        gigaController.setBrowser(fileSystemBrowserView, 
                fileSystemBrowser);
        gigaController.setPhoto(photoView, photoThread, photoSaver, 
                imageComparer);
        
        Display.getDisplay(this).setCurrent(photoView);
        
        final Midlet midlet = this;
        Display.getDisplay(this).setCurrent(DebugScreen.getInstance());
        DebugScreen.getInstance().setCommandListener(new CommandListener() {

            public void commandAction(Command cmnd, Displayable dsplbl) {
                Display.getDisplay(midlet).setCurrent(photoView);
            }
        });
        DebugScreen.getInstance().setMsg("debug started");
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
