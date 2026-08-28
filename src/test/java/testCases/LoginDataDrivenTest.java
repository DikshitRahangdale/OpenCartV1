package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import  pageObjects.MyAccountPage;
import testBase.DriverSetup;
import utilities.LoginDataDriven;

public class LoginDataDrivenTest extends DriverSetup {

	@Test(dataProvider = "LoginData", dataProviderClass = LoginDataDriven.class,groups = {"Regression","Smoke"})
	public void loginwithMultipledata(String username, String password, String datastatus) {

		HomePage home = new HomePage(driver);
		home.clickMyAccount();
		home.clickLogin();

		LoginPage loginPag = new LoginPage(driver);
		loginPag.enterUserEmail(username);
		loginPag.enterPassword(password);
		loginPag.clickLoginBtn();

		MyAccountPage myaccount = new MyAccountPage(driver);
		boolean loginstatu = myaccount.vrifyMyAccountHeading();

		if (datastatus.equalsIgnoreCase("Valid")) {
			if (loginstatu == true) {
				Assert.assertTrue(loginstatu);
				System.out.println("Login Successfull: " + "Username: " + username + " Password:" + password
						+ " Creadential status:" + datastatus);
				home.clickMyAccount();
				myaccount.clickLogout();

			} else {
				Assert.assertTrue(false);
			}
		} else if (datastatus.equalsIgnoreCase("Invalid")) {
			if (loginstatu == true) {
				Assert.assertTrue(false);
				System.out.println("Login is Successfull with Invalid Creadential: " + "Username: " + username
						+ " Password:" + password + " Creadential status:" + datastatus);
				home.clickMyAccount();
				myaccount.clickLogout();

			} else {
				Assert.assertTrue(true);
				System.out.println("Login is failed with Invalid Creadential: " + "Username: " + username + " Password:"
						+ password + " Creadential status:" + datastatus);
			}

		}
	}
}
