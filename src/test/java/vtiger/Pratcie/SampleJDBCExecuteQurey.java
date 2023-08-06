package vtiger.Pratcie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteQurey {
	
	public static void main(String[] args) throws SQLException {
		
		//Get driver from my sql jar and register this in driver manager
		Driver driverRef=new Driver();
		
		//Step 1:Register the driver
		
		DriverManager.registerDriver(driverRef);

		//Step 2 Get connection with driver - productdb name
		
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb", "root", "root");

		//Step 3:Issue create statement
		 
          Statement state=con.createStatement();

		//Step 4:Execute the Query - provide table name
          
          ResultSet result=state.executeQuery("select * from sample;");
           while(result.next())
           {
        	   System.out.println(result.getString(1)+"  "+result.getString(2)+"  "+result.getString(3));
           }
           
		 //Step 5:Close the database
           con.close();
	
		
		
	
	}
	
}
