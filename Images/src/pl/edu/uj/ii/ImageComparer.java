/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii;

import javax.microedition.lcdui.Image;

/**
 *
 * @author gumik
 */
public interface ImageComparer {
    public double compare(Image img1, Image img2);
    public boolean isSimilar(Image img1, Image img2);
}
