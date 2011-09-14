package pl.edu.uj.ii;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author gumik
 */
public class Midlet extends MIDlet {

    public void startApp() {
        Canvas imgCanvas = new ImgCanvas();
        Display.getDisplay(this).setCurrent(imgCanvas);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
