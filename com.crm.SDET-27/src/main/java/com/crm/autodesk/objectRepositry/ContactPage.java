package com.crm.autodesk.objectRepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genricuUtilite.WebDriverUtilite;

public class ContactPage extends WebDriverUtilite
{// find all The Element 
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createContactLookUpBtn;
// initialize By Constructor
	public ContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
// Utilize 
	public WebElement getCreateContactLookUpBtn() {
		return createContactLookUpBtn;
	}
//	Business Liberaries
	
	public void createContactImg()
	{
		createContactLookUpBtn.click();
	}
	

}
