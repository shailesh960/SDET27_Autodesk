package rmgyantraT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class AddProject {

	public static void main(String[] args) throws Throwable
	{

		String expectedname="school";
		Connection con=null;

		
		//browser is called
		WebDriver driver=new ChromeDriver();
		//set the url of the applicatiin
		driver.get("http://localhost:8084/");
		//maximize the vbrowser
		driver.manage().window().maximize();
		//applying implicitWait
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//inspect the username text field and the give the user name
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		//inspect the pasword fieldand give the valide password
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		// inspect thesign in button and clickedd
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		//inspect the projects link
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		//inspect the create project button 
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		//inspect the project name field
		driver.findElement(By.name("projectName")).sendKeys("school");
		//inspect the project maneger field
		driver.findElement(By.name("createdBy")).sendKeys("Deepanshu");
		//inspect the select text field  
		WebElement dropdown = driver.findElement(By.xpath("(//select[@class='form-control'])[2]"));
		Select get=new Select(dropdown);
		//selecting the option from the dropdown list by usnig select by value
		get.selectByIndex(3);
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		 

		try
		{
			//Register the Driver
			Driver dbdriver = new Driver();
			DriverManager.registerDriver(dbdriver);
			//established the Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root","root");
			//create the statement
			Statement state = con.createStatement();
			//execute the Query
			ResultSet result = state.executeQuery("select * from project");
			while(result.next()) 
			{
			if(	result.getString(4).equals(expectedname))
			{
				System.out.println("Passed");
			}

			}
			//System.out.println("at mid");
			
		
		}
		catch(Exception e)
		{

		}
		finally
		{
			con.close();
		}
		driver.quit();

	}

}
