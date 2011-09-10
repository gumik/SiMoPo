/*
 * 2010.12.09
 */

package pl.edu.uj.ii;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Krzysztof Mataj
 */
public class Logger {
    static private Logger instance;

    List list;
    MIDlet midlet;

    private Logger() {
        list = new List("Logs", List.IMPLICIT);
    }

    static public Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public final static boolean DEBUG = true;

    public void error(Throwable e) {
        if (!DEBUG)
            return;

        log(String.valueOf(e));
    }

    private void log(String s) {
        list.append(s, null);
        if (midlet != null) {
            Display.getDisplay(midlet).setCurrent(list);
        }
    }

    public void setMidlet(MIDlet midlet) {
        this.midlet = midlet;
    }


}
