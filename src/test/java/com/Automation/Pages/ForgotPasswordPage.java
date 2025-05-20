package com.Automation.Pages;

import java.util.List;

import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.Automation.Pages.Base.BasePage;

public class ForgotPasswordPage extends BasePage{

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id="un") WebElement userName;
	@FindBy(id="continue") WebElement continueButton;
	@FindBy(tagName = "p") List <WebElement> textList;
//	public List<WebElement> navDropdownItems;
	
	
	public String resendEmailText()
	{
		return textList.get(0).getText();
	}
	
	public void enterUsername(String data) throws InterruptedException
	{
		enterText(userName, data, "Username field");
	}
	
	public void clickContinue() throws InterruptedException
	{
		clickElement(continueButton, "ContinueButton");
	}
	
}
