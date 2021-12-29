package practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;
import com.crm.autodesk.objectRepositry.ContactInfo;
import com.crm.autodesk.objectRepositry.ContactPage;
import com.crm.autodesk.objectRepositry.CreateContactPage;
import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.LoginPage;

public class CreateContactWithOrganizationWithPOM
{
	public static void main(String[] args) throws Throwable {
		
		
// Creating The Object Of The GenricUtilite
	
	FileUtilite fileU=new FileUtilite();
	ExcelUtlites excelU= new ExcelUtlites();
	JavaUtilite javaU= new JavaUtilite();
	WebDriverUtilite webU=new WebDriverUtilite();
	
	// Get The data From The Properties File 
	String URL = fileU.getPropertyFileKeyValue("url");
	String USERNAME = fileU.getPropertyFileKeyValue("username");
	String PASSWORD = fileU.getPropertyFileKeyValue("password");
	String BROWSER = fileU.getPropertyFileKeyValue("browser");
	
	// Call the Browser
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
	hp.clickOnContact();
	
	// Move To Contact page 
	ContactPage cp=new ContactPage(driver);
	
	cp.createContactImg();
	
	int randomnum = javaU.getRandomNumber();
	
	String lName = excelU.getDataFromExcelSheet("Sheet1", 1, 1)+randomnum;
//	Creating the New Contact with Organization Name 
	CreateContactPage ccp=new CreateContactPage(driver);

	ccp.enterLastName(lName);
	String title="Accounts";
	String title2="Contacts";
	ccp.clickOrganization(driver,title , title2,lName);
	ccp.saveButton();
	ContactInfo cif=new ContactInfo(driver);
	String actualData = cif.getHeaderContact();
	// Validate The Data 
	if(actualData.contains(lName))
	{
		System.out.println(actualData);
		System.out.println(" Contact Is Created With Organization");
		
	}
	else
	{
		System.out.println("Test Case Is Failed");
	}
	
	hp.clickOnLogout(driver);
	
	}	
	

}
