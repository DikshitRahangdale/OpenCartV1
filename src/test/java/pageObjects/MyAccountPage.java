package pageObjects;

import java.security.PublicKey;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.ConstructorBase;

public class MyAccountPage extends ConstructorBase {

	public WebDriver driver;
	public WebDriverWait wait;

	public MyAccountPage(WebDriver driver, WebDriverWait wait) {
		super(driver);
		this.wait = wait;
	}

	@FindBy(xpath = "//div[@id='content']/child::*[normalize-space()='My Account']")
	WebElement myAccountHeading;

	@FindBy(xpath = "//a[@title='My Account']/following-sibling::ul/li[normalize-space()='Logout']")
	WebElement logoutOption;

	@FindBy(xpath = "//div[@id='content']/child::div/descendant::a[contains(@class, 'btn btn-primary')]")
	WebElement clickOnlogoutcontBtn;

	@FindBy(xpath = "//div[@id='content']//a[contains(@href,'newsletter')]")
	WebElement subscribeNewsletterlinks;

	public void clickLogout() {
		logoutOption.click();
	}

	public void clickOnlogoutcontBtns() {
		wait.until(ExpectedConditions.elementToBeClickable(clickOnlogoutcontBtn)).click();
	}

	public boolean vrifyMyAccountHeading() {

		try {
			return myAccountHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickonNewsletterLink() {
		wait.until(ExpectedConditions.visibilityOf(subscribeNewsletterlinks)).click();
	}

}
