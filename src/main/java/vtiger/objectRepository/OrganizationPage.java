package vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	//Declaration
		@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
		private WebElement CreateOrgLookUpImg;
		
		//Initialization
		public OrganizationPage(WebDriver driver)
		{
		  PageFactory.initElements(driver, this);	
		}

		//Utilization
		public WebElement getCreateOrgLookUpImg() {
			return CreateOrgLookUpImg;
		}
		
		//Business Library
		/**
		 * This method will click on create organization look up image
		 */
		
		public void clickOnCreateOrgLookUpImg()
		{
		  CreateOrgLookUpImg.click();
			
		}
			
					
}
