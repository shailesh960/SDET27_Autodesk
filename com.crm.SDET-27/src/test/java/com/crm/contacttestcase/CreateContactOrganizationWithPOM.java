package com.crm.contacttestcase;

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

import com.crm.autodesk.genricuUtilite.ExcelUtlites;
import com.crm.autodesk.genricuUtilite.FileUtilite;
import com.crm.autodesk.genricuUtilite.JavaUtilite;
import com.crm.autodesk.genricuUtilite.WebDriverUtilite;
import com.crm.autodesk.objectRepositry.ContactInfo;
import com.crm.autodesk.objectRepositry.ContactPage;
import com.crm.autodesk.objectRepositry.CreateContactPage;
import com.crm.autodesk.objectRepositry.CreateOrganizationPage;
import com.crm.autodesk.objectRepositry.HomePage;
import com.crm.autodesk.objectRepositry.LoginPage;
import com.crm.autodesk.objectRepositry.OrganizationInfo;
import com.crm.autodesk.objectRepositry.OrganizationPage;

public class CreateContactOrganizationWithPOM {

	public static void main(String[] args) throws Throwable 
	{
		FileUtilite fileU=new FileUtilite();
		ExcelUtlites excelU= new ExcelUtlites();
		JavaUtilite javaU= new JavaUtilite();
		WebDriverUtilite webU=new WebDriverUtilite();
		
		String URL = fileU.getPropertyFileKeyValue("url");
		String USERNAME = fileU.getPropertyFileKeyValue("username");
		String PASSWORD = fileU.getPropertyFileKeyValue("password");
		String BROWSER = fileU.getPropertyFileKeyValue("browser");
		WebDriver driver;
		if(BROWSER.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		
		//Random random= new Random();
		int randomnum = javaU.getRandomNumber();
		webU.waitForToLaod(driver);
		driver.manage().window().maximize();
		driver.get(URL);
		
		LoginPage lp= new LoginPage(driver);
		lp.clickOnLogginButton(USERNAME, PASSWORD);
		
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganizationLink();
		
		OrganizationPage op=new OrganizationPage(driver);
		op.clickOnOrganizationlookUp();
		
		
		String org = excelU.getDataFromExcelSheet("Sheet1", 1, 2)+randomnum;
		String contactName = excelU.getDataFromExcelSheet("Sheet1", 1, 1)+randomnum;
		
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		
		cop.enterLastName(org);
		cop.saveOrganization();
		WebDriverWait wait=new WebDriverWait(driver,20);
		 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("dvHeaderText"))));
		 
		
		OrganizationInfo oinfo=new OrganizationInfo(driver);
		
		oinfo.clickContactLink();
		 hp.clickOnContact();
		 
		 ContactPage cp=new ContactPage(driver);
		 cp.createContactImg();
		
	
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.enterLastName(contactName);
		String title1="Accounts";
		String title2="Contacts";
		
		ccp.clickOrganization(driver, title1, title2,org);
		ccp.saveButton();
		ContactInfo cinfo=new ContactInfo(driver);
		String actualName = cinfo.getHeaderContact();
		
		
			if(actualName.contains(contactName))
			{
				System.out.println(org+"Organization is Sucessfully Attached with Contct");
			}
			else
			{
				System.out.println(org+"===>Organization is Not Attached with Contct");	
			}
	
			hp.clickOnLogout(driver);
			
			driver.quit();
		

	}

}
