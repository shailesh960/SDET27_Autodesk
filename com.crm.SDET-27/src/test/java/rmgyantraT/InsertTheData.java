package rmgyantraT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertTheData {

	public static void main(String[] args) throws Throwable
	{
		String exceptecedname="bussiness";
	 Connection con=null;
	 try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root","root");
		Statement state = con.createStatement();
		int result = state.executeUpdate("insert into project(project_id,created_by,created_on,project_name,status,team_size) value(9,'Radhe','16/12/2021','bussiness','on going',0)");
		
		if(result==1)
		{
		System.out.println("updated");	
		}
		else
		{
			System.out.println("failed");
		}
		
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 
	 }
	
		con.close();
	}

}
