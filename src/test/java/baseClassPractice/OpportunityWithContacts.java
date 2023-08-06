package baseClassPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vitiger.GenericUtilities.BaseClass;
import vitiger.GenericUtilities.ExcelFileUtility;
import vitiger.GenericUtilities.JavaUtility;
import vitiger.GenericUtilities.PropertyFileUtilitiy;
import vitiger.GenericUtilities.WebDriverUtility;

public class OpportunityWithContacts extends BaseClass{

	@Test(groups = "Smoke")
	public void OpportunityWithContactsTest()  throws Throwable
	{
		
		 
		 /*Read the data from Excel Sheet */
					
		  String LastName =eUtil.getDataFromExcelFile("Opportunities", 7, 3)+jUtil.getRandomNumber();
		  String OpportunityName=eUtil.getDataFromExcelFile("Opportunities", 7, 2);
					
		 							 
			//Step 6:Create Contacts
			
         driver.findElement(By.linkText("Contacts")).click();
		
		//Step 7:Navigate to create contact look up image
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 8:Create a contacts with mandatory information
		
		driver.findElement(By.name("lastname")).sendKeys(LastName);
		
		//Step 9:Save
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 10:Validate for Organization
		
		String ContactHead=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(ContactHead.contains(LastName));
		
		 // Step 11: Create Opportunity
		
		driver.findElement(By.linkText("Opportunities")).click();
		
        //Step 12: Navigate to the Opportunity look up
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		driver.findElement(By.name("potentialname")).sendKeys(OpportunityName);
		
		//Step 13:Choose RelatedTo 'Contacts' in dropDown
		
		WebElement RelatedTo=driver.findElement(By.id("related_to_type"));
		wUtil.handleDropDown(RelatedTo, "Contacts");
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		//Step 14:Switch to child window
		
		wUtil.switchToWindow(driver, "Contacts");
				
		//Step 15:Search for Contacts
				
		driver.findElement(By.name("search_text")).sendKeys(LastName);
				
		driver.findElement(By.name("search")).click();
				
		driver.findElement(By.xpath("//a[text()=' "+LastName+"']")).click();//Dynamic Xpath
				
		//Step 16:Switch back to the parent window
				
		wUtil.switchToWindow(driver, "Potentials");
				
		//Step 17:Save
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
		//Step 18:Validate for Organization
				
		String OppInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(OppInfo.contains(LastName));
						
				 			
	  }

}
