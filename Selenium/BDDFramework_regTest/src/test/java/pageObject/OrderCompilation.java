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

	public void clickorderProcessing() throws InterruptedException {
		driver.findElement(clickorderProcessing).click();
		Thread.sleep(2000);
	}

	public void orderCompilation() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(orderCompilation).click();
	}

	public void stockReserving() throws InterruptedException {

		driver.navigate().refresh();

		Thread.sleep(2000);

	    By filterIcon = By.xpath("(//*[name()='svg' and @data-testid='FilterAltRoundedIcon'])[1]");
	    By searchBoxLocator = By.xpath("//input[@type='text' and @placeholder='Order ID']");

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(filterIcon))).click();

		WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));

		Thread.sleep(3000);

	    String orderId = Placeorder.orderId;
	    System.out.println("Order ID = " + orderId);

	    searchBox.clear();
	    searchBox.sendKeys(orderId);
	    Thread.sleep(1000);
	    searchBox.sendKeys(Keys.ENTER);
	    
	    Thread.sleep(3000);
	    
	}
	
	public void stockReserve() throws InterruptedException {

		Thread.sleep(2000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//tbody/tr[not(contains(@style,'display:none'))]")));

		Thread.sleep(2000);

		WebElement moreBtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//button[contains(@class,'actions-dropdown')])[2]")));
		moreBtn.click();

		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Reserve Batch']"))).click();

		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='SUBMIT']"))).click();

		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Confirm']"))).click();

		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='OK']"))).click();
	}


	}
