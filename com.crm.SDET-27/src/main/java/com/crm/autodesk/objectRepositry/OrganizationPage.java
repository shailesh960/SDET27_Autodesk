package com.crm.autodesk.objectRepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genricuUtilite.WebDriverUtilite;

/**
 * 
 * @author Shailesh
 *
 */
public class OrganizationPage extends WebDriverUtilite
{
	//identify all The element
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createOrganizationLink;
//initilatization  By the Constructor 
	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
//utilization by Getter Method 
	public WebElement getCreateOrganizationLink() {
		return createOrganizationLink;
	}
// Business liberary 	
	public void clickOnOrganizationlookUp()
	{
		createOrganizationLink.click();
		
	}
}
