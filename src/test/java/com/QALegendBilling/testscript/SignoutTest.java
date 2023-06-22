package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.dataprovider.DataProviders;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.aventstack.extentreports.ExtentTest;

public class SignoutTest extends Base 
{
	HomePage home;
	LoginPage login;
	
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	
	@Test(dataProvider="ValidUserCredentials",dataProviderClass=DataProviders.class)
	public void TC_004_verifySignout(String username, String Password)
	{
		List<ArrayList<String>> data=ExcelUtility.excelDataReader("LoginPage");
		String ExpUserLoginName=data.get(1).get(2);
		String Exptitle=data.get(1).get(4);
		login=new LoginPage(driver);
		login.enterUserCredentials(username, Password);
		home=login.clickOnLoginButton();
		String actUsername=home.getLoginUserName();
		home.clickEndTourButton();
		Assert.assertEquals(actUsername, ExpUserLoginName,com.QALegendBilling.constants.ErrorMessages.INVALID_LOGIN_MESSAGE);
		home.clickuserProfileDropdownButton();
		home.clicksignoutButton();
		String ActTitle=login.getTitleLoginPage();
		//System.out.println(Exptitle);
		//System.out.println(ActTitle);
		Assert.assertEquals(ActTitle, Exptitle,com.QALegendBilling.constants.ErrorMessages.INVALID_TITLE_FOUND_MESSAGE);	
	}
}
