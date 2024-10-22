package testPackage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestAuto {
	
	public static WebDriver driver;

	@FindBy(name = "username")
	WebElement textBox_UserName;

	@FindBy(name = "password")
	WebElement textBox_Password;

	@FindBy(xpath = "//button[text() = ' Login ']")
	WebElement button_Login;

	public TestAuto() {
		PageFactory.initElements(driver, TestAuto.class);
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\drivers\\chromedriver_129.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		maximizeAWindow();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//		driver.findElement(By.name("username")).sendKeys("Admin");
//		driver.findElement(By.name("password")).sendKeys("admin123");
//		driver.findElement(By.xpath("//button[text() = ' Login ']")).click();

		TestAuto obj = new TestAuto();
		obj.loginOrangeHRMPage("Admin", "admin123");

	}

	public void sendTextToAnElement(WebElement ele, String text) {
		ele.sendKeys(text);
	}

	public void clickAnElement(WebElement ele) {
		ele.click();
	}

	public void loginOrangeHRMPage(String userName, String password) {
//		textBox_UserName.sendKeys(userName);
		sendTextToAnElement(textBox_UserName, userName);
		sendTextToAnElement(textBox_Password, password);
		clickAnElement(button_Login);

	}

}
