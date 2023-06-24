package com.QALegendBilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.QALegendBilling.utilities.TestHelperUtility;

public class UserPage extends TestHelperUtility
{
	public WebDriver driver;

	public UserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private final String _addUserButton = "//a[@class='btn btn-block btn-primary']";
	@FindBy(xpath = _addUserButton)
	private WebElement addUserButton;
	
	private final String _titleField = "surname";
	@FindBy(id = _titleField)
	private WebElement titleField;
	
	private final String _firstNameField = "first_name";
	@FindBy(id = _firstNameField)
	private WebElement firstNameField;
	
	private final String _lastNameField = "last_name";
	@FindBy(id = _lastNameField)
	private WebElement lastNameField;
	
	private final String _emailField = "email";
	@FindBy(id = _emailField)
	private WebElement emailField;
	
	private final String _userNameField = "username";
	@FindBy(id = _userNameField)
	private WebElement userNameField;
	
	private final String _userPasswordField = "password";
	@FindBy(id = _userPasswordField)
	private WebElement userPasswordField;
	
	private final String _userConfirmPasswordField = "confirm_password";
	@FindBy(id = _userConfirmPasswordField)
	private WebElement userConfirmPasswordField;
	
	private final String _userSalesCommissionField = "cmmsn_percent";
	@FindBy(id = _userSalesCommissionField)
	private WebElement userSalesCommissionField;
	
	private final String _saveButton = "submit_user_button";
	@FindBy(id = _saveButton)
	private WebElement saveButton;
	
	private final String _searchUserField = "//input[@class='form-control input-sm']";
	@FindBy(xpath = _searchUserField)
	private WebElement searchUserField;

	
	private final String _invalidSearchText = "//td[@class='dataTables_empty']";
	@FindBy(xpath = _invalidSearchText)
	private WebElement invalidSearchText;
	
	
	String fname=random.getfName();
	String lname=random.getlName();
	public String email=random.getRandomEmail();
	
	
	public void clickUserAddButton()
	{
		addUserButton.click();
	}
	
	public void enterTitle(String title)
	{
		page.enterText(titleField, title);
	}
	
	public void enterCommission(String commission)
	{
		page.enterText(userSalesCommissionField, commission);
	}
	
	public void enterFirstName()
	{
		page.enterText(firstNameField, fname);
	}
	
	public void enterLastName()
	{
		page.enterText(lastNameField, lname);
	}
	
	public void enterUserEmail()
	{
		page.enterText(emailField, email);
		
	}
	
	public void enterUserName()
	{
		page.enterText(userNameField, fname+lname);
	}
	
	public void enterUserPassword()
	{
		page.enterText(userPasswordField, fname+123);
	}
	
	public void enterConfirmPassword()
	{
		page.enterText(userConfirmPasswordField, fname+123);
	}
	
	public void clickSaveButton()
	{
		saveButton.click();
	}
	
	public void searchUser()
	{
		page.enterText(searchUserField, fname+lname);
	}
	
	public void searchInvalidUser()
	{
		String invaliduserfname=random.getfName();
		String invaliduserlname=random.getlName();
		page.enterText(searchUserField, invaliduserfname+invaliduserlname);
		wait.waitForElementToBeVisible(driver, invalidSearchText);
		
	}
	
	public String getInvalidSearchText()
	{
		String invalidtext=invalidSearchText.getText();
		return invalidtext;
		
	}
	

}
