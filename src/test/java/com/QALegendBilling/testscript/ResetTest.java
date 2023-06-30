package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constants.ErrorMessages;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.ResetPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.QALegendBilling.utilities.RandomUtility;
import com.aventstack.extentreports.ExtentTest;

public class ResetTest extends Base {
	LoginPage login;
	ResetPage reset;
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(priority = 1, enabled = true, description = "TC_003_verifyForgotPassword", groups = { "Regression" })
	public void TC_003_verifyForgotPassword() {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("ResetPage");
		String expInvalidMessage = data.get(1).get(0);
		login = new LoginPage(driver);
		reset = login.clickForgotPassword();
		String invalidEmail = RandomUtility.getRandomEmail();
		reset.enterInvalidEmail(invalidEmail);
		reset.resetButtonClick();
		String actInvalidMessage = reset.getInvalidResetMessage();
		Assert.assertEquals(actInvalidMessage, expInvalidMessage, ErrorMessages.INVALID_ERROR_MESSAGE_FOUND);
	}

}
