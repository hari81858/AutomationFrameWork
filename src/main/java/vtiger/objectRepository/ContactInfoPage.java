package vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement ContactHeaderText;
	
	public ContactInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getContactHeaderText() {
		return ContactHeaderText;
	}

	//Business Library
	
	/**
	 * This method will captures the header text return it to the caller
	 * @return
	 */
	public String getContactHeader()
	{
		return ContactHeaderText.getText();
	}

}
