package vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {	//Rule 1:Create a Separate pom class for every web page

	//Rule 2:Identify the web elements by using @findBy,@findBys and @findAll
	@FindBy(name = "user_name")
	private WebElement userNameEdt;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdt;
	
	@FindBy(id = "submitButton")
	private WebElement loginBtn;
	
	//Rule 3:Create a constructor to initialize the web elements
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 4:Provide getters to access to the web elements
	
	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule 5:Business library
	/**
	 * This method will perform login operation
	 * @param USERNAME
	 * @param PASSWORD
	 */
	public void loginToApp(String USERNAME,String PASSWORD)
	{
		userNameEdt.sendKeys(USERNAME);
		passwordEdt.sendKeys(PASSWORD);
		loginBtn.click();
	}
	

	
	
		

}
