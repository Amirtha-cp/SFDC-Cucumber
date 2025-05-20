package com.Automation.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.Automation.Pages.Base.BasePage;

public class HomePage extends BasePage {
	
	String expectedPageTitleValidLogin = "Salesforce - Developer Edition";
//	public TopPanelPage topPanel;

    public HomePage(WebDriver driver) {
        super(driver);
//        topPanel = new TopPanelPage(driver);
    }

    public boolean isAtHomePage() {
        return getPageTitle().contains(expectedPageTitleValidLogin);
    }
}