package com.crm.contacttest;

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

import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;

public class CreateContacctWithOrganizationTestCase {

	public static void main(String[] args) throws Throwable 
	{
		FileUtilite fl=new FileUtilite();
		JavaUtilite ja=new JavaUtilite();
		WebDriverUtilite web =new WebDriverUtilite();
		ExcelUtlites excel= new ExcelUtlites();
		//Create the File Input Stream fis
		String URL= fl.getPropertyFileKeyValue("url");
		String USERNAME = fl.getPropertyFileKeyValue("username");
		String PASSWORD= fl.getPropertyFileKeyValue("password");
		String BROWSER = fl.getPropertyFileKeyValue("browser");
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
		String name = excel.getDataFromExcelSheet("Sheet1", 1, 1);
		
			
		driver.findElement(By.name("lastname")).sendKeys(name);
		
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
		//ChildBrowser popup
		web.switchToWindow(driver, "Accounts");
		driver.findElement(By.xpath("//a[text()='TestYantra']")).click();
		// Back to parent Window
		web.switchToWindow(driver, "Contacts");
		
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		// Get The title Of The Header
		
		String verified = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		System.out.println(verified+"==>message passed");
		WebElement out = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		web.mouseHoverElement(driver, out);	
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

	
	driver.quit();

		
	}

}
