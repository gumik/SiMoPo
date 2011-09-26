/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

/**
 *
 * @author gumik
 */
public class PhotoThread extends Thread {
    
    
    public void run() {
        if (listener != null) {
            listener.started();
        }
        
        while (run) {
            try {
                byte[] snapshot = getVideoControl().getSnapshot(null);
                Image image = Image.createImage(snapshot, 0, snapshot.length);
                if (listener != null) {
                    listener.photoReceived(image);
                }
            } catch (Exception ex) {
                notifyError(ex);
                run = false;
            }
            
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                notifyError(ex);
                run = false;
            }
        }
        
        if (listener != null) {
            listener.stopped();
        }
    }
    
    public void start() {
        if (run) {
            return;
        }
        
        run = true;
        super.start();
    }
    
    public void stop() {
        run = false;
    }
    
    public void setListener(PhotoThreadListener listener) {
        this.listener = listener;
    }
    
    private VideoControl getVideoControl() throws IOException, MediaException {        
        if (videoControl == null) {
            Player player = Manager.createPlayer("capture://video");
            player.realize();
            videoControl = (VideoControl) player.getControl("VideoControl");
            videoControl.initDisplayMode(VideoControl.USE_GUI_PRIMITIVE, null);
            player.start();
        }
        
        return videoControl;
    }
    
    private void notifyError(Exception e) {
        if (listener != null) {
            listener.errorOccured(e);
        }
    }
    
    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    private PhotoThreadListener listener;
    private VideoControl videoControl;
    private boolean run;
    private int delay;
}
