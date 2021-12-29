package practiseTestNG;

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
import com.crm.autodesk.objectRepositry.CreateOrganizationPage;
import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.OrganizationInfo;
import com.crm.autodesk.objectRepositry.OrganizationPage;
@Listeners(com.crm.autodesk.genricuUtilite.ListnerImpementation.class)
public class CreateOrganizationTestNgGTestCase extends BaseClass
{
	FileUtilite file=new FileUtilite();
	ExcelUtlites excel=new ExcelUtlites();
	JavaUtilite java=new JavaUtilite();
	WebDriverUtilite web=new WebDriverUtilite();
	@Test(groups = "regressionSuite")
	public void createOrganization() throws Throwable
	{
		// click on Organization link from HomePage
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		// create new Organization 
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnOrganizationlookUp();
		
		//Gentrating Random Number
		int randomNumber = java.getRandomNumber();
		
		//Getting the Data From the Excel Sheet
		String orgName = excel.getDataFromExcelSheet("Sheet1", 1, 2)+randomNumber;
		Assert.fail();
		// New Organization is created
		CreateOrganizationPage corp= new CreateOrganizationPage(driver);
		corp.enterLastName(orgName);
		corp.saveOrganization();
		
		// Verification 
		OrganizationInfo oInfo=new OrganizationInfo(driver);
		String actualName = oInfo.getHeaderName();
		
		
		/*if (actualName.contains(orgName))
		{
			System.out.println("Organization is Created Succesfully");
		}s
		else
		{
			System.out.println("Organization Is not Created");
		}*/
			
		
		
	}
 
}
