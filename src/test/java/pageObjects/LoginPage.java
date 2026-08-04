package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.ConstructorBase;

public class LoginPage extends ConstructorBase {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-email")
	WebElement userEmailAddress;

	@FindBy(id = "input-password")
	WebElement userPassword;

	@FindBy(xpath = "//input[@id='input-password']/parent::div/following-sibling::input[@value='Login']")
	WebElement loginBtn;

	public void enterUserEmail(String username) {
		userEmailAddress.sendKeys(username);
	}

	public void enterPassword(String pass) {
		userPassword.sendKeys(pass);
	}

	public void clickLoginBtn() {
		loginBtn.click();
	}

}
