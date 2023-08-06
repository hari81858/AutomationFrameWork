package vitiger.GenericUtilities;
import java.util.Date;
import java.util.Random;

/**
 * This class consists of all generic methods related to java
 * @author hari
 * 
 */

public class JavaUtility {
	
	/**
	 * This method will generate a random number for every execution
	 * @return random value
	 * 
	 */
	
	public int getRandomNumber()
	{
		Random r=new Random();
		int random=r.nextInt(1000);
		return random;
	}
	
	/**
	 * This method will generate the system date
	 * 
	 * @return
	 */
	
	public String getSystemDate()
	{
		Date d=new Date();
		String date=d.toString();
		return date;	
	}
	
	/**
	 * This method will generate the system date in specfict format
	 * @return 
	 * @return current system date
	 * 
	 */
	
	public String getSystemDateInFormat()
	{
		Date d=new Date();
		String[]date=d.toString().split(" ");
		String currentDate=date[2];
		String month=date[1];
		String year=date[5];
		String time=date[3].replace(":", "_");
		
		String dateInFormat=currentDate+"_"+month+"_"+year+"_"+time;
		return dateInFormat;	
	}

}
