package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constants.ErrorMessages;
import com.QALegendBilling.constants.ExtentLog;
import com.QALegendBilling.dataprovider.DataProviders;
import com.QALegendBilling.listeners.Retry;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Hometest extends Base {
	HomePage home;
	LoginPage login;
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(priority = 1, enabled = true, description = "TC_005_verifUserManagementDropdown", groups = {
			"Smoke" }, dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class, retryAnalyzer=Retry.class)
	public void TC_005_verifUserManagementDropdown(String username, String Password) {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		String ExpUserLoginName = data.get(1).get(2);
		String Exptitle = data.get(1).get(4);
		login = new LoginPage(driver);
		login.enterUserCredentials(username, Password);
		extentTest.get().log(Status.PASS, ExtentLog.CREDENTIALS_ENTERED_MESSAGE);
		home = login.clickOnLoginButton();
		String actUsername = home.getLoginUserName();
		home.clickEndTourButton();
		Assert.assertEquals(actUsername, ExpUserLoginName, ErrorMessages.INVALID_LOGIN_MESSAGE);
		extentTest.get().log(Status.PASS, ExtentLog.SUCCESFUL_LOGIN_MESSAGE);
		home.clickuserManagementDropdownbutton();
		boolean userMenuStatus = home.userMenuIsDisplayed();
		Assert.assertTrue(userMenuStatus, ErrorMessages.INVALID_ERROR_MESSAGE_FOUND);
		boolean rolesMenuStatus = home.rolesMenuIsDisplayed();
		Assert.assertTrue(rolesMenuStatus, ErrorMessages.INVALID_ERROR_MESSAGE_FOUND);
		boolean salesCommissionStatus = home.salesCommissionMenuIsDisplayed();
		Assert.assertTrue(salesCommissionStatus, ErrorMessages.INVALID_ERROR_MESSAGE_FOUND);
		extentTest.get().log(Status.PASS, ExtentLog.VERIFIED_USER_MANAGEMENT_DROPDOWN_ALL_VISIBLE);
	}

}
