package com.Orange.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Orange.utilities.ReadAndWriteProperty;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Generic {

	public static WebDriver driver;

	public static ReadAndWriteProperty prop = new ReadAndWriteProperty();

	public static ExtentReports extentReport;
	public static ExtentTest extentLogger;
	public static ExtentSparkReporter sparkReporter;
	static String reportPath;
	static String extenLoggername;

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Generic.driver = driver;
	}

	@BeforeMethod
	public WebDriver launchApplication1() throws Exception {

		String browserName = prop.readAProperty("browser");
		String applicationUrl = prop.readAProperty("URL");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\drivers\\chromedriver_128.exe");
			driver = new ChromeDriver();
		}

		getDriver().manage().window().maximize();

		getDriver().get(applicationUrl);
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		return getDriver();
	}

	@BeforeSuite
	public void extentReport() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");

		String sDate = sdf.format(date);

		reportPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "TCExecutedOn"
				+ sDate + File.separator;

		sparkReporter = new ExtentSparkReporter(reportPath + "extent.html");
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);

	}

	@AfterSuite
	public void flushReport() {
		extentReport.flush();
	}

//	@AfterMethod
	public void quitBrowser() {
		getDriver().quit();
	}

	public static String captureScreenshot() throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File src = scrShot.getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");

		String sDate = sdf.format(date);

		String screenshotdir = reportPath + "ScreenShots" + File.separator + "imageName" + sDate + ".png";

		File screenshotName = new File(screenshotdir);

		FileUtils.copyFile(src, screenshotName);

		return screenshotdir;
	}

//	WebElement methods

	public void sendTextToAnElement(WebElement ele, String text) {
		ele.sendKeys(text);
	}

	public void clickAnElement(WebElement ele) {
		ele.click();
	}

//	Frame Related methods

	public int noOfiFramesInWebPage() {
		int noOfiFramesdriver = driver.findElements(By.tagName("iframe")).size();
		return noOfiFramesdriver;
	}

	public int noOfFramesInWebPage() {
		int noOfiFramesdriver = driver.findElements(By.tagName("frame")).size();
		return noOfiFramesdriver;
	}

	public int noOfFramesiFramesInWebPage() {

		return noOfiFramesInWebPage() + noOfFramesInWebPage();
	}

	public void switchToFrameByIndex(int frameIndex) {
		driver.switchTo().frame(frameIndex);
	}

	public void switchToFrameByName(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public void switchToFrameByWebElement(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	public void switchToFrameByWebElement(String xpath) {
		WebElement ele = driver.findElement(By.xpath(xpath));
		driver.switchTo().frame(ele);
	}

	public void switchToAFrameByIndexAndClickAnElement(int frameIndex, WebElement ele) {
		switchToFrameByIndex(frameIndex);
		ele.click();
	}

	public void switchToAFrameByIndexAndSendTextToAnElement(int frameIndex, WebElement ele, String text) {
		switchToFrameByIndex(frameIndex);
		ele.sendKeys(text);
	}

	public void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}

	public void switchToMainWebPage() {
		driver.switchTo().defaultContent();
	}

	public void switchToActiveElement() {
		driver.switchTo().activeElement();
	}

	public void openNewWindowAndSwitch() {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}

	public void switchToWindow(String windowHandle) {
		driver.switchTo().window(windowHandle);
	}

//	Actions class methods

	public void scrollToAnElementUsingActions(WebElement element) {
		Actions actions = new Actions(driver);
		actions.scrollToElement(element).build().perform();
	}

	public void rightClickAnElement(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.contextClick(ele).build().perform();
	}

	public void moveMouseOnAnElement(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).perform();
		;
	}

	public void rightClickOnMouse() {
		Actions actions = new Actions(driver);
		actions.contextClick().build().perform();
	}

	public void clickAnElementUsingActions(WebElement ele) {
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).build().perform();
	}

	public void pressAKeyUsingActionsClass(String keyName) {
		Actions actions = new Actions(driver);
		switch (keyName) {
		case "a":
//			actions.keyDown(Keys.A).keyUp(Keys.a);
		}
	}

	public void typeInCaptialUsingActions(String text) {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.SHIFT).sendKeys(text).keyUp(Keys.SHIFT).build().perform();
	}

//	wait methods 

	public void pauseExecution(Integer seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForElementToBeClickable(WebElement ele, int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {
			throw new NoSuchElementException(ele + "is not clickable");
		}
	}
	
//	Alert methods 
	
	
	
	public void acceptAlert() {
		Alert alert = getDriver().switchTo().alert();
		alert.accept();
	}
	
	public void dismissAlert() {
		Alert alert = getDriver().switchTo().alert();
		alert.dismiss();
	}
	
	public void getTextFromAlert() {
		Alert alert = getDriver().switchTo().alert();
		alert.getText();
	}
	
	public void sendTextToAnAlert(String text) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(text);
	}
	
	//Validate Image
	

}
