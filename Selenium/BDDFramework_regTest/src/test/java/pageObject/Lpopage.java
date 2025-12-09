package pageObject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.time.LocalDate;




public class Lpopage {
	
	public WebDriver driver;

	public Lpopage(WebDriver driver) {
		this.driver = driver;
}
	By clickgrid = By.xpath("//div[@class='menu-cont' ] //img[@alt='img']");
	By clickprocur = By.xpath("//ul//li//span[text()='Procurement']");
	By clickLpogrid = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/ul[5]/ul/li[3]/span/span");
	By clickfilterbutton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div[1]/div/div[1]/img");
	By selectwearhouse = By.name("del_hub_id");
	By Applyfilter = By.xpath("//button[text()='Apply Filters']");
	By selectproducts = By.xpath("//table//tbody//tr/td[1]");
	By NextButton = By.xpath("//div[text()='Next ']");
	By SubmitButton = By.xpath("//button[text()='SUBMIT']");
	By ConfirmButton = By.xpath("//button[text()='Confirm']");
	By NoButton = By.xpath("//button[text()='No']");
	By Validation = By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/button");
	
	public void clickthreedot() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.findElement(clickgrid).click();
	}
	
	public void clickprocurement() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	    driver.findElement(clickprocur).click();
	}
	
	public void clickLpo() {
		driver.findElement(clickLpogrid).click();
	}
	
	public void clickwearhousefitler() {
		driver.findElement(clickfilterbutton).click();
	}
	
	public void wearhousedropdown() throws InterruptedException {
//		driver.findElement(wearhouseDropdown).click();		  
        WebElement warehouseDropdown = driver.findElement(selectwearhouse);
        warehouseDropdown.click();
       	         
                Select select = new Select(warehouseDropdown);
                select.selectByValue("50127"); //50465 - Abu Dhabi
 //               select.selectByIndex(1);
	}
	
	public void selectwearouse() {
		driver.findElement(Applyfilter).click();
		
	}

	public void selectproducts() throws InterruptedException {
		
driver.findElement(By.xpath("(//*[@class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium filter-icon  css-vubbuv'])[5]")).click();
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
       By.xpath("//*[@id=\"filter-popover-ord_id\"]/div[3]/div/div[1]/input")));
