package nopCommerce.stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;

public class homePageSteps {
WebDriver driver;
	
	protected homePageSteps() {
		this.driver = Hooks.openAndQuitBrowser();
	}


}
