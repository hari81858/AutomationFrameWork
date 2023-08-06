package vtiger.Pratcie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithIndustryUsingDDT {

	public static void main(String[] args) throws Throwable {
		
		WebDriver driver=null;
		Random r=new Random();
		int random=r.nextInt(1000);

		//Step 1:Read the data from the property file and Excel file
		/*Read the Data from Property file -Common Data */
		FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommanData.properties");
		Properties pobj=new Properties();
		pobj.load(fisp);
		String BROWSER=pobj.getProperty("browser");
		String USERNAME=pobj.getProperty("username");
		String PASSWORD=pobj.getProperty("password");
		String URL=pobj.getProperty("url");
		
		/*Read the data from Excel Sheet */
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb=WorkbookFactory.create(fise);
		Sheet sh=wb.getSheet("Organizations");
		String ORGNAME=sh.getRow(4).getCell(2).getStringCellValue();
		String INDUSTRY=sh.getRow(4).getCell(3).getStringCellValue();
		
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
		        driver.manage().window().maximize();
		        
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
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
				String OrgName=ORGNAME+random;
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME+random);
				
				//Step 9:Choose 'Chemicals' in industry drop down
				WebElement IndurstyDropDown=driver.findElement(By.name("industry"));
				Select s=new Select(IndurstyDropDown);
				s.selectByValue(INDUSTRY);
				
				//Step 10:Save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(3000);
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
				Actions act=new Actions(driver);
				act.moveToElement(AdminImg).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				System.out.println("Logout successfull");
				
				
				
	}

}
