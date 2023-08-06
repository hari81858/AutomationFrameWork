package vtiger.organization;

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

public class OrganizationWithIndustryEnergy {

	public static void main(String[] args) throws Throwable {
		
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
		
		String ORGNAME=eUtil.getDataFromExcelFile("Organizations", 10, 2)+jUtil.getRandomNumber();
		String INDUSTRY=eUtil.getDataFromExcelFile("Organizations", 10, 3);
		String TYPE=eUtil.getDataFromExcelFile("Organizations", 10, 4);
		
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
				
		//Step 8:Create Organization with mandatory details
				
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
				
		//Step 9:Choose 'Energy' in industry drop down
		
		WebElement IndurstyDropDown=driver.findElement(By.name("industry"));
		wUtil.handleDropDown(IndurstyDropDown, "Energy");
				
		//Step 10:Choose Type as 'Customer'
		
		 WebElement Type=driver.findElement(By.name("accounttype"));
		 wUtil.handleDropDown(Type, "Customer");
				
		//Step 10:Save
		 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Step 11:Validate
		
		String OrgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(OrgHeader.contains(ORGNAME))
		{
			System.out.println("Pass");
			System.out.println(OrgHeader);
		}
	  else
		{
		    System.out.println("Fail");
		}
				
		//Step 12:Logout of Application
		
		WebElement AdminImg=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wUtil.mouseHoverActions(driver, AdminImg);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Logout successfull");
				
	 }

}
