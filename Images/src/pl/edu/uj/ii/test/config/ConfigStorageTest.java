/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.test.config;

import j2meunit.framework.Test;
import j2meunit.framework.TestCase;
import j2meunit.framework.TestMethod;
import j2meunit.framework.TestSuite;
import javax.microedition.rms.RecordStoreFullException;
import pl.edu.uj.ii.config.ConfigStorage;

/**
 *
 * @author gumik
 */
public class ConfigStorageTest extends TestCase {
    
    private ConfigStorage cs;
    
    public ConfigStorageTest() {
        
    }
    
    public ConfigStorageTest(String name, TestMethod method) {
        super(name, method);
    }
    
    public void setUp() {        
        cs = ConfigStorage.getInstance();
    }
    
    public Test suite() {
        TestSuite suite = new TestSuite();
        
        suite.addTest(new ConfigStorageTest("ByteArraySimpleWriteRead", new TestMethod() {

            public void run(TestCase tc) throws Throwable {
                ((ConfigStorageTest)tc).testByteArraySimpleWriteRead();
            }
        }));
        
        suite.addTest(new ConfigStorageTest("ByteArrayDefaultValue", new TestMethod() {

            public void run(TestCase tc) throws Throwable {
                ((ConfigStorageTest)tc).testByteArrayDefaultValue();
            }
        }));
        
        suite.addTest(new ConfigStorageTest("String", new TestMethod() {

            public void run(TestCase tc) throws Throwable {
                ((ConfigStorageTest)tc).testString();
            }
        }));
                
        suite.addTest(new ConfigStorageTest("Int", new TestMethod() {

            public void run(TestCase tc) throws Throwable {
                ((ConfigStorageTest)tc).testInt();
            }
        }));
        return suite;
    }
    
    public void testByteArraySimpleWriteRead() throws RecordStoreFullException {
        byte[] b1 = new byte[] { 1, 2, 3, 4 };
        byte[] b2 = new byte[] { 4, 3, 2, 1 };
        byte[] b3 = new byte[] {  };
        byte[] b4 = new byte[] { 6, 5, 7, 3 };
        byte[] bad = null;
        
        cs.set("test1", "key1", b1);
        cs.set("test1", "key2", b2);
        cs.set("test2", "key1", b3);
        cs.set("test2", "key2", b4);
        
        assertEquals("b1", b1, cs.get("test1", "key1", bad));
        assertEquals("b2", b2, cs.get("test1", "key2", bad));
        assertEquals("b3", b3, cs.get("test2", "key1", bad));
        assertEquals("b4", b4, cs.get("test2", "key2", bad));
    }
    
    public void testByteArrayDefaultValue() throws RecordStoreFullException {
        byte[] b = new byte[] { 1, 6, 8 };        
        
        cs.delete("test", "test");
        assertEquals(null, b, cs.get("test", "test", b));
    }
    
    public void testString() throws RecordStoreFullException {
        cs.set("test3", "key1", "value1");
        cs.set("test3", "key2", "value2");
        cs.set("test4", "key1", "");
        cs.set("test4", "key2", "value4");
        String bad = null;
        
        assertEquals("3,1", "value1", cs.get("test3", "key1", bad));
        assertEquals("3,2", "value2", cs.get("test3", "key2", bad));
        assertEquals("4,1", "", cs.get("test4", "key1", bad));
        assertEquals("4,2", "value4", cs.get("test4", "key2", bad));
    }
    
    public void testInt() throws RecordStoreFullException {
        cs.set("test5", "key1", 0);
        cs.set("test5", "key2", 1);
        cs.set("test5", "key3", -1);
        cs.set("test6", "key1", Integer.MAX_VALUE);
        cs.set("test6", "key2", Integer.MIN_VALUE);
        int bad = 666;
        
        assertEquals("0", 0, cs.get("test5", "key1", bad));
        assertEquals("1", 1, cs.get("test5", "key2", bad));
        assertEquals("-1", -1, cs.get("test5", "key3", bad));
        assertEquals("max", Integer.MAX_VALUE, cs.get("test6", "key1", bad));
        assertEquals("min", Integer.MIN_VALUE,  cs.get("test6", "key2", bad));
    }
}
