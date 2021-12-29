package practiseTestNG;


import org.openqa.selenium.WebDriver;

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
import com.crm.autodesk.objectRepositry.HomePage;

@Listeners(com.crm.autodesk.genricuUtilite.ListnerImpementation.class)
public class CreateContactTestCase extends BaseClass
{
@Test(groups = {"regressionSuite","smokeSuite"},retryAnalyzer = com.crm.autodesk.genricuUtilite.RetryAnalyzer.class)
public void CreateContact() throws Throwable
{
		
	
	//FileUtilite fileU=new FileUtilite();
	ExcelUtlites excelU= new ExcelUtlites();
	JavaUtilite javaU= new JavaUtilite();
	//WebDriverUtilite webU=new WebDriverUtilite();
	
	// Get The data From The Properties File 
	
	// Call the Browser
	
	// clicking on the Contact link 
	HomePage hp=new HomePage(driver);
	hp.clickOnContact();
	
	
	// Move To Contact page 
	ContactPage cp=new ContactPage(driver);
	cp.createContactImg();
	
	int randomnum = javaU.getRandomNumber();
	
	String lName = excelU.getDataFromExcelSheet("Sheet1", 1, 1)+randomnum;
	Assert.fail();
	CreateContactPage ccp=new CreateContactPage(driver);
	ccp.enterLastName(lName);
	ccp.saveButton();
	
	//validation
	ContactInfo ci= new ContactInfo(driver);
	String actualData = ci.getHeaderContact();
	Assert.assertTrue(actualData.contains(lName));
	Reporter.log(lName);
	/*if(actualData.contains(lName))
	{
		System.out.println(actualData);
	}
	else	
	{
		System.out.println("Failed");
	}*/
	}

@Test
public void demo()
{
	System.out.println("deemo");
}
}