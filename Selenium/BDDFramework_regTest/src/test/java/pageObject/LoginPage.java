package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

}
	
	By phNum = By.xpath("//input[@type='text']");
	By passWord = By.xpath("//input[@type='password']");
	By login = By.xpath("//button[text()='Login']");
	By uaeadmin =By.xpath("//div[text()='UAEADMIN']");
	By logout = By.xpath("//*[text()='Logout']");
	By LoginVali = By.xpath("//*[text()='FRESHONTABLE']");
	
	public void enterusername(String uname) {
		driver.findElement(phNum).clear();
		driver.findElement(phNum).sendKeys(uname);
	}

	public void enterpassword(String pwd) {
		driver.findElement(passWord).clear();
		driver.findElement(passWord).sendKeys(pwd);
	}

	public void clickOnLoginButton() {
		driver.findElement(login).click();
	}

	public boolean isLoggedIn() {
		try {
			return driver.findElement(uaeadmin).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
/*	public boolean loginValidation() {
	    String text = driver.findElement(LoginVali).getText();
	    System.out.println("Login label: " + text);

	    if(text.contains("FRESHONTABLE")) {
	        System.out.println("Login Successful");
	        return true;
	    } else {
	        System.out.println("Login Failed");
	        return false;
	    }
	}*/
	
	public boolean loginValidation() {
		String title = driver.findElement(LoginVali).getText();
	    if (title.equals("FRESHONTABLE")) {
	        System.out.println("Login Successful and title name is " + title);
	        return true;
	    } else {
	        System.out.println("Login validation Failed");
	        return false;
	    }
	}


	public void clickUAEADMIN() {
		driver.findElement(uaeadmin).click();
	}

	public void clickLogout() {
		driver.findElement(logout).click();
	}

	public void quiteBrowser() {
		driver.quit();
	}
}