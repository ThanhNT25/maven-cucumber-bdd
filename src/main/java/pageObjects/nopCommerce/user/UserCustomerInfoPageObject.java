package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {

	private WebDriver driver;
	
	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInfoPageDisplay() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		clickToElement(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return true;
	}

	
}
