package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.DriverManager;
import testBase.DriverSetup;

public class MyLoginTest extends DriverSetup {

	@Test(groups = "Smoke")
	public void veryLogin() {

		logger.info("Login Test Start");

		HomePage homePage = new HomePage(DriverManager.getDriver(),wait);
		homePage.clickMyAccount();
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(DriverManager.getDriver(),wait);
		loginPage.enterUserEmail(pr.getProperty("userEmail"));
		loginPage.enterPassword(pr.getProperty("userPassword"));
		loginPage.clickLoginBtn();
		logger.info("My Accoutn Page is Opening");
		MyAccountPage accountPage = new MyAccountPage(DriverManager.getDriver(),wait);

		boolean flag = accountPage.vrifyMyAccountHeading();
		Assert.assertTrue(flag, "Login Failed");
	}
}
