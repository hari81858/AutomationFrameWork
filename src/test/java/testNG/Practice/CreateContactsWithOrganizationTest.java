package testNG.Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vitiger.GenericUtilities.ExcelFileUtility;
import vitiger.GenericUtilities.JavaUtility;
import vitiger.GenericUtilities.PropertyFileUtilitiy;
import vitiger.GenericUtilities.WebDriverUtility;

public class CreateContactsWithOrganizationTest {
	
	@Test()
	public void CreateContactsWithOrgTest() throws Throwable
	{
		
	   /*Create Organization*/
      //Create objects of required utilities
		
      JavaUtility jUtil=new JavaUtility();
      PropertyFileUtilitiy pUtil=new PropertyFileUtilitiy();
	  ExcelFileUtility eUtil=new ExcelFileUtility();
	  WebDriverUtility wUtil=new WebDriverUtility();
				
	  WebDriver driver=null;
				
	  //Step 1:Read the data from the property file and Excel file
	  /*Read the Data from Property file -Common Data */
				
	  String BROWSER=pUtil.getDataFromPropertyFile("browser");
	  String USERNAME=pUtil.getDataFromPropertyFile("username");
	  String PASSWORD=pUtil.getDataFromPropertyFile("password");
	  String URL=pUtil.getDataFromPropertyFile("url");
				
	 /*Read the data from Excel Sheet */
				
	  String ORGNAME=eUtil.getDataFromExcelFile("Contacts", 4, 3)+jUtil.getRandomNumber();
	  String LastName=eUtil.getDataFromExcelFile("Contacts", 4, 2);
				
	 //Step 2:Launch the browser - driver acting based run time Data - run time polymorphism
	
	  if(BROWSER.equalsIgnoreCase("Chrome"))
	 {
	   WebDriverManager.chromedriver().setup();
	   driver=new ChromeDriver();
	   System.out.println(BROWSER+"---chrome browser launched");
					
	  }
	  
     else if(BROWSER.equalsIgnoreCase("FireFox"))
     {
	        	
	   WebDriverManager.firefoxdriver().setup();
	   driver=new FirefoxDriver();
	   System.out.println(BROWSER+"---Fire fox browser launched");
			
     }
   else
			  
     {
			 
       System.out.println("Invalid browser name");
			
	 }
	
		//Step 3:Maximize the browser
	   
		 wUtil.maximizeWindow(driver);
		 wUtil.waitForElementsToLoad(driver);
				        
		//Step 4: Load the URL
		 
	    driver.get(URL);
						 
		//Step 5:Login to the Application
	    
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
						
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
		if(OrgHeader.contains(ORGNAME))
		{
	    	System.out.println("Organization Created");
			System.out.println(OrgHeader);
		}
		else
		{
			System.out.println("Fail");
		}
						
										
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
		if(ContactHead.contains(LastName))
		{
			System.out.println("pass");
			System.out.println(ContactHead);
		}
		else {
			System.out.println("Fail");
		}
		
		//Step 19:LogOut
		
		WebElement AdminImg=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverActions(driver,AdminImg);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logout successful");
		
					
	}

	
}
