/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.TextBox;

/**
 *
 * @author gumik
 */
public class DebugScreen extends TextBox {
    public static DebugScreen getInstance() {
        if (instance == null) {
            instance = new DebugScreen();
        }
        
        return instance;
    }
    
    private DebugScreen() {
        super("Debug", null, 8049, 0);
        Command okCommand = new Command("OK", Command.OK, 0);
        addCommand(okCommand);
    }
    
    public void setMsg(String msg) {
        insert(msg, size());
        insert("\n", size());
    }
    
    private static DebugScreen instance;
}
