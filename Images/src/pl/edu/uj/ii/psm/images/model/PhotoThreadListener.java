/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

import javax.microedition.lcdui.Image;

/**
 *
 * @author gumik
 */
public interface PhotoThreadListener {
    public void photoReceived(Image image);
    public void errorOccured(Exception e);
    public void started();
    public void stopped();
}
