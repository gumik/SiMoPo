/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.view;

import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;

/**
 *
 * @author gumik
 */
public class FileSystemBrowserView extends List {
    
    public FileSystemBrowserView() {
        super("Select destination folder", List.IMPLICIT);        
        Command goUpCommand = new Command("up", Command.SCREEN, 0);
        Command goCommand = new Command("go", Command.EXIT, 0);
        this.addCommand(goUpCommand);
        this.setSelectCommand(goCommand);
    }
    
    public void setItems(Vector items) {
        this.deleteAll();
        
        for (int i = 0; i < items.size(); ++i) {
            String item = (String) items.elementAt(i);
            this.append(item, null);
        }
    }
    
    public String getSelected() {
        return getString(getSelectedIndex());
    }
}
