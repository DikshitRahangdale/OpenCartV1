package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.ConstructorBase;

public class NewsLetterpage extends ConstructorBase {
	public WebDriverWait wait;
	public WebDriver driver;

	@FindBy(css = "input[name='newsletter'][value='1']")
	WebElement newsLetterYes;
	
	@FindBy(css = "input[name='newsletter'][value='0']")
	WebElement newsLetterNo;

	public NewsLetterpage(WebDriver driver, WebDriverWait wait) {
		super(driver);
		this.wait = wait;
	}

	public boolean statusOfNewsletterYes() {
		return wait.until(ExpectedConditions.visibilityOf(newsLetterYes)).isSelected();

	}

	public boolean statusOfNewsletterNo() {
		return wait.until(ExpectedConditions.visibilityOf(newsLetterNo)).isSelected();

	}
}
