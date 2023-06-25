package com.QALegendBilling.testscript;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.constants.ErrorMessages;
import com.QALegendBilling.dataprovider.DataProviders;
import com.QALegendBilling.listeners.TestListener;
import com.QALegendBilling.pages.AddRolesPage;
import com.QALegendBilling.pages.HomePage;
import com.QALegendBilling.pages.LoginPage;
import com.QALegendBilling.pages.SalesCommissionAgentsPage;
import com.QALegendBilling.pages.UserPage;
import com.QALegendBilling.utilities.ExcelUtility;
import com.aventstack.extentreports.ExtentTest;

public class SalesCommissionAgentsTest extends Base
{
	
	UserPage user;
	HomePage home;
	LoginPage login;
	SalesCommissionAgentsPage salescommission;
	
	ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
	
	@Test(dataProvider = "ValidUserCredentials", dataProviderClass = DataProviders.class)
	public void TC_010_verifyAddSCM(String username, String Password)
	{
		List<ArrayList<String>> data = ExcelUtility.excelDataReader("LoginPage");
		login = new LoginPage(driver);
		login.enterUserCredentials(username, Password);
		home = login.clickOnLoginButton();
		home.clickEndTourButton();
		home.clickuserManagementDropdownbutton();
		salescommission=new SalesCommissionAgentsPage(driver);
		salescommission.clickSCALink();
		salescommission.clickAddASC();
		Alert alert = driver.switchTo().alert();
		List<ArrayList<String>> data1 = ExcelUtility.excelDataReader("SCMCreds"); 
		String surname=data1.get(0).get(0);
		String SCMcontact=data1.get(1).get(0);
		String SCMaddress=data1.get(2).get(0);
		String SCMcommission=data1.get(3).get(0);
		
		String SCMfname=salescommission.random.getfName();
		String SCMlname=salescommission.random.getlName();
		String SCMemail=salescommission.random.getRandomEmail();
		salescommission.enterSCMData(surname, SCMfname, SCMlname, SCMemail, SCMcontact, SCMaddress, SCMcommission);
		salescommission.clickSCMSave();
		salescommission.searchSCM(SCMfname,SCMlname);
		String actSCMname=salescommission.searchresultSCM();
		String expSCMname=SCMfname+SCMlname;
		Assert.assertEquals(actSCMname, expSCMname, ErrorMessages.SALES_AGENT_NOT_FOUND_MESSAGE);
		
	}
}
