package pl.edu.uj.ii;


import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gumik
 */
public class ImgCanvas extends Canvas {
    
    public ImgCanvas() {
        this.addCommand(new Command("take", Command.SCREEN, 0));
        this.setCommandListener(new CommandListener() {

            public void commandAction(Command cmnd, Displayable dsplbl) {
                getPhoto();
            }
        });
    }

    protected void paint(Graphics grphcs) {
        if (image != null) {
            grphcs.drawImage(image, this.getWidth() / 2, this.getHeight() / 2, 
                    Graphics.HCENTER | Graphics.VCENTER);
        }
    }
    
    private void getPhoto() {
        final Canvas canvas = this;
        new Thread() {
            public void run() {                
                VideoControl videoControl = getVideoControl();
                System.out.println(System.getProperty("video.snapshot.encodings"));
                try {
                    byte[] snapshot = videoControl.getSnapshot(null);
                    image = Image.createImage(snapshot, 0, snapshot.length);
                    canvas.repaint();
                } catch (MediaException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }
    
    private VideoControl getVideoControl() {        
        if (videoControl == null) {
            try {
                Player player = Manager.createPlayer("capture://video");
                player.realize();
                videoControl = (VideoControl) player.getControl("VideoControl");
                videoControl.initDisplayMode(VideoControl.USE_GUI_PRIMITIVE, null);
                player.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
        }
        
        return videoControl;
    }
    
    private VideoControl videoControl;
    private Image image;
}
