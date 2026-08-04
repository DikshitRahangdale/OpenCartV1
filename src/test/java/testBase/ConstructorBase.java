package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConstructorBase {
	public WebDriver driver;

	public ConstructorBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
