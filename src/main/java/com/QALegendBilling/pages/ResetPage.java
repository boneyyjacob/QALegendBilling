package com.QALegendBilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.QALegendBilling.utilities.TestHelperUtility;

public class ResetPage extends TestHelperUtility {
	public WebDriver driver;

	public ResetPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private final String _resetPasswordEmailField = "//input[@id='email']";
	@FindBy(xpath = _resetPasswordEmailField)
	private WebElement resetPasswordEmailField;

	private final String _resetPasswordEmailErrorMessage = "//input[@id='email']//following::span";
	@FindBy(xpath = _resetPasswordEmailErrorMessage)
	private WebElement resetPasswordEmailErrorMessage;

	private final String _sendPasswordResetButton = "//button[@class='btn btn-primary']";
	@FindBy(xpath = _sendPasswordResetButton)
	private WebElement sendPasswordResetButton;

	public void enterInvalidEmail(String email) {
		page.enterText(resetPasswordEmailField, email);
	}

	public String getInvalidResetMessage() {

		String resetPasswordError = page.getElementText(resetPasswordEmailErrorMessage);
		return resetPasswordError;
	}

	public void resetButtonClick() {
		sendPasswordResetButton.click();
	}

}
