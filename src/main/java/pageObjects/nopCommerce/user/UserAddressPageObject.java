package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserAddressPageObject extends BasePage {

	private WebDriver driver;
	
	public UserAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
