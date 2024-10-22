package com.Orange.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Orange.base.Generic;
import com.aventstack.extentreports.MediaEntityBuilder;


public class HRM_CommonPage {

	WebDriver driver;
	Generic generic = new Generic();

	public HRM_CommonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}

	@FindBy(xpath = "//div[@class = 'oxd-brand-banner']//img")
	static WebElement image_ClientImage;

	@FindBy(tagName = "h6")
	WebElement text_PageHeading;

	@FindBy(xpath = "//input[@placeholder='Search']")
	static WebElement textBox_Search;

	@FindBy(xpath = "//ul[@class='oxd-main-menu']//a")
	List<WebElement> links_allMenu;

	@FindBy(xpath = "//i[@class='oxd-icon bi-chevron-left']")
	WebElement button_MinimizeMenu;

	@FindBy(className = "oxd-userdropdown-name")
	WebElement text_UserName;

	@FindBy(xpath = "//button[@class='oxd-glass-button orangehrm-upgrade-button']")
	WebElement button_Upgrade;

	@FindBy(xpath = "//ul[@class = 'oxd-dropdown-menu']//a")
	List<WebElement> dropDown_UserMenu;

//	@FindBy(xpath = "")
//	WebElement a;

//	@FindBy(xpath = "")
//	WebElement a;

//	@FindBy(xpath = "")
//	WebElement a;

//	@FindBy(xpath = "")
//	WebElement a;

//	@FindBy(xpath = "")
//	WebElement a;

//	@FindBy(xpath = "")
//	WebElement a;

//	@FindBy(xpath = "")
//	WebElement a;

	public void validateClientImage1() {
		Assert.assertTrue(image_ClientImage.isDisplayed());
		
		Generic.extentLogger.pass("Image Identified");
		
	}

	public void minimizeTheMenuSection() throws Exception {

		generic.clickAnElement(textBox_Search);
		Thread.sleep(1000);
		
		Generic.extentLogger.pass("Clicked SearchBar",MediaEntityBuilder.createScreenCaptureFromPath(Generic.captureScreenshot()).build());
	}
	
	public void searchAPage(String inputText) throws Exception {
		textBox_Search.sendKeys(inputText);
		Thread.sleep(1000);
		
		Generic.extentLogger.pass(inputText+" Text Sent to SearchBar",MediaEntityBuilder.createScreenCaptureFromPath(Generic.captureScreenshot()).build());
	}
	
	public void searchAndGoToPage(String inputText) {
		textBox_Search.sendKeys(inputText);
			links_allMenu.get(0);
	}
	
	
	public void goToTheGivenPage(String pageName) throws Exception {
		for (WebElement eachPage : links_allMenu) {
			if (eachPage.getText().equalsIgnoreCase(pageName)) {
				eachPage.click();
				Thread.sleep(2000);
				Generic.extentLogger= Generic.extentReport.createTest(pageName);
				Generic.extentLogger.pass(pageName+"Page Loaded Sucessfully",
						MediaEntityBuilder.createScreenCaptureFromPath(Generic.captureScreenshot()).build());
				break;
				
			}
		}
		
		Assert.assertEquals(text_PageHeading.getText(), pageName);
	}
	
	public String getUserName() {
		Generic.extentLogger= Generic.extentReport.createTest("Home Page");
		String UserName=text_UserName.getText();
		Generic.extentLogger.pass("User Name Fetched from Website= "+UserName);
		return text_UserName.getText();
	}
	
//	public void goToUserMenuOption(String menuName) {
//		for (WebElement eachPage : dropDown_UserMenu) {
//			if (eachPage.getText().equalsIgnoreCase(menuName)) {
//				eachPage.click();
//			}
//		}
//	}
}
