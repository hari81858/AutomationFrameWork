package baseClassPractice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vitiger.GenericUtilities.BaseClass;
import vitiger.GenericUtilities.ExcelFileUtility;
import vitiger.GenericUtilities.JavaUtility;
import vitiger.GenericUtilities.PropertyFileUtilitiy;
import vitiger.GenericUtilities.WebDriverUtility;



@Listeners(vitiger.GenericUtilities.ListenerImplementationClass.class)
public class CreateContactsWithOrganizationTest extends BaseClass {
	
	@Test(groups = "RegressionSuite")
	public void CreateContactsWithOrgTest() throws Throwable
	{
	
	 /*Read the data from Excel Sheet */
				
	  String ORGNAME=eUtil.getDataFromExcelFile("Contacts", 4, 3)+jUtil.getRandomNumber();
	  String LastName=eUtil.getDataFromExcelFile("Contacts", 4, 2);
				
	
		//Step 6:Click on Organization Link
		driver.findElement(By.linkText("Organizations")).click();
						
		//Step 7:Click on create organization Look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
						
		//Step 8:Create Organization
						
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
						
		//Step 9:Save
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						
		//Step 10:Validate
		
		String OrgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(OrgHeader);								
		/*Create contact with organization*/
		//Step 11:Click on contacts link
		
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 12:Navigate to create contact look up image
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 13:Create a contacts with mandatory information
		
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
		
		//Step 14:Switch to child window
		
		wUtil.switchToWindow(driver, "Accounts");
		
		//Step 15:Search for organization
		
		driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);
		
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();//Dynamic Xpath
		
		//Step 16:Switch back to the parent window
		
		wUtil.switchToWindow(driver, "Contacts");
		
		//Step 17:Save
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 18:Validate for Organization
		
		String ContactHead=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(ContactHead.contains(LastName));
		System.out.println(ContactHead);
				
		
	}

	@Test()
	public void sampleTest()
	{
		//Assert.fail();
		System.out.println("demo");
	}
	
	
}
