package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.DriverSetup;

public class MyLoginTest extends DriverSetup {

	@Test(groups="Smoke")
	public void veryLogin() {

		logger.info("Login Test Start");

		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserEmail(pr.getProperty("userEmail"));
		loginPage.enterPassword(pr.getProperty("userPassword"));
		loginPage.clickLoginBtn();

		MyAccountPage accountPage = new MyAccountPage(driver);

		boolean flag = accountPage.vrifyMyAccountHeading();
		Assert.assertTrue(flag, "Login Failed");
	}
}
