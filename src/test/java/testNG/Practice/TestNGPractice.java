package testNG.Practice;

import org.testng.annotations.Test;

public class TestNGPractice {
	
	@Test(priority = 1)
	public void createcustomer()
	{
		System.out.println("Customer is created");
	}

	@Test(priority = 3)
	public void modifiyCustomer()
	{
		System.out.println("customer modifyed");
	}
	@Test(priority = -1,invocationCount = 2)
	public void deleteCustomer()
	{
		System.out.println("customer deleted");
	}
}
