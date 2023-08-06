 package testNG.Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPratcie {
	
	@Test(dataProvider = "Phones")
	public void addToCartTest(String name,int price,String model)
	{
		System.out.println("Name of Phone "+name+" Price of the phone" +price+" model of the phone" +model);
	}

	@DataProvider(name = "Phones")
	public Object[][] getdata()
	{
	 Object[][] data=new Object[3][3];
	 
	 data[0][0]="Iphone";
	 data[0][1]=20000;
	 data[0][2]="s13";
	 
	 data[1][0]="Samsung";
	 data[1][1]=15000;
	 data[1][2]="A80";
	 
	 data[2][0]="Vivo";
	 data[2][1]=10000;
	 data[2][2]="Y20";
	 
	 return data;
	 
	} 
	 
}
