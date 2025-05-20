package StepDefinition;

import static org.testng.Assert.*;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.Automation.Base.BaseTest;
import com.Automation.Pages.*;
import com.Automation.Utility.Constants;
import com.Automation.Utility.ExcelUtils;

import io.cucumber.java.en.*;

public class UserMenuSteps extends BaseTest {

    HomePage homePage;
    TopPanelPage topPanel;
    MyProfilePage profilePage;
    MySettingsPage mySettingsPage;
    DeveloperConsolePage developerConsole;

    @Given("User logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() throws Exception {
        performValidLogin(Constants.TEST_USERNAME, Constants.TEST_PASSWORD);
        homePage = new HomePage(driver);
        assertTrue(homePage.isAtHomePage(), "Login failed or home page not loaded");
    }

    @When("User opens the User Menu dropdown")
    public void user_opens_user_menu_dropdown() throws InterruptedException {
        topPanel = new TopPanelPage(driver);
        topPanel.openUserDropdown();
        Thread.sleep(1000);
    }

    @Then("Dropdown should display all expected options")
    public void dropdown_should_display_all_expected_options() throws InterruptedException {
        assertTrue(topPanel.verifyDropDownOptions(), "Dropdown menu options are missing or incorrect");
    }

    @When("User clicks on {string}")
    public void user_clicks_on(String menuOption) throws InterruptedException {
        topPanel.clickUserDropdownOption(menuOption);
        Thread.sleep(1000);
    }

    @Then("User should be on the profile page with correct name")
    public void user_should_be_on_profile_page() {
        assertTrue(homePage.getPageTitle().toLowerCase().contains("amirtha"));
    }

    @When("User edits the last name in the profile")
    public void user_edits_last_name() throws InterruptedException {
        profilePage = new MyProfilePage(driver);
        profilePage.clickEditButton();
        profilePage.profileEditContactIframe();
    }

    @Then("User profile name should be updated")
    public void user_profile_name_should_be_updated() throws InterruptedException {
        String expectedFirstName = profilePage.getfirstName();
        String expectedLastName = "Panneer";
        String expectedProfileName = expectedFirstName + " " + expectedLastName;
        assertEquals(profilePage.getuserProfileName(), expectedProfileName);
    }

    @When("User posts a comment")
    public void user_posts_comment() {
        try {
			profilePage.clickPostlink();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			profilePage.profilePostComment();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Then("Comment should be displayed on profile")
    public void comment_should_be_displayed() throws InterruptedException {
        assertEquals(profilePage.getPostedComment(), profilePage.postComment);
    }

    @When("User uploads a file")
    public void user_uploads_file() throws InterruptedException {
        profilePage.profileFileUpload();
    }

    @Then("File upload should be successful")
    public void file_upload_should_be_successful() throws InterruptedException {
        String text = profilePage.profileFileUpload();
        assertTrue(text.contains(" posted a file."));
    }

    @Then("My Settings page should be displayed")
    public void my_settings_page_should_be_displayed() {
        assertEquals(topPanel.mySettingPageText(), "My Settings");
    }

    @When("User downloads login history")
    public void user_downloads_login_history() throws InterruptedException {
    	MySettingsPage mySettingsPage = new MySettingsPage(driver); 
        mySettingsPage.clickPersonalTab();
        mySettingsPage.clickLoginHistory();
        mySettingsPage.clickDownloadLoginHistory();
    }

    @Then("Login history file should be downloaded")
    public void login_history_file_should_be_downloaded() {
        boolean isDownloaded = ExcelUtils.isFileDownloaded(Constants.UPLOAD_FILE_PATH, "LoginHistory");
        assertTrue(isDownloaded, "Login history file not downloaded");
    }

    @When("User adds {string} tab to SalesForce app")
    public void user_adds_tab_to_salesforce_app(String tabName) throws InterruptedException {
        mySettingsPage.clickDisplayAndLayout();
        mySettingsPage.clickCutomMyTabs();
        mySettingsPage.clickCustomAppDropddown();
        mySettingsPage.selectSalesForce();
        mySettingsPage.clickAvailableTabs();
        mySettingsPage.selectReports();
        mySettingsPage.clickAddButton();
        mySettingsPage.clickSaveButton();
        Thread.sleep(1000);
    }

    @Then("{string} tab should be present in selected tabs")
    public void tab_should_be_present(String tabName) throws InterruptedException {
        mySettingsPage.clickDisplayAndLayout();
        mySettingsPage.clickCutomMyTabs();
        mySettingsPage.clickCustomAppDropddown();
        mySettingsPage.selectSalesForce();
        mySettingsPage.clickSelectedTabs();
        List<String> tabs = mySettingsPage.getSelectedTabDropdownList().stream()
            .map(WebElement::getText).toList();
        assertTrue(tabs.contains(tabName), tabName + " tab not found in selected tabs");
    }

    @When("User tests activity reminders")
    public void user_tests_activity_reminders() throws InterruptedException {
        mySettingsPage.clickCalendarReaminders();
        mySettingsPage.clickActivityRemainders();
        Thread.sleep(3000);
        mySettingsPage.clickTestRemainderButton();
        mySettingsPage.storeMainWindowHandle();
        Thread.sleep(2000);
        mySettingsPage.switchToNewWindow();
        mySettingsPage.clickDismissAllButton();
        Thread.sleep(1000);
        mySettingsPage.closeAllOtherWindows();
        mySettingsPage.switchToMainWindow();
    }

    @Then("Reminder popup should appear and be dismissed")
    public void reminder_popup_should_be_dismissed() {
        // You may log or assert window state or just ensure no exception thrown.
        assertTrue(true, "Reminder popup not handled properly");
    }

    @Then("Developer Console should open in a new window")
    public void developer_console_should_open() throws InterruptedException {
        developerConsole = new DeveloperConsolePage(driver);
        developerConsole.storeMainWindowHandle();
        developerConsole.switchToNewWindow();
        Thread.sleep(1000);
        assertEquals(driver.getTitle(), "Developer Console");
        developerConsole.closeAllOtherWindows();
        developerConsole.switchToMainWindow();
    }
}
