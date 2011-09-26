/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.view;

import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

/**
 *
 * @author gumik
 */
public class BrowserView extends List {
    
    public BrowserView() {
        super("Select destination folder", List.IMPLICIT);        
        final Command goUpCommand = new Command("Back", Command.SCREEN, 1);
        final Command goCommand = new Command("Open", Command.SCREEN, 0);
        final Command selectCommand = new Command("Select current", Command.OK, 1);
        final Command mkdirCommand = new Command("Create directory", Command.SCREEN, 2);
        
        this.addCommand(goUpCommand);
        this.addCommand(selectCommand);
        this.addCommand(mkdirCommand);
        this.setSelectCommand(goCommand);
        
        this.setCommandListener(new CommandListener() {

            public void commandAction(Command cmnd, Displayable dsplbl) {
                if (listener == null) {
                    return;
                }
                
                if (cmnd == goUpCommand) {
                    listener.goUpRequested();
                } else if (cmnd == goCommand) {
                    int selectedIndex = getSelectedIndex();
                    if (selectedIndex == -1) {
                        return;
                    }                    
                    listener.goIntoRequested(getString(selectedIndex));
                } else if (cmnd == selectCommand) {
                    listener.selectPressed();
                } else if (cmnd == mkdirCommand) {
                    listener.mkdirPressed();
                }
            }
        });
    }
    
    public void setItems(Vector items) {
        this.deleteAll();
        
        for (int i = 0; i < items.size(); ++i) {
            String item = (String) items.elementAt(i);
            this.append(item, null);
        }
    }
    
    public void setListener(BrowserViewListener listener) {
        this.listener = listener;
    }
    
    private BrowserViewListener listener;
}
