package com.Automation.Pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Automation.Pages.Base.BasePage;

public class TopPanelPage extends BasePage {

	public TopPanelPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Home page Tab menus
	@FindBy(linkText = "Home")
    private WebElement homeTab;

    @FindBy(linkText = "Accounts")
    private WebElement accountsTab;

    @FindBy(linkText = "Leads")
    private WebElement leadsTab;

    @FindBy(linkText = "Opportunities")
    private WebElement opportunitiesTab;
    
    @FindBy(linkText = "Contacts")
    private WebElement contactsTab;

    @FindBy(linkText = "Campaigns")
    private WebElement campaignsTab;


    
    // ===== User Dropdown =====
    @FindBy(id = "userNav") private WebElement userDropdown;

    @FindBy(xpath = "//div[@id = 'userNav-menuItems']/a") List<WebElement> dropdownOptions;
    
    @FindBy(xpath = "//*[@id=\"PersonalSetup_font\"]/span[@class=\"folderText\"]") WebElement mySettingPage;
    
    
    public void openUserDropdown() throws InterruptedException {
        clickElement(userDropdown, "User Navigation Dropdown");
    }

    public List<String> getUserDropdownoptions() throws InterruptedException 
    {
    	
    	List<String> actualDropdownList = new ArrayList<String>();
    	
    	for (WebElement menuItem: dropdownOptions) 
    	{
    		actualDropdownList.add(getTextFromElement(menuItem, "DropdownOptions"));
    		
    	}
    	
    	return actualDropdownList;
//        return dropdownOptions.stream()
//                .map(WebElement::getText)
//                .collect(Collectors.toList());
    }
    
    public void clickUserDropdownOption(String optionText) throws InterruptedException 
    {
        for (WebElement option : dropdownOptions) 
        {
            if (getTextFromElement(option, optionText).equalsIgnoreCase(optionText)) 
            {
            	clickElement(option, optionText);
                break;
            }
        }
        
    }
    
    public boolean verifyDropDownOptions() throws InterruptedException {
    	List<String>  actualUserMenuItems = getUserDropdownoptions();
    	List<String>  expectedUserMenuItems = Arrays.asList("My Profile","My Settings","Developer Console", "Switch to Lightning Experience", "Logout");
    	
    	return actualUserMenuItems.equals(expectedUserMenuItems);
    }
    public String mySettingPageText() 
    {
    	return getTextFromElement(mySettingPage, "My Setting Page check");
    }
    
    
}
