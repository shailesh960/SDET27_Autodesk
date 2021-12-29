package practiseTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.autodesk.genricuUtilite.BaseClass;
import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;
import com.crm.autodesk.objectRepositry.ContactInfo;
import com.crm.autodesk.objectRepositry.ContactPage;
import com.crm.autodesk.objectRepositry.CreateContactPage;
import com.crm.autodesk.objectRepositry.CreateOrganizationPage;
import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.OrganizationPage;
@Listeners(com.crm.autodesk.genricuUtilite.ListnerImpementation.class)
public class CreateOrganozationWithContactTestCaseTestNg extends BaseClass
{
	FileUtilite file=new FileUtilite();
	ExcelUtlites excel=new ExcelUtlites();
	JavaUtilite java=new JavaUtilite();
	WebDriverUtilite web=new WebDriverUtilite();
	
	
	@Test(groups = "smokeSuite")
	public void createOrganizationWithContact() throws Throwable
	{
		//Genrating the Random Number
		int randomNumber = java.getRandomNumber();
		
		//Getting the Data From the External File
		String orgName = excel.getDataFromExcelSheet("Sheet1", 1, 2)+randomNumber;
		String lName = excel.getDataFromExcelSheet("Sheet1", 1, 1)+randomNumber;
		String industry = excel.getDataFromExcelSheet("Sheet2", 1, 4);
		String type = excel.getDataFromExcelSheet("Sheet2", 1, 5);
		
	//click on the Organization Link
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		//Click on create Organization lookup img
		OrganizationPage op= new OrganizationPage(driver);
		op.clickOnOrganizationlookUp();
		
		// Create New Organization
		CreateOrganizationPage corp=new CreateOrganizationPage(driver);
		corp.enterLastName(orgName);
		corp.selectIndustry(industry);
		corp.selectType(type);
		corp.saveOrganization();
		
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
	
		// Goto Contact Page
		hp.clickOnContact();
		
		//
		ContactPage cp=new ContactPage(driver);
		cp.createContactImg();
		
		//Create Contact LookUp img
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.enterLastName(lName);
		
		String title1="Accounts";
		String title2="Contacts";
		
		ccp.clickOrganization(driver, title1, title2, orgName);
		
		ccp.saveButton();
		
		//Verification
		
		ContactInfo cInfo=new ContactInfo(driver);
		 String actaulName = cInfo.getHeaderContact();
		/* Assert.assertTrue(actaulName.contains(lName));
			Reporter.log(industry);*/
			
		/*if (actaulName.contains(lName))
		{
		System.out.println("Organization is created with Contact Name");	
		}
		else
		{
			System.out.println("Organization is not Created with Contact Name");
		}*/
		
	}

}
