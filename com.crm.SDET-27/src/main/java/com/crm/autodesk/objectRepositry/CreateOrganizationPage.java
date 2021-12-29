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
public class CreateOrganizationPage extends WebDriverUtilite
{
	//identify all The Element 
	@FindBy(name="accountname")
	private WebElement lastNameEdt;
	
	@FindBy(name = "industry")
	private WebElement industryDrpDwn;
	
	@FindBy(name = "accounttype")
	private WebElement typeDrpDwn;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	// Declatration By Construction
	public CreateOrganizationPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
// Utilization By getter 
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getIndustryDrpDwn() {
		return industryDrpDwn;
	}

	public WebElement getTypeDrpDwn() {
		return typeDrpDwn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	//Business Liberaries
	
	public void enterLastName(String lastName)
	{
		lastNameEdt.sendKeys(lastName);
		
	}
	 public void selectIndustry(String value)
	 {
		 
		selectByValue(industryDrpDwn, value);
		 
	 }
	public void selectType(String value)
	{
		selectByValue(typeDrpDwn, value);
	}
	public void saveOrganization()
	{
		saveBtn.click();
	}

}
