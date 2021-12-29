package practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
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

public class CreateContactWithOrganization {

	public static void main(String[] args) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./Data/data.properties");
		Properties pObj= new Properties();
		pObj.load(fis);
		String URL=pObj.getProperty("url");
		String USERNAME=pObj.getProperty("username");
		String PASSWORD=pObj.getProperty("password");
		String BROWSER=pObj.getProperty("browser");
		WebDriver driver;
		if(BROWSER.equals("Chrome"))
		{
			driver= new ChromeDriver();
			
		}
		else if(BROWSER.equals("FireFox"))
		{
			driver= new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
			
		}
		Random random= new Random();
		int randomnum = random.nextInt(1000);
		
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click(); 
		FileInputStream fis_exel= new FileInputStream("./Data/Organisation.xlsx");
		Workbook wb = WorkbookFactory.create(fis_exel);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		 String org=row.getCell(2).getStringCellValue()+randomnum;
		 driver.findElement(By.name("accountname")).sendKeys(org);
		 driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("(//td[@class='tabUnSelected'])[4]")).click();
		 driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		  String text = row.getCell(1).getStringCellValue()+randomnum;
		  driver.findElement(By.name("lastname")).sendKeys(text);
		  driver.findElement(By.xpath("(//img[@language='javascript'])[1]")).click();
		  String parent = driver.getWindowHandle();
		  Set<String> child = driver.getWindowHandles();
		  child.remove(parent);
		  for(String window:child)
		  {
			driver.switchTo().window(window);
			driver.findElement(By.id("search_txt")).sendKeys(org);
			driver.findElement(By.name("search")).click();
			WebElement tablerow = driver.findElement(By.tagName("table"));
			List<WebElement> value = tablerow.findElements(By.xpath("//*[@id=\"ListViewContents\"]//tr[1]//td[2]"));
			int size = value.size();
			System.out.println(size);
			
			for(WebElement ele:value)
			{
			String orgname = ele.getText();
			System.out.println(orgname);
			}
			Thread.sleep(2000);
			driver.quit();
			
		  }
		  
		  
		 
		 
		 
		 Thread.sleep(1000);
		 driver.quit();
		
		

	}

}
