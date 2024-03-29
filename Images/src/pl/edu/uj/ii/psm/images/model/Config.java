/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

import javax.microedition.rms.RecordStoreFullException;

/**
 *
 * @author gumik
 */
public class Config {
    private static Config instance;
    private static final String RECORD_STORE_NAME = "config";
    private static final String DELAY_KEY = "delay";
    private static final String SIMILARITY_FACTOR_KEY = "similarity";
    private static final String PATH_KEY = "path";
    private static final String DEVICE_KEY = "device";
    
    private ConfigListener listener;
    
    private Config() {
        
    }
    
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        
        return instance;
    }
    
    public void setDelay(int delay) throws RecordStoreFullException {
        if (delay < 0 || delay > 99999) {
            throw new IllegalArgumentException(
                    "Delay should be between 0 and 99999 (inclusive).");
        }
        
        ConfigStorage.getInstance().set(RECORD_STORE_NAME, DELAY_KEY, delay);
        
        if (listener != null) {
            listener.DelayChanged(delay);
        }
    }
    
    public int getDelay() throws RecordStoreFullException {
        return ConfigStorage.getInstance().get(RECORD_STORE_NAME, 
                DELAY_KEY, 1000);
    }
    
    public void setSimilarityFactor(double value) 
            throws RecordStoreFullException {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException(
                "Similarity factor should be between 0 and 100 (inclusive).");
        }
        if (value * 100 - (int)(value * 100) > 0) { 
            throw new IllegalArgumentException("Similarity factor precision "
                    + "can't be better than 0.01");
        }
        
        ConfigStorage.getInstance().set(RECORD_STORE_NAME, 
                SIMILARITY_FACTOR_KEY, (int)(value * 100));
        
        if (listener != null) {
            listener.SimilarityFactorChanged(value);
        }
    }
    
    public double getSimilarityFactor() throws RecordStoreFullException {
        return (double)ConfigStorage.getInstance().get(RECORD_STORE_NAME, 
                SIMILARITY_FACTOR_KEY, 6660) / 100;
    }
    
    public void setPath(String value) throws RecordStoreFullException {
        ConfigStorage.getInstance().set(RECORD_STORE_NAME, PATH_KEY, value);
        
        if (listener != null) {
            listener.PathChanged(value);
        }
    }
    
    public String getPath() throws RecordStoreFullException {
        return ConfigStorage.getInstance().get(RECORD_STORE_NAME, PATH_KEY, "");
    }
    
    public void setDevice(String value) throws RecordStoreFullException {
        ConfigStorage.getInstance().set(RECORD_STORE_NAME, DEVICE_KEY, value);
        
        if (listener != null) {
            listener.DeviceChanged(value);
        }
    }
    
    public String getDevice() throws RecordStoreFullException {
        return ConfigStorage.getInstance().get(RECORD_STORE_NAME, DEVICE_KEY, "");
    }
    
    public void setListener(ConfigListener listener) {
        this.listener = listener;
    }
}
