package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageUIs.nopCommerce.user.BasePageUI;

public class BasePage {
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openUrlPage(WebDriver driver, String urlPage) {
		driver.get(urlPage);
		
	}
	
	public String getTitlePage(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrlPage(WebDriver driver) {
		return driver.getCurrentUrl();
		
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backPage (WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardPage (WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshPage (WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}
	
	public Alert waitForAlertPresence (WebDriver driver) {
		WebDriverWait expliciWait = new WebDriverWait(driver,30);
		return expliciWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public String getAlertText(WebDriver driver) {
		 return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		 waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for(String id : allWindowIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for(String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}
	
	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for(String id : allWindowIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		//System.out.println("Locator type = " +locatorType);
		if(locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=") ) {
			by = By.id(locatorType.substring(3));
		} else if(locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=") ){
			by = By.className(locatorType.substring(6));
		} else if(locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") ){
			by = By.name(locatorType.substring(5));
		} else if(locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=") ){
			by = By.cssSelector(locatorType.substring(4));
		} else if(locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=") ){
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}
		
		return by;
	}
	
	public By getByXpath(String xpathLocator) {
		 return By.xpath(xpathLocator); 
	}
	
	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		System.out.println("Locator Type Before = " +locatorType);
		if(locatorType.startsWith("xpath=") ||locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") ) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		} 
		
		for(String value :  dynamicValues) {
			System.out.println("Values map to locator = " +value);
		}
		System.out.println("Locator type After = " +locatorType);
		return locatorType;
	}
	
	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
//	public WebElement getWebElement(WebDriver driver, String locatorType) {
//		return driver.findElement(getByLocator(locatorType));
//	}
	
	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator){
		return driver.findElements(getByLocator(xpathLocator));
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}
	
	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}
	
	public void clearValueInElementByPressKey(WebDriver driver, String locatorType) {
		WebElement element = this.getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}
	
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}
	
	public String getElementText(WebDriver driver, String locatorType,String... dynamicValues ) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String locatorType, String textItem,String... dynamicValues) {
		Select select = new Select(getWebElement(driver,  getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver,  xpathLocator));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver,xpathLocator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedText) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);
		
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));
		for (WebElement item :  allItems) {
			if(item.getText().trim().equals(expectedText)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName,String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType,dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getAttribute(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver,xpathLocator).size();
	}
	
	public int getElementSize(WebDriver driver,String locatorType, String... dynamicValues) {
		return getListWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).size();
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType ) {
		WebElement element = getWebElement(driver,locatorType);
		if(!element.isSelected()) {
			element.click();
		}
				
	}
	
