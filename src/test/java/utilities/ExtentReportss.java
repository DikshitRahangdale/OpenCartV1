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
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;

	@Override
	public void onStart(ITestContext context) {
		String browser = context.getCurrentXmlTest().getParameter("browsers");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String reportPath = System.getProperty("user.dir") + "\\reports\\ExtentReport_" + timeStamp + ".html";
		spark = new ExtentSparkReporter(reportPath);
		spark.config().setDocumentTitle("Automation Hybrid Framework");
		spark.config().setReportName("Automation Testing");
		spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(spark);

		extent.setSystemInfo("OS", "Window");
		extent.setSystemInfo("Browser", browser);
		extent.setSystemInfo("Device", "Lenovo Laptop");
		extent.setSystemInfo("Server", "Release");
		extent.setSystemInfo("QA Name", "Dikshit R");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups()); // Group name
		test.log(Status.PASS, " Test Case Pass->" + result.getName());
		test.assignAuthor("Dikshit R");
		test.assignDevice("Lenovo Laptop", "Chrome Browser");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups()); // group name
		test.log(Status.FAIL, "Test Case Fail->" + result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String screenshot = DriverSetup.takesScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshot);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		test.assignAuthor("Dikshit R");
		test.assignDevice("Lenovo Laptop", "Chrome Browser");

	}

	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case Skipped->" + result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
		test.assignAuthor("Dikshit R");
		test.assignDevice("Lenovo Laptop", "Chrome Browser");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
