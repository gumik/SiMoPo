/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.psm.images.test;

import j2meunit.framework.Test;
import j2meunit.framework.TestCase;
import j2meunit.framework.TestMethod;
import j2meunit.framework.TestSuite;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import pl.edu.uj.ii.SimpleImageComparer;

/**
 *
 * @author gumik
 */
public class SimpleImageComparerTest extends TestCase {
    
    private SimpleImageComparer comparer;
    
    public SimpleImageComparerTest() {
        
    }
    
    public SimpleImageComparerTest(String name, TestMethod method) {
        super(name, method);
    }
    
    public void setUp() {
        comparer = new SimpleImageComparer(0);
    }
    
    public void tearDown() {
        
    }
    
    public Test suite() {
        TestSuite suite = new TestSuite();
        
        suite.addTest(new SimpleImageComparerTest("OnePixel", new TestMethod() {

            public void run(TestCase tc) throws Throwable {
                ((SimpleImageComparerTest)tc).testOnePixel();
            }
        }));
        
        suite.addTest(new SimpleImageComparerTest("OnePixelSame", new TestMethod() {

            public void run(TestCase tc) throws Throwable {
                ((SimpleImageComparerTest)tc).testOnePixelSame();
            }
        }));
        
        suite.addTest(new SimpleImageComparerTest("SmallImageSelf", new TestMethod() {

            public void run(TestCase tc) throws Throwable {
                ((SimpleImageComparerTest)tc).testSmallImageSelf();
            }
        }));
        
        return suite;
    }
    
    public void testOnePixel() {
        int[] ints1 = new int[] { 0xff000000 };
        int[] ints2 = new int[] { 0xffffffff };
        int[] ints3 = new int[] { 0xff7f7f7f };
        
        Image img1 = Image.createRGBImage(ints1, 1, 1, false);
        Image img2 = Image.createRGBImage(ints2, 1, 1, false);
        Image img3 = Image.createRGBImage(ints3, 1, 1, false);
        
        assertEquals("img1, img2", 0, comparer.compare(img1, img2), 0.0001);
        assertEquals("img1, img3", 0.50196078431372549020, comparer.compare(img1, img3), 0.0001);
        //assertEquals("img2, img3", 1, comparer.compare(img2, img3), 0.0001);
        
        assertEquals("img2, img1", 0, comparer.compare(img2, img1), 0.0001);
        assertEquals("img3, img1", 0.50196078431372549020, comparer.compare(img3, img1), 0.0001);
        //assertEquals("img3, img2", 1, comparer.compare(img3, img2), 0.0001);
    }
    
    public void testOnePixelSame() {
        Image img1 = Image.createImage(16, 16);
        Image img2 = Image.createImage(16, 16);
        Image img3 = Image.createImage(16, 16);
        
        img1.getGraphics().setColor(0, 0, 0);
        img1.getGraphics().fillRect(0, 0, 16, 16);
        
        img2.getGraphics().setColor(255, 255, 255);
        img2.getGraphics().fillRect(0, 0, 16, 16);
        
        img3.getGraphics().setColor(127, 127, 127);
        img3.getGraphics().fillRect(0, 0, 16, 16);
        
        double t0a = comparer.compare(img1, img1);
        double t0b = comparer.compare(img2, img2);
        double t0c = comparer.compare(img3, img3);
        
        assertEquals("self img1", 1, t0a, 0.0001);
        assertEquals("self img2", 1, t0b, 0.0001);
        assertEquals("self img3", 1, t0c, 0.0001);
    }
    
    public void testSmallImageSelf() throws IOException {        
        Image img1 = Image.createImage("/pl/edu/uj/ii/test/earth.png");
        Image img2 = Image.createImage("/pl/edu/uj/ii/test/earth.png");
        
        assertEquals("self earth.png", 1, comparer.compare(img1, img2), 0.0001);
    }
}
