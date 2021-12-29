package practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContacctWithOrganizationTestCase {

	public static void main(String[] args) throws Throwable 
	{
		//Create the File Input Stream fis
		FileInputStream fis= new FileInputStream("./Data/data.properties");
		//Create Properties Pobj to load the Filed
		Properties pobj= new Properties();
		pobj.load(fis);
		//Accessing the Value From Properties File
		String URL = pobj.getProperty("url");
		String USERNAME= pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		String BROWSER= pobj.getProperty("browser");
		//WebDriver Call
		WebDriver driver;
		if(BROWSER.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		
		driver.get(URL);
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//Valid Password
		
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		// Sign Up
		driver.findElement(By.id("submitButton")).click();
		//clicking On Contact Button
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		//FileInputStream For Excel Sheet
		FileInputStream fis2=new FileInputStream("./Data/Organisation.xlsx");
		
		Workbook wb1 = WorkbookFactory.create(fis2);
		// sheet Name call
		Sheet sheet = wb1.getSheet("Sheet1");
		// Finding Row;
		Row row = sheet.getRow(1);
		
		String name = row.getCell(1).getStringCellValue();
		
		driver.findElement(By.name("lastname")).sendKeys(name);
		
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
		//ChildBrowser popup
		Set<String> wind = driver.getWindowHandles();
		Iterator<String> it1= wind.iterator();
		//Getting the Child Window Browser 
	while(it1.hasNext())
	{
		String cId = it1.next();
		driver.switchTo().window(cId);
		String pageTitle = driver.getTitle();
		if(pageTitle.contains("Accounts"))
		{
			break;
		}
	}
		driver.findElement(By.xpath("//a[text()='TestYantra']")).click();
		// Back to parent Window
		Iterator<String> it2 = wind.iterator();
		while(it2.hasNext())
		{
			String cId = it2.next();
			driver.switchTo().window(cId);
			String mainPage = driver.getTitle();
			if(mainPage.contains("Contacts"))
			{
				break;
			}
			
		}
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		// Get The title Of The Header
		
		String verified = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		System.out.println(verified+"==>message passed");
		WebElement out = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(out).perform();
	
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	
	driver.quit();

		driver.quit();

	}

}
