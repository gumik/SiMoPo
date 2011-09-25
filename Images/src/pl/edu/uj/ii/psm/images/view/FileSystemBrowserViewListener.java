/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.view;

/**
 *
 * @author gumik
 */
public interface FileSystemBrowserViewListener {
    public void goUpRequested();
    public void goIntoRequested(String item);
    public void selectPressed();
}
