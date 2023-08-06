package vitiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * This class consists of generic methods related to Property file
 * @author hari
 *
 */

public class PropertyFileUtilitiy {
	/**
	 * This method read the data from property file based on given key
	 * @return 
	 * @throws Throwable 
	 * 
	 */

	public String getDataFromPropertyFile(String key) throws Throwable 
	{
		
		//FileInputStream fis=new FileInputStream(".src\\test\\resources\\CommanData.properties");

		FileInputStream fis=new FileInputStream(IConstants.propertyFilePath);
		Properties pobj=new Properties();
		pobj.load(fis);
		String value=pobj.getProperty(key);
		return value;
	}
		
}
