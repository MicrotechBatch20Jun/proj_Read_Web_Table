package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExtentManager;
import utilities.ExcelReader;
import org.apache.log4j.Logger;

public class TestBase {

	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties config;
	public static String projectPath;
	public static ExcelReader excel;
	public static Logger log;
	public static WebDriverWait wait;
	public static ExtentTest test;
	public static ExtentReports rep;
	public static String browser;

	@BeforeSuite
	public void setup() throws IOException {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		log = Logger.getLogger("devpinoyLogger");

		rep = ExtentManager.getInstance();
		
		projectPath = System.getProperty("user.dir");

		excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\data\\testdata.xlsx");

		fis = new FileInputStream(projectPath + "\\src\\test\\resources\\properties\\config.properties");
		config = new Properties();
		config.load(fis);

		if(config.getProperty("HEADLESS").contains("true")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--headless");
			co.addArguments("disable-gpu");
			driver = new ChromeDriver(co);
		}
		else {
			
			driver = new ChromeDriver();		
		}


	}// setup

	public static void Click(By locator) {
		driver.findElement(locator).click();
	}

	public static void SendKeys(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
	}

	public static boolean isDisplayed(By locator) {
		boolean result = driver.findElement(locator).isDisplayed();
		return result;
	}

	public static String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	public static boolean isEnabled(By locator) {
		return driver.findElement(locator).isEnabled();
	}

	public static boolean isSelected(By By) {
		return driver.findElement(By).isSelected();
	}

	public static boolean element_is_Clickable(By By) {
		driver.findElement(By).click();
		return true;
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {

			return false;
		}

	}

	@BeforeClass
	public void get() throws InterruptedException {
		driver.get(config.getProperty("BASE_URL"));
		log.debug(driver.getTitle());
		Thread.sleep(2000);
	}

	@AfterSuite
	public void tearDowm() {
		driver.close();
		log.debug("TEST IS COMPLETED");
	}

}// class