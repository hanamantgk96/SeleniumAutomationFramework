package stepDefinitions;

import pageObject.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.BaseClass;


public class Steps {
	
	public BaseClass setupclass;

	public Steps(BaseClass setupclass) {
		this.setupclass = setupclass;

}
	
	@Given("User Launch the browser")
	public void user_launch_the_chrome_browser() {
		LoginPage login = setupclass.pageObjectManager.getLoginPage1();
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		LoginPage login = setupclass.pageObjectManager.getLoginPage1();

	}

	@When("User enters username {string} and password {string}")
	public void user_enters_credentials(String username, String password) throws InterruptedException {
	    LoginPage login = setupclass.pageObjectManager.getLoginPage1();
	    login.enterusername(username);
	    login.enterpassword(password);
	}

	@When("Click on login button")
	public void click_on_login_button() {
		LoginPage login = setupclass.pageObjectManager.getLoginPage1();
		login.clickOnLoginButton();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
		LoginPage login = setupclass.pageObjectManager.getLoginPage1();
		
		login.loginValidation();
	}
	
	@When("User clcik on the Log out link")
	public void user_clcik_on_the_log_out_link() throws InterruptedException {
		LoginPage login = setupclass.pageObjectManager.getLoginPage1();
		if (login.isLoggedIn()) {
	        Thread.sleep(1000);
	        login.clickUAEADMIN();
	        Thread.sleep(1000);
	        login.clickLogout();
	    } else {
	        System.out.println("Login failed → Skip logout");
	    }
	}
	
	@Then("Page title should be the {string}")
	public void page_title_should_be_the(String string) {
		LoginPage login = setupclass.pageObjectManager.getLoginPage1();
	}

	@And("Log in is successfull")
	public void logged_in_success() {
		LoginPage login = setupclass.pageObjectManager.getLoginPage1();
//		login.quiteBrowser();
	}

}
