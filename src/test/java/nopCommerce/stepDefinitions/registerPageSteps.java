package nopCommerce.stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import commons.DataHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class registerPageSteps {
	
	WebDriver driver;
	UserRegisterPageObject userRegisterPage;
	DataHelper dataTest;
	String firstName, lastName, password, confirmPassword;
	
	public registerPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		userRegisterPage = PageGeneratorManager.getUserRegisterPage(driver);
		dataTest = DataHelper.getDataHelper();
		firstName = "Thanh";
		lastName = "Nguyen";
		password = "123Aa@";
		confirmPassword = "123Aa@";
		
	}


	@Given("^Input to Firstname textbox$")
	public void inputToFirstnameTextbox()  {
		userRegisterPage.inputToFirstnameTextbox(dataTest.getFirstName());
	    
	}

	@Given("^Input to Lastname textbox$")
	public void inputToLastnameTextbox()  {
		userRegisterPage.inputToLastnameTextbox(dataTest.getLastName());
	    
	}

	@Given("^Input to email textbox$")
	public void inputToEmailTextbox()  {
		userRegisterPage.inputToEmailTextbox(dataTest.getEmailAddress());
	    
	}

	@Given("^Input to password textbox$")
	public void inputToPasswordTextbox()  {
		userRegisterPage.inputToPasswordTextbox(password);
	    
	}

	@Given("^Input to comfirm password textbox$")
	public void inputToComfirmPasswordTextbox()  {
		userRegisterPage.inputToConfirmPasswordTextbox(confirmPassword);
	    
	}

	@When("^Click to register button$")
	public void clickToRegisterButton()  {
		userRegisterPage.clickToRegisterButton();
	    
	}

	@Then("^Get register success message$")
	public void GetRegisterSuccessMessage()  {
	    userRegisterPage.getRegisterSuccessMessage();
	    
	}



}
