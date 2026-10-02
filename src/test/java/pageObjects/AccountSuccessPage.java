package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.ConstructorBase;

public class AccountSuccessPage extends ConstructorBase {
	public WebDriverWait wait;
	public WebDriver driver;

	@FindBy(xpath = "//div[@id='content']/child::div[@class='buttons']/descendant::a[contains(@class, 'btn-primary')]")
	WebElement contButton;

	public AccountSuccessPage(WebDriver driver, WebDriverWait wait) {
		super(driver);
		this.wait = wait;
	}

	public void clickOnContBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(contButton)).click();
	}

}
