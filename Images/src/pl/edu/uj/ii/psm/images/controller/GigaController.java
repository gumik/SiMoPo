/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.controller;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.RecordStoreFullException;
import pl.edu.uj.ii.Midlet;
import pl.edu.uj.ii.psm.images.model.Config;
import pl.edu.uj.ii.psm.images.model.FileSystemBrowser;
import pl.edu.uj.ii.psm.images.model.FileSystemBrowserListener;
import pl.edu.uj.ii.psm.images.view.ConfigForm;
import pl.edu.uj.ii.psm.images.view.FileSystemBrowserView;

/**
 *
 * @author gumik
 */
public class GigaController implements CommandListener {

    private Config configModel;
    private ConfigForm configForm;
    private FileSystemBrowser fileSystemBrowser;
    private FileSystemBrowserView fileSystemBrowserView;
    private Midlet midlet;
    
    public GigaController(Midlet midlet) {        
        this.midlet = midlet;
    }
    
    public void setConfig(ConfigForm form, Config config) {
        this.configForm = form;
        form.setCommandListener(this);
        this.configModel = config;
        
        try {
            form.setDelay(config.getDelay());
            form.setSimilarityFactor(config.getSimilarityFactor());
        } catch (RecordStoreFullException ex) {
            // TODO
            ex.printStackTrace();
        }
    }
    
    public void configOkPressed() {
        try {
            configModel.setDelay(configForm.getDelay());
            configModel.setSimilarityFactor(configForm.getSimilarityFactor());
        } catch (IllegalArgumentException e) {            
            Alert alert = new Alert("Error while saving config", e.getMessage(),
                    null, AlertType.ERROR);
            showAlert(alert, configForm);
            return;
        } catch (RecordStoreFullException e) {
            // TODO
            e.printStackTrace();
        }
        
        // TODO change
        exitApp();
    }
    
    public void configCancelPressed() {
        // TODO change
        exitApp();
    }
    
    public void setFileSystemBrowser(
            FileSystemBrowserView fileSystemBrowserView,
            FileSystemBrowser fileSystemBrowser) {
        this.fileSystemBrowserView = fileSystemBrowserView;
        this.fileSystemBrowser = fileSystemBrowser;
        fileSystemBrowserSetItems();
        
        fileSystemBrowser.setListener(new FileSystemBrowserListener() {

            public void itemsChanged(FileSystemBrowser fileSystemBrowser) {
                fileSystemBrowserSetItems();
            }
        });
        
        fileSystemBrowserView.setCommandListener(this);
    }

    private void fileSystemBrowserSetItems() {
        fileSystemBrowserView.setItems(fileSystemBrowser.getItems());
        fileSystemBrowserView.setTitle(fileSystemBrowser.getPath());
    }
    
    private void browserBackPressed() {
        fileSystemBrowser.goUp();
    }
    
    private void browserGoPressed() {
        fileSystemBrowser.go(fileSystemBrowserView.getSelected());
    }

    public void commandAction(Command command, Displayable displayable) {
        if (displayable == configForm) {
            switch (command.getCommandType()) {
                case Command.OK: configOkPressed(); break;
                case Command.CANCEL: configCancelPressed(); break;
            }
        } else if (displayable == fileSystemBrowserView) {
            if (command.getLabel().equals("go")) {
                browserGoPressed();
            } else if (command.getLabel().equals("up")) {
                browserBackPressed();
            }
        }
    }
    
    private void exitApp() {
        midlet.destroyApp(false);
        midlet.notifyDestroyed();
    }
    
    private void showAlert(Alert alert, Displayable next) {
        Display.getDisplay(midlet).setCurrent(alert, next);
    }
}
