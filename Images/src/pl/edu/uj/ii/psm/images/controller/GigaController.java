/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.controller;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.RecordStoreFullException;
import pl.edu.uj.ii.Midlet;
import pl.edu.uj.ii.psm.images.model.Config;
import pl.edu.uj.ii.psm.images.model.FileSystemBrowser;
import pl.edu.uj.ii.psm.images.model.FileSystemBrowserListener;
import pl.edu.uj.ii.psm.images.view.ConfigForm;
import pl.edu.uj.ii.psm.images.view.ConfigFormListener;
import pl.edu.uj.ii.psm.images.view.FileSystemBrowserView;
import pl.edu.uj.ii.psm.images.view.FileSystemBrowserViewListener;

/**
 *
 * @author gumik
 */
public class GigaController {

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
        //form.setCommandListener(this);
        this.configModel = config;
        
        try {
            form.setDelay(config.getDelay());
            form.setSimilarityFactor(config.getSimilarityFactor());
            form.setPath(config.getPath());
        } catch (RecordStoreFullException ex) {
            // TODO
            ex.printStackTrace();
        }
        
        configForm.setListener(new ConfigFormListener() {

            public void okPressed() {
                configOkPressed();
            }

            public void cancelPressed() {
                configCancelPressed();
            }

            public void pathEditRequested() {
                configPathEditRequested();
            }
        });
    }
    
    public void configOkPressed() {
        try {
            configModel.setDelay(configForm.getDelay());
            configModel.setSimilarityFactor(configForm.getSimilarityFactor());
            configModel.setPath(configForm.getPath());
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
        try {
            fileSystemBrowser.setPath(configModel.getPath());
        } catch (RecordStoreFullException ex) {
            // TODO
            ex.printStackTrace();
        }
        fileSystemBrowser.setListener(new FileSystemBrowserListener() {

            public void itemsChanged(FileSystemBrowser fileSystemBrowser) {
                fileSystemBrowserSetItems();
            }
        });
        
        fileSystemBrowserView.setListener(new FileSystemBrowserViewListener() {
            public void goUpRequested() {
                browserBackPressed();
            }

            public void goIntoRequested(String item) {
                browserGoPressed(item);
            }

            public void selectPressed() {
                browserSelectPressed();
            }
        });
    }
    
    private void configPathEditRequested() {
        Display.getDisplay(midlet).setCurrent(fileSystemBrowserView);
    }

    private void fileSystemBrowserSetItems() {
        fileSystemBrowserView.setItems(fileSystemBrowser.getItems());
        fileSystemBrowserView.setTitle(fileSystemBrowser.getSimplifiedPath());
    }
    
    private void browserBackPressed() {
        fileSystemBrowser.goUp();
    }

    private void browserGoPressed(String item) {
        fileSystemBrowser.go(item);
    }
    
    private void browserSelectPressed() {
        configForm.setPath(fileSystemBrowser.getSimplifiedPath());
        Display.getDisplay(midlet).setCurrent(configForm);
    }
    
    private void exitApp() {
        midlet.destroyApp(false);
        midlet.notifyDestroyed();
    }
    
    private void showAlert(Alert alert, Displayable next) {
        Display.getDisplay(midlet).setCurrent(alert, next);
    }
}
