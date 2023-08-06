package vtiger.Pratcie;

import vitiger.GenericUtilities.ExcelFileUtility;
import vitiger.GenericUtilities.JavaUtility;
import vitiger.GenericUtilities.PropertyFileUtilitiy;

public class GenericUtilitiyPractice {

	public static void main(String[] args) throws Throwable {

		JavaUtility jUtil=new JavaUtility();
		int value=jUtil.getRandomNumber();
		System.out.println(value);
		
		//String value1=jUtil.getSystemDate();
		//System.out.println(value1);
		System.out.println(jUtil.getSystemDate());
		
		//String value2=jUtil.getSystemDateInFormat();
		//System.out.println(value2);
		System.out.println(jUtil.getSystemDateInFormat());
		

		PropertyFileUtilitiy pUtil=new PropertyFileUtilitiy();
		String value1=pUtil.getDataFromPropertyFile("username");
		System.out.println(value1);
		
		ExcelFileUtility eUtil=new ExcelFileUtility();
		String value2=eUtil.getDataFromExcelFile("Organizations", 1, 2);
		System.out.println(value2);
		
		eUtil.writeDataIntoExeclSheet("sample", 3, 4, "BatMan");
		System.out.println("data added");
	

	}

}
