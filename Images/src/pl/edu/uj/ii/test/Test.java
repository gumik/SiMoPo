/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.uj.ii.test;

import j2meunit.midletui.TestRunner;

/**
 *
 * @author gumik
 */
public class Test extends TestRunner {
    protected void startApp()
    {
	start(new String[] { 
            //"pl.edu.uj.ii.test.SimpleImageComparerTest",
            "pl.edu.uj.ii.test.config.ConfigStorageTest",
        });
    }

}
