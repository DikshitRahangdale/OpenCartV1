package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountSuccessPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.NewsLetterpage;
import pageObjects.RegistrationPage;
import testBase.DriverManager;
import testBase.DriverSetup;
import utilities.DataProviders;

public class AccountRegisteration extends DriverSetup {
	public HomePage home;
	public MyAccountPage myaccount;
	public RegistrationPage register;
	public AccountSuccessPage successPage;
	public NewsLetterpage newsLetterPage;
	public LoginPage loginPage;

	@BeforeClass
	public void pageObjectSetup() {
		home = new HomePage(DriverManager.getDriver(), wait);
		myaccount = new MyAccountPage(DriverManager.getDriver(), wait);
		register = new RegistrationPage(DriverManager.getDriver(), wait);
		successPage = new AccountSuccessPage(DriverManager.getDriver(), wait);
		newsLetterPage = new NewsLetterpage(DriverManager.getDriver(), wait);
		loginPage = new LoginPage(DriverManager.getDriver(), wait);
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
		String actualUrlString = DriverManager.getDriver().getCurrentUrl();
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

	@Test(priority = 4)
	public void navigateToDifferentWay() {
		logger.info("Test Case 7");
		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = DriverManager.getDriver().getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString, "Registration Page URL does not Match");

		home.clickMyAccount();
		home.clickLogin();
		String expectedLoginUrl = "https://tutorialsninja.com/demo/index.php?route=account/login";
		String actualLoginUrl = DriverManager.getDriver().getCurrentUrl();
		Assert.assertEquals(actualLoginUrl, expectedLoginUrl, "Login Page URL does not Match");

		loginPage.clickContinueBtn();
		String expectedRegisterUrl = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualRegisterUrl = DriverManager.getDriver().getCurrentUrl();
		Assert.assertEquals(actualRegisterUrl, expectedRegisterUrl, "Register Page URL does not Match");

		home.clickMyAccount();
		home.clickLogin();
		loginPage.clickRegisterLink();
		String expectedRegisterUrl1 = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualRegisterUrl1 = DriverManager.getDriver().getCurrentUrl();
		Assert.assertEquals(actualRegisterUrl1, expectedRegisterUrl1, "Register Page URL does not Match");

		register.clickOnHomeIcon();
		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		String expectedHomeUrl = "https://tutorialsninja.com/demo/index.php?route=common/home";
		Assert.assertEquals(currentUrl, expectedHomeUrl, "Home Page is not opened");
		logger.info("Register Page Navigation Test completed successfully");
	}

	@Test(priority = 5)
	public void validateDifferentPassowrd() {
		logger.info("Test Case 8");
		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = DriverManager.getDriver().getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString, "Registration Page URL does not Match");

		register.enterFirstName(faker.name().firstName());
		register.enterLastName(faker.name().lastName());
		register.enterEmail(faker.internet().safeEmailAddress());
		register.enterTephoneNumber(faker.phoneNumber().cellPhone());

		String password = faker.internet().password(5, 10);
		register.enterPassword(password);
		register.enterCnfrmPassword(faker.internet().password(5, 12));
		register.newsLetterYes();
		register.clickPrivacyPolicycheckbox();
		register.clickContnueBtn();

		String actualRegisterURL = DriverManager.getDriver().getCurrentUrl();
		String expectedRegisterURL = "https://tutorialsninja.com/demo/index.php?route=account/register";
		Assert.assertEquals(actualRegisterURL, expectedRegisterURL, "User is not on Register Page");

		String actualCnfrmPasswordError = register.cnfrmPasswordWrngMsg();
		String expectedCnfrmPasswordError = "Password confirmation does not match password!";
		Assert.assertEquals(actualCnfrmPasswordError, expectedCnfrmPasswordError,
				"Confirm Password Warning Message Does not Match");
		logger.info("Confirm Password Warning Message validated successfully" + "Actual Message: "
				+ actualCnfrmPasswordError + " Expected Message: " + expectedCnfrmPasswordError);
		register.clickOnHomeIcon();
		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		String expectedHomeUrl = "https://tutorialsninja.com/demo/index.php?route=common/home";
		Assert.assertEquals(currentUrl, expectedHomeUrl, "Home Page is not opened");
		logger.info("Continue Without Entering Confirm Password Test completed successfully");
	}

	@Test(priority = 5)
	public void RegisterwithExsisting() {
		logger.info("Test Case 9");
		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString = DriverManager.getDriver().getCurrentUrl();
		logger.info("Verify Resgistration URL ");
		Assert.assertEquals(actualUrlString, expecetdUrlString, "Registration Page URL does not Match");
		String firstname = faker.name().firstName();
		String lastName = faker.name().lastName();
		String email = faker.internet().safeEmailAddress();
		String phoneNumber = faker.phoneNumber().cellPhone();
		String password = faker.internet().password(5, 10);
          	
		register.fillRegistrationForm(firstname, lastName, email, phoneNumber, password, password, "Yes");
		register.clickPrivacyPolicycheckbox();
		register.clickContnueBtn();
		
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

      
		home.clickMyAccount();
		home.clickRegister();
		String expecetdUrlString1 = "https://tutorialsninja.com/demo/index.php?route=account/register";
		String actualUrlString1 = DriverManager.getDriver().getCurrentUrl();
		Assert.assertEquals(actualUrlString1, expecetdUrlString1, "Registration Page URL does not Match");
		
		register.fillRegistrationForm(firstname, lastName, email, phoneNumber, password, password, "Yes");
		register.clickPrivacyPolicycheckbox();
		register.clickContnueBtn();
		
		String actualRegisterURL = DriverManager.getDriver().getCurrentUrl();
		String expectedRegisterURL = "https://tutorialsninja.com/demo/index.php?route=account/register";
		Assert.assertEquals(actualRegisterURL, expectedRegisterURL, "User is not on Register Page");
		
		String actualExtngWarnMsg=register.existingAcntWrnMsg();
		String expectExtngWarnMsg="Warning: E-Mail Address is already registered!";
		
		Assert.assertEquals(actualExtngWarnMsg, expectExtngWarnMsg, "Exsting Account Warnign Message is not Matching");
		register.clickOnHomeIcon();
		logger.info("Create Account with Existing Account Datails Test completed successfully");
	}
}
