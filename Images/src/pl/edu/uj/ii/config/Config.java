/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.config;

import javax.microedition.rms.RecordStoreFullException;

/**
 *
 * @author gumik
 */
public class Config {
    private static Config instance;
    private static final String RECORD_STORE_NAME = "config";
    private static final String DELAY_KEY = "delay";
    
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
    }
    
    public int getDelay() throws RecordStoreFullException {
        return ConfigStorage.getInstance().get(RECORD_STORE_NAME, 
                DELAY_KEY, 1000);
    }
}
