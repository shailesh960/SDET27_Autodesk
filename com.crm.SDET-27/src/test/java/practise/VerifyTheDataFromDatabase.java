package practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class VerifyTheDataFromDatabase {

	public static void main(String[] args) throws Throwable
	{
		String exepected="shailesh";
		Connection con=null;
		try{//register the database 
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		//Establish the connection with Database
	   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");
		//issuse the satatment
		Statement statment = con.createStatement();
		//exceute the queries
		ResultSet resultset = statment.executeQuery("select * from studentinfo");
		while(resultset.next()) 
		{
			if(resultset.getString(4).equals(exepected))
			{
				System.out.println("passed");
			}
		}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		//close the connection
		finally
		{
		con.close();
		}


	}

}
