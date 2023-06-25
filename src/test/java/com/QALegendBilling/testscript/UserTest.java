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
		String actemail=user.getEmailID();
		Assert.assertEquals(actemail, ExpUseremail,ErrorMessages.USER_NOT_FOUND_MESSAGE);
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
		System.out.println(expMsg);
		//Assert.assertEquals(actinvlaidMsg, expMsg, ErrorMessages.INVALID_USER_MESSAGE_NOT_FOUND);
		//td[@class='dataTables_empty']
		String actMsg=user.getInvalidSearchText();
		Assert.assertEquals(actMsg, expMsg, ErrorMessages.INVALID_USER_MESSAGE_NOT_FOUND);
	}
	@Test(dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class)
	public void TC_008_verifyNewlyAddedUserLogin(String username, String Password) throws InterruptedException
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
		
		Thread.sleep(15000);
		user.userProfileclick();
		user.userProfileLogout();
		
		String newUname=user.newLoginUserName();
		String newPWord=user.newLoginUserPword();
		login.enterUserCredentials(newUname, newPWord);
		login.clickOnLoginButton();
		String actNewUsername=home.getLoginUserName();
		String ExpNewUsername=user.newLoginName();
		Assert.assertEquals(actNewUsername, ExpNewUsername,com.QALegendBilling.constants.ErrorMessages.INVALID_LOGIN_MESSAGE);
	
	}
}
