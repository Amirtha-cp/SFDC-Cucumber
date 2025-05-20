package com.Automation.Pages.Base;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.Automation.Base.BaseTest;
import com.Automation.Utility.ConfigProperties;
import com.Automation.Utility.Constants;
import com.Automation.Utility.ExtentReportUtils;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage extends BaseTest{
	
//	To specify the class that the logger is associated with. 
//	This is commonly done to allow the logger to record log messages that are related to the class in which the logger is defined, 
//	helping to easily trace where logs are coming from in a large application.
//	the below logger belongs to the this project of Base class and thus it can be extended to sub classes.
	protected static Logger logger = LogManager.getLogger(BasePage.class);
	protected static ExtentReportUtils extentReportUtilsObj = ExtentReportUtils.getInstance();
	private WebDriver driver ;
	protected static final String TEST_USERNAME = "amirtha.c.panneer126@agentforce.com";
	protected static final String TEST_PASSWORD = "amirthajava123"; 

	private WebDriverWait wait ; 
	private String mainWindowHandle;
//	Constructor for BasePage to initilaize the PageFactory
	public BasePage (WebDriver driver){
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
			
		}
	}
	
	public void storeMainWindowHandle() {
        mainWindowHandle = driver.getWindowHandle();
    }
	
	 public void switchToNewWindow() {
	        Set<String> handles = driver.getWindowHandles();
	        for (String handle : handles) {
	            if (!handle.equals(mainWindowHandle)) {
	                driver.switchTo().window(handle);
	                break;
	            }
	        }
	    }
	 
	 public void switchToMainWindow() {
	        driver.switchTo().window(mainWindowHandle);
	    }
	   public void closeAllOtherWindows() {
	        Set<String> handles = driver.getWindowHandles();
	        for (String handle : handles) {
	            if (!handle.equals(mainWindowHandle)) {
	                driver.switchTo().window(handle);
	                driver.close();
	            }
	        }
	        driver.switchTo().window(mainWindowHandle);
	    }
	public void pageRefresh()  {
		
		driver.navigate().refresh();
		
	}
	
	public String getPageTitle() {
		
	 return driver.getTitle();
	}
	
	public String getCurrentURL(WebElement ele, String objName)
	{
			
		return (driver.getCurrentUrl());
		
	}
	//To enter text in IputBox
	public void enterText(WebElement ele,String data,String objName) throws InterruptedException {
		if(ele.isDisplayed())
		{
			ele.clear();
			ele.sendKeys(data);
			Thread.sleep(2000);
			System.out.println("data is entered to "+objName);
		}
		else
		{
			System.out.println(objName+" textbox is not displayed");
		}
	}
	
	//To get text from element
	public String getTextFromElement(WebElement ele, String objName) {
		String data=null;
		if(ele.isDisplayed()) {
			data = ele.getText();
		}
		else {
			logger.info(objName+" not dispalyed");
		}
		return data;
	}
	
	public String getAttributevalue(WebElement ele, String attribute ,String objName)
	{
		String value = null;
		if(ele.isDisplayed())
			value = ele.getAttribute(attribute);
		else
			logger.info(objName+" not dispalyed");
		
		return value;
	}
	//TO click WebElement
	public void clickElement(WebElement ele,String objName) throws InterruptedException {
		if(ele.isEnabled())
		{
			ele.click();
			Thread.sleep(2000);
			System.out.println(objName+" button is clicked");
		}
		else {
			System.out.println(objName+" button is not displayed");
		}
	}

	
	//To select checkbox
	public void selectCheckBox(WebElement ele,String objectName) {
		if(!ele.isSelected()) {
			ele.click();

		}
		else{
			System.out.println(objectName+" button is already selected");
		}
	}
	public void waitUntilAlertPresent(int sec) {

	}
	
	//Select from dropdown by Value
	public void selectByValueData(WebElement ele, String value) {

		Select select = new Select(ele);
		select.selectByValue(value);
	}

	//Select from dropdown by Visible Text
	public void selectByTextData(WebElement ele, String value, String objName) {

		Select select = new Select(ele);
		select.selectByVisibleText(value);;
	}
	
	//Select from dropdown by IndexVlue
	public void selectByIndexData(WebElement ele, int value) {

		Select select = new Select(ele);
		select.selectByIndex(value);
	}
	
	public List<WebElement> getSelectDropdownList(WebElement ele)
	{
		Select select = new Select(ele);
//		select.getOptions();
	    return select.getOptions();
	}
	
//----------------------------------------IFrame------------------------------------------------------
	public void switchtoFramebyIndex(int index,  String objName)
	{
		driver.switchTo().frame(index);
		System.out.println(objName+" IFrame by ID is clicked");
		
	}
	
	public void switchtoDefault()
	{
		driver.switchTo().defaultContent();
		System.out.println("  Deafaultyframe is clicked");
	}
	public void switchToFramebyLocatorID(String locatorID, String objName)
	{
		driver.switchTo().frame(locatorID);
	}
	public void switchtoFramebyLocator(WebElement Iframe, String objName)
	{
		driver.switchTo().frame(Iframe);
		System.out.println(objName+" IFrame by Locator is clicked");
	}

//	---------------------------------------ALERT------------------------------------------------------
	public Alert switchToAlert() 
	{
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }
	
	
	public String getAlertText(Alert alert, String objectName) {
        String text = alert.getText();
        System.out.println(objectName + " Alert text: " + text);
        return text;
    }
	
	public void alertAccept(Alert alert)
	{
		alert.accept();
	}
	public void alertCancel(Alert alert)
	{
		alert.dismiss();
	}
	
	
	public void hoverOverElement(WebElement element) {
		Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        System.out.println("Hovered over: " + element);
    }

    // Click using Actions
    public void mouseClickElement(WebElement element) {
    	Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        System.out.println("Clicked using Actions: " + element);
    }

    // Right-click (context click)
    public void rightClickElement(WebElement element) {
    	Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        System.out.println("Right-clicked on: " + element);
    }

    // Double-click
    public void doubleClickElement(WebElement element) {
    	Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        System.out.println("Double-clicked on: " + element);
    }

    // Drag and drop
    public void dragAndDrop(WebElement source, WebElement target) {
    	Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        System.out.println("Dragged and dropped from source to target");
    }

    // Click and hold
    public void clickAndHold(WebElement element) {
    	Actions actions = new Actions(driver);
        actions.clickAndHold(element).perform();
        System.out.println("Clicked and held on: " + element);
    }
    
    //Wiat for visibilty using  Explicit wait time
    public void waitForVisibility(WebElement ele,long timeInSec, String ObjectName) {
		System.out.println(ObjectName+ "waiting for visibility for maximum of "+timeInSec+ " sec");
		wait=new WebDriverWait(driver,timeInSec);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
    
    public void waitForIframe(WebElement ele, long timeInSec, String ObjectName)  {
    	wait = new WebDriverWait(driver, timeInSec);
    	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
    }
    //Take screen shot
    public void takeScreenshot(String filepath) {
    
		TakesScreenshot screenCapture=(TakesScreenshot)driver;
		File srcFile= screenCapture.getScreenshotAs(OutputType.FILE);
		File destFile=new File(filepath);
		try {
			
			Files.copy(srcFile, destFile);
			logger.info("Screen captured");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Screencapture failed");
		}
	}

}



