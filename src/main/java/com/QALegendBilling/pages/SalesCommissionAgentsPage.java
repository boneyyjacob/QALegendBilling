package com.QALegendBilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.QALegendBilling.utilities.TestHelperUtility;

public class SalesCommissionAgentsPage extends TestHelperUtility
{
	public WebDriver driver;

	public SalesCommissionAgentsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private final String _SalesCommissionAgentsDropdownLink = "//i[@class='fa fa-handshake-o']";
	@FindBy(xpath = _SalesCommissionAgentsDropdownLink)
	private WebElement SalesCommissionAgentsDropdownLink;
	
	private final String _AddSalesCommissionAgentsButton = "//button[@class='btn btn-primary btn-modal pull-right']";
	@FindBy(xpath = _AddSalesCommissionAgentsButton)
	private WebElement AddSalesCommissionAgentsButton;
	
	
	
	private final String _SCMprefixField = "surname";
	@FindBy(id = _SCMprefixField)
	private WebElement SCMprefixField;
	
	private final String _SCMFnameField = "first_name";
	@FindBy(id = _SCMFnameField)
	private WebElement SCMFnameField;
	
	private final String _SCMLnameField = "last_name";
	@FindBy(id = _SCMLnameField)
	private WebElement SCMLnameField;
	
	private final String _SCMEmailField = "email";
	@FindBy(id = _SCMEmailField)
	private WebElement SCMEmailField;
	
	private final String _SCMContactField = "contact_no";
	@FindBy(id = _SCMContactField)
	private WebElement SCMContactField;
	
	private final String _SCMAddressField = "address";
	@FindBy(id = _SCMAddressField)
	private WebElement SCMAddressField;
	
	private final String _SCMPercentageField = "cmmsn_percent";
	@FindBy(id = _SCMPercentageField)
	private WebElement SCMPercentageField;
	
	private final String _SCMSaveButton = "//button[@class='btn btn-primary']";
	@FindBy(xpath = _SCMSaveButton)
	private WebElement SCMSaveButton;
	
	private final String _SCMSearchField = "//input[@class='form-control input-sm']";
	@FindBy(xpath = _SCMSearchField)
	private WebElement SCMSearchField;
	
	private final String _SCMSearchResult = "//td[@class='sorting_1']";
	@FindBy(xpath = _SCMSearchResult)
	private WebElement SCMSearchResult;
	
	
	public void clickSCALink()
	{
		SalesCommissionAgentsDropdownLink.click();
	}
	public void clickAddASC()
	{
		AddSalesCommissionAgentsButton.click();
	}
	public void enterSCMData(String title, String fname, String lname, String email, String contact, String address, String scm)
	{
		page.enterText(SCMprefixField, title);
		page.enterText(SCMFnameField, fname);
		page.enterText(SCMLnameField, lname);
		page.enterText(SCMEmailField, email);
		page.enterText(SCMContactField, contact);
		page.enterText(SCMAddressField, address);
		page.enterText(SCMPercentageField, scm);
	}
	
public void clickSCMSave()
{
	SCMSaveButton.click();
}
public void searchSCM(String firstn, String lastn)
{
	SCMSearchField.sendKeys(firstn+" "+lastn);
	wait.waitForElementToBeVisible(driver, SCMSearchResult);
}
public String searchresultSCM()
{
	String scmresult=page.getElementText(SCMSearchResult);
	return scmresult;
}
}