	public void checkToDefaultCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues ) {
		WebElement element = getWebElement(driver,getDynamicXpath(locatorType,dynamicValues));
		if(!element.isSelected()) {
			element.click();
		}
				
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType ) {
		WebElement element = getWebElement(driver,locatorType);
		if(element.isSelected()) {
			element.click();
		}
				
	}
	
	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType ,String... dynamicValues) {
		WebElement element = getWebElement(driver,getDynamicXpath(locatorType,dynamicValues));
		if(element.isSelected()) {
			element.click();
		}
				
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorlType) {
		try {
			return getWebElement(driver, locatorlType).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTime(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locator);
				
		overrideGlobalTime(driver, longTimeout);		
		
		if(elements.size()==0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
//			System.out.println("Case 2 - Eelement cos trong DOM nhung khong visible/display");
//			System.out.println("End time = " +new Date().toString());
			return true;
		} else {
//			System.out.println("Element in DOM and visiable");
			return false;
		}
		
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator, String...dynamicValues) {
		overrideGlobalTime(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver,getDynamicXpath( locator, dynamicValues));
		overrideGlobalTime(driver, longTimeout);		
		
		if(elements.size()==0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
//			System.out.println("Case 2 - Eelement cos trong DOM nhung khong visible/display");
//			System.out.println("End time = " +new Date().toString());
			return true;
		} else {
//			System.out.println("Element in DOM and visiable");
			return false;
		}
		
	}
	
	public void overrideGlobalTime(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	public boolean isElementEnable(WebDriver driver, String locatorlType) {
		return getWebElement(driver,locatorlType).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locatorlType) {
		return getWebElement(driver,locatorlType).isSelected();
	}
	
	public void switchToFrameIframe(WebDriver driver, String locatorlType) {
		driver.switchTo().frame(getWebElement(driver,locatorlType));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver,xpathLocator)).perform();
		
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver,locatorType), key).perform();
		
	}
	
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String...dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)), key).perform();
		
	}
	
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isImageLoaded(WebDriver driver, String LocatorType, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",getWebElement(driver,getDynamicXpath(LocatorType, dynamicValues)));
		return status;
	}
	
	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(xpathLocator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(xpathLocator)));
	}
	
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait expliciWait = new WebDriverWait(driver, shortTimeout);
		overrideGlobalTime(driver, shortTimeout);
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideGlobalTime(driver, longTimeout);
	}
	
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(xpathLocator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
//	public void uploadMutipleFiles(WebDriver driver, String...fileNames) {
//		String filePath = GlobalConstants.UPLOAD_FILE;
//		String fullFileName ="";
//		for(String file : fileNames) {
//			fullFileName = fullFileName + filePath + file+"\n";
//		}
//		fullFileName = fullFileName.trim();
//		getWebElement(driver, BasePageUploadUI.UPLOAD_FILE).sendKeys(fullFileName);
//	}
//	
//	public UserCustomerInfoPageObject openCustomerInfoPage (WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
//		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
//		return PageGeneratorManager.getUserCustomerInfoPage(driver);
//	}
//	
//	
//	
//	public UserAddressPageObject openAddressPage (WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
//		clickToElement(driver, BasePageUI.ADDRESS_LINK);
//		return PageGeneratorManager.getUserAddressPage(driver);
//	}
//	
//	public UserMyProductReviewPageObject openMyProductReviewPage (WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
//		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
//		return PageGeneratorManager.getUserMyProductReviewPage(driver);
//	}
//	
//	public UserRewardPointPageObject openRewardPointPage (WebDriver driver) {
//		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
//		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
//		return PageGeneratorManager.getUserRewardPointPage(driver);
//	}
//	
//	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
//		waitForElementClickable(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
//		clickToElement(driver, BasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
//		switch(pageName) {
//		case "Customer info":
//			return PageGeneratorManager.getUserCustomerInfoPage(driver);
//		case "Addresses":
//			return PageGeneratorManager.getUserAddressPage(driver);
//		case "My product reviews":
//			return PageGeneratorManager.getUserMyProductReviewPage(driver);
//		case "Reward points":
//			return PageGeneratorManager.getUserRewardPointPage(driver);
//		default:
//			throw new RuntimeException("Invalid page name at My Account area");
//		}
//	}
	
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver,BasePageUI.DYNAMIC_TEXTBOX_BY_ID, value,textboxID);
		
	}

	
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
	
	public void selectToDropdownByName(WebDriver driver, String dropdowAttributeName, String itemValue) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdowAttributeName);
		selectItemDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdowAttributeName);
	}
	

	public void clickToRadioButtonByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxOrRadio(driver,BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioLabelName);
		
	}

	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLableName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLableName);
		checkToDefaultCheckboxOrRadio(driver,BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLableName);
	}


	/**
	 * Get value in textbox by textboxID
	 * @param dirver
	 * @param textboxID
	 * @return
	 *  */
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver,BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID,"value", textboxID);
	}

//	public UserHomePO openEndUserSite(WebDriver driver, String endUserUrl) {
//		openUrlPage(driver, endUserUrl);
//		return pageObject.wordpress.admin.PageGeneratorManager.getUserHomePage(driver);
//	}
//
//	public AdminDashboardPO openAdminSite(WebDriver driver, String adminUrl) {
//		openUrlPage(driver, adminUrl);
//		return pageObject.wordpress.admin.PageGeneratorManager.getAdminDashboardPage(driver);
//	}


	
	public long longTimeout = GlobalConstants.LONG_TIME;
	public long shortTimeout = GlobalConstants.SHORT_TIME;
	
}
