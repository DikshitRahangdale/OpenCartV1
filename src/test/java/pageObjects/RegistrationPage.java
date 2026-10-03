package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import testBase.ConstructorBase;

public class RegistrationPage extends ConstructorBase {

	
	public WebDriverWait wait;
	public Faker faker;

	@FindBy(id = "input-firstname")
	WebElement firstName;

	@FindBy(id = "input-lastname")
	WebElement lastName;

	@FindBy(id = "input-email")
	WebElement emailinput;

	@FindBy(id = "input-telephone")
	WebElement telephoneInput;

	@FindBy(id = "input-password")
	WebElement passwordInput;

	@FindBy(id = "input-confirm")
	WebElement cnfrmPasswordInput;

	@FindBy(xpath = "//label[normalize-space()='Yes']")
	WebElement newsletterYes;

	@FindBy(xpath = "//label[normalize-space()='No']")
	WebElement newsletterNo;

	@FindBy(xpath = "//input[@name='agree' and @type='checkbox']")
	WebElement privacypolicybox;

	@FindBy(xpath = "//input[@name='agree' and @type='checkbox']/following-sibling::input[@value='Continue']")
	public WebElement continueBtn;

	@FindBy(xpath = "//div[@id='content']/*[normalize-space()='Your Account Has Been Created!']")
	WebElement registrationCongratMsg;

	@FindBy(xpath = "//input[@id='input-firstname']/ancestor::div[contains(@class,'form-group')]//div[contains(@class,'text-danger')]")
	WebElement firstNamewarningMsg;

	@FindBy(xpath = "//input[@id='input-lastname']/ancestor::div[contains(@class,'form-group')]//div[contains(@class,'text-danger')]")
	WebElement lastNameWarnMsg;

	@FindBy(xpath = "//input[@id='input-email']/ancestor::div[contains(@class,'form-group')]//div[contains(@class,'text-danger')]")
	WebElement emailWarnMsg;

	@FindBy(xpath = "//input[@id='input-telephone']/ancestor::div[contains(@class,'form-group')]//div[contains(@class,'text-danger')]")
	WebElement telePhoneWarnMsg;

	@FindBy(xpath = "//input[@id='input-password']/ancestor::div[contains(@class,'form-group')]//div[contains(@class,'text-danger')]")
	WebElement passWordWarnMsg;

	@FindBy(css = ".alert.alert-danger.alert-dismissible")
	WebElement privacyPolicyWarnMsg;

	@FindBy(css = ".fa-home")
	WebElement homeIcon;

	public RegistrationPage(WebDriver driver, WebDriverWait wait) {
		super(driver); // call the base or parent class constructor
		this.wait = wait;
		faker = new Faker();
	}

	public void enterFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}

	public void enterEmail(String email) {
		this.emailinput.sendKeys(email);
	}

	public void enterTephoneNumber(String telephoneNumber) {
		this.telephoneInput.sendKeys(telephoneNumber);
	}

	public void enterPassword(String password) {
		this.passwordInput.sendKeys(password);
	}

	public void enterCnfrmPassword(String cnfrmPassword) {
		this.cnfrmPasswordInput.sendKeys(cnfrmPassword);
	}

	public void newsLetterYes() {
		this.newsletterYes.click();
	}

	public void newsLetterNo() {
		this.newsletterNo.click();
	}

	public void clickPrivacyPolicycheckbox() {
		privacypolicybox.click();
	}

	public void clickContnueBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

	}

	public String rgstrSuccessMsg() {
		try {
			return (registrationCongratMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public String firstNameWarnMsg() {
		return wait.until(ExpectedConditions.visibilityOf(firstNamewarningMsg)).getText();

	}

	public String lastNameWarnMsg() {
		return wait.until(ExpectedConditions.visibilityOf(lastNameWarnMsg)).getText();
	}

	public String emailWarnMsg() {
		return wait.until(ExpectedConditions.visibilityOf(emailWarnMsg)).getText();
	}

	public String telePhoneWarnMsg() {
		return wait.until(ExpectedConditions.visibilityOf(telePhoneWarnMsg)).getText();
	}

	public String passWordWrngMsg() {
		return wait.until(ExpectedConditions.visibilityOf(passWordWarnMsg)).getText();
	}

	public String privacyPolicyWarnMsg() {
		return wait.until(ExpectedConditions.visibilityOf(privacyPolicyWarnMsg)).getText();
	}

	public void clickOnHomeIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(homeIcon)).click();
	}

	public void fillRegistrationForm(String newsletter) {

		enterFirstName(faker.name().firstName());
		enterLastName(faker.name().lastName());
		enterEmail(faker.internet().safeEmailAddress());
		enterTephoneNumber(faker.phoneNumber().cellPhone());

		String password = faker.internet().password(5, 10);

		enterPassword(password);
		enterCnfrmPassword(password);

		if (newsletter.equalsIgnoreCase("Yes")) {
			newsLetterYes();
		} else {
			newsLetterNo();
		}

		clickPrivacyPolicycheckbox();
		clickContnueBtn();
	}

}
