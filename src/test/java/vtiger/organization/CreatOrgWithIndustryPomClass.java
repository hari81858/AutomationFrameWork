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
import vtiger.objectRepository.CreateNewOrganizationPage;
import vtiger.objectRepository.HomePage;
import vtiger.objectRepository.LoginPage;
import vtiger.objectRepository.OrganizationInfoPage;
import vtiger.objectRepository.OrganizationPage;

public class CreatOrgWithIndustryPomClass {
	
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
		
		String ORGNAME=eUtil.getDataFromExcelFile("Organizations", 1, 2)+jUtil.getRandomNumber();
		String INDUSTRY=eUtil.getDataFromExcelFile("Organizations", 4, 3);
		
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
		cnop.getOrgNameEdt();
		
		//Step 9:Choose 'Chemicals' in industry drop down
		
		cnop.createOrganization(ORGNAME, INDUSTRY);
		
		//Step 10:Save
		
		cnop.getSaveBtn();
		
		//Step 11:Validate
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String OrgHeader=oip.getHeaderText();
		if(OrgHeader.contains(ORGNAME))
		{
			System.out.println("pass");
			System.out.println(ORGNAME);
		}
		else 
		{
			System.out.println("fail");
		}		
			
		//Step 12:Logout of Application
		hp.logOutOfApplication(driver);
		System.out.println("Logout successfull");
		
			      
	   }

	}


