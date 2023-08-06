package vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vitiger.GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement OrganizationLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement ContactsLink;
	
	@FindBy(linkText = "Opportunities")
	private WebElement OpportunityLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdministrationImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement SignOutLink;

	public WebElement getOrganizationLink() {
		return OrganizationLink;
	}

	
	
	public WebElement getContactsLink() {
		return ContactsLink;
	}

	public WebElement getOpportunityLink() {
		return OpportunityLink;
	}

	public WebElement getAdministrationImg() {
		return AdministrationImg;
	}

	public WebElement getSignOutLink() {
		return SignOutLink;
	}
	
	//Business library
	/**
	 * This method will click on Organization link
	 */
	public void clickOnOrgLink()
	{
		OrganizationLink.click();
	}
	
	/**
	 * This method will click on Contacts link
	 */
	public void clickOnContactLink()
	{
		ContactsLink.click();
	}
	
	/**
	 * This method will click on Opportunity link
	 */
	public void clickOnOpportunityLink()
	{
		OpportunityLink.click();
	}
	
	/**
	 * This method will LogOut Of the application
	 * @param driver
	 */
	public void logOutOfApplication(WebDriver driver)
	{
		mouseHoverActions(driver, AdministrationImg);
		SignOutLink.click();
	}
	

}