Thread.sleep(1000);
     searchBox.sendKeys("283272");
     searchBox.sendKeys(Keys.ENTER);

	    wait.until(ExpectedConditions.invisibilityOfElementLocated(
	            By.cssSelector(".react-confirm-alert-overlay")));

	    List<WebElement> checkboxes = driver.findElements(selectproducts);
	    System.out.println(checkboxes.size());

	    for (int i = 0; i < 3 && i < checkboxes.size(); i++) {
	        WebElement checkbox = checkboxes.get(i);
	        if (!checkbox.isSelected()) {
	            checkbox.click();
	        }
	    }
	}
	
	public void clicknextbutton() {
		driver.findElement(NextButton).click();
	}
	
	public void clicksubmittbutton() {
		driver.findElement(SubmitButton).click();
	}
	
	public void ClickConfirmButton() {
		driver.findElement(ConfirmButton).click();
	}
	
	public void clickNoButton(){
		driver.findElement(NoButton).click();
	}
	
	public void CreateLpoValidation() {
		WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div/div[2]/button"));
		String expectedValue = "Create LPOs";
        String actualValue = element.getText();	        
        Assert.assertEquals(actualValue, expectedValue, "The values don't match!");
        System.out.println("Lpo created sucessfully : " + actualValue);
	}
	
	public void updateTopLpoStatus(int countToUpdate) throws InterruptedException {

	    Thread.sleep(1000); // wait for table to load

	    // Locate all rows in the LPO table
	    List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
	    System.out.println("Total rows in LPO table: " + rows.size());

	    int updatedCount = 0;

	    for (int i = 1; i <= rows.size(); i++) {

	        if (updatedCount == countToUpdate) {
	            break; // stop after updating required number
	        }

	        // Locate Status column (9th column) — adjust index if needed
	        WebElement statusElement = driver.findElement(
	                By.xpath("//table//tbody//tr[" + i + "]/td[11]"));

	        String statusText = statusElement.getText().trim();
	        System.out.println("Row " + i + " → Status: " + statusText);

	        // Check match
	        if (statusText.equalsIgnoreCase("Waiting For Acceptance")) {

	        	// Click More button
	        	WebElement moreButton = driver.findElement(
	        	        By.xpath("//table//tbody//tr[" + i + "]/td[last()]//button"));
	        	moreButton.click();
	        	Thread.sleep(800);

	        	// Locate dropdown menu inside the same row
	        	WebElement changeStatusOption = driver.findElement(
	        	        By.xpath("//a[contains(text(),'Change Status')]")
	        	);

	        	// Click Change Status
	        	changeStatusOption.click();

	            System.out.println("Status updated for row " + i);

	            updatedCount++;
	            Thread.sleep(2000);
	        }
	    }

	    System.out.println("Total updated LPOs: " + updatedCount);
	        
	}
	
	public void changeNewStatus() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	    // 1️⃣ Locate the <select> element
	    WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.name("selectedStatus")
	    ));

	    // 2️⃣ Use Select class to choose an option
	    Select select = new Select(dropdown);

	    // You can select by visible text OR value:
	    select.selectByVisibleText("Accepted But Unfulfilled");
	    // select.selectByValue("109");  // Same option if you want by value

	    // 3️⃣ Enter comments
	    WebElement comments = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//textarea[@name='sName']")));
	    
	    comments.sendKeys("Status updated automatically.");

	    // 4️⃣ Click Submit
	    WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Submit']")
	    ));
	    submitBtn.click();

	    // 5️⃣ Click Confirm
	    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Confirm']")
	    ));
	    confirmBtn.click();

	    System.out.println("Status updated successfully!");
	    
	    //click ok
	    WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='OK']")
        ));
        ok.click();
	}

	public void changeLpoAtHub(int rowIndex) throws InterruptedException {
	 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//table//tbody//tr[\" + rowIndex + \"]//button[contains(@class,'dropdown-toggle')]")).click();		
//		WebElement moreButton = driver.findElement(
//                By.xpath("//table//tbody//tr/td[last()]//button"));
//        moreButton.click();
		
		WebElement moreBtn = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//table//tbody//tr[" + rowIndex + "]//button[contains(@class,'dropdown-toggle')]")
		    ));
		    moreBtn.click();
        
		WebElement option = driver.findElement(By.xpath("//a[@class='dropdown-item'][4]"));
        option.click();
		
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

	    // 1️⃣ Locate the <select> element
	    WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.name("selectedStatus")
	    ));

	    // 2️⃣ Use Select class to choose an option
	    Select select = new Select(dropdown);

	    // You can select by visible text OR value:
	    Thread.sleep(1000);
	    select.selectByVisibleText("LPO At Hub");
	    // select.selectByValue("109");  // Same option if you want by value

	    // 3️⃣ Enter comments
	    WebElement comments = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//textarea[@name='sName']")));
	    
	    comments.sendKeys("Status updated automatically.");

	    // 4️⃣ Click Submit
	    WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Submit']")
	    ));
	    submitBtn.click();

	    // 5️⃣ Click Confirm
	    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Confirm']")));
	    confirmBtn.click();

	    System.out.println("Status updated successfully!");
	    
	  //click ok
	    Thread.sleep(2000);
	    WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
               By.xpath("//button[normalize-space()='OK']")
        ));
        ok.click();
	   
 //   WebElement ok1=  driver.findElement(By.xpath("//button[normalize-space()='OK']"));
 //       ok1.click();

	}
	
public void Lpofulfillment() throws InterruptedException {
	
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
//		driver.findElement(By.xpath("(//div//button[@class='actions-dropdown dropdown-toggle btn btn-success'])[1]")).click();
		
		WebElement moreButton = driver.findElement(
                By.xpath("//table//tbody//tr/td[last()]//button"));
        moreButton.click();
		
		WebElement option = driver.findElement(By.xpath("//a[@class='dropdown-item'][4]"));
        option.click();	    
}
	    // 1️⃣ Locate the <select> element 

public void enterInvoiceDetails() throws InterruptedException {
	    Random random = new Random();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

	    // ----------------------------
	    // 1️⃣ INVOICE NUMBER – random 10 digits
	    // ----------------------------
	    String invoiceNumber = String.valueOf(1000000000L + random.nextInt(900000000));
	    WebElement invoiceNo = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//input[@name='sup_inv_no']")));
	    invoiceNo.sendKeys(invoiceNumber);

	    // ----------------------------
	    // 2️⃣ INVOICE DATE – pick any date
	    // ----------------------------
	    WebElement invoiceDate = driver.findElement(By.xpath("//input[@name='supplier_inv_dt']"));
	    invoiceDate.click();

	    // pick random day 1–28
	    int day = random.nextInt(28) + 1;

	    WebElement pickedDay = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']")));
	    pickedDay.click();
        Thread.sleep(1000);

	    // ----------------------------
	    // 3️⃣ INVOICE VALUE – random 4 digits
	    // ----------------------------
	    String invoiceValue = String.valueOf(1000 + random.nextInt(9000));
	    WebElement invoiceVal = driver.findElement(By.xpath("//input[@name='sup_inv_val']"));
	    invoiceVal.sendKeys(invoiceValue);
	    Thread.sleep(2000);

	    // ----------------------------
	    // 4️⃣ RATING – random star click (1 to 5 stars)
	    // ----------------------------
	    int star = new Random().nextInt(5) + 1;

	    WebElement ratingStar = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("(//label[contains(@class,'css-ykqdxu')])[" + star + "]")
	    ));

	    ratingStar.click();
        Thread.sleep(1000);     

	    // ----------------------------
	    // 5️⃣ SUPPLIER UNIT PRICE – 2-digit random value
	    // ----------------------------
        Thread.sleep(2000);
	    String supplierPrice = String.valueOf(10 + random.nextInt(90));
	    WebElement supplierUnitPrice = driver.findElement(
	            By.xpath("(//input[@type='number'])[3]")
	    );
	    supplierUnitPrice.sendKeys(supplierPrice);
	    
	    try {
	        WebElement popupMsg = new WebDriverWait(driver, Duration.ofSeconds(3))
	                .until(ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//*[contains(text(),'Supplier Unit Price entered is higher')]")
	                ));

	        WebElement okBtn = new WebDriverWait(driver, Duration.ofSeconds(3))
	                .until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//button[text()='OK']")
	                ));

	        okBtn.click();
	        System.out.println("Supplier price popup handled successfully.");

	    } catch (Exception e) {
	        System.out.println("No Supplier Price popup displayed.");
	    }

    // 6️⃣ EXPIRY DATE – pick any FUTURE date from current month

