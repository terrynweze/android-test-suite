package com.solera.android.interviewtest.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {
	private ApDriver ad;
	private int globalSeTimeOut = 20;
    
    public Element(ApDriver ad){
		this.ad = ad;
    }
    
    /**
     * Wait for an element to be click-able
     *
     * @param locator
     * @param timeOutInSeconds
     * @return
     */
    public Element waitForElementToBeClickable(final By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(ad.driver(), timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return this;
        } catch (Throwable t) {
            System.out.println(t);
            return null;
        } 
    }

    public Element clickElement(final By locator) {
        // Build logging message from options
        try {
            new WebDriverWait(ad.driver(), globalSeTimeOut)
                    .ignoring(Throwable.class)
                    .withMessage("Unable to click on element")
                    .until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {

                            // Find targetElement
                            WebElement targetElement = null;
                            targetElement = ad.driver().findElement(locator);
                            targetElement.click();
                            
                            // Check for success locator
                            boolean success;
                            success = (targetElement != null);
                            return success;
                        }
                    });
            return this;
        } catch (Throwable t) {
            System.out.println(t);
            return null;
        }
    }
    
    /**
     * Finds and returns the element
     *
     * @param locator
     * @return
     */
    public WebElement getElement(final By locator) {
        try {
            return ad.driver().findElement(locator);
        } catch (Throwable t) {
            System.out.println(t);
            return null;
        } 
    }

    /**
     * Gets the current setting for the timeout
     *
     * @return the current timeout setting
     */
    public int getTimeOut() {
        return globalSeTimeOut;
    }
}
