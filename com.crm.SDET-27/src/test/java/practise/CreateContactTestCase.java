package practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CreateContactTestCase 
{
	public static void main(String[] args) throws Throwable {
		
	
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
	
	
	// clicking on the Contact link 
	HomePage hp=new HomePage(driver);
	hp.clickOnContact();
	
	
	// Move To Contact page 
	ContactPage cp=new ContactPage(driver);
	cp.createContactImg();
	
	int randomnum = javaU.getRandomNumber();
	
	String lName = excelU.getDataFromExcelSheet("Sheet1", 1, 1)+randomnum;
	
	CreateContactPage ccp=new CreateContactPage(driver);
	ccp.enterLastName(lName);
	ccp.saveButton();
	
	//validation
	ContactInfo ci= new ContactInfo(driver);
	String actualData = ci.getHeaderContact();
	if(actualData.contains(lName))
	{
		System.out.println(actualData);
	}
	else	
	{
		System.out.println("Failed");
	}

	//logout
	hp.clickOnLogout(driver);
	}
}
