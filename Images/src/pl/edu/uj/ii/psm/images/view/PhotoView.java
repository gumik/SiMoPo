/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.view;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author gumik
 */
public class PhotoView extends Form {
    
    public PhotoView() {
        super("Camera preview");
        sentCountString = new StringItem("Sent:", null);
        savedCountString = new StringItem("Saved:", null);
        differentCountString = new StringItem("Different:", null);
        allCountString = new StringItem("All:", null);
        
        actualPhoto = new ImageItem("Last saved photo:", null, 
                ImageItem.LAYOUT_DEFAULT, null);
        
        configCommand = new Command("Config", Command.SCREEN, 2);
        final Command exitCommand = new Command("Exit", Command.EXIT, 3);
        startCommand = new Command("Start", Command.OK, 1);
        stopCommand = new Command("Stop", Command.CANCEL, 1);
        
        this.append(actualPhoto);
        
        this.append(sentCountString);
        this.append(savedCountString);
        this.append(differentCountString);
        this.append(allCountString);
        
        this.addCommand(configCommand);
        this.addCommand(exitCommand);
        this.addCommand(startCommand);
        //this.addCommand(stopCommand);
        
        this.setCommandListener(new CommandListener() {

            public void commandAction(Command cmnd, Displayable dsplbl) {
                if (listener == null) {
                    return;
                }
                
                if (cmnd == configCommand) {
                    listener.configPressed();
                } else if (cmnd == exitCommand) {
                    listener.exitPressed();
                } else if (cmnd == startCommand) {
                    listener.startPressed();
                } else if (cmnd == stopCommand) {
                    listener.stopPressed();
                }
            }
        });
    }
    
    public void setPhotosAllCount(int value) {
        allCountString.setText(Integer.toString(value));
    }
    
    public void setPhotosDiffCount(int value) {
        differentCountString.setText(Integer.toString(value));
    }
    
    public void setPhotosSavedCount(int value) {
        savedCountString.setText(Integer.toString(value));
    }
    
    public void setPhotosSentCount(int value) {
        sentCountString.setText(Integer.toString(value));
    }
    
    public void setActualPhoto(Image image) {
        actualPhoto.setImage(image);
    }

    public void setRunning(boolean value) {
        if (value) {
            this.removeCommand(startCommand);
            this.removeCommand(configCommand);
            this.addCommand(stopCommand);
        } else {
            this.removeCommand(stopCommand);
            this.addCommand(startCommand);
            this.addCommand(configCommand);
        }
    }
    
    public void setListener(PhotoViewListener listener) {
        this.listener = listener;
    }
    
    private PhotoViewListener listener;
    
    private StringItem sentCountString;
    private StringItem savedCountString;
    private StringItem differentCountString;
    private StringItem allCountString;
    private ImageItem actualPhoto;
    
    private Command configCommand;
    private Command startCommand;
    private Command stopCommand;
}
