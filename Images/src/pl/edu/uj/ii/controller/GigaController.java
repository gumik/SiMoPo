/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.controller;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.rms.RecordStoreFullException;
import pl.edu.uj.ii.MidletManager;
import pl.edu.uj.ii.config.Config;
import pl.edu.uj.ii.gui.ConfigForm;

/**
 *
 * @author gumik
 */
public class GigaController implements CommandListener {

    private Config configModel;
    private ConfigForm configForm;
    
    public GigaController() {        
        
    }
    
    public void setConfig(ConfigForm form, Config config) {
        this.configForm = form;
        form.setCommandListener(this);
        this.configModel = config;
        
        try {
            form.setDelay(config.getDelay());
        } catch (RecordStoreFullException ex) {
            ex.printStackTrace();
        }
    }
    
    public void configOkPressed() {
        try {
            configModel.setDelay(configForm.getDelay());
        } catch (IllegalArgumentException e) {            
            Alert alert = new Alert("Error while saving config", e.getMessage(),
                    null, AlertType.ERROR);
            MidletManager.getInstance().showAlert(configForm, alert);
        } catch (RecordStoreFullException e) {
            e.printStackTrace();
        }
        
        // TODO change
        MidletManager.getInstance().exitApp();
    }

    public void configCancelPressed() {
        // TODO change
        MidletManager.getInstance().exitApp();
    }

    public void commandAction(Command command, Displayable displayable) {
        if (displayable == configForm) {
            switch (command.getCommandType()) {
                case Command.OK: configOkPressed(); break;
                case Command.CANCEL: configCancelPressed(); break;
            }
        }
    }
    
}
