package practisePom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;
import com.crm.autodesk.objectRepositry.CreateOrganizationPage;
import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.LoginPage;
import com.crm.autodesk.objectRepositry.OrganizationInfo;
import com.crm.autodesk.objectRepositry.OrganizationPage;

public class CreateOrganizationWithContact {

	public static void main(String[] args) throws Throwable 
	{

		FileUtilite fileU=new FileUtilite();
		ExcelUtlites excelU=new ExcelUtlites();
		JavaUtilite javaU=new JavaUtilite();
		WebDriverUtilite webU=new WebDriverUtilite();
		
	String URl = fileU.getPropertyFileKeyValue("url");
	String USERNAME = fileU.getPropertyFileKeyValue("username");
	String PASSWORD = fileU.getPropertyFileKeyValue("password");
	String BROWSER = fileU.getPropertyFileKeyValue("browser");
	
	WebDriver driver;
	if(BROWSER.equalsIgnoreCase("Chrome"))
	{
		driver =new ChromeDriver();
	}
	else
	{
		driver =new FirefoxDriver();
	}
	
	webU.waitForToLaod(driver);
	driver.manage().window().maximize();
	driver.get(URl);
	
	LoginPage login=new LoginPage(driver);
	login.clickOnLogginButton(USERNAME, PASSWORD);
	
	HomePage homeP=new HomePage(driver);
	homeP.clickOnOrganizationLink();
	
	OrganizationPage op=new OrganizationPage(driver);
	op.clickOnOrganizationlookUp();
	
	int randomNum = javaU.getRandomNumber();
	
	String orgName = excelU.getDataFromExcelSheet("Sheet1", 1, 2)+ randomNum;
	
	CreateOrganizationPage createOP=new CreateOrganizationPage(driver);
	createOP.enterLastName(orgName);
	
	createOP.saveOrganization();
	
	WebDriverWait wait=new WebDriverWait(driver,20);
	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("dvHeaderText"))));
	 
	OrganizationInfo oinfo=new OrganizationInfo(driver);
	oinfo.clickContactLink();
	
	
	//homeP.clickOnContact();
	
	driver.quit();
	
		
	}

}
