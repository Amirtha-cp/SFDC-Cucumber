package StepDefinition;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.Automation.Base.BaseTest;
import com.Automation.Pages.HomePage;
import com.Automation.Pages.LoginPage;
import com.Automation.Pages.Base.BasePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginTest extends BaseTest {


	//	logger.info("Valid login test using Excel file");
	LoginPage loginPage = new LoginPage(driver);
	HomePage homePage = new HomePage(driver);
	
	@Given("User is on SFDC login page")
	public void user_is_on_sfdc_login_page() {
	    // Write code here that turns the phrase above into concrete actions
		assertTrue(loginPage.isAtLoginPage());
	}

	@When("User logs in with {string} and {string}")
	public void user_logs_in_with_and(String username, String password) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		 System.out.println("Username: " + username);
		 System.out.println("Password: " + password);
	  	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
	}

	@Then("User should land on home page with title")
	public void user_should_land_on_home_page_with_title() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
    	loginPage.clickLoginButton();
//    	logger.info("Clicked login button");

        assertTrue(homePage.isAtHomePage(), "Valid login test passed!");
    }
	
	
}
