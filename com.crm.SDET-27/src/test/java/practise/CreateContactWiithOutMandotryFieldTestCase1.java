package practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateContactWiithOutMandotryFieldTestCase1 {

	public static void main(String[] args) throws Throwable
	{
		//Create the Object of FileInputStream to Acess The Properties File
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		//Properties Obj is created to load the Propertiees File 
		Properties pObj=new Properties();
		pObj.load(fis);
		//GEtting the Value of The Properties File
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD= pObj.getProperty("password");
		String BROWSER= pObj.getProperty("browser");
		//Calling The Browser
		WebDriver driver;
		if(BROWSER.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else
		{
			driver= new FirefoxDriver();
		}
		driver.manage().window().maximize();
		// Give Url 
		driver.get(URL);
		
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		//User Name Enter
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//Valid Password
		
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		// Sign Up
		driver.findElement(By.id("submitButton")).click();
		//clicking On Contact Button
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		// click On LooKup Create ContoactButton
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			FileInputStream fs2=new FileInputStream("./Data/Organisation.xlsx");
		//WorkbookFactory to Acces ExcelFile	
			Workbook wb = WorkbookFactory.create(fs2);
		//Get Sheet Name
			Sheet sh = wb.getSheet("Sheet1");
			
		//Get Row Value
			Row row = sh.getRow(1);
		//Get Cell Index And Cell Value and Store It in String Variable Name
			String name = row.getCell(1).getStringCellValue();
			
			WebElement ele = driver.findElement(By.name("salutationtype"));
			
			//handling Drop Down
			Select sel= new Select(ele);
			
			sel.selectByValue("Mr.");
			
			driver.findElement(By.name("firstname")).sendKeys(name);
			
			
			//Click The Save button
			driver.findElement(By.name("button")).click();
			//handle the alert Pop
			Alert alrt = driver.switchTo().alert();
			String message = alrt.getText();
			alrt.accept();
			System.out.println("=====>"+message+"<======");
			
		 //Close The Browser
			WebElement out = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions act=new Actions(driver);
			act.moveToElement(out).perform();
		
		 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		
		driver.quit();
		
		
		
		
		

	}

}
