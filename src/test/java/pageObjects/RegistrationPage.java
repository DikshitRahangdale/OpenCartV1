package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.ConstructorBase;

public class RegistrationPage extends ConstructorBase {

	public WebDriver driver;

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

	public RegistrationPage(WebDriver driver) {
		super(driver); // call the base or parent class constructor

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
		continueBtn.click();
	}

	public String rgstrSuccessMsg() {
		try {
			return (registrationCongratMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
