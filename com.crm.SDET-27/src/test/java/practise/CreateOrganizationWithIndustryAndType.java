package practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
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
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustryAndType {

	public static void main(String[] args) throws Throwable 
	{
		// Create thr file Input Stream  To add Properties File
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		//Creating the Object of Properties File
		Properties pObj=new Properties();
		//Load The File To use The Data 
		pObj.load(fis);
		//Get Property Of the Properties File
		String URL = pObj.getProperty("url");
		
		String USERNAME= pObj.getProperty("username");
		
		String PASSWORD= pObj.getProperty("password");
		
		String BROWSER = pObj.getProperty("browser");
		// call the Web Browser
		WebDriver driver;
		
		if(BROWSER.equals("Chrome"))
		{
		driver=new ChromeDriver();	
		}
		else
		{
			driver=new FirefoxDriver();
		}
		// Random Class to Random Number
		Random random= new Random();
		
		int randomnum = random.nextInt(1000);
		
		driver.manage().window().maximize();
		
		driver.get(URL);
		
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("(//td[@class='tabUnSelected'])[3]")).click();
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		// Create the FileInput Stream For Excel File 
		FileInputStream fis1= new FileInputStream("./Data/Organisation.xlsx");
		//WorkBook Factory
		Workbook wb = WorkbookFactory.create(fis1);
		
		Sheet sh = wb.getSheet("Sheet1");
		
		Row row = sh.getRow(1);
		
		String org = row.getCell(2).getStringCellValue()+randomnum;
		
		driver.findElement(By.name("accountname")).sendKeys(org);
	
		Sheet sh2 = wb.getSheet("Sheet2");
		
		Row row2 = sh2.getRow(1);
	
		String indus=row2.getCell(4).getStringCellValue();
		
		
		 WebElement ind = driver.findElement(By.name("industry"));
		
		 Select sel=new Select(ind);
		 
		 sel.selectByVisibleText(indus);
		 
		 String type = row2.getCell(5).getStringCellValue();
		 
		 WebElement value = driver.findElement(By.name("accounttype"));
		 
		 Select sel1=new Select(value);
		 sel1.selectByVisibleText(type);
		 
		 
		 
		 driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		 
		 String actualIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		 if(actualIndustries.contains(indus))
		 {
			 System.out.println(indus+"Selected industries");
		 }
		 else
		 {
			 System.out.println(indus+"Test is failed");
		 }
		 
		String actulaType = driver.findElement(By.id("mouseArea_Type")).getText();
		
		 if(actulaType.contains(type))
		 {
			 System.out.println(type+"Test is Passes");
		 }
		 else
		 {
			 System.out.println(type+"Test is failed");
		 }
		 WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		

		driver.quit();
		
		

	}

}
