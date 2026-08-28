package testCases;



import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

import pageObjects.RegistrationPage;
import testBase.DriverSetup;

public class AccountRegisteration extends DriverSetup {
	@Test(groups = { "Sanity", "Smoke" })
	public void registeration() throws InterruptedException {
		

	        

		logger.info("*****Starting Registration Process*******");

		HomePage home = new HomePage(driver);

		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = driver.getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString, "Registration Page URL does not Match");

		RegistrationPage register = new RegistrationPage(driver);
		register.enterFirstName("Tst");
		register.enterLastName("QAs");
		register.enterEmail("autordduyikj@gmail.com");
		register.enterTephoneNumber("878762778");
		register.enterPassword("Te12s@2345");
		register.enterCnfrmPassword("Te12s@2345");
		register.newsLetterYes();
		register.clickPrivacyPolicycheckbox();
		
		wait.until(ExpectedConditions.elementToBeClickable(register.continueBtn));
		register.clickContnueBtn();
		

		String registerConfrmMsg = register.rgstrSuccessMsg();
		System.out.println(registerConfrmMsg);
		

		Assert.assertEquals(registerConfrmMsg, "Your Account Has Been Created!",
				"Account is not Created or Success Message does not match");
	}
}
