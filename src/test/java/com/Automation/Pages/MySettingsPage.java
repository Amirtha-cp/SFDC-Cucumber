package com.Automation.Pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Automation.Pages.Base.BasePage;

public class MySettingsPage extends BasePage{

	public MySettingsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}
	
	@FindBy(id = "PersonalInfo_font") WebElement personalTab;
	@FindBy(id = "LoginHistory_font") WebElement loginHistory;
	@FindBy(xpath = "//a[contains(text(),\"(Excel .csv file)\")]") WebElement downloadLoginLinkBtn;
	
	@FindBy(id = "DisplayAndLayout_font") WebElement displayAndLayout;
	@FindBy(xpath = "//span[@id=\"CustomizeTabs_font\"]") WebElement cutomMyTabs;
	@FindBy(xpath = "//select[@id=\"p4\"]") WebElement cutomAppDropdown;
	@FindBy(id = "duel_select_0") WebElement availableTabs;
	@FindBy(id = "duel_select_0_right") WebElement addArrowbtn;
	@FindBy(xpath = "//input[@name=\"save\"]") WebElement saveBtn;
	@FindBy(id = "duel_select_1") WebElement selectedTabs;
	
	@FindBy(xpath= "//span[text()=\"Calendar & Reminders\"]") WebElement calendarReaminders;
	@FindBy(xpath = "//span[text()=\"Activity Reminders\"]") WebElement activityRemainders;
	@FindBy(id = "testbtn") WebElement testReminderBtn;
	@FindBy(xpath = "//input[@name=\"dismiss_all\"]") WebElement dismissAll;
	
	
	
	public void clickPersonalTab() throws InterruptedException {
		clickElement(personalTab,"Personal Tab");
	}
	
	public void clickLoginHistory() throws InterruptedException {
		clickElement(loginHistory,"Login History");
	}
	
	public void clickDownloadLoginHistory() throws InterruptedException {
		clickElement(downloadLoginLinkBtn, "Download Login History link");
	}

	public void clickDisplayAndLayout() throws InterruptedException {
		clickElement(displayAndLayout,"Display & Layout");
	}
	
	public void clickCutomMyTabs() throws InterruptedException {
		clickElement(cutomMyTabs,"Customize My tabs");
	}
	
	public void clickCustomAppDropddown() throws InterruptedException {
		clickElement(cutomAppDropdown,"Custom App Dropdown");
	}
	
	public void selectSalesForce() throws InterruptedException {
		selectByTextData(cutomAppDropdown, "Salesforce Chatter", "Salesforce Chatter selected");
	}
	
	public void clickAvailableTabs() throws InterruptedException {
		clickElement(availableTabs,"Available tabs");
	}
	
	public void selectReports() throws InterruptedException {
		selectByTextData(availableTabs, "Reports", "Reports from list");
	}
	
	public void clickAddButton() throws InterruptedException {
		clickElement(addArrowbtn,"Added Reports Tab");
	}
	
	public void clickSaveButton() throws InterruptedException {
		
		clickElement(saveBtn,"Added Reports Tab");
	}
	public void clickSelectedTabs() throws InterruptedException {
		clickElement(selectedTabs,"Selected tabs");
	}
	
	public List<WebElement> getSelectedTabDropdownList() {
		return getSelectDropdownList(selectedTabs);
		
	}
	public void selectReportsFromSelectedTab() throws InterruptedException {
		selectByTextData(selectedTabs, "Reports", "Reports tab");
	}
	
	public void clickCalendarReaminders() throws InterruptedException {
		clickElement(calendarReaminders,"Calendar & Reaminders");
	}
	
	public void clickActivityRemainders() throws InterruptedException {
		clickElement(activityRemainders,"Activity Remainders");
	}
	
	public void clickTestRemainderButton() throws InterruptedException {
		clickElement(testReminderBtn,"Open a Test Reminder");
	}
	
	public void clickDismissAllButton() throws InterruptedException {
		clickElement(dismissAll,"Pop up window Dismiss all button");
	}
	
	
}
