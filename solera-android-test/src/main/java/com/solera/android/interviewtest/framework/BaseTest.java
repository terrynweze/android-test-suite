package com.solera.android.interviewtest.framework;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.solera.android.interviewtest.framework.Platform.Platforms;

public abstract class BaseTest {
	@BeforeMethod(alwaysRun = true)
	protected void beforeMethod(Object[] params) {
		ApDriver ad = ((ApDriver) params[0]);
		Platforms myPlatform = ad.currentPlatform();
		ad.startSession(myPlatform);
	}

	@AfterMethod(alwaysRun = true)
	protected void afterMethod(Object[] params) {
		ApDriver ad = ((ApDriver) params[0]);
		ad.platform().quit();
	}

	@DataProvider(name = "DefaultDataProvider", parallel = true)
	public Object[][] DefaultDataProvider() {
		return createApDriver(getPlatformList(SystemProperties.getPlatforms(), ","));
	}
	
	public static Object[][] createApDriver(List<Platform.Platforms> platforms) {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (Platform.Platforms platform : platforms) {
        	List<Object> adHelper = new ArrayList<Object>();
        	ApDriver ad = new ApDriver(platform);
            adHelper.add(ad);
            list.add(adHelper);
        }

        return objectArrayFromList(list);
    }
	

	public static List<Platform.Platforms> getPlatformList(String delimitedString, String delimiter) {
		List<Platform.Platforms> list = new ArrayList<Platform.Platforms>();
		for (String cellContents : delimitedString.split(delimiter)) {
			try {
				list.add(Platform.Platforms.valueOf(cellContents));
			} catch (IllegalArgumentException e) {
				System.out.println("unrecognized platform in pom.xml: '" + cellContents + "' reverting to default platform: " + SystemProperties.getDefaultPlatform());
				list.add(Platform.Platforms.valueOf(SystemProperties.getDefaultPlatform()));
			}
		}
		return list;
	}
	
	private static Object[][] objectArrayFromList(List<List<Object>> list) {
   	 Object[][] objectArray = new Object[list.size()][];
        for (int row = 0; row < list.size(); row++) {
       	 objectArray[row] = list.get(row).toArray();
        }
        return objectArray;
   }

}
