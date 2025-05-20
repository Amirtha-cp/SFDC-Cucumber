package com.Automation.Tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import com.Automation.Base.BaseTest;
import com.Automation.Pages.ForgotPasswordPage;
import com.Automation.Pages.HomePage;
import com.Automation.Pages.LoginPage;
import com.Automation.Utility.Constants;
import com.Automation.Utility.ExcelUtils;

public class LoginTest extends BaseTest {
//	 String expectedPageTitleValidLogin = "Salesforce - Developer Edition";
     String expectedPageInValidLoginMessage = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		long waitTime = 60;
	
	
	@DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData() {
		logger.info(ExcelUtils.getFilteredData(Constants.LOGINCREDENTIALS_EXCEL, "Sheet1", "Y"));
		System.out.println(ExcelUtils.getFilteredData(Constants.LOGINCREDENTIALS_EXCEL, "Sheet1", "Y"));
        return ExcelUtils.getFilteredData(Constants.LOGINCREDENTIALS_EXCEL, "Sheet1", "Y");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return ExcelUtils.getFilteredData(Constants.LOGINCREDENTIALS_EXCEL, "Sheet1", "N");
    }
    
    @Test(dataProvider = "validLoginData")
    public void validLoginTest(String username, String password, String isValid) throws Exception {
    	logger.info("Valid login test using Excel file");
    	LoginPage loginPage = new LoginPage(driver);
    	HomePage homePage = new HomePage(driver);
    	assertTrue(loginPage.isAtLoginPage());
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
    	Thread.sleep(1000);
    	loginPage.rememberMe();
    	loginPage.clickLoginButton();
    	logger.info("Clicked login button");

        assertTrue(homePage.isAtHomePage(), "Valid login test passed!");
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginTest(String username, String password, String isValid) throws Exception {
    	logger.info("Invalid login test using Excel file");
    	LoginPage loginPage = new LoginPage(driver);
    	HomePage homePage = new HomePage(driver);
    	assertTrue(loginPage.isAtLoginPage());
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
    	Thread.sleep(1000);
    	loginPage.rememberMe();
    	loginPage.clickLoginButton();
    	logger.info(loginPage.invalidLoginErrorMessage());
        assertEquals(loginPage.invalidLoginErrorMessage(), expectedPageInValidLoginMessage);
    }
    
    @Test
    
    public void rememberMeTest() throws Exception {
    	logger.info("Testing Remeber Me checkbox");
    	LoginPage loginPage = new LoginPage(driver);
    	HomePage homePage = new HomePage(driver);
    	assertTrue(loginPage.isAtLoginPage());
    	loginPage.enterUsername(Constants.TEST_USERNAME);
    	loginPage.enterPassword(Constants.TEST_PASSWORD);
    	Thread.sleep(1000);
    	loginPage.rememberMe();
    	loginPage.clickLoginButton();
    	logger.info(driver.getTitle());
    	homePage.isAtHomePage();
		assertTrue(homePage.isAtHomePage(), "Valid login test failed!");
	
    }
    

    @Test
    public void forgotPasswordTest() throws Exception {

    	LoginPage loginPage = new LoginPage(driver);
    	ForgotPasswordPage forgotPwd = new ForgotPasswordPage(driver);
    	loginPage.forgotPassword(); 
    	forgotPwd.enterUsername(Constants.TEST_USERNAME);
    	forgotPwd.clickContinue();
 
    	assertEquals(forgotPwd.resendEmailText(),"We’ve sent you an email with a link to finish resetting your password.");  
    	

    }

    @Test
    public void logoutTest() throws Exception {
    	
     	logger.info("Testing Logout");
     	LoginPage loginPage = new LoginPage(driver);
     	assertTrue(loginPage.isAtLoginPage());
     	loginPage.enterUsername(Constants.TEST_USERNAME);
     	loginPage.enterPassword(Constants.TEST_PASSWORD);
    	Thread.sleep(1000);
    	loginPage.rememberMe();
    	loginPage.clickLoginButton();
    	logger.info(driver.getTitle());
    	logger.debug("Logging out");
    	assertTrue(loginPage.isAtLoginPage());
    	
    }
}
