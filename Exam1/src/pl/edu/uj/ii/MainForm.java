/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;

/**
 *
 * @author gumik
 */
public class MainForm extends Form {

    public MainForm() {
        super("Exam1");
        this.append(getImageItem());
        
        Command exitCommand = new Command("Exit", Command.EXIT, 1);
        Command formCommand = new Command("Form", Command.OK, 0);
        
        this.addCommand(exitCommand);
        this.addCommand(formCommand);
        
        this.setCommandListener(new CommandListener() {

            public void commandAction(Command command, Displayable dsplbl) {                
                int commandType = command.getCommandType();
                if (commandType == Command.EXIT) {
                    if (listener != null) {
                        listener.exitPressed();
                    }
                } else if (commandType == Command.OK) {
                    if (listener != null) {
                        listener.okPressed();
                    }
                }
            }
        });
    }
    
    public void setListener(MainFormListener listener) {
        this.listener = listener;
    }

    private ImageItem getImageItem() {
        if (imageItem == null) {
            try {
                Image background = Image.createImage("/smile2.png");
                imageItem = new ImageItem(null, background, ImageItem.LAYOUT_CENTER, null, ImageItem.PLAIN);
            } catch (Exception e) {
                
                Logger.getInstance().error(e);
            }
        }

        return imageItem;
    }
    
    private ImageItem imageItem;
    private MainFormListener listener;
}
