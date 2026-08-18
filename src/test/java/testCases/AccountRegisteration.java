package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.DriverSetup;

public class AccountRegisteration extends DriverSetup {
	@Test(groups = { "Sanity", "Smoke" })
	public void registeration() {

		logger.info("*****Starting Registration Process*******");

		HomePage home = new HomePage(driver);

		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = driver.getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString, "Registration Page URL does not Match");

		RegistrationPage register = new RegistrationPage(driver);
		register.enterFirstName("Test");
		register.enterLastName("Accsount");
		register.enterEmail("estisfsdffdkjjdsng@gmail.com");
		register.enterTephoneNumber("87889582778");
		register.enterPassword("Tedsss@2345");
		register.enterCnfrmPassword("Tedsss@2345");
		register.newsLetterYes();
		register.clickPrivacyPolicycheckbox();
		register.clickContnueBtn();

		String registerConfrmMsg = register.rgstrSuccessMsg();

		Assert.assertEquals(registerConfrmMsg, "Your Account Has Been Created!",
				"Account is not Created or Success Message does not match");
	}
}
