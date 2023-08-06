package vtiger.contacts;

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
import vtiger.objectRepository.ContactInfoPage;
import vtiger.objectRepository.ContactPage;
import vtiger.objectRepository.CreateNewContactPage;
import vtiger.objectRepository.CreateNewOrganizationPage;
import vtiger.objectRepository.HomePage;
import vtiger.objectRepository.LoginPage;
import vtiger.objectRepository.OrganizationInfoPage;
import vtiger.objectRepository.OrganizationPage;


public class CreateContactWithOrganizationPomClass {
	
	public static void main(String[] args) throws Throwable {
		
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
		    
		    LoginPage lp=new LoginPage(driver);
		    lp.loginToApp(USERNAME, PASSWORD);
		    				
			//Step 6:Click on Organization Link
		    
		    HomePage hp=new HomePage(driver);
		    hp.clickOnOrgLink();
		    
			//Step 7:Click on create organization Look up image
		    
		    OrganizationPage op=new OrganizationPage(driver);
		    op.clickOnCreateOrgLookUpImg();
		    				
			//Step 8:Create Organization
		    
			CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
			cnop.createOrganization(ORGNAME);
		
			//Step 10:Validate
			
			OrganizationInfoPage oip=new OrganizationInfoPage(driver);
			
			String OrgHeader=oip.getHeaderText();
			
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
			
			hp.clickOnContactLink();
			
			//Step 12:Navigate to create contact look up image
			
			ContactPage cp=new ContactPage(driver);
			cp.clickOnCreateContactLookUpImg();
			
			//Step 13:Create a contacts with mandatory information
			
			CreateNewContactPage cncp=new CreateNewContactPage(driver);
			
			cncp.createContact(driver, LastName, ORGNAME);
			
			//Step 18:Validate for contact
			
			ContactInfoPage cip=new ContactInfoPage(driver);
			
			String ContactHeader =cip.getContactHeader();
			
			if(ContactHeader.contains(LastName))
			{
				System.out.println(ContactHeader);
				System.out.println("Pass");
			}
			else {
				System.out.println("Fail");
			}
			
			//Step 19:LogOut
			
			hp.logOutOfApplication(driver);
			
			System.out.println("Logout successful");
						
		}
	
		 
	}


