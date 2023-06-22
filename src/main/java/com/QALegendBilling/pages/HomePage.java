package com.QALegendBilling.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import com.QALegendBilling.testscript.Select;
import com.QALegendBilling.utilities.TestHelperUtility;

//import org.openqa.selenium.support.ui.Select;

public class HomePage extends TestHelperUtility {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private final String _loginname = "//a[@class='dropdown-toggle']//span";
	@FindBy(xpath = _loginname)
	private WebElement loginname;
	
	private final String _userProfileDropdownButton = "//a[@class='dropdown-toggle']//span";
	@FindBy(xpath = _userProfileDropdownButton)
	private WebElement userProfileDropdownButton;
	
	private final String _signoutButton = "//div[@class='pull-right']//a[@class='btn btn-default btn-flat']";
	@FindBy(xpath = _signoutButton)
	private WebElement signoutButton;
	
	private final String _endTourButton = "//button[@class='btn btn-default btn-sm']";
	@FindBy(xpath = _endTourButton)
	private WebElement endTourButton;
	
	private final String _userManagementDropdownbutton = "//span[@class='title' and text()='User Management']";
	@FindBy(xpath = _userManagementDropdownbutton)
	private WebElement userManagementDropdownbutton;
	
	private final String _userButton = "//a[contains(@href, 'public/users')]";
	@FindBy(xpath = _userButton)
	private WebElement userButton;
	
	
	public String getLoginUserName() {
		String loginUser = page.getElementText(loginname);
		return loginUser;
	}
	
	public void clickuserProfileDropdownButton()
	{
		userProfileDropdownButton.click();
	}
	
	public void clicksignoutButton()
	{
		signoutButton.click();
	}
	
	public void clickEndTourButton()
	{
		endTourButton.click();
	}
	
	public void clickuserManagementDropdownbutton()
	{
		userManagementDropdownbutton.click();
	}
	
	public void clickUserButton()
	{
		userButton.click();
    }
	
	
}
