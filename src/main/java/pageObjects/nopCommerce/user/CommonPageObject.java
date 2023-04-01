package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CommonPageUI;

public class CommonPageObject extends BasePage {
	WebDriver driver;
	
	public CommonPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	
	public void inputToDynamicTextbox(WebDriver driver, String fieldName, String inputValue) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX, fieldName);
		sendkeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX, inputValue, fieldName);
	}
	
	public void clickToDynamicButton(WebDriver driver, String buttonValue) {
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(driver,CommonPageUI.DYNAMIC_BUTTON, buttonValue);
	}
	

}
