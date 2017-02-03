package com.solera.android.interviewtest.framework.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

import com.solera.android.interviewtest.framework.ApDriver;
import com.solera.android.interviewtest.framework.pageobjects.BasePageObject;

public class CalculationUtils {
	public static final int ADD_OPERATOR = 0;
	public static final int SUB_OPERATOR = 1;
	public static final int MUL_OPERATOR = 2;
	public static final int DIV_OPERATOR = 3;
	public static final int EQL_OPERATOR = 4;
	
	private Map<String,By> digits;
	private Map<String,By> operators;
	private By button_clear = By.id("buttonC");	
	
	private ApDriver ad;
	
	
	public CalculationUtils(ApDriver ad){
		this.ad = ad;
		digits = setupNumericalInputFields();
		operators = setupOperatorFields();
	}
	
	/**
	 * @param value
	 */
	public void enterValues(char[] value){
		String inputString = "";
		ad.log().logStepTrace("Inputting the value: ");
		
		for (char val : value) {
			if(".".equalsIgnoreCase(String.valueOf(val))){
				ad.element().clickElement(digits.get("button_dec"));
			} else{
				ad.element().clickElement(digits.get("button_" + val));
			}
			
			inputString += val;
        }
		
		ad.log().logStepTrace("Entered the value: " + inputString);
	}
	
	/**
	 * @param input
	 * @return
	 */
	public char[] getDataInputAsArray(String input){
		return input.toCharArray();
	}
	
	/**
	 * @param operator
	 */
	public void invokeOperator(int operator){
		ad.log().logStepTrace("Invoking Operator");
		String opStr = "";
		
		switch (operator) {
			case ADD_OPERATOR:
				ad.element().clickElement(operators.get("button_add"));
				opStr += "'+'";
				break;
			case SUB_OPERATOR:
				ad.element().clickElement(operators.get("button_sub"));
				opStr += "'-'";
				break;
			case MUL_OPERATOR:
				ad.element().clickElement(operators.get("button_mul"));
				opStr += "'*'";
				break;
			case DIV_OPERATOR:
				ad.element().clickElement(operators.get("button_div"));
				opStr += "'/'";
				break;
			case EQL_OPERATOR:
				opStr += "'='";
				ad.element().clickElement(operators.get("button_eql"));
				break;
		}
		
		ad.log().logStepTrace("Invoked Operator: " + opStr);
	}
	
	public void invokeEquals(BasePageObject page){
		invokeOperator(EQL_OPERATOR);
	}
	
	public void invokeClear(){
		ad.log().logStepTrace("Invoking Clear Button");
		
		ad.element().clickElement(button_clear);
	}
	
	/**
	 * @return
	 */
	public Map<String,By> setupNumericalInputFields(){
		Map<String,By> digits = new HashMap<String,By>();
		
		digits.put("button_0", By.id("button0"));
		digits.put("button_1", By.id("button1"));
		digits.put("button_2", By.id("button2"));
		digits.put("button_3", By.id("button3"));
		digits.put("button_4", By.id("button4"));
		digits.put("button_5", By.id("button5"));
		digits.put("button_6", By.id("button6"));
		digits.put("button_7", By.id("button7"));
		digits.put("button_8", By.id("button8"));
		digits.put("button_9", By.id("button9"));
		digits.put("button_dec", By.id("button10"));
				
		return digits;
	}
	
	/**
	 * @return
	 */
	public Map<String,By> setupOperatorFields(){
		Map<String,By> operators = new HashMap<String,By>();
		
		operators.put("button_add", By.id("buttonadd"));
		operators.put("button_sub", By.id("buttonsub"));
		operators.put("button_mul", By.id("buttonmul"));
		operators.put("button_div", By.id("buttondiv"));
		operators.put("button_eql", By.id("buttoneql"));
		
		return operators;
	}
}
