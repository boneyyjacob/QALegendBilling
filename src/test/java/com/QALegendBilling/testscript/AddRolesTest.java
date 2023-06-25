package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constants.ErrorMessages;
import com.QALegendBilling.dataprovider.DataProviders;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.AddRolesPage;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.UserPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.QALegendBilling.utilities.TestHelperUtility;
import com.aventstack.extentreports.ExtentTest;

public class AddRolesTest extends Base
{
	UserPage user;
	HomePage home;
	LoginPage login;
	AddRolesPage addroles;
	
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	

@Test(dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class)
public void TC_009_verifyAddRoles(String username, String Password)
{
	List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
	login = new LoginPage(driver);
	login.enterUserCredentials(username, Password);
	home = login.clickOnLoginButton();
	home.clickEndTourButton();
	home.clickuserManagementDropdownbutton();
	addroles=new AddRolesPage(driver);
	addroles.clickRolesDropdown();
	addroles.clickAddRoles();
	String userrole=addroles.random.getfName();
	addroles.enterRoleName(userrole);
	addroles.clickSaveRoles();
	addroles.searchRole(userrole);
	String actsearchresult=addroles.getSearchResults();
	System.out.println(userrole);
	Assert.assertEquals(actsearchresult, userrole, ErrorMessages.ROLES_NOT_FOUND_MESSAGE);
}
}
