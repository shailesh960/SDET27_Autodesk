package com.crm.FetchTheData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.Action;

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

import com.google.j2objc.annotations.Property;

public class FetchThedata {

	public static void main(String[] args) throws Throwable 
	{
		//
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
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
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
		 
		 String actualtext = driver.findElement(By.xpath("(//td[@class='dvtCellInfo'])[1]")).getText();
		 if(actualtext.contains(org))
		 {
			 System.out.println("The data Enterd Is Verify");
			 
		 }
		 else
		 {
			 System.out.println("Data Is Not Verified");
		 }
		 Actions a=new Actions(driver);
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(logout).perform();
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.close();
		
		
		
		

	}

}
