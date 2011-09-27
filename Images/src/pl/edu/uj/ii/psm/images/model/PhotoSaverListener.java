/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

/**
 *
 * @author gumik
 */
public interface PhotoSaverListener {
    public void savedCountChanged(int count);
    public void sentCountChanged(int count);
    public void differentCountChanged(int count);
    public void allCountChanged(int count);
}
