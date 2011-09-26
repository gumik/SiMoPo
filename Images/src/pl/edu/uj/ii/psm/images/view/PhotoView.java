/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.view;

import java.util.Date;
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
        lastPhotoDate = new StringItem("Last photo:", null);
        savedPhotoDate = new StringItem("Last different photo:", null);
        sentPhotoDate = new StringItem("Last photo sent:", null);
        countersString = new StringItem("sent / saved / different / all", null);
        actualPhoto = new ImageItem("Last saved photo:", null, 
                ImageItem.LAYOUT_DEFAULT, null);
        
        configCommand = new Command("Config", Command.SCREEN, 2);
        final Command exitCommand = new Command("Exit", Command.EXIT, 3);
        startCommand = new Command("Start", Command.OK, 1);
        stopCommand = new Command("Stop", Command.CANCEL, 1);
        
        this.append(actualPhoto);
        this.append(lastPhotoDate);
        this.append(savedPhotoDate);
        
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
        allCount = value;
        refreshCounters();
    }
    
    public void setPhotosDiffCount(int value) {
        differentCount = value;
        refreshCounters();
    }
    
    public void setPhotosSavedCount(int value) {
        savedCount = value;
        refreshCounters();
    }
    
    public void setPhotosSentCount(int value) {
        sentCount = value;
        refreshCounters();
    }
    
    public void setDateSaved(Date date) {
        savedPhotoDate.setText(date.toString());
    }
    
    public void setDateLastPhoto(Date date) {
        lastPhotoDate.setText(date.toString());
    }
    
    public void setDateSent(Date date) {
        sentPhotoDate.setText(date.toString());
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
    
    private void refreshCounters() {
        StringBuffer sb = new StringBuffer();
        sb.append(sentCount);
        sb.append(" / ");
        sb.append(savedCount);
        sb.append(" / ");
        sb.append(differentCount);
        sb.append(" / ");
        sb.append(allCount);
        countersString.setText(sb.toString());
    }
    
    private PhotoViewListener listener;
    
    private StringItem lastPhotoDate;
    private StringItem savedPhotoDate;
    private StringItem sentPhotoDate;
    private StringItem countersString;
    private ImageItem actualPhoto;
    
    private int allCount;
    private int differentCount;
    private int savedCount;
    private int sentCount;
    
    private Command configCommand;
    private Command startCommand;
    private Command stopCommand;
}
