package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.ConstructorBase;

public class HomePage extends ConstructorBase {
	WebDriverWait wait;

	@FindBy(xpath = "//span[normalize-space()='My Account']/parent::a")
	WebElement myAccoun;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement register;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu-right')]//a[contains(@href,'account/login')]")
	WebElement loginElement;

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver);
		this.wait = wait;
	}

	public void clickMyAccount() {
		myAccoun.click();
	}

	public void clickRegister() {
		register.click();
	}

	public void clickLogin() {
		wait.until(ExpectedConditions.visibilityOf(loginElement)).click();

	}

}
