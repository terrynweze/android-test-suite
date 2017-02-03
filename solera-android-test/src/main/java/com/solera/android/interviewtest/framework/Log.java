package com.solera.android.interviewtest.framework;

import org.testng.Reporter;

public class Log {
	public void logTestName(String platform, String testname){
		logPlatformName(platform.toUpperCase());
		logMessage("   TEST: " + testname.toUpperCase());
	}
	
	public void logTestStep(String msg){
		logMessage("     TEST STEP: " + msg);
	}
	
	public void logStepTrace(String msg){
		logMessage("       TRACE: " + msg);
	}
	
	public void logProblem(String msg){
		logMessage("         FAILURE: " + msg);
	}
	
	public void logSuccess(String msg){
		logMessage("         SUCCESS: " + msg);
	}
	
	public void logPlatformName(String name){
		logMessage("TEST PLATFORM: " + name);
	}
	
	private void logMessage(String msg){
		Reporter.log(msg);
	}
}
