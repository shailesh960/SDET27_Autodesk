package practiseTestNG;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.autodesk.genricuUtilite.BaseClass;
import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.objectRepositry.CreateOrganizationPage;
import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.OrganizationInfo;
import com.crm.autodesk.objectRepositry.OrganizationPage;
@Listeners(com.crm.autodesk.genricuUtilite.ListnerImpementation.class)
public class CreateOrganizationWithIndustryTestNg  extends BaseClass
{

	@Test(groups = "smokeSuite")
	public void createOrgWithIndus() throws Throwable
	{
		//Genrating the Random Number
				int randomNumber = javaU.getRandomNumber();
				
				//Getting the Data From the External File
				String orgName = excelu.getDataFromExcelSheet("Sheet1", 1, 2)+randomNumber;
				String lName = excelu.getDataFromExcelSheet("Sheet1", 1, 1)+randomNumber;
				String industry = excelu.getDataFromExcelSheet("Sheet2", 1, 4);
				String type = excelu.getDataFromExcelSheet("Sheet2", 1, 5);
				
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
				
				// Verification 
				OrganizationInfo oInfo=new OrganizationInfo(driver);
				String actualName = oInfo.getHeaderName();
				/*Assert.assertTrue(actualName.contains(orgName));
				Reporter.log(lName,true);
					Reporter.log(industry);*/
				/*if (actualName.contains(orgName))
				{
					System.out.println("Organization is Created Succesfully");
				}
				else
				{
					System.out.println("Organization Is not Created");
				}*/
				
				
				
	}

}
