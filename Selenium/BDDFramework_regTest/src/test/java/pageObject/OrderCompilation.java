package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderCompilation {
	
	public WebDriver driver;

	public OrderCompilation(WebDriver driver) {
		this.driver = driver;
		
		}
	By clickgrid = By.xpath("//div[@class='menu-cont' ] //img[@alt='img']");
	By clickorderProcessing = By.xpath("//ul//li//span[text()='Order Processing']");
	By orderCompilation = By.xpath("//ul//li//span[text()='Order Compilation']");
	
	public void clickgrid() {
		driver.findElement(clickgrid).click();
	}

	public void clickorderProcessing() {
		driver.findElement(clickorderProcessing).click();
	}

	public void orderCompilation() {
		driver.findElement(orderCompilation).click();
	}

	public void stockReserving() throws InterruptedException {
		
		Thread.sleep(2000);

	    By filterIcon = By.xpath("(//*[name()='svg' and @data-testid='FilterAltRoundedIcon'])[1]");
	    By searchBoxLocator = By.xpath("//input[@type='text' and @placeholder='Order ID']");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.refreshed(
	            ExpectedConditions.elementToBeClickable(filterIcon)
	    )).click();

	    WebElement searchBox = wait.until(
	            ExpectedConditions.elementToBeClickable(searchBoxLocator)
	    );
	    
	    Thread.sleep(2000);

	    String orderId = Placeorder.orderId;
	    System.out.println("Order ID = " + orderId);

	    searchBox.clear();
	    searchBox.sendKeys(orderId);
	    searchBox.sendKeys(Keys.ENTER);
	    
	}
	
	public void stockReserve() throws InterruptedException {
		
		driver.findElement(By.xpath("(//button[@type='button' and @class='actions-dropdown dropdown-toggle btn btn-success'])[1]")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Reserve Batch']")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[text()='SUBMIT']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		Thread.sleep(2000);
		
	}

	}
