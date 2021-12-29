package com.crm.autodesk.objectRepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genricuUtilite.WebDriverUtilite;
/**
 * 
 * @author Shailesh
 *
 */
public class HomePage  extends WebDriverUtilite
{//create SeprateClass
	// Indentfy All the Element And Declaration
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;
	
	@FindAll({@FindBy(xpath = "//a[@href='index.php?module=Contacts&action=index']"),@FindBy(linkText = "Contacts")})
	private WebElement contactLink; 
	
	public WebElement getContactLink() {
		return contactLink;
	}
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement logOutBtn;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOut;
	//Intilization using Constructor
	public HomePage (WebDriver driver)
	{
		PageFactory.initElements( driver,this);
	}
  // Utlization using Getter Method
	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	
	public WebElement getLogOutBtn() {
		return logOutBtn;
	}
	public WebElement getSignOut() {
		return signOut;
	}
	// click on th organization link
	public void clickOnOrganizationLink()
	{
		organizationLink.click();
	}
	//clicking on Contactlink
	public void clickOnContact()
	{
		contactLink.click();
	}
	
	// logout From The Application 
	public void clickOnLogout(WebDriver driver)
	{
		
		
		mouseHoverElement(driver,logOutBtn );
		signOut.click();
		
	}
	
	

}
