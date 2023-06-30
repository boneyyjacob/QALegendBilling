package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constants.ErrorMessages;
import com.QALegendBilling.constants.ExtentLog;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.QALegendBilling.dataprovider.DataProviders;

public class Logintest extends Base {
	LoginPage login;
	HomePage home;
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(priority = 1, enabled = true, description = "TC_001_verifyUserLoginWithValidCredentials", groups = {
			"Smoke" })
	public void TC_001_verifyUserLoginWithValidCredentials() {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		String ExpUserLoginName = data.get(1).get(2);
		String userName = data.get(1).get(0);
		String pword = data.get(1).get(1);
		login = new LoginPage(driver);
		login.enterUserCredentials(userName, pword);
		extentTest.get().log(Status.PASS, ExtentLog.CREDENTIALS_ENTERED_MESSAGE);
		home = login.clickOnLoginButton();
		String actUsername = home.getLoginUserName();
		Assert.assertEquals(actUsername, ExpUserLoginName,ErrorMessages.INVALID_LOGIN_MESSAGE);
		extentTest.get().log(Status.PASS, ExtentLog.SUCCESFUL_LOGIN_MESSAGE);
	}

	@Test(priority = 1, enabled = true, description = "TC_002_verifyUserLoginWithInvalidCredentials", groups = {
			"Smoke" }, dataProvider = "InvalidUserCredentials", dataProviderClass = DataProviders.class)
	public void TC_002_verifyUserLoginWithInvalidCredentials(String userName, String userPassword) {
		List<ArrayList<String>> data1 = ExcelUtility.excelDataReader("LoginPage");
		String ExpErrorMessage = data1.get(1).get(3);
		login = new LoginPage(driver);
		login.enterUserCredentials(userName, userPassword);
		extentTest.get().log(Status.PASS, ExtentLog.CREDENTIALS_ENTERED_MESSAGE);
		login.clickOnLoginButton();
		String actErrMessage = login.getLoginErrorMessage();
		Assert.assertEquals(actErrMessage, ExpErrorMessage, ErrorMessages.INVALID_LOGIN_TEST_FAILED);
		extentTest.get().log(Status.PASS, ExtentLog.INVALID_LOGIN_MESSAGE);
	}

}
