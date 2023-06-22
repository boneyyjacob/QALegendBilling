package com.QALegendBilling.utilities;

import org.openqa.selenium.WebElement;

public class PageUtility 
{
	public void enterText(WebElement element, String text)
	{
		element.sendKeys(text);
	}
	public String getElementText(WebElement element)
	{
		String text=element.getText();
		return text;
	}
	public void clickOnElement(WebElement element)
	{
		element.click();
	}

}
