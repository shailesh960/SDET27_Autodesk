package com.crm.contacttest;

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

import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;

public class CreateContactWiithOutMandotryFieldTestCase1 {

	public static void main(String[] args) throws Throwable
	{
		JavaUtilite ja=new JavaUtilite();

		FileUtilite fl=new FileUtilite();
		ExcelUtlites excel=new ExcelUtlites();
		WebDriverUtilite web=new WebDriverUtilite();
	
		//Create the Object of FileInputStream to Acess The Properties File
		String URL = fl.getPropertyFileKeyValue("url");
		String USERNAME = fl.getPropertyFileKeyValue("username");
		String PASSWORD = fl.getPropertyFileKeyValue("password");
		String BROWSER= fl.getPropertyFileKeyValue("browser");
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
		web.waitForToLaod(driver);
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
		String name = excel.getDataFromExcelSheet("Sheet1", 1, 1);
			
			
			WebElement ele = driver.findElement(By.name("salutationtype"));
			
			web.selectByValue(ele,"Mr.");
			//handling Drop Down
			
			driver.findElement(By.name("firstname")).sendKeys(name);
			
			
			//Click The Save button
			driver.findElement(By.name("button")).click();
			//handle the alert Pop
			web.switchToAlertWindowAndAccept(driver);
		
			//System.out.println("=====>"+message+"<======");
			
		 //Close The Browser
			WebElement out = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			
		web.mouseHoverElement(driver, out);
		 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		
		driver.quit();
		
		
		
		
		

	}

}
