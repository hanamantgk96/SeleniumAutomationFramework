package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.PageObjectUtils;

public class LoginPage {
	WebDriver driver;
	PageObjectUtils utils;
	public By USERNAME = By.cssSelector("input[name=userName]");
	public By PASSWORD = By.cssSelector("input[name=password]");
	public By LOGIN = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div[2]/div[1]/div/div/button");
	public By MAINMENU = By.className("menu-cont");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		this.utils = new PageObjectUtils(driver);
	}
	public void login(String userName,String passWord) throws InterruptedException {
		driver.findElement(USERNAME).sendKeys(userName);
		driver.findElement(PASSWORD).sendKeys(passWord);
		driver.findElement(LOGIN).click();
	}
	 public void clickMainMenu() {
		 utils.waitForElementToAppear(MAINMENU);
		 driver.findElement(MAINMENU).click();
//		 utils.click(MAINMENU);
		  
	
}
}
