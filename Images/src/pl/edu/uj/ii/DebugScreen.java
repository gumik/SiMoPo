/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author gumik
 */
public class DebugScreen extends Form {
    public static DebugScreen getInstance() {
        if (instance == null) {
            instance = new DebugScreen();
        }
        
        return instance;
    }
    
    private DebugScreen() {
        super("Debug");
        stringItem = new StringItem(null, null);
        append(stringItem);
        Command okCommand = new Command("OK", Command.OK, 0);
        addCommand(okCommand);
    }
    
    public void setMsg(String msg) {
        stringItem.setText(msg);
    }
    
    private static DebugScreen instance;
    private StringItem stringItem;
}
