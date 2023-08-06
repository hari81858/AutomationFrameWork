package baseClassPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vitiger.GenericUtilities.BaseClass;
import vitiger.GenericUtilities.ExcelFileUtility;
import vitiger.GenericUtilities.JavaUtility;
import vitiger.GenericUtilities.PropertyFileUtilitiy;
import vitiger.GenericUtilities.WebDriverUtility;
import vtiger.objectRepository.CreateNewOrganizationPage;
import vtiger.objectRepository.HomePage;
import vtiger.objectRepository.LoginPage;
import vtiger.objectRepository.OrganizationInfoPage;
import vtiger.objectRepository.OrganizationPage;

public class CreateMultipleOrgWithIndustry extends BaseClass{
	
	@Test(dataProvider = "getdata")
	public void createMultipleOrg(String Org,String INDUSTRY) throws Throwable
	{
		
		/*Read the data from Excel Sheet */
		
		String ORGNAME=Org+jUtil.getRandomNumber();
			
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
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(ORGNAME);
	  }
					
	@DataProvider()
	public Object[][] getdata() throws Throwable
	{
		return eUtil.readMultipleData("MultipleOrg");
		
	}
	


}
