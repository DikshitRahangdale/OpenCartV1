package pageObjects;

import java.security.PublicKey;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.ConstructorBase;

public class MyAccountPage extends ConstructorBase {

	public WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='content']/child::*[normalize-space()='My Account']")
	WebElement myAccountHeading;

	@FindBy(xpath = "//a[@title='My Account']/following-sibling::ul/li[normalize-space()='Logout']")
	WebElement logoutOption;

	public void clickLogout() {
		logoutOption.click();
	}

	public boolean vrifyMyAccountHeading() {

		try {
			return myAccountHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
