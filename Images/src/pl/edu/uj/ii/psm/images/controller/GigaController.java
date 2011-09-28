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
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStoreFullException;
import pl.edu.uj.ii.DebugScreen;
import pl.edu.uj.ii.Midlet;
import pl.edu.uj.ii.psm.images.model.ImageComparer;
import pl.edu.uj.ii.psm.images.model.Config;
import pl.edu.uj.ii.psm.images.model.Browser;
import pl.edu.uj.ii.psm.images.model.BrowserListener;
import pl.edu.uj.ii.psm.images.model.ConfigListener;
import pl.edu.uj.ii.psm.images.model.PhotoSaver;
import pl.edu.uj.ii.psm.images.model.PhotoSaverListener;
import pl.edu.uj.ii.psm.images.model.PhotoThread;
import pl.edu.uj.ii.psm.images.model.PhotoThreadListener;
import pl.edu.uj.ii.psm.images.view.ConfigForm;
import pl.edu.uj.ii.psm.images.view.ConfigFormListener;
import pl.edu.uj.ii.psm.images.view.BrowserView;
import pl.edu.uj.ii.psm.images.view.BrowserViewListener;
import pl.edu.uj.ii.psm.images.view.PhotoView;
import pl.edu.uj.ii.psm.images.view.PhotoViewListener;

/**
 *
 * @author gumik
 */
public class GigaController {

    private Config configModel;
    private ConfigForm configForm;
    private Browser browser;
    private BrowserView browserView;
    private PhotoThread photoThread;
    private PhotoView photoView;
    private PhotoSaver photoSaver;
    private ImageComparer imageComparer;
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
        
