package vitiger.GenericUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.objectRepository.HomePage;
import vtiger.objectRepository.LoginPage;
/**
 * This method will 
 * @author hari
 *
 */
public class BaseClass {

	
	public JavaUtility jUtil=new JavaUtility();
	public PropertyFileUtilitiy pUtil=new PropertyFileUtilitiy();
	public ExcelFileUtility eUtil=new ExcelFileUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();		
			
	public WebDriver driver=null;
	
	// Only for listener to take screenshot 
	public static WebDriver sdriver;
	
	@BeforeSuite(alwaysRun = true)
	public void bsConfig()
	{
		System.out.println("====== db connection successful =====");
	}
	//@Parameters("browser")
	//@BeforeTest()
	@BeforeClass(alwaysRun = true)
	public void bcConfig(/*String BROWSER */) throws Throwable
	{
		
	    //Read the Browser name and URl from Property file
		
		String BROWSER=pUtil.getDataFromPropertyFile("browser");
		
		String URL=pUtil.getDataFromPropertyFile("url");
		
		//Step 2:Launch the browser - driver acting based run time Data - run time polymorphism		

		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			System.out.println(BROWSER+"---chrome browser launched----");
			
		}
		else if(BROWSER.equalsIgnoreCase("FireFox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println(BROWSER+"---Fire fox browser launched-----");
		}
		else
		{
			System.out.println("Invalid browser name");
		}		
			
		// Only for listener to take screenshot 
		sdriver=driver;

		
		 //Step 3:Maximize the browser
        wUtil.maximizeWindow(driver);
        wUtil.waitForElementsToLoad(driver);
        
       //Step 4: Load the URL

		driver.get(URL);	
		
	    }
	
	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws Throwable
	{
		//Read user name and password from the property file
		String USERNAME=pUtil.getDataFromPropertyFile("username");
		String PASSWORD=pUtil.getDataFromPropertyFile("password");
		
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("==== Login successful=====");
	}
	
	@AfterMethod(alwaysRun = true)
	public void amConfig()
	{
		HomePage hp=new HomePage(driver);
		hp.logOutOfApplication(driver);
		System.out.println("===LogOut successful===");
	}
	
	@AfterClass(alwaysRun = true)
	public void acConfig()
	{
		driver.quit();
		System.out.println("====Browser closed=====");
		
	}
	@AfterSuite(alwaysRun = true)
	public void asConfig()
	{
		System.out.println("==== db connection closed =====");

	}

}
