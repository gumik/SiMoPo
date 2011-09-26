/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

/**
 *
 * @author gumik
 */
public interface BrowserListener {
    public void itemsChanged(Browser fileSystemBrowser);
    public void errorOccured(String message);
}
