package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constants.ErrorMessages;
import com.QALegendBilling.constants.ExtentLog;
import com.QALegendBilling.dataprovider.DataProviders;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.AddRolesPage;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.UserPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.QALegendBilling.utilities.RandomUtility;
import com.QALegendBilling.utilities.TestHelperUtility;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AddRolesTest extends Base {
	UserPage user;
	HomePage home;
	LoginPage login;
	AddRolesPage addroles;

	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

	@Test(priority = 1, enabled = true, description = "TC_009_verifyAddRoles", groups = {
			"Regression" }, dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class)
	public void TC_009_verifyAddRoles(String username, String Password) {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		login = new LoginPage(driver);
		login.enterUserCredentials(username, Password);
		extentTest.get().log(Status.PASS, ExtentLog.CREDENTIALS_ENTERED_MESSAGE);
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		home.clickuserManagementDropdownbutton();
		addroles = new AddRolesPage(driver);
		addroles.clickRolesDropdown();
		addroles.clickAddRoles();
		extentTest.get().log(Status.PASS, ExtentLog.CLICKED_ADD_ROLES);
		String userrole = RandomUtility.getfName();
		addroles.enterRoleName(userrole);
		addroles.clickSaveRoles();
		addroles.searchRole(userrole);
		String actsearchresult = addroles.getSearchResults();
		System.out.println(userrole);
		Assert.assertEquals(actsearchresult, userrole, ErrorMessages.ROLES_NOT_FOUND_MESSAGE);
		extentTest.get().log(Status.PASS, ExtentLog.VERIFIED_ADD_ROLES);
	}

	@Test(priority = 1, enabled = true, description = "TC_010_verifyEditRoles", groups = {
			"Regression" }, dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class)
	public void TC_010_verifyEditRoles(String username, String Password) {
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		login = new LoginPage(driver);
		login.enterUserCredentials(username, Password);
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		home.clickuserManagementDropdownbutton();
		addroles = new AddRolesPage(driver);
		addroles.clickRolesDropdown();
		addroles.clickAddRoles();
		String userrole = RandomUtility.getfName();
		addroles.enterRoleName(userrole);
		addroles.clickSaveRoles();
		addroles.searchRole(userrole);
		addroles.editRoles();
		extentTest.get().log(Status.PASS, ExtentLog.CLICKED_EDIT_ROLES);
		String actrolesheader = addroles.getRolesheader();
		List<ArrayList<String>> data1 = ExcelUtility.excelDataReader("Roles");
		String expRolesheader = data1.get(0).get(0);
		Assert.assertEquals(actrolesheader, expRolesheader, ErrorMessages.ROLES_HEADER_NOT_FOUND_MESSAGE);
		extentTest.get().log(Status.PASS, ExtentLog.VERIFIED_EDIT_ROLES);
	}
}