//	        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
Thread.sleep(2000);
	        // Open date picker
	        WebElement expiryIcon = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                By.xpath("(//span[contains(@class,'e-date-icon')])[1]")
	            )
	        );
	        expiryIcon.click();
	        Thread.sleep(500);

	        // Today's date
	        LocalDate today = LocalDate.now();
	        int todayDay = today.getDayOfMonth();

	        // Find all date elements
	        List<WebElement> allDates = driver.findElements(
	            By.xpath("//span[contains(@class,'e-day')]")
	        );

	        WebElement futureDate = null;

	        // Try selecting future day in current month
	        for (WebElement dateElement : allDates) {
	            try {
	                String dayText = dateElement.getText().trim();
	                int day1 = Integer.parseInt(dayText);

	                if (day1 > todayDay) {   // ensures future date
	                    futureDate = dateElement;
	                    break;
	                }
	            } catch (Exception ignore) {}
	        }

	        if (futureDate != null) {
	            futureDate.click();  // pick future date in current month
	        } else {
	            // No future dates → next month
	            WebElement nextMonthButton = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                    By.xpath("//span[contains(@class,'e-next')]")
	                )
	            );
	            nextMonthButton.click();
	            Thread.sleep(500);

	            WebElement firstDayNextMonth = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                    By.xpath("(//span[contains(@class,'e-day')])[1]")
	                )
	            );
	            firstDayNextMonth.click();
	        }
	        Thread.sleep(1000);
	        
	    // 4️⃣ Click Submit	        
	    WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='SUBMIT']")
	    ));
	    submitBtn.click();

	    // 5️⃣ Click Confirm
	    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Confirm']")
	    ));
	    confirmBtn.click();
	    
	    //click ok
	    WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='OK']")
        ));
        ok.click();
}

  public void VeryfyAndApprove() {
	  
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	WebElement morebutton =	driver.findElement(By.xpath("(//div//button[@class='actions-dropdown dropdown-toggle btn btn-success'])[1]"));
		morebutton.click();
		
		WebElement option = driver.findElement(By.xpath("(//*[@class='dropdown-item'])[6]"));
      option.click();
		
	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
	    
	 // 4️⃣ Click Submit
	    WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='SUBMIT']")
	    ));
	    submitBtn.click();

	    // 5️⃣ Click Confirm
	    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Confirm']")
	    ));
	    confirmBtn.click();
	    
	    //click ok
	    WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='OK']")
        ));
        ok.click();
}
  
  public void updateOnlyWaitingForAcceptance(int maxLposToProcess) throws InterruptedException {

	    Thread.sleep(1500);

	    List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));

	    int processedCount = 0;

	    for (int i = 1; i <= rows.size(); i++) {

	        if (processedCount == maxLposToProcess) {
	            break;
	        }

	        WebElement statusCell = driver.findElement(
	                By.xpath("//table//tbody//tr[" + i + "]/td[11]")
	        );
	        String status = statusCell.getText().trim();

	        if (!status.equalsIgnoreCase("Waiting For Acceptance")) {
	            continue;
	        }

	        WebElement moreButton = driver.findElement(
	                By.xpath("//table//tbody//tr[" + i + "]/td[last()]//button")
	        );
	        moreButton.click();
	        Thread.sleep(800);

	        driver.findElement(By.xpath("//a[contains(text(),'Change Status')]")).click();

	        Thread.sleep(1200);

	        processLpoWorkflow();  // ⭐ Only ONE call

	        processedCount++;
	    }
	}

  public void processLpoWorkflow() throws InterruptedException {
	  Thread.sleep(1500);
	    changeNewStatus();
	    Thread.sleep(1500);
	    changeLpoAtHub(1);
	    Thread.sleep(1500);
	    Lpofulfillment();
	    Thread.sleep(1500);
	    enterInvoiceDetails();
	    Thread.sleep(1500);
	    VeryfyAndApprove();
	}

	
}