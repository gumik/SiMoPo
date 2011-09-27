/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Image;

/**
 *
 * @author gumik
 */
public class PhotoSaver extends Thread {
    public PhotoSaver(ImageComparer imageComparer) {
        this.imageComparer = imageComparer;
        this.calendar = Calendar.getInstance();
        this.lock = new Object();
    }
    
    public void setPath(String path) {
        synchronized (lock) {
            this.path = "file:///" + path;
        }
    }
    
    public void addPhoto(byte[] data, Image image) {
        synchronized (lock) {
            incrementAllCount();
            
            if (image == null) {
                image = Image.createImage(data, 0, data.length);
            }
            if (prevPhoto != null 
                    && imageComparer.isSimilar(prevPhoto, image)) {
                return;
            }
            
            incrementDiffCount();
            prevPhoto = image;
            
            String fileName = getPhotoName();
            
            OutputStream outStream = null;
            try {
                FileConnection fc = (FileConnection)Connector.open(path + fileName);
                if (fc.exists()) {
                    fc.delete();                    
                }
                fc.create();
                outStream = fc.openOutputStream();
            } catch (IOException ex) {
                // TODO
                ex.printStackTrace();
                return;
            }
            
            boolean saved = false;
            
            while (!saved) {
                try {
                    outStream.write(data);
                    outStream.close();
                    saved = true;
                    incrementSavedCount(1);
                } catch (IOException e) {
                    saved = false;
                    deleteOldestFile();
                    incrementSavedCount(-1);
                }
            }
        }
    }
    
    public void setListener(PhotoSaverListener listener) {
        this.listener = listener;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private String getPhotoName() {
        StringBuffer sb = new StringBuffer(28);
        calendar.setTime(new Date());
        sb.append("IMEI-").append(toString(calendar.get(Calendar.YEAR), 4))
                .append("-").append(toString(calendar.get(Calendar.MONTH), 2))
                .append("-").append(toString(calendar.get(Calendar.DAY_OF_MONTH), 2))
                .append("-").append(toString(calendar.get(Calendar.HOUR_OF_DAY), 2))
                .append("-").append(toString(calendar.get(Calendar.MINUTE), 2))
                .append("-").append(toString(calendar.get(Calendar.SECOND), 2))
                .append(".jpg");        
        
        return sb.toString();
    }
    
    private static String toString(int x, int length) {
        String number = Integer.toString(x);
        StringBuffer sb = new StringBuffer(length);
        
        int toAdd = length - number.length();
        for (int i = 0; i < toAdd; ++i) {
            sb.append('0');
        }
        
        sb.append(number);
        return sb.toString();
    }

    private void deleteOldestFile() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    private void incrementAllCount() {
        ++allCount;
        if (listener != null) {
            listener.allCountChanged(allCount);
        }
    }
    
    private void incrementDiffCount() {
        ++diffCount;
        if (listener != null) {
            listener.differentCountChanged(diffCount);
        }
    }
    
    private void incrementSavedCount(int value) {
        savedCount += value;
        if (listener != null) {
            listener.savedCountChanged(savedCount);
        }
    }
    
    private void incrementSentCount() {
        ++sentCount;
        if (listener != null) {
            listener.sentCountChanged(sentCount);
        }
    }
    
    private Object lock;
    private Calendar calendar;
    private String path;
    private Image prevPhoto;
    private ImageComparer imageComparer;
    private PhotoSaverListener listener;
    
    private int allCount;
    private int diffCount;
    private int savedCount;
    private int sentCount;
}
