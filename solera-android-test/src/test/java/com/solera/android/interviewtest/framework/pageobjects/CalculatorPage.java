package com.solera.android.interviewtest.framework.pageobjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.solera.android.interviewtest.framework.ApDriver;
import com.solera.android.interviewtest.framework.utils.CalculationUtils;

public class CalculatorPage extends BasePageObject {
	private By calculator_page = By.id("relative1");
	private By data_field = By.id("edt1");
	private CalculationUtils cu;
	
	
	
	public CalculatorPage(ApDriver ad){
		super(ad);
		
		// creates a utility object that assists with calculations
		cu = new CalculationUtils(ad);
	}
	
	protected CalculatorPage waitForPageToLoad(int... secondsToWait) {
		// logs a trace step within the report
		ad.log().logStepTrace("Waiting for Calculator Page To Load ...");
		
		// determine how long to wait
		int secsToWait = ad.element().getTimeOut();
		if (secondsToWait.length > 0) {
			secsToWait = secondsToWait[0];
		}

		// perform the wait
		if (ad.element().waitForElementToBeClickable(calculator_page, secsToWait) != null)
			return this;
		else
			return null;
	}
	
	// Given methods, used to get the test into and ensure that is in a specific state
	
	public CalculatorPage givenIAmViewingTheCalculatorApp(){
		// logs a test step within the report
		ad.log().logTestStep("Given I Am Viewing The Calculator App");
		
		ad.verify().assertTrue("Calculator app is not displayed as expected.", waitForPageToLoad() != null);
		return this;
	}
	
	public CalculatorPage givenTheResultsAreDisplayedForAPreviousCalculation(){
		ad.log().logTestStep("Given The Results Are Displayed For A Previous Calculation");
		
		whenIAdd2PositiveNumbersTogether("10","10")
		.whenIInvokeTheEqualsButton()
		.thenTheOutputtedResultIs("20.0");
		return this;
	}
	
	// When methods, the specific test actions to make within a test
	
	
	public CalculatorPage whenIAdd2PositiveNumbersTogether(String firstNum, String secondNum){
		ad.log().logTestStep("When I Add Two Positive Numbers Together");
		
		whenIEnterTheValue(firstNum);
		whenIAddTheNumber(secondNum);
		return this;
	}
	
	public CalculatorPage whenIMultiply2PositiveNumbersTogether(String firstNum, String secondNum){
		ad.log().logTestStep("When I Multiply Two Positive Numbers Together");
		
		whenIEnterTheValue(firstNum);
		whenIMultiplyTheNumber(secondNum);
		return this;
	}
	
	public CalculatorPage whenIEnterTheValue(String num){
		ad.log().logTestStep("When I Enter The Value " + num);
		
		cu.enterValues(cu.getDataInputAsArray(num));
		return this;
	}
	
	public CalculatorPage whenIAddTheNumber(String num){
		ad.log().logTestStep("When I Add The Number " + num);
		
		cu.invokeOperator(CalculationUtils.ADD_OPERATOR);
		cu.enterValues(cu.getDataInputAsArray(num));
		return this;		
	}
	
	public CalculatorPage whenIMultiplyTheNumber(String num){
		ad.log().logTestStep("When I Multiply The Number " + num);
		
		cu.invokeOperator(CalculationUtils.MUL_OPERATOR);
		cu.enterValues(cu.getDataInputAsArray(num));
		return this;
	}
	
	public CalculatorPage whenISubtractTheValue(String num){
		ad.log().logTestStep("When I Subtract The Value " + num);
		
		cu.invokeOperator(CalculationUtils.SUB_OPERATOR);
		cu.enterValues(cu.getDataInputAsArray(num));
		return this;
	}
	
	public CalculatorPage whenIStartToAddANumberAndClearIt(){
		ad.log().logTestStep("When I Start To Add A Number And Clear It");
		
		whenIAddTheNumber("15");
		cu.invokeClear();
		return this;
	}
	
	public CalculatorPage whenIInvokeTheEqualsButton() {
		ad.log().logTestStep("When I Invoke The Equals Button");
		
		cu.invokeEquals();
		return this;
	}
	
	// Then methods, used to verify that the test actions produce the correct results.
	
	public CalculatorPage thenTheOutputtedResultIsANegativeValue(){
		ad.log().logTestStep("Then The Outputted Result Is A Negative Value");
		
		ad.verify().assertEquals("Result is not negative as expected, should start with a '-'", getElement(data_field).getText().substring(0,1), "-");
		return this;
	}
	
	public CalculatorPage thenTheOutputFieldIsCleared(){
		ad.log().logTestStep("Then The Output Field Is Cleared");
		
		ad.verify().assertTrue("Output Field is not cleared as expected", getElement(data_field).getText().isEmpty());
		return this;
	}
	
	
	public CalculatorPage thenTheOutputtedResultIs(String result) {
		ad.log().logTestStep("Then The Outputted Result Is " + result);
		
		ad.verify().assertEquals("Incorrect value seen in the output field:", getElement(data_field).getText(), result);
		return this;
	}
}