        configModel.setListener(new ConfigListener() {

            public void PathChanged(String path) {
                configPathChanged(path);
            }

            public void SimilarityFactorChanged(double factor) {
                configSimilarityFactorChanged(factor);
            }

            public void DelayChanged(int delay) {
                configDelayChanged(delay);
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
        
        Display.getDisplay(midlet).setCurrent(photoView);
    }
    
    public void configCancelPressed() {
        Display.getDisplay(midlet).setCurrent(photoView);
    }
    
    private void configPathEditRequested() {
        Display.getDisplay(midlet).setCurrent(browserView);
    }

    private void configPathChanged(String path) {
        photoSaver.setPath(path);
    }

    private void configSimilarityFactorChanged(double factor) {
        imageComparer.setMargin(factor / 100);
    }

    private void configDelayChanged(int delay) {
        photoThread.setDelay(delay);
    }
    
    public void setBrowser(
            BrowserView browserView,
            Browser browser) {
        this.browserView = browserView;
        this.browser = browser;
        browserSetItems();
        try {
            browser.setPath(configModel.getPath());
        } catch (RecordStoreFullException ex) {
            // TODO
            ex.printStackTrace();
        }
        browser.setListener(new BrowserListener() {

            public void itemsChanged(Browser fileSystemBrowser) {
                browserSetItems();
            }

            public void errorOccured(String message) {
                browserErrorOccured(message);
            }
        });
        
        browserView.setListener(new BrowserViewListener() {
            public void goUpRequested() {
                browserViewBackPressed();
            }

            public void goIntoRequested(String item) {
                browserViewGoPressed(item);
            }

            public void selectPressed() {
                browserViewSelectPressed();
            }

            public void mkdirPressed() {
                browserViewMkdirPressed();
            }
        });
    }

    private void browserSetItems() {
        browserView.setItems(browser.getItems());
        browserView.setTitle(browser.getSimplifiedPath());
    }
    
    private void browserViewBackPressed() {
        browser.goUp();
    }

    private void browserViewGoPressed(String item) {
        browser.go(item);
    }
    
    private void browserViewSelectPressed() {
        configForm.setPath(browser.getSimplifiedPath());
        Display.getDisplay(midlet).setCurrent(configForm);
    }
    
    private void browserViewMkdirPressed() {
        final TextBox textBox = new TextBox("Enter directory name", null, 32, 
                TextField.NON_PREDICTIVE);
        final Command okCommand = new Command("OK", null, Command.OK, 0);
        final Command cancelCommand = new Command("Cancel", null, Command.CANCEL, 1);
        textBox.addCommand(okCommand);
        textBox.setCommandListener(new CommandListener() {

            public void commandAction(Command cmnd, Displayable dsplbl) {                
                Display.getDisplay(midlet).setCurrent(browserView);
                if (cmnd == okCommand) {
                    browser.makeDirectory(textBox.getString());                
                }
            }
        });
        Display.getDisplay(midlet).setCurrent(textBox);
    }

    private void browserErrorOccured(String message) {
        Alert alert = new Alert("Błąd", message, null, AlertType.ERROR);
        showAlert(alert, browserView);
    }
    
    public void setPhoto(final PhotoView photoView, PhotoThread photoThread, 
            PhotoSaver photoSaver, ImageComparer imageComparer) {
        this.photoThread = photoThread;
        this.photoView = photoView;
        this.photoSaver = photoSaver;
        this.imageComparer = imageComparer;
        
        try {
            imageComparer.setMargin(configModel.getSimilarityFactor() / 100);
        } catch (RecordStoreFullException ex) {
            ex.printStackTrace();
        }
        
        try {
            photoSaver.setPath(configModel.getPath());
        } catch (RecordStoreFullException ex) {
            ex.printStackTrace();
        }
        
        try {
            photoThread.setDelay(configModel.getDelay());
        } catch (RecordStoreFullException ex) {
            ex.printStackTrace();
        }
        
        photoThread.setListener(new PhotoThreadListener() {

            public void photoReceived(byte[] image) {
                photoPhotoReceived(image);
            }

            public void errorOccured(Exception e) {
                photoErrorOccured(e);
            }

            public void started() {
                photoStarted();
            }

            public void stopped() {
                photoStopped();
            }
        });
        
        photoView.setListener(new PhotoViewListener() {

            public void startPressed() {
                photoViewStartPressed();
            }

            public void stopPressed() {
                photoViewStopPressed();
            }

            public void configPressed() {
                photoViewConfigPressed();
            }

            public void exitPressed() {
                photoViewExitPressed();
            }
        });
        
        photoSaver.setListener(new PhotoSaverListener() {

            public void savedCountChanged(int count) {
                photoSaverSavedCountChanged(count);
            }

            public void sentCountChanged(int count) {
                photoSaverSentCountChanged(count);
            }

            public void differentCountChanged(int count) {
                photoSaverDiffCountChanged(count);
            }

            public void allCountChanged(int count) {
                photoSaverAllCountChanged(count);
            }
        });
        
        photoSaver.startBluetooth();
    }

    private void photoPhotoReceived(byte[] photo) {
        Image image = Image.createImage(photo, 0, photo.length);
        photoView.setActualPhoto(image);
        photoSaver.addPhoto(photo, image);
    }

    private void photoErrorOccured(Exception e) {
        e.printStackTrace();
    }

    private void photoStarted() {
        photoView.setRunning(true);
    }

    private void photoStopped() {
        photoView.setRunning(false);
    }

    private void photoViewStartPressed() {        
        photoThread.start();
    }

    private void photoViewStopPressed() {
        photoThread.stop();
    }

    private void photoViewConfigPressed() {
        Display.getDisplay(midlet).setCurrent(configForm);
    }

    private void photoViewExitPressed() {
        exitApp();
    }

    private void photoSaverSavedCountChanged(int count) {
        photoView.setPhotosSavedCount(count);
    }

    private void photoSaverSentCountChanged(int count) {
        photoView.setPhotosSentCount(count);
    }

    private void photoSaverDiffCountChanged(int count) {
        photoView.setPhotosDiffCount(count);
    }

    private void photoSaverAllCountChanged(int count) {
        photoView.setPhotosAllCount(count);
    }
    
    private void exitApp() {
        midlet.destroyApp(false);
        midlet.notifyDestroyed();
    }
    
    private void showAlert(Alert alert, Displayable next) {
        Display.getDisplay(midlet).setCurrent(alert, next);
    }
}