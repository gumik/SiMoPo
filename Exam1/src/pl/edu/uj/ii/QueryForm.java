/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii;

import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;

/**
 *
 * @author gumik
 */
public class QueryForm extends Form {

    public QueryForm() {
        super(null);

        firstName = new TextField("First name", null, 40, TextField.INITIAL_CAPS_WORD);
        sureName = new TextField("Surename", null, 40, TextField.INITIAL_CAPS_WORD);
        phone = new TextField("Phone", null, 20, TextField.DECIMAL);
        password = new TextField("Password", null, 40, TextField.PASSWORD);

        back = new Command("Back", Command.BACK, 1);
        save = new Command("Save", Command.SCREEN, 0);
        read = new Command("Read", Command.SCREEN, 0);
        retreive = new Command("Retreive", Command.SCREEN, 1);

        this.append(firstName);
        this.append(sureName);
        this.append(phone);
        this.append(password);

        this.addCommand(back);
        this.addCommand(save);
        this.addCommand(read);
        this.addCommand(retreive);

        this.setCommandListener(new CommandListener() {

            public void commandAction(Command command, Displayable dsplbl) {
                if (command == back) {
                    if (listener != null) {
                        listener.BackPressed();
                    }
                } else if (command == save) {
                    save();
                } else if (command == read) {
                    read();
                } else if (command == retreive) {
                    retreive();
                }
            }
        });
    }

    public void setListener(QueryFormListener listener) {
        this.listener = listener;
    }

    private void save() {
        try {
            RecordStore record_store =
                    RecordStore.openRecordStore(RS_NAME, true);

            writeEntry(record_store, RS_FIRSTNAME_ID, firstName.getString());
            writeEntry(record_store, RS_SURNAME_ID, sureName.getString());
            writeEntry(record_store, RS_PHONE_ID, phone.getString());
            writeEntry(record_store, RS_PASSWORD_ID, password.getString());

            record_store.closeRecordStore();

        } catch (RecordStoreException e) {
            Logger.getInstance().error(e);
        }
    }

    private void read() {
        try {
            RecordStore record_store =
                    RecordStore.openRecordStore(RS_NAME, true);

            firstName.setString(readEntry(record_store, RS_FIRSTNAME_ID));
            sureName.setString(readEntry(record_store, RS_SURNAME_ID));
            phone.setString(readEntry(record_store, RS_PHONE_ID));
            password.setString(readEntry(record_store, RS_PASSWORD_ID));

            record_store.closeRecordStore();

        } catch (RecordStoreNotFoundException e) {
        } catch (RecordStoreException e) {
            Logger.getInstance().error(e);
        }
    }

    private void retreive() {
        if (retreived) {
            return;
        }
        
        final Form form = this;
        
        Thread thread = new Thread() {
            
            public void run() {                
                StringItem stringItem = new StringItem("Status:", 
                        "Retreiving image...");
                int itemNum = form.append(stringItem);
                
                try {
                    InputStream input_stream = Connector.openDataInputStream(
                            "http://www.ii.uj.edu.pl/ban-kandydaci.jpg");
                    Image img = Image.createImage(input_stream);
                    
                    form.delete(itemNum);
                    form.append(img);
                    retreived = true;
                } catch (Exception e) {
                    stringItem.setText("Failed!");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e2) {
                    }
                    
                    form.delete(itemNum);
                    
                    Logger.getInstance().error(e);
                }
            }
        };
        
        thread.start();
    }

    private void writeEntry(RecordStore recordStore, final byte key, 
            String value) throws RecordStoreException {
        RecordEnumeration enumeration = recordStore.enumerateRecords(
                new RecordFilter() {

                    public boolean matches(byte[] bytes) {
                        return bytes != null && bytes.length > 0 && bytes[0] == key;
                    }
                },
                null, false);

        byte[] valueData = value.getBytes();
        byte[] data = new byte[valueData.length + 1];
        data[0] = key;
        for (int i = 0; i < valueData.length; ++i) {
            data[i + 1] = valueData[i];
        }

        if (enumeration.hasNextElement()) {
            int id = enumeration.nextRecordId();
            recordStore.setRecord(id, data, 0, data.length);
        } else {
            recordStore.addRecord(data, 0, data.length);
        }
    }

    private String readEntry(RecordStore recordStore, final byte key) 
            throws RecordStoreException {
        RecordEnumeration enumeration = recordStore.enumerateRecords(
                new RecordFilter() {

                    public boolean matches(byte[] bytes) {
                        return bytes != null && bytes.length > 0 && bytes[0] == key;
                    }
                },
                null, false);

        if (enumeration.hasNextElement()) {
            byte[] data = enumeration.nextRecord();
            byte[] valueData = new byte[data.length - 1];
            for (int i = 0; i < valueData.length; ++i) {
                valueData[i] = data[i + 1];
            }
            return new String(valueData);
        }

        return null;
    }
    
    private QueryFormListener listener;
    
    private TextField firstName;
    private TextField sureName;
    private TextField phone;
    private TextField password;
    
    private Command back;
    private Command save;
    private Command read;
    private Command retreive;
    
    private boolean retreived;
    
    private final static byte RS_FIRSTNAME_ID = 0;
    private final static byte RS_SURNAME_ID = 1;
    private final static byte RS_PHONE_ID = 2;
    private final static byte RS_PASSWORD_ID = 3;
    
    private final static String RS_NAME = "SAVE";
}
