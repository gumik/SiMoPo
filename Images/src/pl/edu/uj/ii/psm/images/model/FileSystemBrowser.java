/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;

/**
 *
 * @author gumik
 */
public class FileSystemBrowser {
    public FileSystemBrowser() {        
        setDrivesList();
        level = 0;
        path = "file:///";
        lock = new Object();
    }
    
    public Vector getItems() {
        return items;
    }
    
    public String getSimplifiedPath() {
        return path.substring(8);
    }
    
    public String getPath() {
        return path;
    }
    
    public void setListener(FileSystemBrowserListener listener) {
        this.listener = listener;
    }
    
    public void setPath(String path) {
        while (level > 0) {
            goUp();
        }
        
        int lastIdx = 0;
        while (true) {
            int idx = path.indexOf("/", lastIdx);
            if (idx == -1) {
                break;
            }
            
            go(path.substring(lastIdx, idx + 1));
            lastIdx = idx + 1;
        }
//        
//        if (listener != null) {
//            listener.itemsChanged(this);
//        }        
    }
    
    public void goUp() {
        if (level == DRIVES) {
            return;
        }
        
        if (level == ROOT_DIR) {            
            new Thread() {
                public void run() {
                    synchronized (lock) {
                        removeFromPath();
                        setDrivesList();
                        --level;
                    }
                }
            }.start(); 
        } else {
            new Thread() {            
                public void run() {
                    synchronized (lock) {
                        String prevPath = path;
                        removeFromPath();
                        try
                        {
                            setDirsList();
                            --level;
                        } catch (IOException e) {
                            path = prevPath;
                        }
                    }
                }
            }.start();
        }
    }
    
    public void go(final String part) {
        new Thread() {
            public void run() {
                synchronized (lock) {
                    String prevPath = path;
                    addToPath(part);

                    try {
                        setDirsList();
                        ++level;
                    } catch (Exception e) {
                        path = prevPath;
                    }
                }
            }
        }.start();
    }
    
    private void addToPath(String part) {
        StringBuffer sb = new StringBuffer(path.length() + part.length()/* + 1*/);
        sb.append(path)./*append('/').*/append(part);
        path = sb.toString();
    }
    
    private void removeFromPath() {
        int index = path.lastIndexOf('/', path.length() - 2);
        path = path.substring(0, index + 1);
    }
    
    private void setDrivesList() {
        System.out.println("setDrivesList, " + path);
        items = new Vector();
        Enumeration drive = FileSystemRegistry.listRoots();
        while (drive.hasMoreElements()) {
            items.addElement(drive.nextElement());
        }
        
        notifyChanged();
    }
    
    private void setDirsList() throws IOException {
        System.out.println("setDirsList, " + path);
        Vector newList = new Vector();
        FileConnection fc = (FileConnection)Connector.open(path, Connector.READ);
        Enumeration filelist = fc.list();

        while(filelist.hasMoreElements()) {
            String fileName = (String) filelist.nextElement();
            if (fileName.endsWith("/")) {
                newList.addElement(fileName);
            }
        }
        
        items = newList;
        notifyChanged();            
    }
    
    private void notifyChanged() {
        if (listener != null) {
            listener.itemsChanged(this);
        }
    }
    
    private static final int DRIVES = 0;
    private static final int ROOT_DIR = 1;
    
    private Vector items;
    private FileSystemBrowserListener listener;
    private int level;
    private String path;
    private final Object lock;
}