package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {

	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getLoginPageUrl() {
		return getCurrentUrlPage(driver);
	}

	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public void inputToEmailTextbox(String userEmailAddress) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX,userEmailAddress);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver,UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getErrorMessageLoginUnsuccessful() {
		waitForElementVisible(driver,UserLoginPageUI.LOGIN_UNSUCCESSFUL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.LOGIN_UNSUCCESSFUL_ERROR_MESSAGE);
	}

	public void inputToPasswordTextbox(String userPassword) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX,userPassword);
		
	}

	public UserHomePageObject loginAsUser(String userEmailAddress, String userPassword) {
		inputToEmailTextbox(userEmailAddress);
		inputToPasswordTextbox(userPassword);
		return clickToLoginButton();
		
	}
	
	public void OpenRegisterPage() {
		waitForElementClickable(driver, UserLoginPageUI.REGISTER_TEXTLINK);
		clickToElement(driver, UserLoginPageUI.REGISTER_TEXTLINK);
		
	}
}
