package vitiger.GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class consists of all the re usable methods related to the web driver actions
 * @author hari
 *
 */
public class WebDriverUtility {
	/**
	 * This will maximize the window
	 * @param driver
	 */
	
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();	
	}
	/**
	 * this method will minimize the window
	 * @param driver
	 */
	
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait for all the findElement and findElements operations to be performed
	 * @param driver
	 */
	
	public void waitForElementsToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait until the specified element is visible in DOM
	 * @param driver
	 */
	public void waitForElementsToBeVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will handle dropDown by using index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * This method will handle dropDown by using value
	 * @param element
	 * @param value
	 */
	public void handleDropDown(WebElement element,String value)
	{
		Select sel=new Select(element);
		sel.selectByValue(value);
	}

	/**
	 * This method will handle dropDown by using visible text
	 * @param value
	 * @param element
	 */
	
	public void handleDropDown(String text,WebElement element)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	
	/**
	 * This method will perform mouseHover action on a target element
	 * @param driver
	 * @param element
	 */
	
	public void mouseHoverActions(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform double click on anywhere on the web page
	 * @param driver
	 */
	
	public void doubleClickActions(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 *This method will perform double click on web element 
	 * @param driver
	 * @param element
	 */
	
	public void doubleClickActions(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	
    /**
     * This method will perform right click on anywhere on the web page
     * @param driver
     */
	
	public void rightClickActions(WebDriver driver)
	{
		Actions act=new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * This method will perform right click on a particular web element
	 * @param driver
	 * @param element
	 */
	
	public void rightClickActions(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will perform drag and drop
	 * @param driver
	 * @param srcElement
	 * @param targetElement
	 */
	
	public void dragAndDropActions(WebDriver driver,WebElement srcElement,WebElement targetElement)
	{

		Actions act=new Actions(driver);
		act.dragAndDrop(srcElement,targetElement).perform();
	}
	
	/**
	 * This method is used to move the cursor to the anywhere on the web page based on the offset value
	 * @param driver
	 * @param xOffset
	 * @param yOffset
	 */
	
	public void moveAcrossWebPage(WebDriver driver,int xOffset,int yOffset)
	{
		Actions act=new Actions(driver);
		act.moveByOffset(xOffset, yOffset).click().perform();
		
	}
	
	/**
	 * This method will scroll vertically for 500 pixels
	 * @param driver
	 */
	
	public void scrollActions(WebDriver driver)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollby(0,500);", " ");
				
	}
	
	/**
	 * This method will scroll vertically until the elements reference
	 * @param driver
	 * @param element
	 */
	
	public void scrollActions(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollby(0,500);", " ");
		int y=element.getLocation().getY();
		js.executeScript("window.scrollby(0,"+y+")",element);
		js.executeScript("argument[0].scrollIntoView();",element);
				
	}
	
	/**
	 * This method will accept the alert pop up
	 * @param driver
	 */
	
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel the configuration pop up
	 * @param driver
	 */
	
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will enter the text in prompt pop up
	 * @param driver
	 * @param text
	 */
	
	public void sendTextToAlert(WebDriver driver,String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
	/**
	 * This method will capture the alert message and return to the caller
	 * @param driver
	 * @return
	 */
	
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
		
	}

	/**
	 * This method will handle frame based on frame index
	 * @param driver
	 * @param index
	 */
	
	public void handelFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will handle frame based on name or ID Attribute
	 * @param driver
	 * @param nameOrID
	 */
	
	public void handelFrame(WebDriver driver,String nameOrID)
	{
		driver.switchTo().frame(nameOrID);
		
	}
	
	/**
	 * This method will handle frame based on web element
	 * @param driver
	 * @param element
	 */
	
	public void handelFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will help to switch the control back to immediate parent
	 * @param driver
	 */
	
	public void swiftToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method will help to switch the control back to current page
	 * @param driver
	 */
	
	public void swiftToDefaultPage(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}

	/**
	 * This method will switch the seleninum control from parent to child or 
	 * child to parent based on partial window title
	 * @param <string>
	 * @param driver
	 * @param partialWinTitel
	 * @param screenShotName 
	 */
	
	public  void switchToWindow(WebDriver driver,String partialWinTitel)
	{
		//Step 1:Capture the all window ID
		
		Set<String>allWindowIDs=driver.getWindowHandles();
		
		//Step 2:iterate through individual IDs
		
		for(String winID:allWindowIDs)
		{
			//Step 3:
			String currentTitle=driver.switchTo().window(winID).getTitle();
			
			//Step 4:
			if(currentTitle.contains(partialWinTitel))
			{
				break;
			}
		}
			
	}
	
	/**
	 * This method will take the screenShot and return the absolute path of it
	 * @param driver
	 * @param screenShotName
	 * @return
	 * @throws Throwable
	 */
	
	public String takeScreenShot(WebDriver driver,String screenShotName) throws Throwable
	{
		//Step 1:It will takes the screenShot
		TakesScreenshot ts=(TakesScreenshot) driver;
		
		//Step 2:It will store in IDE tool
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		//It will provide system path
		File dst=new File(".\\screenShots\\"+screenShotName+".png");
		
		Files.copy(src, dst);//this class from common IO Tool
		return dst.getAbsolutePath();//attach the screen shot to extend reports
			
	}
	
}

