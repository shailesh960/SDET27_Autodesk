package practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateContactOrganization {

	public static void main(String[] args) throws Throwable 
	{
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String URL=pobj.getProperty("url");
		String USERNAME=pobj.getProperty("username");
		String PASSWORD=pobj.getProperty("password");
		String BROWSER=pobj.getProperty("browser");
		WebDriver driver;
		if(BROWSER.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
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
		FileInputStream fis_exel= new FileInputStream("./Data/Organisation.xlsx");
		Workbook wb = WorkbookFactory.create(fis_exel);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		 String org=row.getCell(2).getStringCellValue()+randomnum;
		 driver.findElement(By.name("accountname")).sendKeys(org);
		 driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		 WebDriverWait wait=new WebDriverWait(driver,20);
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("dvHeaderText"))));
		 driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		 driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
		 driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		 String actualdata = row.getCell(1).getStringCellValue()+randomnum;
			driver.findElement(By.name("lastname")).sendKeys(actualdata);
			driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
			Set<String> wind = driver.getWindowHandles();
			java.util.Iterator<String> it = wind.iterator();
			while(it.hasNext())
			{
				String cId = it.next();
				driver.switchTo().window(cId);
				String cPageTitle = driver.getTitle();
				if(cPageTitle.contains("Accounts"))
				{
					break;
				}
				
			
			}
			driver.findElement(By.id("search_txt")).sendKeys(org);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='"+org+"']")).click();
			Set<String> wind1 = driver.getWindowHandles();
			java.util.Iterator<String> it1 = wind1.iterator();
			
			while(it1.hasNext())
			{
				String cId = it1.next();
				driver.switchTo().window(cId);
				String cPageTitle = driver.getTitle();
				if(cPageTitle.contains("Contacts"))
				{
					break;
				}
				
			}
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
			String actualName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actualName.contains(org))
			{
				System.out.println(org+"Organization is Sucessfully Attached with Contct");
			}
			else
			{
				System.out.println(org+"===>Organization is Not Attached with Contct");	
			}
	
			
			driver.quit();
		

	}

}
