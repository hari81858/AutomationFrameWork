package vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vitiger.GenericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {
	
	@FindBy(name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement IndustryDropDown;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getIndustryDropDown() {
		return IndustryDropDown;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}
	//Business library
	/**
	 * This method will create organization with mandatory fields
	 * @param ORGNAME
	 */
	
	public void createOrganization(String ORGNAME)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		SaveBtn.click();
	}
	/**
	 * This method will create organization with industry drop down
	 * @param ORGNAME
	 * @param INDUSTY
	 */
	public void createOrganization(String ORGNAME,String INDUSTY)
	{
		OrgNameEdt.sendKeys(ORGNAME);
		handleDropDown(IndustryDropDown, INDUSTY);
		SaveBtn.click();
		
	}

}
