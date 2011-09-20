/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.config;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author gumik
 */
// TODO: add closing of record store
public class ConfigStorage {
    
    private static ConfigStorage instance;
    
    public static ConfigStorage getInstance() {
        if (instance == null) {
            instance = new ConfigStorage();
        }
        
        return instance;
    }
    
    public void set(String recordStoreName, String key, int value) 
            throws RecordStoreFullException {
        byte[] byteValue = new byte[] {    
            (byte)((value >>> 0x18) & 0xff),
            (byte)((value >>> 0x10) & 0xff),
            (byte)((value >>> 0x8) & 0xff),
            (byte)(value & 0xff),        
        };
        
        set(recordStoreName, key, byteValue);
    }
    
    public void set(String recordStoreName, String key, String value) 
            throws RecordStoreFullException {
        set(recordStoreName, key, value.getBytes());
    }
    
    public void set(String recordStoreName, String key, byte[] value) 
            throws RecordStoreFullException {
        final byte[] byteKey = key.getBytes();        
        RecordStore recordStore = initRecordStore(recordStoreName, true);
        
        try {
            RecordStoreData rsData = findInRecordStore(recordStore, byteKey);
            byte[] data = makeData(key, value);  
            
            if (rsData != null) {
                recordStore.setRecord(rsData.getId(), data, 0, data.length);
            } else {
                recordStore.addRecord(data, 0, data.length);
            }
        } catch (RecordStoreFullException e) {
            throw e;
        } catch (RecordStoreException e) {
            e.printStackTrace();
        } finally {
            closeRecordStore(recordStore);
        }
    }
    
    public int get(String recordStoreName, String key, int defaultValue) 
            throws RecordStoreFullException {
        byte[] returnedValue = get(recordStoreName, key, (byte[])null);
        if (returnedValue == null) {
            return defaultValue;
        } else {
            int value = ((returnedValue[0] << 0x18) & 0xff000000)
                    | ((returnedValue[1] << 0x10) & 0x00ff0000)
                    | ((returnedValue[2] << 0x8) & 0x0000ff00)
                    | (returnedValue[3] & 0x000000ff);
            return value;
        }
    }
    
    public String get(String recordStoreName, String key, String defaultValue) 
            throws RecordStoreFullException {
        byte[] returnedValue = get(recordStoreName, key, (byte[])null);
        if (returnedValue == null) {
            return defaultValue;
        } else {
            return new String(returnedValue);
        }
    }
    
    public byte[] get(String recordStoreName, String key, byte[] defaultValue) 
            throws RecordStoreFullException {
        final byte[] byteKey = key.getBytes();
        RecordStore recordStore = initRecordStore(recordStoreName, false);
        
        try {
            RecordStoreData rsData = findInRecordStore(recordStore, byteKey);
            
            if (rsData != null) {
                 return retreiveValue(rsData.getData(), byteKey.length);
            } else {
                return defaultValue;
            }
        } catch (RecordStoreFullException e) {
            throw e;
        } catch (RecordStoreException e) {
            e.printStackTrace();
        } finally {
            closeRecordStore(recordStore);
        }
        
        return defaultValue;
    }
    
    public byte[] delete(String recordStoreName, String key) 
            throws RecordStoreFullException {
        final byte[] byteKey = key.getBytes();        

        try {
            RecordStore recordStore = 
                    RecordStore.openRecordStore(recordStoreName, true);            
            RecordStoreData rsData = findInRecordStore(recordStore, byteKey); 
            
            if (rsData != null) {
                recordStore.deleteRecord(rsData.getId());
                return retreiveValue(rsData.getData(), byteKey.length);
            }
        } catch (RecordStoreFullException e) {
            throw e;
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private RecordStore initRecordStore(String name, boolean createIfNeeded) 
            throws RecordStoreFullException {
        RecordStore recordStore = null;
        
        try {
            recordStore = 
                    RecordStore.openRecordStore(name, createIfNeeded);      
        } catch (RecordStoreFullException e) {
            throw e;
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
        
        return recordStore;
    }
    
    private void closeRecordStore(RecordStore recordStore) {
        try {
            recordStore.closeRecordStore();
        } catch (RecordStoreException e) {
            // ignore
        }
    }
    
    private RecordStoreData findInRecordStore(RecordStore recordStore, 
            final byte[] byteKey) throws RecordStoreFullException {
        try {
            RecordEnumeration enumeration = recordStore.enumerateRecords(
                new RecordFilter() {
                    public boolean matches(byte[] bytes) {
                        return isKeyMatch(byteKey, bytes);
                    }
                }, null, false);
            
            if (enumeration.hasNextElement()) {
                int id = enumeration.nextRecordId();
                byte[] data = recordStore.getRecord(id);
                return new RecordStoreData(id, data);
            }
        } catch (RecordStoreFullException e) {
            throw e;
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private boolean isKeyMatch(byte[] byteKey, byte[] entry) {
        if (entry.length < byteKey.length) {
            return false;
        }
        
        for (int i = 0; i < byteKey.length; ++i) {
            if (byteKey[i] != entry[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    private byte[] retreiveValue(byte[] data, int keyLength) {
        byte[] value = new byte[data.length - keyLength];
        
        for (int i = keyLength; i < data.length; ++i) {
            value[i - keyLength] = data[i];
        }
        
        return value;
    }
    
    private byte[] makeData(String key, byte[] value) {
        return joinTables(key.getBytes(), value);
    }
    
    private byte[] joinTables(byte[] t1, byte[] t2) {
        byte[] data = new byte[t1.length + t2.length];
        
        int i = 0;
        for (int j = 0; j < t1.length; ++j) {
            data[i++] = t1[j];
        }
        
        for (int j = 0; j < t2.length; ++j) {
            data[i++] = t2[j];
        }
        
        return data;
    }
}

class RecordStoreData {
    private int id;
    private byte[] data;

    public RecordStoreData(int id, byte[] data) {
        this.id = id;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public int getId() {
        return id;
    }    
}