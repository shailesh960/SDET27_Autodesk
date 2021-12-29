package com.crm.autodesk.genricuUtilite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;

import com.mysql.cj.jdbc.Driver;

/**
 * 
 * @author Shailesh
 *
 */

public class JdbcUtilite 
{
	/**
	 *It  will Createt the DataBase Connection
	 * @throws Throwable 
	 */
	Connection con;
	public void createJdbcConnection() throws Throwable
	{
		/*Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("jdbc:mysql:\\localhost:3306\\student","root", "root");
		Statement state = con.createStatement();
		ResultSet result = state.executeQuery("select * from studentinfo");
		while(result.next()) 
		{
			System.out.println(result.getString(2)+"\t"+result.getString(3));
		}
		*/
		System.out.println("Connection get Created");
		}
		
		
	
	
	/**
	 * It will Close the Data Base
	 * @throws Throwable 
	 */
	public void closeConnection() throws Throwable
	{
/*
		con.close();
*/
		System.out.println("connectiom get Closed");
	}
	
			
	

}
