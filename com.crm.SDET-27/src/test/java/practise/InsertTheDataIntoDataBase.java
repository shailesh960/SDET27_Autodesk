package practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertTheDataIntoDataBase {

	public static void main(String[] args) throws Throwable 
	{
		
		//register the driver
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		//establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");
		Statement statment = conn.createStatement();
		//execute the query
		int result = statment.executeUpdate("insert into studentinfo(fname,lname,address) value ('balram','singh','india')");
		//verificcation
		if (result == 1) {
			System.out.println("success");
		}else {
			System.out.println("error");
		}
		
		conn.close();
		
		

	}

}
