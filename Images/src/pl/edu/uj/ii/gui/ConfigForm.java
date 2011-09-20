/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.gui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.TextField;
import pl.edu.uj.ii.MidletManager;

/**
 *
 * @author gumik
 */
public class ConfigForm extends Form {
    
    public ConfigForm() {
        super("Configuration");
        
        similarityFactor = new TextField("Similarity factor:", null, 6, TextField.DECIMAL);
        similarityFactor.setItemCommandListener(new ItemCommandListener() {
            public void commandAction(Command cmnd, Item item) {
                
            }
        });
        
        delay = new TextField("Delay (ms)", null, 5, TextField.NUMERIC);
        
        final Command cancelCommand = new Command("Cancel", Command.CANCEL, 0);
        final Command okCommand = new Command("OK", Command.OK, 0);
        
        this.append(similarityFactor);
        this.append(delay);
        
        this.addCommand(okCommand);
        this.addCommand(cancelCommand);
        
//        this.setCommandListener(new CommandListener() {
//            public void commandAction(Command cmnd, Displayable dsplbl) {
//                if (cmnd == okCommand) {
//                    okAction();
//                } else if (cmnd == cancelCommand) {
//                    cancelAction();
//                }
//            }
//        });
    }
    
//    public void setListener(ConfigFormListener listener) {
//        this.listener = listener;
//    }
    
    public int getDelay() {
        return Integer.parseInt(delay.getString());
    }
    
    public void setDelay(int delay) {
        this.delay.setString(Integer.toString(delay));
    }
    
//    public void showError(String message) {
//        Alert alert = new Alert("Error while saving config", message, null, 
//                AlertType.ERROR);
//        DisplayManager.getInstance().showAlert(this, alert);
//    }
    
//    private void okAction() {
//        if (listener != null) {
//            listener.okPressed();
//        }
//    }
//    
//    private void cancelAction() {
//        if (listener != null) {
//            listener.cancelPressed();
//        }
//    }
    
//    private ConfigFormListener listener;
    private TextField similarityFactor;
    private TextField delay;
}
