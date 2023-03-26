package nopCommerce.stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class loginPageSteps {
	
	WebDriver driver;
	static String loginPageUrl;
	UserLoginPageObject userLoginPage;
	
	public loginPageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
		userLoginPage = PageGeneratorManager.getUserLoginPage(driver);
	}
	
	@Given("^Get Login Page Url$")
	public void getLoginPageUrl() {
		loginPageUrl = userLoginPage.getLoginPageUrl();
	}
	
	@When("^Open register url$")
	public void openRegisterUrl() {
	   userLoginPage.OpenRegisterPage();
	}

}