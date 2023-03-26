package facebook.stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class facebookSteps {
	WebDriver driver;
	
    @Given("^Open facebook aplication$")
    public void openFacebookAplication()  {
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();
    	driver.get("https://www.facebook.com/");
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
    }

    @Then("^Verify email textbox displayed$")
    public void verifyEmailTextboxDisplayed()  {
    	Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
        
    }

    @And("^Verify password textbox is display$")
    public void verifyPasswordTextboxIsDisplay()  {
    	Assert.assertTrue(driver.findElement(By.id("pass")).isDisplayed());
        
    }
    
    @And("^Close application$")
    public void CloseApplication()  {
    	driver.quit();;
        
    }


}
