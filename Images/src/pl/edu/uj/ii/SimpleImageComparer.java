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
public class SimpleImageComparer implements ImageComparer {

    private final int R_MASK = 0xff0000;
    private final int G_MASK = 0x00ff00;
    private final int B_MASK = 0x0000ff;
    private final double MAX_DIST = Math.sqrt(255 * 255 + 255 * 255 + 255 * 255);
    
    private double margin;
    
    public SimpleImageComparer(double margin) {
        this.setMargin(margin);
    }
    
    public double compare(Image img1, Image img2) {
        if (img1 == null || img2 == null) {
            throw new NullPointerException();
        }
        
        if (img1.getWidth() != img2.getWidth()
                || img1.getHeight() != img2.getHeight()) {
            return 0d;
        }
        
        int numberOfPixels = img1.getWidth() * img1.getHeight();
        
        int[] rgbData1 = new int[numberOfPixels];
        int[] rgbData2 = new int[numberOfPixels];
        
        // Something is wrong here.
        img1.getRGB(rgbData1, 0, img1.getWidth(), 0, 0, img1.getWidth(), img1.getHeight());
        img2.getRGB(rgbData2, 0, img2.getWidth(), 0, 0, img2.getWidth(), img2.getHeight());
        
        double totalDist = 0;
        
        for (int i = 0; i < numberOfPixels; ++i) {
            double dist = normalizedDist(rgbData1[i], rgbData2[i]);
            totalDist += dist;
        }
        
        return 1d - (totalDist / numberOfPixels);
    }

    public boolean isSimilar(Image img1, Image img2) {
        return compare(img1, img2) >= margin;
    }
    
    public final void setMargin(double margin) {
        if (margin < 0) {
            margin = 0;
        } else if (margin > 1) {
            margin = 1;
        }
        
        this.margin = margin;
    }
    
    public double getMargin() {
        return this.margin;
    }
    
    private double dist(int c1, int c2) {
        int r1 = c1 & R_MASK;
        int g1 = c1 & G_MASK;
        int b1 = c1 & B_MASK;
        
        int r2 = c2 & R_MASK;
        int g2 = c2 & G_MASK;
        int b2 = c2 & B_MASK;

        int rd = r1 - r2;
        int gd = g1 - g2;
        int bd = b1 - b2;
        
        double dist = Math.sqrt(rd * rd + gd * gd + bd * bd);
        
        return dist;
    }
    
    private double normalizedDist(int c1, int c2) {
        return dist(c1, c2) / MAX_DIST;
    }
}
