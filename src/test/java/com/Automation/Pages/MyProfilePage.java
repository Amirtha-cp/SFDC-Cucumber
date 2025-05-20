package com.Automation.Pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Automation.Pages.Base.BasePage;
import com.Automation.Utility.Constants;

public class MyProfilePage extends BasePage{

	
	@FindBy(xpath = "//img[@title=\"Edit Profile\"]") WebElement editProfileButton;
	
	@FindBy(id = "contactInfoContentId") WebElement editContactIFrameID;
	
	@FindBy(xpath = "//li/a[contains(text(),\"About\")]") WebElement aboutTab;
	
	@FindBy(xpath = "//li/a[contains(text(),\"Contact\")]") WebElement contactTab;
	
	@FindBy(id = "firstName") WebElement fName;
	
	@FindBy(id = "lastName") WebElement lName;
	
	@FindBy(xpath = "//input[contains(@value,\"Save All\")]") WebElement saveButton;
	
	@FindBy(id = "userNavLabel") WebElement userProfileName;
	
	@FindBy(id = "publisherAttachTextPost") WebElement postLinkButton;
	
	@FindBy(xpath = "//body[@role=\"textbox\"]") WebElement postTextBox;
	
	@FindBy(id = "publishersharebutton") WebElement postShareButton;
	
	@FindBy(xpath = "//p") List<WebElement> postedComments;
	
	@FindBy(id = "publisherAttachContentPost") WebElement fileLinkButton;
	
	@FindBy(id = "chatterUploadFileAction") WebElement uploadFileButton;
	
	@FindBy(id = "chatterFile") WebElement chooseFile;
	
	@FindBy(xpath = "//input[@value=\"Share\"]") WebElement fileShareButton;
	
	@FindBy(xpath = "//div[@class = \"preamblecontainer displayblock\"]/span[text()=\" posted a file.\"]") List <WebElement> filePosted;
	
	int frameid = 0;
	
	public String postComment = "Hybrid Framework UI Automation using Desiogn Pattern POM , Singleton, And Mdoularity implementation";
	

	public MyProfilePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void clickAboutTab() throws InterruptedException {
		clickElement(aboutTab, "About Tab from Iframe window after switch" );
	}
	
	public void clickContactTab() throws InterruptedException {
		clickElement(contactTab, "Contact Tab from Iframe window after switch" );
	}
	
	public void editlastName() throws InterruptedException {
	
		enterText(lName, "C P", "Last Name edited");
	}
	
	public String getfirstName() throws InterruptedException {
		return getAttributevalue(fName, "value", "Get first Name");
		
	}
	
	public String getuserProfileName() throws InterruptedException {
		return getTextFromElement(userProfileName, "User Peofile Name");
		
	}
	
	public void clickEditButton() throws InterruptedException {
		
		clickElement(editProfileButton, "Edit Profile button" );
	}
	
	public void clickProfileSavebutton() throws InterruptedException {
		
		clickElement(saveButton, "Profile save Button");
	}
	public void clickFileLinkButton() throws InterruptedException {
		
		clickElement(fileLinkButton, "File Link to upload");
	}
	
	public void clickUploadButton() throws InterruptedException {
		
		clickElement(uploadFileButton, "File upload button");
	}
	
	public void enterFilePath() throws InterruptedException {
		
		enterText(chooseFile, Constants.UPLOAD_FILE_PATH, "path for file to be uploaded");
	}
	
	public void clickFileUploadShareButton() throws InterruptedException {
		
		clickElement(fileShareButton, "File share button after attaching");
	}
	
	public String profileFileUpload() throws InterruptedException {
		
		 clickFileLinkButton();
		 waitForVisibility(uploadFileButton, 30, "Wait for visibility of upload file button");
		 clickUploadButton();
		 enterFilePath();
		 Thread.sleep(1000);
//		 waitForVisibility(filePosted, 30, "Posted file in recent activity");
		 String text = filePosted.get(1).getText();
		return text;
		 
	}
	public void  profileEditContactIframe() throws InterruptedException {
		
		switchtoFramebyLocator(editContactIFrameID,"Edit Contact Iframe window opened");
//		switchToFramebyLocatorID("contactInfoContentId","Edit Contact Iframe window opened");
		Thread.sleep(2000);
		waitForIframe(editContactIFrameID, 60, "Edit Contact Iframe woindow is opened");
		
		clickContactTab();
//		boolean contactTabvisible = contactTab.isDisplayed();
		waitForVisibility(contactTab, 60, "About Tab from Iframe window after switch");
		clickAboutTab();
//		boolean aboutTabvisible = aboutTab.isDisplayed();
		waitForVisibility(aboutTab, 60, "About Tab from Iframe window after switch");
		editlastName();
		clickProfileSavebutton();
		Thread.sleep(2000);
		switchtoDefault();
		Thread.sleep(1000);
		
	}
	
	public void clickPostlink() throws InterruptedException {
		clickElement(postLinkButton, "Post Comment Link");
	}
	
	public void clickShareButton() throws InterruptedException {
		
		clickElement(postShareButton, "Comment Share Button");
	}

	public void enterComment() throws InterruptedException {
	
		enterText(postTextBox, postComment, "Post comment");
	}
	
	public String getPostedComment() throws InterruptedException {
		
		return getTextFromElement(postedComments.get(1), "Last Posted Comment");
	}
	
	public void profilePostComment() throws InterruptedException {
		
		switchtoFramebyIndex(frameid, "Switching to Iframe by ID ");
		Thread.sleep(2000);
		enterComment();
		
		switchtoDefault();
		Thread.sleep(2000);
		clickShareButton();
		}
	

}
	


