package com.solera.android.interviewtest.framework;

public class SystemProperties {
	//Keys, set or override defaults from command line or ide using -Dkey=value
	private final static String PLATFORM = "platform";
	private final static String OS_VERSION = "osVersion";
	private final static String DEVICE_NAME = "deviceName";
	
	//Default values
	private final static String PLATFORM_DEFAULT = "Android";
	private final static String OS_VERSION_DEFAULT = "6.0";
	private final static String DEVICE_NAME_DEFAULT = "Test_Device";
	
	//instantiations
	private final static String PLATFORMS = System.getProperties().containsKey(PLATFORM) ? System.getProperty(PLATFORM) : PLATFORM_DEFAULT;
	private final static String OS_VERSIONS = System.getProperties().containsKey(OS_VERSION) ? System.getProperty(OS_VERSION) : OS_VERSION_DEFAULT;
	private final static String DEVICE_NAMES = System.getProperties().containsKey(DEVICE_NAME) ? System.getProperty(DEVICE_NAME) : DEVICE_NAME_DEFAULT;
	
	//getter methods
	public static String getPlatforms() {
		return PLATFORMS;
	}
	public static String getDefaultPlatform() {
		return PLATFORM_DEFAULT;
	}
	
	public static String getOsVersions() {
		return OS_VERSIONS;
	}
	public static String getDeviceName() {
		return DEVICE_NAMES;
	}
}
