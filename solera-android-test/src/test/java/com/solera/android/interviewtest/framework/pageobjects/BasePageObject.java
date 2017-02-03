package com.solera.android.interviewtest.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.solera.android.interviewtest.framework.ApDriver;

public class BasePageObject {
	protected ApDriver ad;
	
	public BasePageObject(ApDriver ad){
		this.ad = ad;
	}
	
	public WebElement getElement(By by){
		return ad.element().getElement(by);
	}
}
