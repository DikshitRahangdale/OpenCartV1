package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.ConstructorBase;

public class LoginPage extends ConstructorBase {
	WebDriverWait wait;

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver);
		this.wait = wait;
	}

	@FindBy(id = "input-email")
	WebElement userEmailAddress;

	@FindBy(id = "input-password")
	WebElement userPassword;

	@FindBy(xpath = "//input[@id='input-password']/parent::div/following-sibling::input[@value='Login']")
	WebElement loginBtn;

	@FindBy(css = "a[class='btn btn-primary']")
	WebElement continueBtn;

	@FindBy(xpath = "//aside[@id='column-right']/descendant::a[contains(@href,'account/register')]")
	WebElement registerLink;

	public void enterUserEmail(String username) {
		userEmailAddress.sendKeys(username);
	}

	public void enterPassword(String pass) {
		userPassword.sendKeys(pass);
	}

	public void clickLoginBtn() {
		loginBtn.click();
	}

	public void clickContinueBtn() {
		continueBtn.click();
	}

	public void clickRegisterLink() {
		wait.until(ExpectedConditions.visibilityOf(registerLink)).click();
		// registerLink.click();
	}

}
