/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.TextField;

/**
 *
 * @author gumik
 */
public class ConfigForm extends Form {
    
    public ConfigForm() {
        super("Configuration");
        
        similarityFactor = new TextField("Similarity factor:", null, 6, 
                TextField.DECIMAL);
        
        delay = new TextField("Delay (ms)", null, 5, TextField.NUMERIC);
        
        final Command pathCommand = new Command("change", Command.ITEM, 0);
        
        path = new TextField("Images catalog:", null, 1024, 
                TextField.URL | TextField.UNEDITABLE);
        path.addCommand(pathCommand);
        
        final Command cancelCommand = new Command("Cancel", Command.CANCEL, 0);
        final Command okCommand = new Command("OK", Command.OK, 0);
        
        this.append(similarityFactor);
        this.append(delay);
        this.append(path);
        
        this.addCommand(okCommand);
        this.addCommand(cancelCommand);
        
        this.setCommandListener(new CommandListener() {
            public void commandAction(Command command, Displayable dsplbl) {
                if (listener == null) {
                    return;
                }
                
                if (command == okCommand) {
                    listener.okPressed();
                } else {
                    listener.cancelPressed();
                }
            }
        });
        
        path.setItemCommandListener(new ItemCommandListener() {
            public void commandAction(Command command, Item item) {
                if (listener == null) {
                    return;
                }
                
                if (command == pathCommand) {
                    listener.pathEditRequested();
                }
            }
        });
    }
    
    public int getDelay() {
        return Integer.parseInt(delay.getString());
    }
    
    public void setDelay(int value) {
        this.delay.setString(Integer.toString(value));
    }
    
    public double getSimilarityFactor() {
        return Double.parseDouble(similarityFactor.getString());
    }
    
    public void setSimilarityFactor(double value) {
        similarityFactor.setString(Double.toString(value));
    }
    
    public String getPath() {
        return path.getString();
    }
    
    public void setPath(String value) {
        path.setString(value);
    }
    
    public void setListener(ConfigFormListener listener) {
        this.listener = listener;
    }
    
    private TextField similarityFactor;
    private TextField delay;
    private TextField path;
    private ConfigFormListener listener;
}
