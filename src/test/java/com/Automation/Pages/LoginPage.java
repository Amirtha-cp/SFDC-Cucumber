package com.Automation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.Automation.Pages.Base.BasePage;

public class LoginPage extends BasePage {

		@FindBy(id="username") WebElement userName;
		@FindBy(id="password") WebElement password;
		@FindBy(id="rememberUn") WebElement rememberMe;
		@FindBy(linkText="Forgot Your Password?") WebElement forgotPassword;
		@FindBy(name="Login") WebElement loginButton;
		@FindBy(id="error") WebElement invalidLoginError;
		 
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
		// TODO Auto-generated constructor stub
	}
	
	public void enterUsername(String data) throws InterruptedException
	{
		enterText(userName, data, "Username field");
	}
	
	public void enterPassword(String data) throws InterruptedException
	{
		enterText(password, data, "password field");
	}
	
	public void rememberMe() throws InterruptedException
	{
		clickElement(rememberMe, "RemeberMe Checkbox");
	}
	public void clickLoginButton() throws InterruptedException
	{
		clickElement(loginButton, "Login Button");
	}
	
	public void forgotPassword() throws InterruptedException
	{
		clickElement(forgotPassword, "Login Button");
	}
	
	public String invalidLoginErrorMessage() throws InterruptedException 
	{
		String errorMessage = getTextFromElement(invalidLoginError, "Invald login error");
		return errorMessage;
	}
	
	 public boolean isAtLoginPage() {
	        return getPageTitle().contains("Login | Salesforce");
	    }
}
