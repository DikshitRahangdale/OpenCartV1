package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class DriverSetup {
	public static WebDriver driver;
	public Logger logger;
	public Properties pr;
	public WebDriverWait wait;
	public MutableCapabilities options; // MutableCapabilities is a parent class for browser-specific option classes

	@BeforeClass(groups = { "Smoke", "Sanity", "Regression" })
	@Parameters({ "browsers","os"})
	public void driversetup(String browsers, String os) throws IOException {

		String huburl = "http://localhost:4444";

		FileReader fileReader = new FileReader("./src//test//resources//config.properties");
		pr = new Properties();
		pr.load(fileReader);

		// For Selenium Grid
		if (pr.getProperty("execution_env").equalsIgnoreCase("remote"))
            {
		
			// check Browsers
			switch (browsers) {
			case "Chrome":
				options = new ChromeOptions();
				break;

			case "Edge":
				options = new EdgeOptions();
				break;
			case "FirFox":
			     options=new FirefoxOptions();
			     break;
			default:
				System.out.println("Browser not Mach");
				return;
			}

			// Chekc OS
			if (os.equalsIgnoreCase("Windows")) {
			
                 options.setCapability("platformName", "Windows");
			} else if (os.equalsIgnoreCase("Mac")) {
				options.setCapability("platformName", "mac");
			} else if (os.equalsIgnoreCase("linux")) {
				options.setCapability("platformName", "Linux");
			} else {
				System.out.println("Operating System name is Incorrect");
				return;
			}

			driver = new RemoteWebDriver(URI.create(huburl).toURL(), options);

		}

		// For Local Execuation

		else if (pr.getProperty("execution_env").equalsIgnoreCase("local"))
         
		{
			switch (browsers) {
			case "Chrome":
				driver = new ChromeDriver();
				break;

			case "Edge":
				driver = new EdgeDriver();
				break;

			case "FireFox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Browser Parameter Does Not Match");
				return;
			}
		}
		else {
			System.out.println("Please Select Correct Environments");
			return;
		}

		logger = LogManager.getLogger(this.getClass()); // use for logging

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(pr.getProperty("appUrl")); // reading URL from properties file
	}

	@AfterClass(groups = { "Smoke", "Sanity", "Regression" })
	public void closeDriver() {
		driver.quit();
	}

	public static String takesScreenshot(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\testscreenshot_" + timeStamp + "_"
				+ tname + ".jpg";
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		File srcFile = new File(screenshotPath);
		FileUtils.copyFile(file, srcFile);

		return screenshotPath;

	}
}
