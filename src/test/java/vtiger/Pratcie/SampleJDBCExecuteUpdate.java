package vtiger.Pratcie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;


public class SampleJDBCExecuteUpdate {

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
          
          String Query="insert into sample values('ben',26,'hyderabad');";
           int result=state.executeUpdate(Query);
           //Validate
           if(result==1)
           {
        	   System.out.println("data added");
        	   
           }
           else 
           {
			System.out.println("Data not added");
		}
         ResultSet res=state.executeQuery("select * from sample;");
           while(res.next())
           {
        	   System.out.println(res.getString(1));
           }
           
       //Step 5: close the db
           
           con.close();
	}
	
}
