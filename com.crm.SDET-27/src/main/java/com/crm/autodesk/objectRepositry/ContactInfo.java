package com.crm.autodesk.objectRepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genricuUtilite.WebDriverUtilite;

public class ContactInfo extends WebDriverUtilite
{
	@FindBy(className="dvHeaderText")
	private WebElement ContactHeader;
	
	public ContactInfo(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getContactHeader() {
		return ContactHeader;
	}
	
	public String getHeaderContact()
	{
		return(ContactHeader.getText());
	}

}
