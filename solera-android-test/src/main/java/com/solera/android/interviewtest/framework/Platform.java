package com.solera.android.interviewtest.framework;

public class Platform {
	private ApDriver ad;

	/**
	 * Supported Browsers
	 */
	public static enum Platforms {
		Android, iOS
	}

	public Platform(ApDriver ad) {
		this.ad = ad;
	}

	/**
	 * Close browser and browser session.
	 * <p/>
	 * !! Should be called after all tests !!
	 */
	public void quit() {
		ad.driver().quit();
	}
}
