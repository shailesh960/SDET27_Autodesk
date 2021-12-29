package com.crm.autodesk.genricuUtilite;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * 
 * @author Shailesh
 *
 */
public class WebDriverUtilite 
{
	/**
	 * It wait For The Page to Load before Identfying any Synchronized element on WebPage
	 * @param driver
	 */
public void waitForToLaod(WebDriver driver)
{
	driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
}
/**
 * wait the page To Load Before Assynchronized[]
 * @param driver
 */
public void waitForPageToLoadJsElement(WebDriver driver)
{
	driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);
}
/**
 * it is Used to Wait For Element to  be Clickable in GUI
 * @param driver
 * @param element
 */
public void waitForElementToBeClickAble(WebDriver driver,WebElement element)
{
	WebDriverWait wait= new WebDriverWait(driver,20);
	wait.until(ExpectedConditions.elementToBeClickable(element));
}
/**
 * It is used  to Wait For The Element TO ClickAble in GUI
 * @param driver
 * @param element
 * @param pollingTime In the Form Of Seconds
 * @throws Throwable
 */
public void waitForTheElementWithCustomTimeOut(WebDriver driver, WebElement element,int pollingTime) throws Throwable
{
	FluentWait wait=new FluentWait(driver);
	wait.pollingEvery(pollingTime,TimeUnit.SECONDS);
	wait.wait(20);
	wait.until(ExpectedConditions.elementToBeClickable(element));
	
	}
/**
 * It Is Use TO Switch to the Window Based On Window Title
 * @param driver
 * @param partialTitleOFWindow
 */
public void switchToWindow(WebDriver driver, String partialTitleOFWindow)
	{
	Set<String> set = driver.getWindowHandles();
	Iterator<String> it = set.iterator();
	while(it.hasNext())
	{
		String wid = it.next();
		driver.switchTo().window(wid);
		String currentTitle = driver.getTitle();
		if(currentTitle.contains(partialTitleOFWindow))
		{
			break;
		}
		
	}
	}
/**
 * Used To Switch To Alert Window And Accept
 * @param driver
 */
	public void switchToAlertWindowAndAccept(WebDriver driver)
	{
	driver.switchTo().alert().accept();	
	}
	
	/**Used To Switch TO  Alert Window And Click Dissmiss
	 * 
	 * @param driver
	 */
	
	public void switchToAlertWindowAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	/**
	 * Used To Switch The Frame By Index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
		
	}
	/**
	 * Used TO Switch The Bu Id Or Attribute
	 * @param driver
	 * @param id_Attribute
	 */
	public void switchToFrame(WebDriver driver,String id_Attribute)
	{
		driver.switchTo().frame(id_Attribute);
		
	}
	/**
	 * Used to handle DropBy Select By Index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element,int index)
	{
		Select s1=new Select(element);
		s1.selectByIndex(index);
	}
	/**
	 * Used to handle DropDpown By Using select By VisibleText
	 * @param element
	 * @param text
	 */
	public void selectByText(WebElement element, String text)
	{
		Select s1=new Select(element);
		s1.selectByVisibleText(text);
	}
	/**
	 * Used to Handle DropDown By Select By Value
	 * @param element
	 * @param value
	 */
	public void  selectByValue(WebElement element,String value)
	{
	Select s1=new Select(element);
	s1.selectByValue(value);
	}
	/**
	 * Used To handle The Mouse Cursor
	 * @param driver
	 * @param element
	 */
	public void mouseHoverElement(WebDriver driver,WebElement element)
	{
		Actions a1=new Actions(driver);
		a1.moveToElement(element).perform();
		
	}
	/**
	 * Used To Right Click on the Element
	 * @param driver
	 * @param element
	 */
	public void contextClick(WebDriver driver, WebElement element)
	{
		Actions a1= new Actions(driver);
		a1.contextClick(element).perform();
				
		
	}
	 /**
	  * Used to Double Click On the Element
	  * @param driver
	  * @param element
	  */
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions a1=new Actions(driver);
		a1.doubleClick(element).perform();
		
	}
	/**
	 * 
	 * @param driver
	 * @param jScript
	 */
	public void javaScriptExecuter(WebDriver driver,String jScript)
	{
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript(jScript, null);
	}
	/**
	 * used to click the element 
	 * @param element
	 * @throws Throwable
	 */
	public void waitAndClick(WebElement element) throws Throwable
	{
		int count=0;
		while(count<20)
		{
			try {
				element.click();
				break;
				
			}
			catch(Throwable e)
			{
				Thread.sleep(1000);
				count++;
			}
		}
		
	}
	/**
	 * taking The ScreenShot 
	 * @param driver
	 * @param screenShot
	 * @throws Throwable 
	 */
	
	public void takeScreenShot(WebDriver driver, String screenShot) throws Throwable
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		 File src = ts.getScreenshotAs(OutputType.FILE);
		 File dest= new File("./screenshot/"+screenShot+".png");
		 Files.copy(src, dest);
	}
/**
 * pass Enter key appertain in the browser
 * @param driver
 */
	public void passEnterKey(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.sendKeys(Keys.ENTER).perform();
	}
	
	}

