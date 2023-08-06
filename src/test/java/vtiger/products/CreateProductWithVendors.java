package vtiger.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vitiger.GenericUtilities.ExcelFileUtility;
import vitiger.GenericUtilities.JavaUtility;
import vitiger.GenericUtilities.PropertyFileUtilitiy;
import vitiger.GenericUtilities.WebDriverUtility;

public class CreateProductWithVendors {

	public static void main(String[] args) throws Throwable {

	  /*Create Vendors*/
		
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
				
	 String ProductNAME=eUtil.getDataFromExcelFile("Products", 4, 2)+jUtil.getRandomNumber();
	 String VendorsName=eUtil.getDataFromExcelFile("Products", 4, 3);
				
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
		
		//Step 6:Create Vendors
		
		driver.findElement(By.xpath("//a[text()='More']")).click();
		
		//Step 6:Click on Vendors Link
		
		driver.findElement(By.name("Vendors")).click();
						
		//Step 7:Click on create Vendors Look up image
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
						
		//Step 8:Create Vendor with required details
								
		driver.findElement(By.name("vendorname")).sendKeys(ProductNAME);
		WebElement GLAcount=driver.findElement(By.name("glacct"));
		wUtil.handleDropDown(GLAcount, "303-Interest-Income");
						
		//Step 9:Save
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
								
		//Step 10:Validate
				
		String venHeader=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(venHeader.contains(ProductNAME))
		{
			System.out.println("Vendor Created");
		    System.out.println(venHeader);
		}
		else
		{
			System.out.println("Fail");
		}
		
		/*Create Product with Vendor*/
		
		//Step 11:Click on Product link
		
		driver.findElement(By.linkText("Products")).click();
		
		//Step 12:Navigate to create product look up image
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step 13:Create a product with mandatory information
		
		driver.findElement(By.name("productname")).sendKeys(ProductNAME);
		
		//Step14:Click on vendors look up page
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		//Step 15:Switch to child window
		
		wUtil.switchToWindow(driver, "Vendors");
		
		//Step 16:Search for Vendors
		
		driver.findElement(By.name("search_text")).sendKeys(VendorsName);
		
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ProductNAME+"']")).click();//Dynamic Xpath
		
		//Step 17:Switch back to the parent window
		
		wUtil.switchToWindow(driver, "Products");
		
		//Step 18:Save
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 18:Validate for Organization
		
	    String ProductInf=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
	    if(ProductInf.contains(VendorsName))
	    {
		   System.out.println("pass");
		   System.out.println(ProductInf);
	    }
	   else 
		{
			System.out.println("Fail");
		}
									
		//Step 19:LogOut
		
	    WebElement AdminImg=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverActions(driver,AdminImg);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logout successful");		
		
	    }

}
