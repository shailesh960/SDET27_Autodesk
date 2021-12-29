package practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;
import com.crm.autodesk.objectRepositry.CreateOrganizationPage;
import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.LoginPage;
import com.crm.autodesk.objectRepositry.OrganizationInfo;
import com.crm.autodesk.objectRepositry.OrganizationPage;

public class CreateOrganizationWithPOM {

	public static void main(String[] args) throws Throwable 
	{
		FileUtilite fileU=new FileUtilite();
		ExcelUtlites excelU= new ExcelUtlites();
		JavaUtilite javaU= new JavaUtilite();
		WebDriverUtilite webU=new WebDriverUtilite();
		
		String URL = fileU.getPropertyFileKeyValue("url");
		String USERNAME = fileU.getPropertyFileKeyValue("username");
		String PASSWORD = fileU.getPropertyFileKeyValue("password");
		String BROWSER = fileU.getPropertyFileKeyValue("browser");
		WebDriver driver;
		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			driver= new ChromeDriver();
		}
		else
		{
			driver= new FirefoxDriver();
			
		}
		webU.waitForToLaod(driver);
		driver.manage().window().maximize();
		driver.get(URL);
		//login the Page 
		LoginPage lp=new LoginPage(driver);
		lp.clickOnLogginButton(USERNAME, PASSWORD);
		// clicking on the Organization link 
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationLink();
		OrganizationPage op= new OrganizationPage(driver);
		op.clickOnOrganizationlookUp();
		//Performing Logout 
		int randumnum = javaU.getRandomNumber();
		String orgName = excelU.getDataFromExcelSheet("Sheet1", 1, 2)+randumnum;
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.enterLastName(orgName);
cop.saveOrganization();
		
		OrganizationInfo oInfo= new OrganizationInfo(driver);
		String actualText = oInfo.getHeaderName();
		
		if(actualText.contains(orgName))
		{
			System.out.println(actualText);
		}
		else
		{
			System.out.println("Test Case Failed");
		}
		
		
		hp.clickOnLogout(driver);
		


	}

}
