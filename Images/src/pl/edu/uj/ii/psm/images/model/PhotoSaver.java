/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Image;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import pl.edu.uj.ii.DebugScreen;

/**
 *
 * @author gumik
 */
public class PhotoSaver {
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

    public void setDevice(String value) {
        deviceName = value;
    }
    
    public void startBluetooth() {
        DebugScreen.getInstance().setMsg(("startBluetooth"));
        new Thread() {
            public void run() {
                discoverDevices();
            }
        }.start();
    }
    
    private void discoverDevices() {
        devices = new Vector();
        
        try {
            DebugScreen.getInstance().setMsg(("discoverDevices thread"));
            LocalDevice device = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = device.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {

                public void deviceDiscovered(RemoteDevice rd, DeviceClass dc) {                    
                    try {
                        if (rd.getFriendlyName(false).equals(deviceName)) {
                            devices.addElement(rd);
                        }
                        DebugScreen.getInstance().setMsg("device: " + rd.getFriendlyName(false));
                    } catch (Exception e) { }
                }

                public void servicesDiscovered(int i, ServiceRecord[] srs) {
                }

                public void serviceSearchCompleted(int i, int i1) {
                }

                public void inquiryCompleted(int i) {
                    DebugScreen.getInstance().setMsg("inquiry complete");
                    actDeviceIdx = 0;
                    services = new Vector();
                    searchServicesInDevice();
                }
            });
            DebugScreen.getInstance().setMsg("inquiry started");
        } catch (BluetoothStateException ex) {
            DebugScreen.getInstance().setMsg("inquiry error: " + ex.getMessage());
        } 
    }
    
    private void searchServicesInDevice() {
        if (actDeviceIdx < devices.size()) {
            searchForServices((RemoteDevice)devices.elementAt(actDeviceIdx));
            ++actDeviceIdx;
        } else {
            sendToServices();
        }
    }
    
    private void searchForServices(RemoteDevice rd) {        
        try {
            LocalDevice device = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = device.getDiscoveryAgent();
            UUID[] uuidSet = new UUID[] {
                new UUID(0x1105	), //OBEX Object Push Profile	
            };
            agent.searchServices(null, uuidSet, rd, new DiscoveryListener() {

                public void deviceDiscovered(RemoteDevice rd, DeviceClass dc) {
                }

                public void servicesDiscovered(int i, ServiceRecord[] srs) {
                    for (int j = 0; j < srs.length; ++j) {
                        services.addElement(srs[j]);
                    }
                    DebugScreen.getInstance().setMsg("service discovered");
                }

                public void serviceSearchCompleted(int i, int i1) {
                    DebugScreen.getInstance().setMsg("discovery completed");
                    searchServicesInDevice();
                }

                public void inquiryCompleted(int i) {
                }
            });
            DebugScreen.getInstance().setMsg("discovery started " + actDeviceIdx);
        } catch (Exception ex) {            
            DebugScreen.getInstance().setMsg("search error: " + ex.getMessage());
        } 
    }
    
    private void sendToServices() {
        for (int i = 0; i < services.size(); ++i) {
            send((ServiceRecord)services.elementAt(i));
        }
        
        startBluetooth();
    }
    
    private void send(ServiceRecord sr) {                                
        try {
            ClientSession cs = (ClientSession)Connector.open(
                    sr.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, 
                    false));
            
            cs.connect(null);
                        
            FileConnection dir = (FileConnection)Connector.open(path);
            Enumeration enumeration = dir.list("IMEI*.png", true);
            while (enumeration.hasMoreElements()) {
                String fileName = (String) enumeration.nextElement();
                DebugScreen.getInstance().setMsg("file to send: " + fileName);
                FileConnection file = (FileConnection)Connector.open(
                        path + fileName);
                InputStream is = file.openInputStream();
                Vector buffers = new Vector();
                
                int size = 0;
                int read = 0;
                do {
                    byte[] buffer = new byte[1024];
                    read = is.read(buffer);
                    size += read;
                    
                    if (read > 0) {
                        buffers.addElement(buffer);
                    }
                } while (read > 0);
                
                is.close();
                
                send(cs, buffers, size, fileName);
                incrementSentCount();
                file.delete();
                incrementSavedCount(-1);
            }
            
            cs.disconnect(null);
            
        } catch (IOException ex) {
            DebugScreen.getInstance().setMsg("send error: " + ex.getMessage());
        }
    }
    
    private void send(ClientSession cs, Vector buffers, int size, String name) throws IOException {
        HeaderSet hs = cs.createHeaderSet();
        hs.setHeader(HeaderSet.LENGTH, new Long(size));
        hs.setHeader(HeaderSet.NAME, name);
        Operation op = cs.put(hs);
        OutputStream os = op.openOutputStream();
        
        for (int i = 0; i < buffers.size(); ++i) {            
            os.write((byte[])buffers.elementAt(i), 0, Math.min(size, 1024));
            size -= 1024;
        }
        
        os.close();
        op.close();        
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
    
    private String deviceName;
    
    private int allCount;
    private int diffCount;
    private int savedCount;
    private int sentCount;
    
    private Vector devices;
    private Vector services;
    private int actDeviceIdx;
}
