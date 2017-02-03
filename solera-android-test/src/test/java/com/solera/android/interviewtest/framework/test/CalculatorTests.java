package com.solera.android.interviewtest.framework.test;

import org.testng.annotations.Test;

import com.solera.android.interviewtest.framework.ApDriver;
import com.solera.android.interviewtest.framework.BaseTest;
import com.solera.android.interviewtest.framework.SystemProperties;
import com.solera.android.interviewtest.framework.pageobjects.CalculatorPage;

public class CalculatorTests extends BaseTest {
	
	/**
	 * @param driver driver object used to interact with the application UI
	 * 
	 * Tests that the sum of two positive values are as expected
	 * 
	 * Steps:
	 * 1. Enters a Value
	 * 2. Invokes the '+' button
	 * 3. Enter another Value
	 * 4. Invokes the '=' button
	 * 5. Verifies that the outputted result is the sum of the two values entered.
	 * 
	 */
	@Test(description = "", dataProvider = "DefaultDataProvider", groups = { "sanity" })
	public void addingTwoValuesProducesCorrectResult(ApDriver ad) {
		// writes to the html report (Provides the test platform, os version and name details)
		ad.log().logTestName(ad.currentPlatform().toString() + ": " + SystemProperties.getOsVersions() + ": ","Adding Two Values Produces Correct Result");
		
		// create a CalculatorPage object to start testing from
		CalculatorPage calculatorPage = new CalculatorPage(ad);

		// Tests are written in a given when then format. Methods return the page that is expected to be seen after the method completes
		calculatorPage.givenIAmViewingTheCalculatorApp()
			.whenIAdd2PositiveNumbersTogether("5","10")
			.whenIInvokeTheEqualsButton()
			.thenTheOutputtedResultIs("15.0");
		
	}
	
	/**
	 * @param driver driver object used to interact with the application UI
	 * 
	 * Tests that partially clearing a calculation works as expected.
	 * 
	 * Steps:
	 * 1. Enters a value
	 * 2. Invokes the '+' button
	 * 3. Enters A Value
	 * 4. Invokes the 'C' button
	 * 5. Verify that the data output field has cleared
	 * 6. Enters A Value (different to the one that was cleared)
	 * 7. Invokes the '=' button
	 * 8. Ensures the result for the calculation is as expected (i.e. the data that was cleared was not included in the calculation).
	 * 
	 */
	@Test(description = "", dataProvider = "DefaultDataProvider", groups = { "sanity" })
	public void partialClearCalculationWorksAsExpected(ApDriver ad) {
		ad.log().logTestName(ad.currentPlatform().toString() + ": " + SystemProperties.getOsVersions() + ": ","Partial Clear Calculation Works As Expected");
		
		CalculatorPage calculatorPage = new CalculatorPage(ad);
		
		calculatorPage.givenIAmViewingTheCalculatorApp()
			.whenIEnterTheValue("10")
			.whenIStartToAddANumberAndClearIt()
			.thenTheOutputFieldIsCleared()
			.whenIEnterTheValue("13")
			.whenIInvokeTheEqualsButton()
			.thenTheOutputtedResultIs("23.0");
	}
	
	
	/**
	 * @param driver object used to interact with the application UI
	 * 
	 * Tests that calculations that result in a negative value being produced displays as expected
	 * 
	 * Steps:
	 * 1. Enters A Value
	 * 2. Invokes the '-' button
	 * 3. Enters A Value that is greater than the first value entered
	 * 4. Verifies that the outputted result is the correct value for the calculation conducted
	 * 5. Verifies that the outputted result starts with a '-'.
	 * 
	 */
	@Test(description = "", dataProvider = "DefaultDataProvider", groups = { "sanity" })
	public void negativeResultsDisplayAsExpected(ApDriver ad) {
		ad.log().logTestName(ad.currentPlatform().toString() + ": " + SystemProperties.getOsVersions() + ": ","Negative Results Display As Expected");
		
		CalculatorPage calculatorPage = new CalculatorPage(ad);
		
		calculatorPage.givenIAmViewingTheCalculatorApp()
			.whenIEnterTheValue("10")
			.whenISubtractTheValue("12")
			.whenIInvokeTheEqualsButton()
			.thenTheOutputtedResultIs("-2.0")
			.thenTheOutputtedResultIsANegativeValue();
	}
	
	/**
	 * @param driver driver object used to interact with the application UI
	 * 
	 * Tests that if you add data after a calculation, the new calculation starts from fresh.
	 * 
	 * Steps:
	 * 1. Enters a value
	 * 2. Invokes the '+' button
	 * 3. Enters A Value
	 * 4. Invokes the '=' button
	 * 8. Ensures the result for the calculation is as expected
	 * 5. Enters a NEW value
	 * 6. Verifies that the output screen clears and only displays the NEW value entered.
	 * 
	 */
	@Test(description = "", dataProvider = "DefaultDataProvider", groups = { "sanity" })
	public void enteringValuesAfterACalculationStartsAFreshCalculation(ApDriver ad) {
		ad.log().logTestName(ad.currentPlatform().toString() + ": " + SystemProperties.getOsVersions() + ": ","Entering Values After A Calculation Starts A Fresh Calculation");
		
		CalculatorPage calculatorPage = new CalculatorPage(ad);
		
		String inputVal = "22";
		
		calculatorPage.givenIAmViewingTheCalculatorApp()
			.givenTheResultsAreDisplayedForAPreviousCalculation()
			.whenIEnterTheValue(inputVal)
			.thenTheOutputtedResultIs(inputVal);
	}
	
	/**
	 * @param driver driver object used to interact with the application UI
	 * 
	 * Tests that multiplications using values that contain decimal points displays the correct results
	 * 
	 * Steps:
	 * 1. Enters the value 7.5
	 * 2. Invokes the '*' button
	 * 3. Enters the value 2.3
	 * 4. Invokes the '=' button
	 * 5. Verifies that the output field displays the result as 17.25
	 * 
	 */
	@Test(description = "", dataProvider = "DefaultDataProvider", groups = { "sanity" })
	public void multiplyingTwoValuesWithDecimalsProducesCorrectResult(ApDriver ad) {
		ad.log().logTestName(ad.currentPlatform().toString() + ": " + SystemProperties.getOsVersions() + ": ","Multiplying Two Values With Decimals Produces Correct Result");
		
		CalculatorPage calculatorPage = new CalculatorPage(ad);
		
		calculatorPage.givenIAmViewingTheCalculatorApp()
			.whenIMultiply2PositiveNumbersTogether("7.5","2.3")
			.whenIInvokeTheEqualsButton()
			.thenTheOutputtedResultIs("17.25");	
	}
}
