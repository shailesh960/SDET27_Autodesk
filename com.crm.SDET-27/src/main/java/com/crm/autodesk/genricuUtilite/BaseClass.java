package com.crm.autodesk.genricuUtilite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.LoginPage;
import com.mysql.cj.jdbc.Driver;

public class BaseClass 
{

	public FileUtilite file=new FileUtilite();
	public ExcelUtlites excelu= new ExcelUtlites();
	public JavaUtilite javaU=new JavaUtilite();
	public WebDriverUtilite webU=new WebDriverUtilite();
	public JdbcUtilite jdbc=new JdbcUtilite();
    public	WebDriver driver;
    public static WebDriver sdriver;
    
	@BeforeSuite(groups = { "smokeSuite","regressionSuite"})
	public void dbConnection() throws Throwable
	{
		jdbc.createJdbcConnection();
	}
	//@Parameters("browser")
	@BeforeClass(groups = { "smokeSuite","regressionSuite"})
	public void openBrowser() throws Throwable
	{
		String URL = file.getPropertyFileKeyValue("url");
		String BROWSER = file.getPropertyFileKeyValue("browser");
		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else
		{
			driver =new FirefoxDriver();
		}
		sdriver=driver;
		webU.waitForToLaod(driver);
		driver.manage().window().maximize();
		driver.get(URL);
	}
	
	@BeforeMethod(groups = { "smokeSuite","regressionSuite"})
	public void loginAplication() throws Throwable
	{
		String USERNAME = file.getPropertyFileKeyValue("username");
		String PASSWORD = file.getPropertyFileKeyValue("password");
		LoginPage lp=new LoginPage(driver);
		lp.clickOnLogginButton(USERNAME, PASSWORD);
	
	}
	@AfterMethod(groups ={ "smokeSuite","regressionSuite"})
	public void logoutApplication()
	{
		HomePage hp=new HomePage(driver);
		hp.clickOnLogout(driver);
		
		
	}
	@AfterClass(groups ={ "smokeSuite","regressionSuite"})
	public void closeBrowser()
	{
		driver.quit();
	}
	
	@AfterSuite(groups = { "smokeSuite","regressionSuite"})
	public void CloseDBConnection() throws Throwable
	{
		jdbc.closeConnection();
		
	}
	
	
	

}
