package com.solera.android.interviewtest.framework;

public class Platform {
	private ApDriver ad;

	/**
	 * Supported Platforms
	 */
	public static enum Platforms {
		Android, iOS
	}

	public Platform(ApDriver ad) {
		this.ad = ad;
	}

	/**
	 * Close application and test session.
	 */
	public void quit() {
		ad.driver().quit();
	}
}
