package practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;

public class ContactUpdateWithReportePersonTestCase {

	public static void main(String[] args) throws Throwable
	{
		FileUtilite file=new FileUtilite();
		JavaUtilite java=new JavaUtilite();
		ExcelUtlites excel=new ExcelUtlites();
		WebDriverUtilite web=new WebDriverUtilite();
		
		String URL = file.getPropertyFileKeyValue("url");
		String USERNAME = file.getPropertyFileKeyValue("username");
		String PASSWORD = file.getPropertyFileKeyValue("password");
		String BROWSER = file.getPropertyFileKeyValue("browser");
		WebDriver driver;
		if(BROWSER.equals("Chrome"))
		{
			driver =new ChromeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		int randumnum=java.getRandomNumber();
		driver.manage().window().maximize();
		// Give Url s
		driver.get(URL);
		web.waitForToLaod(driver);
		//User Name Enter
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//Valid Password
		
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		// Sign Up
		driver.findElement(By.id("submitButton")).click();
		//clicking On Contact Button
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		// click On LooKup Create ContactButton
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		String name = excel.getDataFromExcelSheet("Sheet1", 1, 1)+randumnum;
		driver.findElement(By.name("lastname")).sendKeys(name);
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[2]")).click();
		web.switchToWindow(driver,"Popup_picker");
		driver.findElement(By.xpath("//a[text()='shailesh kumar']")).click();
		web.switchToWindow(driver, "parenttab");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		driver.findElement(By.xpath(("//td[@class='dvtCellInfo'])[16]")));
		
		
	}

}
