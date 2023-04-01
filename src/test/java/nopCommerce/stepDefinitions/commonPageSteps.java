package nopCommerce.stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.DataHelper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import junit.framework.Assert;
import pageObjects.nopCommerce.user.CommonPageObject;
import pageObjects.nopCommerce.user.PageGeneratorManager;

public class commonPageSteps {
	WebDriver driver;
	CommonPageObject commonPage;
	DataHelper dataFaker;
	String email;

	public commonPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		commonPage = PageGeneratorManager.getCommonPage(driver);
		dataFaker = DataHelper.getDataHelper();
		email = dataFaker.getEmailAddress();
	}
	
	@When("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
	public void inputToTextboxWithValue(String filedName, String inputValue)  {
		if(filedName.equals("Email")) {
			inputValue = email;
		}
	   commonPage.inputToDynamicTextbox(driver, filedName, inputValue);
	    
	}

	@Then("^Success message displayed with \"([^\"]*)\"$")
	public void successMessageDisplayedWith(String expectedMessage)  {
	   Assert.assertTrue(commonPage.isElementDisplayed(driver, expectedMessage));
	    
	}

}
