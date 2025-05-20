package com.Automation.Base;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import com.Automation.Pages.HomePage;
import com.Automation.Pages.LoginPage;
import com.Automation.Pages.Base.BasePage;
import com.Automation.Utility.Constants;
import com.Automation.Utility.ExtentReportUtils;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	protected static WebDriver driver;
	protected static Logger logger = LogManager.getLogger(BaseTest.class);
	protected static ExtentReportUtils extentReportUtilsObj = ExtentReportUtils.getInstance();
	String url = Constants.TEST_URL;
	
   

	public static WebDriver launchBrowser(String browserName)
	{
		switch(browserName.toLowerCase())
		{
		case("chrome"):
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		case("firefox"):
	
		    System.setProperty("webdriver.gecko.driver", "/Users/mura/Downloads/geckodriver");
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
			driver = new FirefoxDriver(options);
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			break;
			
		case("safari"):
			WebDriverManager.safaridriver().setup();
		    driver = new SafariDriver();
		    break;
		    
		case("edge"):
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		
		default:
			break;
			
		}
		return driver;
	}
	
	public static void goToUrl(String url){
		driver.get(url);
		driver.manage().window().maximize();

	}
	
	public static void closeDriver(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();

	}

//	@BeforeMethod
//	@Parameters("browserName")
//	public void setUpBeforeMethod(@Optional("chrome") String browserName) throws InterruptedException
//	{
//		launchBrowser(browserName);
//		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);		// dynamic wait
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		goToUrl(url);
//		Thread.sleep(1000);
//		
//	}
	
//	@BeforeSuite
//	public static void setup() {
//		extentReportUtilsObj = ExtentReportUtils.getInstance();
//		logger.debug("BaseTest : setup : initializing report manager..");
//
//	}

//	@AfterSuite
//	public static void tearDown() {
//		extentReportUtilsObj.endExtentReport();
//		
//		logger.debug("BaseTest : tearDown : flushing the report");
//	}

		
	
	public void performValidLogin(String username, String password) throws Exception {
	    LoginPage loginPage = new LoginPage(driver);
	    HomePage homePage = new HomePage(driver);
	    loginPage.enterUsername(username);
	    loginPage.enterPassword(password);
	    loginPage.rememberMe();
	    loginPage.clickLoginButton();

//	    HomePage homePage = new HomePage(driver);
	    assertTrue(homePage.isAtHomePage(), "Login failed or not on Landed on Home Page");

	}
	
	@AfterMethod
	public void tearDownAfterMethod() throws Exception
	{
		
		closeDriver();
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


