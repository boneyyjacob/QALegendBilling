package com.QALegendBilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.QALegendBilling.utilities.TestHelperUtility;

public class LoginPage extends TestHelperUtility {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private final String _userNameField = "username";
	@FindBy(id = _userNameField)
	private WebElement userNameField;
	private final String _passwordField = "password";
	@FindBy(id = _passwordField)
	private WebElement passwordField;
	private final String _loginButton = "//button[@type='submit']";
	@FindBy(xpath = _loginButton)
	private WebElement loginButton;
	private final String _loginErrorMessage = "//div[@class='col-md-6']//span";
	@FindBy(xpath = _loginErrorMessage)
	private WebElement loginErrorMessage;
	private final String _clickForgotPasswordLink = "//a[@class='btn btn-link']";
	@FindBy(xpath = _clickForgotPasswordLink)
	private WebElement clickForgotPasswordLink;
	

	public void enterUserCredentials(String email, String pword) {
		page.enterText(userNameField, email);
		page.enterText(passwordField, pword);
	}

	public HomePage clickOnLoginButton() {
		loginButton.click();
		return new HomePage(driver);
	}

	public String getLoginErrorMessage() {
		String errorMessage = page.getElementText(loginErrorMessage);
		return errorMessage;
	}
	
	public ResetPage clickForgotPassword()
	{
		clickForgotPasswordLink.click();
		return new ResetPage(driver);
	}
	
	public String getTitleLoginPage()
	{
		String title=driver.getTitle();
		return title;
	}

}
