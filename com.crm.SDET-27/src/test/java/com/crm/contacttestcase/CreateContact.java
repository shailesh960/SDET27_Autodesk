package com.crm.contacttestcase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;
import com.crm.autodesk.objectRepositry.ContactInfo;
import com.crm.autodesk.objectRepositry.ContactPage;
import com.crm.autodesk.objectRepositry.CreateContactPage;
import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.LoginPage;

public class CreateContact {

	public static void main(String[] args) throws Throwable
	{

		JavaUtilite ja=new JavaUtilite();
	
		FileUtilite fl=new FileUtilite();
		ExcelUtlites excel=new ExcelUtlites();
		WebDriverUtilite web=new WebDriverUtilite();
			
		String URL=fl.getPropertyFileKeyValue("url");
		String USERNAME=fl.getPropertyFileKeyValue("username");
		String PASSWORD=fl.getPropertyFileKeyValue("password");
		String BROWSER=fl.getPropertyFileKeyValue("browser");
		
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

		int randomnum=ja.getRandomNumber();
		
		LoginPage lp=new LoginPage(driver);
		driver.manage().window().maximize();
		web.waitForToLaod(driver);
		driver.get(URL);
		lp.clickOnLogginButton(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.clickOnContact();
		
		ContactPage cp=new ContactPage(driver);
		cp.createContactImg();
		
		
		CreateContactPage ccp=new CreateContactPage(driver);
		String actualdata = excel.getDataFromExcelSheet("Sheet1", 1, 2)+randomnum;
						ccp.enterLastName(actualdata);
						String title="Accounts";
						String title2="Contacts";
						
						ccp.clickOrganization(driver, title, title2,actualdata);
						ccp.saveButton();
						ContactInfo cinfo=new ContactInfo(driver);
						String currentData = cinfo.getHeaderContact();
				
				if(currentData.contains(actualdata))
				{
					System.out.println("Data Is Enterd");
				}
				else
				{
					System.out.println("data is Not Entered");
					
				}
				hp.clickOnLogout(driver);
				
				
						driver.quit();
		
	}

}
