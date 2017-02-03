package com.solera.android.interviewtest.framework;

import org.testng.Assert;

public class Verification {
    private ApDriver ad;

    public Verification(ApDriver ad) {
        this.ad = ad;
    }
    
    public void assertEquals(String testname, boolean actual, boolean expected){
    	Assert.assertEquals(actual, expected,testname);
    }
    
    public void assertEquals(String testname, String actual, String expected){
    	Assert.assertEquals(actual, expected,testname);
    }
    
    public void assertTrue(String testname, boolean actual) {
    	assertEquals(testname, actual, true);
    }
}
