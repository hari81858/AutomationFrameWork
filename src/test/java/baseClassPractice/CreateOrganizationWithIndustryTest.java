package baseClassPractice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vitiger.GenericUtilities.BaseClass;


public class CreateOrganizationWithIndustryTest extends BaseClass{

	@Test()
	public void CreateOrganizationWithInduTest() throws Throwable
	{
		
		
		//Random r=new Random();
		//int random=r.nextInt(100);
	
		//Step 4:Click on Organization Link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 5:Click on create organization Look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step 6:Create Organization
		String OrgName="L&T1"+ jUtil.getRandomNumber();
		driver.findElement(By.name("accountname")).sendKeys("L&T5");
		
		//Step 7:Choose 'Chemicals' in industry drop down
		WebElement IndurstyDropDown=driver.findElement(By.name("industry"));
		Select s=new Select(IndurstyDropDown);
		s.selectByValue("Chemicals");
		
		//Step 8:Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(3000);
		//Step 9:Validate
		String OrgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(OrgHeader.contains("L&T5"));
		System.out.println(OrgHeader);

	
	    }

	
	}

