package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import PageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.BaseTest;

public class LoginSteps {
	private WebDriver driver;
	 public LoginSteps() throws IOException {
		this.driver = BaseTest.getDriver();
	}
	

	
	@Given("user is on the login page")
	public void user_is_on_the_login_page() {
	    
	}

	@When("user logs in with valid credentials")
	public void user_logs_in_with_valid_credentials() throws IOException, InterruptedException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/GlobalData.properties");
		prop.load(fis);
		String url = prop.getProperty("url");
		driver.get(url);

		String userName = prop.getProperty("username");
		String passWord = prop.getProperty("password");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(userName, passWord);
		
		
	}

	@Then("user should be logged in successfully")
	public void user_should_be_logged_in_successfully() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickMainMenu();
	}
}
