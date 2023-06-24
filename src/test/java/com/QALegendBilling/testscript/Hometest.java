package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constants.ErrorMessages;
import com.QALegendBilling.dataprovider.DataProviders;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.aventstack.extentreports.ExtentTest;

public class Hometest extends Base {
	HomePage home;
	LoginPage login;
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class)
	public void TC_005_verifUserManagementDropdown(String username, String Password) {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		String ExpUserLoginName = data.get(1).get(2);
		String Exptitle = data.get(1).get(4);
		login = new LoginPage(driver);
		login.enterUserCredentials(username, Password);
		home = login.clickOnLoginButton();
		String actUsername = home.getLoginUserName();
		home.clickEndTourButton();
		Assert.assertEquals(actUsername, ExpUserLoginName,ErrorMessages.INVALID_LOGIN_MESSAGE);
		home.clickuserManagementDropdownbutton();
		boolean userMenuStatus=home.userMenuIsDisplayed();
		Assert.assertTrue(userMenuStatus,ErrorMessages.INVALID_ERROR_MESSAGE_FOUND);
		boolean rolesMenuStatus=home.rolesMenuIsDisplayed();
		Assert.assertTrue(rolesMenuStatus,ErrorMessages.INVALID_ERROR_MESSAGE_FOUND);
		boolean salesCommissionStatus=home.salesCommissionMenuIsDisplayed();
		Assert.assertTrue(salesCommissionStatus,ErrorMessages.INVALID_ERROR_MESSAGE_FOUND);
	}
	
}
