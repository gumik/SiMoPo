/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

/**
 *
 * @author gumik
 */
public interface ConfigListener {
    public void PathChanged(String path);
    public void SimilarityFactorChanged(double factor);
    public void DelayChanged(int delay);
}
