
package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.DriverSetup;

public class ExtentReportss implements ITestListener {

	private static ExtentReports extent;
	private static ExtentSparkReporter spark;

	// One ExtentTest for each thread
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	private static boolean reportInitialized = false;

	@Override
	public void onStart(ITestContext context) {

		initializeReport();
	}

	private static synchronized void initializeReport() {

		if (reportInitialized) {
			return;
		}

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String reportPath = System.getProperty("user.dir") + "\\reports\\ExtentReport_" + timeStamp + ".html";

		spark = new ExtentSparkReporter(reportPath);

		spark.config().setDocumentTitle("Automation Hybrid Framework");
		spark.config().setReportName("Automation Testing");
		spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter(spark);

		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Device", "Lenovo Laptop");
		extent.setSystemInfo("Server", "Release");
		extent.setSystemInfo("QA Name", "Dikshit R");
		extent.setSystemInfo("Execution", "Cross Browser Parallel");

		reportInitialized = true;
	}

	@Override
	public void onTestStart(ITestResult result) {

		String browser = getBrowserName(result);

		String testName = result.getMethod().getMethodName() + " [" + browser + "]";

		ExtentTest test = extent.createTest(testName);

		test.assignCategory(result.getMethod().getGroups());
		test.assignAuthor("Dikshit R");
		test.assignDevice(browser);

		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		ExtentTest test = extentTest.get();

		if (test != null) {

			test.log(Status.PASS, "Test Case Pass -> " + result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {

		ExtentTest test = extentTest.get();

		if (test == null) {
			return;
		}

		test.log(Status.FAIL, "Test Case Fail -> " + result.getMethod().getMethodName());

		if (result.getThrowable() != null) {

			test.log(Status.FAIL, result.getThrowable().getMessage());
		}

		try {

			String screenshot = DriverSetup.takesScreenshot(result.getMethod().getMethodName());

			test.addScreenCaptureFromPath(screenshot);

		} catch (Exception e) {

			test.log(Status.WARNING, "Screenshot could not be captured: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentTest test = extentTest.get();

		if (test == null) {
			return;
		}

		test.log(Status.SKIP, "Test Case Skipped -> " + result.getMethod().getMethodName());

		if (result.getThrowable() != null) {

			test.log(Status.INFO, result.getThrowable().getMessage());
		}
	}

	@Override
	public void onFinish(ITestContext context) {

		if (extent != null) {
			extent.flush();
		}

		extentTest.remove();
	}

	private String getBrowserName(ITestResult result) {

		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browsers");

		if (browser == null || browser.trim().isEmpty()) {

			return "Unknown Browser";
		}

		return browser;
	}

}
