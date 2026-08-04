package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.ConstructorBase;

public class HomePage extends ConstructorBase {
	public WebDriver driver;

	@FindBy(xpath = "//span[normalize-space()='My Account']/parent::a")
	WebElement myAccoun;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement register;

	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement loginElement;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickMyAccount() {
		myAccoun.click();
	}

	public void clickRegister() {
		register.click();
	}

	public void clickLogin() {
		loginElement.click();
	}

}
