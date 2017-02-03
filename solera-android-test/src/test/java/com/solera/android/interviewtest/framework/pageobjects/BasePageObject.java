package com.solera.android.interviewtest.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.solera.android.interviewtest.framework.ApDriver;

/**
 * @author terry.nweze
 * 
 * A super class for all page classes.
 * 
 * Initiates the ApDriver object and will contain methods that can be used in many or all Page classes
 *
 */
public class BasePageObject {
	protected ApDriver ad;
	
	public BasePageObject(ApDriver ad){
		this.ad = ad;
	}
	
	/**
	 * @param by
	 * 		represents the element within the calculator application
	 * @return
	 * 		the WebElement that is associated with the passed in By
	 * 
	 * Returns the WebElement from the calculator application for the passed in By
	 * 
	 */
	public WebElement getElement(By by){
		return ad.element().getElement(by);
	}
}
