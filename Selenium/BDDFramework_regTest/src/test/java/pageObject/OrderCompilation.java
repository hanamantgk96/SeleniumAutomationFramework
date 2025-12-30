package pageObject;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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
		
		WebElement backArrow = driver.findElement(
			    By.cssSelector("[data-testid='ArrowBackIcon']")
			);
			backArrow.click();
	}

//invoice generation 

	public void clickOrderOption() {
		driver.findElement(By.xpath("//span[text()='Order']")).click();

	}

	public void clickViewOrder() {
		driver.findElement(By.xpath("//span[text()='View Orders']")).click();
	}

	public void clickMoreButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='More']")).click();
	}
	
	public void clickShowAllOptions() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Show all options']")).click();
	}

	public void clickEditOrderDetails1() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div//a[@class='dropdown-item'])[2]")).click();
	}

	public void clickGenerateInvoiceButton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[text()='Generate Invoice'])")).click();
	}
	
	public void invoiceConfirmation() throws InterruptedException {
		Thread.sleep(3000);
      driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
		Thread.sleep(2000);
	}
	
	//order completion
	
	public String getOrderStatus(String orderId) {
	    By statusLocator = By.xpath(
	        "//tr[td[normalize-space()='" + orderId + "']]/td[last()-1]//span"
	    );
	    return driver.findElement(statusLocator).getText().trim();
	}
	
	public void waitUntilStatusAppears(String expectedStatus) {

	    By firstRowStatus = By.xpath(
	        "(//table//tr[1]/td[last()-1]//span)[1]"
	    );

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(3));
	    wait.until(driver -> {
	        String status = driver.findElement(firstRowStatus).getText().trim();
	        System.out.println("Current status: " + status);
	        return status.equalsIgnoreCase(expectedStatus);
	    });
	}
	public void clickMoreOnFirstRow() {

	    By moreBtn = By.xpath(
	        "(//table//tr[1]//button[normalize-space()='More'])[1]"
	    );

	    new WebDriverWait(driver, Duration.ofSeconds(10))
	            .until(ExpectedConditions.elementToBeClickable(moreBtn))
	            .click();
	}

	
	
/*	public void waitUntilAwaitingPaymentAndClickMore() throws InterruptedException, TimeoutException {

	    By firstRowStatus = By.xpath(
	        "(//table//tr[1]//span)[1]"
	    );

	    By firstRowMoreBtn = By.xpath(
	        "(//table//tr[1]//button[contains(@class,'actions-dropdown')])[1]"
	    );

	    long timeoutMillis = Duration.ofMinutes(2).toMillis();
	    long startTime = System.currentTimeMillis();

	    while (System.currentTimeMillis() - startTime < timeoutMillis) {

	        String status = driver.findElement(firstRowStatus).getText().trim();
	        System.out.println("Current status: " + status);

	        if ("Awaiting Payment".equalsIgnoreCase(status)) {
	            new WebDriverWait(driver, Duration.ofSeconds(10))
	                    .until(ExpectedConditions.elementToBeClickable(firstRowMoreBtn))
	                    .click();
	            return; // ✅ EXIT IMMEDIATELY
	        }

	        driver.navigate().refresh();
	        Thread.sleep(15000);
	    }

	    throw new TimeoutException("Status did not change to Awaiting Payment");
	}*/

/*	public void clickPaymentAgainstInvoice() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    By paymentOption = By.xpath(
	        "//a[normalize-space()='Payment Against Invoice']"
	    );

	    wait.until(ExpectedConditions.elementToBeClickable(paymentOption)).click();
	}*/


	/*public void completeOrder(String orderId) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Click More for specific order
//	    By moreBtn = By.xpath(
//	        "//tr[td[normalize-space()='" + orderId + "']]//button[normalize-space()='More']"
//	    );
//	    wait.until(ExpectedConditions.elementToBeClickable(moreBtn)).click();

	    // Show all options (optional)
	    By showAll = By.xpath("//div[normalize-space()='Show all options']");
	    if (!driver.findElements(showAll).isEmpty()) {
	        wait.until(ExpectedConditions.elementToBeClickable(showAll)).click();
	    }

	    // Payment Against Invoice (MENU ITEM — not table row)
	    By paymentOption = By.xpath("//a[normalize-space()='Payment Against Invoice']");
	    wait.until(ExpectedConditions.elementToBeClickable(paymentOption)).click();

	    // Submit
	    By submitBtn = By.xpath("//button[normalize-space()='Submit']");
	    wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
	}*/

	
//	public void verifyCompleted(String orderId) {
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//	    wait.until(driver ->
//	        getOrderStatus(orderId).equalsIgnoreCase("Completed")
//	    );
//	}

	}


