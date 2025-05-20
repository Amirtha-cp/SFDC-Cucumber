package com.Automation.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.Automation.Base.BaseTest;
import com.Automation.Pages.DeveloperConsolePage;
import com.Automation.Pages.HomePage;
import com.Automation.Pages.MyProfilePage;
import com.Automation.Pages.MySettingsPage;
import com.Automation.Pages.TopPanelPage;
import com.Automation.Utility.Constants;
import com.Automation.Utility.ExcelUtils;

public class UserMenuDropDownTest extends BaseTest {
	
	

	@Test
	public void userMenuTest() throws Exception {
	
	performValidLogin(Constants.TEST_USERNAME, Constants.TEST_PASSWORD);
	HomePage homePage = new HomePage(driver);
	TopPanelPage topPanel = new TopPanelPage(driver);
	assertTrue(homePage.isAtHomePage(),"Invalid login or Home page not loaded");
	topPanel.openUserDropdown();
	Thread.sleep(1000);
	assertTrue(topPanel.verifyDropDownOptions(),"Drop Down menu list failed in navigation");

	
	}
	
	@Test
	public void myProfileTest() throws Exception {
		performValidLogin(Constants.TEST_USERNAME, Constants.TEST_PASSWORD);
		String expectedLastName = "Panneer";
		HomePage homePage = new HomePage(driver);
		TopPanelPage topPanel = new TopPanelPage(driver);
		MyProfilePage profilePage = new MyProfilePage(driver);
		topPanel.openUserDropdown();
		topPanel.clickUserDropdownOption("My Profile");
		assertTrue(homePage.getPageTitle().toLowerCase().contains("amirtha"));
//		TC Edit LastName in Profile Pop-up Page Below testcase not passing in this framework 
		profilePage.clickEditButton();
		profilePage.profileEditContactIframe();
    	String expectedFirstName = profilePage.getfirstName();
		String expectedProfileName = expectedFirstName +" "+expectedLastName;
		assertEquals(profilePage.getuserProfileName(), expectedProfileName);
//		TC Post Comment and share the comment.
		profilePage.clickPostlink();
		profilePage.profilePostComment();
		profilePage.getPostedComment();
		assertEquals(profilePage.getPostedComment(), profilePage.postComment);
//		TC Upload file in My profile page
		String postedTextContains = profilePage.profileFileUpload();
		assert(postedTextContains.contains(" posted a file."));
		
		}
	
	@Test
	public void mySettingsTest() throws Exception 
	{
		performValidLogin(Constants.TEST_USERNAME, Constants.TEST_PASSWORD);
		HomePage homePage = new HomePage(driver);
		TopPanelPage topPanel = new TopPanelPage(driver);
		topPanel.openUserDropdown();
		topPanel.clickUserDropdownOption("My Settings");
		assertEquals(topPanel.mySettingPageText(), "My Settings");
		MySettingsPage mySettingPage = new MySettingsPage(driver);
		mySettingPage.clickPersonalTab() ;
		
		mySettingPage.clickLoginHistory() ;
		
		mySettingPage.clickDownloadLoginHistory() ;
	
		boolean downloadSuccess = ExcelUtils.isFileDownloaded(Constants.UPLOAD_FILE_PATH, "LoginHistory");
//		extentReportUtilsObj.logTestFailed("file download failed");
		assertTrue(downloadSuccess);
		
		mySettingPage.clickDisplayAndLayout();
		mySettingPage.clickCutomMyTabs();
		mySettingPage.clickCustomAppDropddown();
		mySettingPage.selectSalesForce();
		mySettingPage.clickAvailableTabs();
		mySettingPage.selectReports();
		mySettingPage.clickAddButton();
		mySettingPage.clickSaveButton();
		Thread.sleep(1000);
		mySettingPage.clickDisplayAndLayout();
		mySettingPage.clickCutomMyTabs();
		mySettingPage.clickCustomAppDropddown();
		mySettingPage.selectSalesForce();
		mySettingPage.clickSelectedTabs() ;
		List<String> optionTexts = mySettingPage.getSelectedTabDropdownList().stream()
                .map(WebElement::getText)
                .toList(); 
		String expectedOption = "Reports";
		Assert.assertTrue(optionTexts.contains(expectedOption), 
		    "Dropdown does not contain expected option: " + expectedOption);
		mySettingPage.selectReportsFromSelectedTab() ;
		
		mySettingPage.clickCalendarReaminders();
		
		mySettingPage.clickActivityRemainders();
		Thread.sleep(5000);
		mySettingPage.clickTestRemainderButton() ;
		mySettingPage.storeMainWindowHandle();
		Thread.sleep(5000);
		mySettingPage.switchToNewWindow();
		
		Thread.sleep(1000);
		mySettingPage.clickDismissAllButton();
		Thread.sleep(1000);
		mySettingPage.closeAllOtherWindows();
		mySettingPage.switchToMainWindow();
		Thread.sleep(1000);
	}
	
	@Test
	public void developerConsoleTest() throws Exception 
	{	
		String expectedTitle = "Developer Console";
		performValidLogin(Constants.TEST_USERNAME, Constants.TEST_PASSWORD);
		HomePage homePage = new HomePage(driver);
		TopPanelPage topPanel = new TopPanelPage(driver);
		topPanel.openUserDropdown();
		topPanel.clickUserDropdownOption("Developer Console");
		DeveloperConsolePage developConsole = new DeveloperConsolePage(driver);
		developConsole.storeMainWindowHandle();
		developConsole.switchToNewWindow();
		Thread.sleep(1000);
		assertEquals(driver.getTitle(), expectedTitle);
		Thread.sleep(1000);
		developConsole.closeAllOtherWindows();
		developConsole.switchToMainWindow();
	}
	

}
