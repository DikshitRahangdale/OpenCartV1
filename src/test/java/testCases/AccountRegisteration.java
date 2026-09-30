package testCases;

import java.util.Random;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.RegistrationPage;
import testBase.DriverSetup;

public class AccountRegisteration extends DriverSetup {
	public HomePage home;
	public MyAccountPage myaccount;
	public RegistrationPage register;

	@BeforeClass
	public void pageObjectSetup() {
		home = new HomePage(driver);
		myaccount = new MyAccountPage(driver, wait);
		register = new RegistrationPage(driver, wait);
	}

	@Test(groups = { "Sanity", "Smoke" }, priority = 1)
	public void registeration() throws InterruptedException {

	

		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = driver.getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString, "Registration Page URL does not Match");

		register.enterFirstName(faker.name().firstName());
		register.enterLastName(faker.name().lastName());

		Random random = new Random();
		int number = random.nextInt(1000);
		register.enterEmail(faker.internet().safeEmailAddress());
		register.enterTephoneNumber(faker.phoneNumber().cellPhone());
		String passwords = faker.internet().password(5, 10);
		register.enterPassword(passwords);
		register.enterCnfrmPassword(passwords);
		register.newsLetterYes();
		register.clickPrivacyPolicycheckbox();

		wait.until(ExpectedConditions.elementToBeClickable(register.continueBtn));
		register.clickContnueBtn();

		String registerConfrmMsg = register.rgstrSuccessMsg();
		System.out.println(registerConfrmMsg);

		Assert.assertEquals(registerConfrmMsg, "Your Account Has Been Created!",
				"Account is not Created or Success Message does not match");
		home.clickMyAccount();
		myaccount.clickLogout();
		myaccount.clickOnlogoutcontBtns();
		String currentUrl = driver.getCurrentUrl();
		String expectedHomeUrl = "https://tutorialsninja.com/demo/index.php?route=common/home";

		if (!currentUrl.equals(expectedHomeUrl)) {
			System.out.println("The user does not Logout or Home Page URL is not matching");
		}

	}

	@Test(priority = 2)
	public void providingOnlyMandatoryField() {
		logger.info("*****Test Case 4**");
		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = driver.getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString,
				"Registration Page URL does not Match or Registeration Page does not OPen");
		register.clickContnueBtn();
		String actualfirstnameError = register.firstNameWarnMsg();
		String expectedFirstError = "First Name must be between 1 and 32 characters!";
		Assert.assertEquals(actualfirstnameError, expectedFirstError, "First Name Warning Message Does not Match");

	}

}
