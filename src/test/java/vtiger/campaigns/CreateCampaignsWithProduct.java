package vtiger.campaigns;

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

public class CreateCampaignsWithProduct {

	public static void main(String[] args) throws Throwable {

	/*Create Product*/
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
		  
			
	// /*Read the data from Excel Sheet */
				
	 String ProName=eUtil.getDataFromExcelFile("Campaigns", 4, 3)+jUtil.getRandomNumber();
	 String CampName=eUtil.getDataFromExcelFile("Campaigns", 4, 2);
				
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
		
		//Step 6:CreateProduct
		
        driver.findElement(By.linkText("Products")).click();
		
		//Step 7:Navigate to create product look up image
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//Step 8:Create a product with mandatory information
		
		driver.findElement(By.name("productname")).sendKeys(ProName);
		
		
		//Step 9:Save
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 10:Validate product
		
		String ProductInf=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(ProductInf.contains(ProName))
		{
			System.out.println("pass");
			System.out.println(ProductInf);
		}
		else 
		{
			System.out.println("Fail");
		}
		
		
		//Step 11:Create Campaign
		
		driver.findElement(By.xpath("//a[text()='More']")).click();
				
		//Step 12:Click on campaign Link
				
		driver.findElement(By.name("Campaigns")).click();

										
		//Step 13:Click on create Campaign Look up image
				
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
								
		//Step 14:Create Campaign with mandatory details
										
		driver.findElement(By.name("campaignname")).sendKeys(CampName);
		
		//Step 15:Select campaign name as Webinar
		
		WebElement CampaignName=driver.findElement(By.name("campaigntype"));
		wUtil.handleDropDown(CampaignName, "Webinar");
		
		//Step 16 :Select Campaign status as Active
		
		WebElement CampaignStatus=driver.findElement(By.name("campaignstatus"));
		wUtil.handleDropDown(CampaignStatus, "Active");
						
		//step 17:Click on product look up
				
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
				
        //Step 18 :Switch to child window
				
		wUtil.switchToWindow(driver, "Products");
				
		//Step 19 :Search for Products
				
		driver.findElement(By.name("search_text")).sendKeys(ProName);
				
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ProName+"']")).click();
								
		//Step 20 :Switch back to the parent window
				
		wUtil.switchToWindow(driver, "Campaigns");
				
		//Step 21:Save
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
											
		//Step 22:Validate
						
		String CampaignInf=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	   if(CampaignInf.contains(CampName))
		{
			System.out.println("Campaign Created");
			System.out.println(CampaignInf);
		}
	   else
		{
		  System.out.println("Fail");
		}		
												
	  //Step 23 :LogOut
				
		WebElement AdminImg=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverActions(driver,AdminImg);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		System.out.println("Logout successful");		
				
	}

}
