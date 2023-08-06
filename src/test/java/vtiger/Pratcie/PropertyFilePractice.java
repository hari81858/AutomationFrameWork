package vtiger.Pratcie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyFilePractice {

	public static void main(String[] args) throws Throwable {

		
	//Step 1:Load the Document in java reusable file
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommanData.properties");
		
	//Step 2:Create a object of properties class from java.utl
		Properties pobj=new Properties();
		
	//Step 3:Load the file into properties class
		pobj.load(fis);
		
	//Step 4:Provide the key and get the value 
		String value=pobj.getProperty("url");
		System.out.println(value);
		
			
	}

}
