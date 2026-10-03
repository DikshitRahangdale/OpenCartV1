package testCases;

import testBase.DriverManager;
import java.util.Random;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.NewsLetterpage;
import pageObjects.RegistrationPage;
import testBase.DriverSetup;
import utilities.DataProviders;

public class AccountRegisteration extends DriverSetup {
	public HomePage home;
	public MyAccountPage myaccount;
	public RegistrationPage register;
	public AccountSuccessPage successPage;
	public NewsLetterpage newsLetterPage;

	@BeforeClass
	public void pageObjectSetup() {
		home = new HomePage(DriverManager.getDriver());
		myaccount = new MyAccountPage(DriverManager.getDriver(), wait);
		register = new RegistrationPage(DriverManager.getDriver(), wait);
		successPage = new AccountSuccessPage(DriverManager.getDriver(), wait);
		newsLetterPage = new NewsLetterpage(DriverManager.getDriver(), wait);
	}

	@Test(groups = { "Sanity", "Smoke" }, priority = 1)
	public void registeration() throws InterruptedException {
		logger.info("Test Case 1");
		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = DriverManager.getDriver().getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString, "Registration Page URL does not Match");

		register.fillRegistrationForm("Yes");

		String registerConfrmMsg = register.rgstrSuccessMsg();
		logger.info("Registration confirmation message: " + registerConfrmMsg);

		Assert.assertEquals(registerConfrmMsg, "Your Account Has Been Created!",
				"Account is not Created or Success Message does not match");

		home.clickMyAccount();
		myaccount.clickLogout();
		myaccount.clickOnlogoutcontBtns();
		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		String expectedHomeUrl = "https://tutorialsninja.com/demo/index.php?route=common/home";

		Assert.assertEquals(currentUrl, expectedHomeUrl,
				"User was not logged out successfully or Home Page URL does not match");

	}

	@Test(priority = 2)
	public void providingOnlyMandatoryField() {
		logger.info("*****Test Case 4**");
		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = DriverManager.getDriver().getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString,
				"Registration Page URL does not Match or Registeration Page does not OPen");
		register.clickContnueBtn();

		logger.info("Validating the First NAme warning message");
		String actualfirstnameError = register.firstNameWarnMsg();
		String expectedFirstError = "First Name must be between 1 and 32 characters!";
		Assert.assertEquals(actualfirstnameError, expectedFirstError, "First Name Warning Message Does not Match");

		logger.info("Validating the Last NAme warning message");
		String actualLastnameError = register.lastNameWarnMsg();
		String expectedLastnameError = "Last Name must be between 1 and 32 characters!";
		Assert.assertEquals(actualLastnameError, expectedLastnameError, "Last Name Warning Message Does not Match");

		logger.info("Validating the Email warning message");
		String actualEmailError = register.emailWarnMsg();
		String expectedEmailError = "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(actualEmailError, expectedEmailError, "Email Warning Message Does not Match");

		logger.info("Validating the TelePhone NUmber warning message");
		String actualPhoneError = register.telePhoneWarnMsg();
		String expectedPhoneError = "Telephone must be between 3 and 32 characters!";
		Assert.assertEquals(actualPhoneError, expectedPhoneError, "Telephone Warning Message Does not Match");

		logger.info("Validating the Password warning message");
		String actualPasswordError = register.passWordWrngMsg();
		String expectedPasswordError = "Password must be between 4 and 20 characters!";
		Assert.assertEquals(actualPasswordError, expectedPasswordError, "Password Warning Message Does not Match");

		logger.info("Validating the Privacy Policy warning message");
		String actualPrivacyError = register.privacyPolicyWarnMsg();
		String expectedPrivacyError = "Warning: You must agree to the Privacy Policy!";
		Assert.assertTrue(actualPrivacyError.contains(expectedPrivacyError),
				"Expected text to contain '" + expectedPrivacyError + "', but got: " + actualPrivacyError);
		logger.info("*****Test Case 4 Pass**");

		register.clickOnHomeIcon();
		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		String expectedHomeUrl = "https://tutorialsninja.com/demo/index.php?route=common/home";
		Assert.assertEquals(currentUrl, expectedHomeUrl, "Home Page is not opened");

	}

	@Test(priority = 3, dataProvider = "Status", dataProviderClass = DataProviders.class)
	public void validationtNewsLetterStatus(String status) {
		logger.info("Test Case 5 & 6");
		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString =  DriverManager.getDriver().getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString, "Registration Page URL does not Match");

		register.fillRegistrationForm(status);

		String registerConfrmMsg = register.rgstrSuccessMsg();
		logger.info("Registration confirmation message: " + registerConfrmMsg);

		Assert.assertEquals(registerConfrmMsg, "Your Account Has Been Created!",
				"Account is not Created or Success Message does not match");

		successPage.clickOnContBtn();
		myaccount.clickonNewsletterLink();
		String actualUrl = DriverManager.getDriver().getCurrentUrl();
		String expectedURL = "https://tutorialsninja.com/demo/index.php?route=account/newsletter";
		Assert.assertEquals(actualUrl, expectedURL, "The NewsLetter URl Does not Match means");

		if (status.equalsIgnoreCase("Yes")) {
			boolean yesradioStatus = newsLetterPage.statusOfNewsletterYes();
			boolean noRadioStatus = newsLetterPage.statusOfNewsletterNo();
			Assert.assertTrue(yesradioStatus, "Yes NewsLetter radio button is not selected");
			Assert.assertFalse(noRadioStatus, "No Newsletter radio button is selected");

			logger.info("Newsletter Yes validation completed successfully");

		} else if (status.equalsIgnoreCase("No")) {
			boolean noradioStatus = newsLetterPage.statusOfNewsletterNo();
			boolean yesRadioStatus = newsLetterPage.statusOfNewsletterYes();
			Assert.assertTrue(noradioStatus, "No NewsLetter radio button is not selected");
			Assert.assertFalse(yesRadioStatus, "Yes Newsletter radio button is selected");

			logger.info("Newsletter No validation completed successfully");
		} else {
			Assert.fail("Invalid Newsletter status provided: " + status);
		}

		home.clickMyAccount();
		myaccount.clickLogout();
		myaccount.clickOnlogoutcontBtns();
		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		String expectedHomeUrl = "https://tutorialsninja.com/demo/index.php?route=common/home";

		Assert.assertEquals(currentUrl, expectedHomeUrl,
				"User was not logged out successfully or Home Page URL does not match");

		logger.info("Newsletter test completed successfully for status: " + status);

	}

}
