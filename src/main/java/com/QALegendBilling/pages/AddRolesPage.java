package com.QALegendBilling.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.QALegendBilling.utilities.TestHelperUtility;

public class AddRolesPage extends TestHelperUtility {
	public WebDriver driver;

	public AddRolesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private final String _RolesDropdown = "//i[@class='fa fa-briefcase']//following-sibling::span[1]";
	@FindBy(xpath = _RolesDropdown)
	private WebElement RolesDropdown;

	private final String _AddRolesButton = "//a[@class='btn btn-block btn-primary']";
	@FindBy(xpath = _AddRolesButton)
	private WebElement AddRolesButton;

	private final String _rolesNameField = "name";
	@FindBy(id = _rolesNameField)
	private WebElement rolesNameField;

	private final String _saveRolesButton = "//button[@class='btn btn-primary pull-right']";
	@FindBy(xpath = _saveRolesButton)
	private WebElement saveRolesButton;

	private final String _rolesSearchField = "//input[@class='form-control input-sm']";
	@FindBy(xpath = _rolesSearchField)
	private WebElement rolesSearchField;

	private final String _rolesSearchResult = "//td[@class='sorting_1']";
	@FindBy(xpath = _rolesSearchResult)
	private WebElement rolesSearchResult;

	private final String _rolesEditButton = "//a[@class='btn btn-xs btn-primary']";
	@FindBy(xpath = _rolesEditButton)
	private WebElement rolesEditButton;

	private final String _rolesHeader = "//section[@class='content-header']";
	@FindBy(xpath = _rolesHeader)
	private WebElement rolesHeader;

	public void clickRolesDropdown() {
		RolesDropdown.click();
	}

	public void clickAddRoles() {
		AddRolesButton.click();
	}

	public void enterRoleName(String role) {
		page.enterText(rolesNameField, role);
	}

	public void clickSaveRoles() {
		saveRolesButton.click();
	}

	public void searchRole(String role) {
		page.enterText(rolesSearchField, role);
		wait.waitForElementToBeVisible(driver, rolesSearchResult);
	}

	public String getSearchResults() {
		String searchResult = page.getElementText(rolesSearchResult);
		return searchResult;
	}

	public void editRoles() {
		rolesEditButton.click();
	}

	public String getRolesheader() {
		String header = page.getElementText(rolesHeader);
		return header;
	}

}
