package com.Orange.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Orange.base.Generic;
import com.aventstack.extentreports.MediaEntityBuilder;

public class HRM_HomePage {

	WebDriver driver;
	Generic generic = new Generic();

	public HRM_HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='oxd-main-menu-item']//span[text()='Admin']")
	WebElement adminPageLink;
//	
//	@FindBy(xpath = "")
//	WebElement a;
//	
//	@FindBy(xpath = "")
//	WebElement a;
//	
//	@FindBy(xpath = "")
//	WebElement a;
//	
//	@FindBy(xpath = "")
//	WebElement a;
//	
//	@FindBy(xpath = "")s
//	WebElement a;
	@Test
	public void validatelink() throws Exception
	{
		System.out.println("method Called");
		Generic.extentLogger= Generic.extentReport.createTest("Home Page");
		Assert.assertTrue(adminPageLink.isDisplayed());
		Generic.extentLogger.pass("HomePage",
				MediaEntityBuilder.createScreenCaptureFromPath(Generic.captureScreenshot()).build());
		
	}

}
