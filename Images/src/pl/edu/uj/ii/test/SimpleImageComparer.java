/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.test;

import j2meunit.framework.Test;
import j2meunit.framework.TestCase;
import j2meunit.framework.TestMethod;
import j2meunit.framework.TestSuite;
import javax.microedition.lcdui.Image;

/**
 *
 * @author gumik
 */
public class SimpleImageComparer extends TestCase {
    
    private pl.edu.uj.ii.SimpleImageComparer comparer;
    
    public SimpleImageComparer() {
        
    }
    
    public SimpleImageComparer(String name, TestMethod method) {
        super(name, method);
    }
    
    public void setUp() {
        comparer = new pl.edu.uj.ii.SimpleImageComparer(0);
    }
    
    public void tearDown() {
        
    }
    
    public Test suite() {
        TestSuite suite = new TestSuite();
        
        suite.addTest(new SimpleImageComparer("OnePixel", new TestMethod() {

            public void run(TestCase tc) throws Throwable {
                ((SimpleImageComparer)tc).testOnePixel();
            }
        }));
        
        return suite;
    }
    
    public void testOnePixel() {
        Image img1 = Image.createImage(1, 1);
        Image img2 = Image.createImage(1, 1);
        Image img3 = Image.createImage(1, 1);
        
        img1.getGraphics().setColor(0, 0, 0);
        img1.getGraphics().drawLine(0, 0, 0, 0);
        img1.getGraphics().fillRect(0, 0, 0, 0);
        
        img2.getGraphics().setColor(255, 255, 255);
        img2.getGraphics().drawLine(0, 0, 0, 0);
        img2.getGraphics().fillRect(0, 0, 0, 0);
        
        img3.getGraphics().setColor(127, 127, 127);
        img3.getGraphics().drawLine(0, 0, 0, 0);
        img3.getGraphics().fillRect(0, 0, 0, 0);
        
        double t1 = comparer.compare(img1, img2);
        double t2 = comparer.compare(img2, img3);
        double t3 = comparer.compare(img1, img3);
        
        double t1a = comparer.compare(img2, img1);
        double t2a = comparer.compare(img3, img2);
        double t3a = comparer.compare(img3, img1);
        
        double e1 = 0;
        double e2 = 0.49803921568627450980;
        double e3 = 1 - e2;
        
        assertTrue("t1 == e1", doubleEquals(t1, e1, 0.0001));
        assertTrue("t2 == e2", doubleEquals(t2, e2, 0.0001));
        assertTrue("t3 == e3", doubleEquals(t3, e3, 0.0001));
        
        assertTrue("t1a == e1", doubleEquals(t1a, e1, 0.0001));
        assertTrue("t2a == e2", doubleEquals(t2a, e2, 0.0001));
        assertTrue("t3a == e3", doubleEquals(t3a, e3, 0.0001));
    }
    
    private boolean doubleEquals(double d1, double d2, double eps) {
        return Math.abs(d1 - d2) <= eps;
    }
}
