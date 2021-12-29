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
public class OrganizationInfo extends WebDriverUtilite
{
	@FindBy(className ="dvHeaderText")
	private WebElement orgHeaderTxt;
	
	@FindBy(xpath = "//a[@href='index.php?module=Contacts&action=index']")
	private WebElement contactLink; 
	
	public OrganizationInfo(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	/*public WebElement getContactLink() {
		return contactLink;
	}*/

	public WebElement getOrgHeaderTxt() {
		return orgHeaderTxt;
	}
	
	public String getHeaderName()
	{
		return(orgHeaderTxt.getText());
	}
	public void clickContactLink()
	{
		contactLink.click();
		
	}

}
