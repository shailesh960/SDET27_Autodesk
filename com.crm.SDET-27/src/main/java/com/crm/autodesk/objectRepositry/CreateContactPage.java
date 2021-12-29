package com.crm.autodesk.objectRepositry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.autodesk.genricuUtilite.WebDriverUtilite;

public class CreateContactPage extends WebDriverUtilite 
{
	//private static final String  = null;

	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	
	@FindBy(xpath = "//img[@title='Select'][1]")
	private WebElement organizationName;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//a[text()='TestYantra']")
	private WebElement clickOrganizationLink;
	
	@FindBy(id = "search_txt")
	private WebElement searchText;
	
	@FindBy(name ="search")
	private WebElement searchBtn;
	
	public WebElement getClickOrganizationLink() {
		return clickOrganizationLink;
	}

	public CreateContactPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	public WebElement getOrganizationName() {
		return organizationName;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	//  Enter The Last Name In The Caontact Info
	public void enterLastName(String name)
	{
		lastnameEdt.sendKeys(name);
	}

	// Save The Contact Info
	
	public void saveButton()
	{
		saveBtn.click();
		
	}
	// Click On Organization Name
	public void clickOrganization(WebDriver driver,String title, String title2)
	{
		organizationName.click();
		switchToWindow(driver, title);
		clickOrganizationLink.click();
		switchToWindow(driver, title2);
		

	}
	public WebElement getSearchText() {
		return searchText;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public void clickOrganization(WebDriver driver,String title, String title2,String org)
	{
		organizationName.click();
		
		switchToWindow(driver, title);
		searchText.sendKeys(org);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+org+"']")).click();
		
		
		switchToWindow(driver, title2);
		

	}

	
	

}
