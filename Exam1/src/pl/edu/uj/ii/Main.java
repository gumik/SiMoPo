/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii;

import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.midlet.*;

/**
 * @author gumik
 */
public class Main extends MIDlet {

    public void startApp() {
        Display.getDisplay(this).setCurrent(getSplash());
//        Display.getDisplay(this).setCurrent(getQueryForm());
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    private Canvas getSplash() {
        if (splash == null) {
            final Display display = Display.getDisplay(this);
            splash = new Splash(new SplashTimedOutListener() {

                public void timedOut() {
                    display.setCurrent(getMainForm());
                }
            }, 1000);
        }

        return splash;
    }

    private MainForm getMainForm() {
        if (mainForm == null) {
            final Display display = Display.getDisplay(this);
            mainForm = new MainForm();
            mainForm.setListener(new MainFormListener() {

                public void exitPressed() {
                    destroyApp(false);
                    notifyDestroyed();
                }

                public void okPressed() {
                    try {
                        InputStream is = getClass().getResourceAsStream("/audio.wav");
                        Player p = Manager.createPlayer(is, "audio/X-wav");
//                        Player p = Manager.createPlayer(is, null);
//                        Player p = Manager.createPlayer("/audio.wav");
                        p.start();                        
                    } catch (Exception e) {
                        Logger.getInstance().error(e);
                    }
                    
                    display.setCurrent(getQueryForm());
                }
            });
        }

        return mainForm;
    }

    private QueryForm getQueryForm() {
        if (queryForm == null) {
            final Display display = Display.getDisplay(this);
            queryForm = new QueryForm();
            queryForm.setListener(new QueryFormListener() {

                public void BackPressed() {
                    display.setCurrent(getMainForm());
                }
            });
        }

        return queryForm;
    }
    private Canvas splash;
    private MainForm mainForm;
    private QueryForm queryForm;
}