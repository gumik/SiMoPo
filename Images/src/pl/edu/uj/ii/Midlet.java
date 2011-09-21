package pl.edu.uj.ii;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;
import pl.edu.uj.ii.psm.images.model.Config;
import pl.edu.uj.ii.psm.images.controller.GigaController;
import pl.edu.uj.ii.psm.images.model.FileSystemBrowser;
import pl.edu.uj.ii.psm.images.view.ConfigForm;
import pl.edu.uj.ii.psm.images.view.FileSystemBrowserView;

/**
 * @author gumik
 */
public class Midlet extends MIDlet {

    public void startApp() {
        MidletManager.getInstance().setMidlet(this);
        
        Config config = Config.getInstance();
        ConfigForm configForm = new ConfigForm();
        
        FileSystemBrowser fileSystemBrowser = new FileSystemBrowser();
        FileSystemBrowserView fileSystemBrowserView = 
                new FileSystemBrowserView();
        
        GigaController gigaController = new GigaController(this);
        gigaController.setConfig(configForm, config);
        gigaController.setFileSystemBrowser(fileSystemBrowserView, 
                fileSystemBrowser);
        
        Display.getDisplay(this).setCurrent(fileSystemBrowserView);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
