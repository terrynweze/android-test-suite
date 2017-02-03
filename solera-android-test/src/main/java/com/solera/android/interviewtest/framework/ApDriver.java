package com.solera.android.interviewtest.framework;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.solera.android.interviewtest.framework.Platform.Platforms;

public class ApDriver {
	private WebDriver driver;
	private final Platform platform;
	private final Element element;
	private final Verification verification;
	private final Log log;

	private Platform.Platforms currentPlatform;

	DesiredCapabilities capabilities;

	public ApDriver(Platforms browserType) {
		setPlatformType(browserType);
		platform = new Platform(this);
		element = new Element(this);
		verification = new Verification(this);
		log = new Log();
	}

	/**
	 * Set the platform type
	 * 
	 * @param platformType
	 */
	public void setPlatformType(Platforms platformType) {
		this.currentPlatform = platformType;
	}

	/**
	 * Current Platform Type for this session
	 */
	public Platforms currentPlatform() {
		return currentPlatform;
	}
	
	public Platform platform() {
		return platform;
	}

	/**
	 * WebDriver object backing this ApDriver object;
	 */
	public WebDriver driver() {
		return driver;
	}

	/**
	 * Element object backing this ApDriver object;
	 */
	public Element element() {
		return element;
	}

	/**
	 * Verification object backing this ApDriver object;
	 */
	public Verification verify() {
		return verification;
	}
	
	/**
	 * Log object backing this ApDriver object;
	 */
	public Log log() {
		return log;
	}

	/**
	 * Starts a session using the specified Platform
	 *
	 * @param myPlatform
	 *            Platform to create for
	 */
	public void startSession(Platforms myPlatform) {
		try{
			startSession(myPlatform, buildDriver(myPlatform));
			} catch (MalformedURLException e) {
				System.out.println(e);
			}
	}

	/**
	 * Starts a session using the specified Platform and pre-created
	 * WebDriver
	 *
	 * @param myPlatform
	 *            Platform to create for
	 * @param driver
	 *            WebDriver to use
	 */
	public void startSession(Platforms myPlatform, WebDriver driver) {
		this.currentPlatform = myPlatform;
		this.driver = driver;
	}

	private WebDriver buildDriver(Platforms myPlatform) throws MalformedURLException {
		switch (myPlatform) {
		case Android:
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", SystemProperties.getPlatforms());
			capabilities.setCapability("platformVersion", SystemProperties.getOsVersions());
			capabilities.setCapability("deviceName", SystemProperties.getDeviceName());
			capabilities.setCapability("app", getAppPath("assignment1.apk"));
			capabilities.setCapability("appPackage", "com.hackpundit.www.assignment1");
			capabilities.setCapability("appActivity", "com.hackpundit.www.assignment1.MainActivity");
			return new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		case iOS:
			// I would initialise the ios driver here if testing was to be executed for ios too.
			// for this suite will return null instead.
			return null;
		default:
			return null;
		}
	}
	
	private String getAppPath(String app){
		return System.getProperty("user.dir") + "/apps/" + app;
	}

}
