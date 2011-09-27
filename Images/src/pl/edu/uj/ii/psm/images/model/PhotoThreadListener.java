/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

/**
 *
 * @author gumik
 */
public interface PhotoThreadListener {
    public void photoReceived(byte[] image);
    public void errorOccured(Exception e);
    public void started();
    public void stopped();
}
