package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constants.ErrorMessages;
import com.QALegendBilling.dataprovider.DataProviders;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.UserPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.QALegendBilling.utilities.TableUtility;
import com.aventstack.extentreports.ExtentTest;



public class UserTest extends Base
{
	UserPage user;
	HomePage home;
	LoginPage login;
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	
	
	@Test(dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class)
	public void TC_006_verifyAddUser(String username, String Password)
	{
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
		home.clickUserButton();
		user = new UserPage(driver);
		user.clickUserAddButton();
		user.enterTitle(Exptitle);
		user.enterFirstName();
		user.enterLastName();
		user.enterUserEmail();
		user.enterUserName();
		user.enterUserPassword();
		user.enterConfirmPassword();
		List<ArrayList<String>> data1 = ExcelUtility.excelDataReader("UserPageData");
		String prefix=data1.get(0).get(0);
		String salesCom=data1.get(1).get(0);
		user.enterTitle(prefix);
		user.enterCommission(salesCom);
		user.clickSaveButton();
		user.searchUser();
		String ExpUseremail=user.email;
		System.out.println(ExpUseremail);	
	}
	@Test(dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class)
	public void TC_007_verifyInvalidUserSearch(String username, String Password)
	{
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
		home.clickUserButton();
		user = new UserPage(driver);
		user.searchInvalidUser();
		//String actinvlaidMsg=user.getInvalidUserMessage();
		List<ArrayList<String>> data1 = ExcelUtility.excelDataReader("UserPageData");
		String expMsg=data1.get(2).get(0);
		//Assert.assertEquals(actinvlaidMsg, expMsg, ErrorMessages.INVALID_USER_MESSAGE_NOT_FOUND);
	}
}
