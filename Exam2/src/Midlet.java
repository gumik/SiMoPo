/** 2011-01-13
 * @author Krzysztof Mataj
 */

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.*;

public class Midlet extends MIDlet implements CommandListener {
    MapCanvasEx canvas;
    Command upCommand;

    public Midlet() {
        canvas = new MapCanvasEx(1, 0, 0);

    }

    public void startApp() {
        Display.getDisplay(this).setCurrent(canvas);
    }

    public void pauseApp() {

    }

    public void destroyApp(boolean unconditional) {

    }

    public void commandAction(Command c, Displayable d) {

    }
}
