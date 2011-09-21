/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.view;

import javax.microedition.lcdui.Command;
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
    
    private TextField similarityFactor;
    private TextField delay;
}
