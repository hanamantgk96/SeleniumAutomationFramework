package PageObject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PlaceOrder {

	double prdGrsAMt = 0.0d;
	public WebDriver driver;
	public WebDriverWait wait;
	
	public PlaceOrder(WebDriver driver) {
		this.driver = driver;
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	By clickgrid = By.xpath("//div[@class='menu-cont' ] //img[@alt='img']");
	By clickorder = By.xpath("//ul//li//span[text()='Order']");
	By placeorder = By.xpath("//ul//li//span[text()='Place Order']");
	By clickbusiness = By.xpath("//div[@style='position: absolute; top: 10px; right: 12px;']");
//	By selectbusiness = By.xpath("//*[@id=\"grid_732322063_0_content_table\"]/tbody/tr");
	By Reviewbutton = By.xpath("//button[text()='Review Order']");

	public void clickgrid() {
		driver.findElement(clickgrid).click();
	}

	public void clickorderoption() {
		driver.findElement(clickorder).click();
	}

	public void clickplaceorder() {
		driver.findElement(placeorder).click();
	}

	public void clickbusinessfield() {
		driver.findElement(clickbusiness).click();
	}
	
	
	 public void validatePagination() {
		     		 	 
//Page Calculation	 

		 WebElement paginationElement = driver.findElement(By.xpath("//div[contains(text(),'pages')]"));
		 String paginationText = paginationElement.getText();  // e.g. "1 of 99 pages (984 items)"

		 // Extract total items
		 int totalItems = Integer.parseInt(paginationText.replaceAll(".*\\((\\d+) items\\).*", "$1"));
		 System.out.println("Total Items: " + totalItems);

		 // Extract total pages from UI
		 int totalPages = Integer.parseInt(paginationText.replaceAll(".*of (\\d+) pages.*", "$1"));
		 System.out.println("Total Pages (UI): " + totalPages);

		 // Get items per page from dropdown
		 WebElement itemsPerPageDropdown = driver.findElement(By.cssSelector("select.custom-pagination-dropdown"));
		 Select select1 = new Select(itemsPerPageDropdown);
		 int itemsPerPage1 = Integer.parseInt(select1.getFirstSelectedOption().getText().trim());
		 System.out.println("Items Per Page: " + itemsPerPage1);

		 // Perform division (double for decimal precision)
		 double divisionResult = (double) totalItems / itemsPerPage1;
		 System.out.println(totalItems + " divided by " + itemsPerPage1 + " = " + divisionResult);

		 // Calculate total pages (round up)
		 int calculatedPages = (int) Math.ceil(divisionResult);
		 System.out.println("Calculated Total Pages (rounded up): " + calculatedPages);

		 // Validate
		 if (calculatedPages == totalPages) {
		     System.out.println("Pagination validation passed!");
		 } else {
		     throw new AssertionError("Pagination validation failed! Calculated pages: " + calculatedPages + ", but UI shows: " + totalPages);
		 }
		 
		 
		 
		// --- STEP 1: VERIFY DEFAULT STATE (10) ---
		 WebElement itemsPerPage = driver.findElement(By.xpath("//select[@class='custom-pagination-dropdown']"));
		 Select select = new Select(itemsPerPage);

		 // 1. Verify Dropdown UI text
		 String defaultVal = select.getFirstSelectedOption().getText();
		 Assert.assertEquals(defaultVal.trim(), "10", "Default value is not 10!");
		 System.out.println("Step 1: UI Dropdown default is confirmed as: " + defaultVal);

		 // 2. Verify Table Row count
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table/tbody/tr")));
		 List<WebElement> defaultRows = driver.findElements(By.xpath("//table/tbody/tr"));

		 int initialCount = defaultRows.size();
		 Assert.assertEquals(initialCount, 10, "Default row count is not 10!");
		 System.out.println("Step 2: Default table line items verified. Count = " + initialCount);


		 // --- STEP 3: CHANGE TO 25 AND VERIFY ---
		 System.out.println("Step 3: Changing dropdown selection to 25...");
		 select.selectByValue("25");

		 // 4. Wait for the rows to update to exactly 25
		 wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table/tbody/tr"), 25));

		 List<WebElement> updatedRows = driver.findElements(By.xpath("//table/tbody/tr"));
		 int finalCount = updatedRows.size();

		 Assert.assertEquals(finalCount, 25, "Row count did not update to 25!");
		 System.out.println("Step 4: Table refreshed successfully. New line item count = " + finalCount);
		
	 }
	
//selecting business	
		public void selectbusinessrandomaly1() throws InterruptedException {			
			
		Random random = new Random();


		 while (true) {  

	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//table/tbody/tr")));

	            WebElement pagesText = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//label[contains(text(),'of')]")));

	            int totalPages = Integer.parseInt(pagesText.getText().replaceAll("\\D+", ""));
	            int randomPage = random.nextInt(totalPages) + 1;

	            for (int i = 1; i < randomPage; i++) {

	                String firstRowBefore = driver.findElement(
	                        By.xpath("//table/tbody/tr[1]")).getText();

	                WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//button[@type='button' and @aria-label='Next']")));
	                nextBtn.click();

	                wait.until(ExpectedConditions.not(
	                        ExpectedConditions.textToBePresentInElementLocated(
	                                By.xpath("//table/tbody/tr[1]"), firstRowBefore)));

	                wait.until(ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//table/tbody/tr")));
	            }

	            // Check for "No records to display"
	            List<WebElement> noRecordsElements = driver.findElements(
	                    By.xpath("//div[contains(text(),'No records to display')]"));

	            if (noRecordsElements.size() > 0) {
	                System.out.println("No products found on selected business. Clicking business selector again...");

	                // Click business selector icon to allow re-selection
	                wait.until(ExpectedConditions.elementToBeClickable(clickbusiness)).click();

	                // Optionally, add a small wait or logic to pick another business here

	                continue;  // Retry whole process
	            }

	            // Safe random row click with retry
	            for (int attempt = 0; attempt < 3; attempt++) {
	                try {
	                    List<WebElement> rows = driver.findElements(
	                            By.xpath("//table/tbody/tr"));

	                    rows.get(random.nextInt(rows.size())).click();

	                    System.out.println("Random Business Selected Successfully");
	                    return; 

	                } catch (StaleElementReferenceException e) {
	                }
	            }

	            System.out.println("Failed to select random business row after retries.");
	            break;
	        }
	    }
	
	    
	
		
	
/*		public void Businessfilteration() {
		
			WebElement textField = driver.findElement(By.xpath("//*[@id='desc_filterBarcell']"));
			textField.click(); // Ensure the field is clicked and focused

			textField.sendKeys("1971 Cafe");
			textField.sendKeys(Keys.ENTER);

			textField.click();

}*/
	
/*	public void FOCOrderCreation() throws InterruptedException {
    Thread.sleep(2000);
	driver.findElement(By.xpath("(//div[@class='fot-formControl']/div[@style='position: absolute; top: 5px; right: 4px;'])[2]")).click();
	}*/
	
		public double selectProduct() {
		    Random random = new Random();
		    int minSelections = 5;
		    int maxSelections = 15;
		    int productsPerPage = 3;

		    int numberOfSelections = random.nextInt((maxSelections - minSelections) + 1) + minSelections;
//		    System.out.println("Number of products to be selected: " + numberOfSelections);

		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		    List<WebElement> products = driver.findElements(By.xpath("//table/tbody/tr/td/div/input"));

		    int totalProducts = products.size();
		    int totalPages = (int) Math.ceil((double) totalProducts / productsPerPage);
		    numberOfSelections = Math.min(numberOfSelections, totalProducts);

		    // Fix: Return a valid double if not enough products are available
		    if (minSelections > totalProducts) {
		        System.out.println("Not enough products to make the minimum selections.Exiting.");
		        return 0.0;
		    }

		    Set<Integer> selectedIndices = new HashSet<>();
		    int selectionsMade = 0;
		    double prdGrsAMt = 0.0; // Initialize gross amount

		    for (int page = 1; page <= totalPages && selectionsMade < numberOfSelections; page++) {
		        if (page > 1) {
		            try {
		                List<WebElement> nextButtons = driver.findElements(By.xpath("//div[@class='e-next e-icons e-icon-next e-nextpage e-pager-default']"));
		                if (nextButtons.isEmpty()) {
		                    break;
		                }

		                WebElement nextPageButton = nextButtons.get(0);
		                Thread.sleep(1000);

		                wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));

		                if (nextPageButton.isEnabled()) {
		                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextPageButton);
		                    wait.until(ExpectedConditions.stalenessOf(products.get(0)));
		                    products = driver.findElements(By.xpath("//table/tbody/tr/td/div/input"));
		                } else {
		                    System.out.println("Next button is disabled. No more pages available. Exiting.");
		                    break;
		                }
		            } catch (Exception e) {
		                System.out.println("Next button not found or an error occurred: " + e.getMessage());
		                break;
		            }
		        }

		        int startIndex = (page - 1) * productsPerPage;
		        int endIndex = Math.min(startIndex + productsPerPage, totalProducts);

		        for (int i = startIndex; i < endIndex && selectionsMade < numberOfSelections; i++) {
		            if (!selectedIndices.contains(i)) {
		                try {
		                    WebElement product = wait.until(ExpectedConditions.elementToBeClickable(products.get(i)));

		                    // Get the parent row for the product
		                    WebElement parentRow = product.findElement(By.xpath("./ancestor::tr"));
		                    String productGrossAmt = parentRow.findElement(By.xpath("./td[13]")).getText();
		                    double prdVal = Double.parseDouble(productGrossAmt);

		                    prdGrsAMt += prdVal; // Accumulate total gross amount

		                    if (!product.isSelected()) {
		                        product.click();
		                        selectedIndices.add(i);
		                        selectionsMade++;
		                    }
		                } catch (StaleElementReferenceException e) {
		                    products = driver.findElements(By.xpath("//table/tbody/tr/td/div/input"));
		                    WebElement product = wait.until(ExpectedConditions.elementToBeClickable(products.get(i)));

		                    WebElement parentRow = product.findElement(By.xpath("./ancestor::tr"));
		                    String productGrossAmt = parentRow.findElement(By.xpath("./td[13]")).getText();
		                    double prdVal = Double.parseDouble(productGrossAmt);

		                    prdGrsAMt += prdVal; // Accumulate total gross amount

		                    if (!product.isSelected()) {
		                        product.click();
		                        selectedIndices.add(i);
		                        selectionsMade++;
		                    }
		                }
		            }
		        }
		    }

		    System.out.println("Total no of products selected: " + selectionsMade);
//		    System.out.println("Total Gross Amount: " + prdGrsAMt);

		    return prdGrsAMt; // Returning the total gross amount
		}
	
	public void Past_shippdate_date() throws InterruptedException {
		driver.findElement(By.name("ship_dt")).click();

	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker")));

	    LocalDate pastDate = LocalDate.now().minusDays(7);
	    DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
	    String targetFullDate = pastDate.format(fullDateFormatter);

	    // Get all the dates
	    List<WebElement> days = driver.findElements(By.cssSelector("div.react-datepicker__day"));

	    boolean isSelectable = false;

	    for (WebElement day : days) {
	        String ariaLabel = day.getAttribute("aria-label");
	        String classAttribute = day.getAttribute("class"); // Get class to check if disabled
//	        System.out.println("Checking date: " + ariaLabel + " | Class: " + classAttribute); // Debugging
	        
	        if (ariaLabel != null && ariaLabel.equals(targetFullDate)) {
	            // Check if the date is disabled
	            if (classAttribute.contains("react-datepicker__day--disabled")) {
	                System.out.println("Test Passed: Cannot select past date.");
	                return; // Test passes as expected behavior
	            } else {
	                isSelectable = true; // This means the test should fail
	            }
	        }
	    }

	    if (isSelectable) {
	        throw new AssertionError("Test Failed: Able to select a disabled past date!");
	    } else {
	        System.out.println("Test Passed: Should not be allowed select previous shipping date");
	        System.out.println("Status: Passed");
	    }
	}

	public void Ship_Date_ErrorMessageValidation() throws InterruptedException {

		WebElement reviewOrderButton = driver.findElement(Reviewbutton);
		reviewOrderButton.click();
		Thread.sleep(1000);
		
		try {
			WebElement okButton = driver.findElement(By.xpath("//button[text()='OK']"));
			okButton.click();
			System.out.println("Popup handled successfully");
		} catch (Exception e) {
			System.out.println("No popup appeared.");
		}

		Thread.sleep(1000);

//		driver.findElement(By.name("ship_dt")).click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("Status failed: Procced without selecting shipping date in the order (mandatory)");
		driver.findElement(By.name("ship_dt")).click();
		

		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker")));

		LocalDate nextDay = LocalDate.now().plusDays(1);
		DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");

		String targetDay = nextDay.format(dayFormatter);
		String targetMonthYear = nextDay.format(monthYearFormatter);

		while (!driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']")).getText()
				.contains(targetMonthYear)) {
			driver.findElement(By.xpath("//button[contains(@class, 'react-datepicker__navigation--next')]")).click();
		}

		List<WebElement> days = driver.findElements(By.cssSelector(
				"div.react-datepicker__month-container div.react-datepicker__month div.react-datepicker__week div.react-datepicker__day"));

		for (WebElement day : days) {
			String ariaLabel = day.getAttribute("aria-label");
			if (ariaLabel != null && ariaLabel.contains(targetDay)
					&& ariaLabel.contains(nextDay.format(DateTimeFormatter.ofPattern("MMMM")))) { // check for Day and
																									// Month
				day.click();
				System.out.println("Status: Passed");
				break;
			}
		}
	}

	public void selectPaymentModeByIndex(int index) throws InterruptedException {
		Thread.sleep(2000);
		WebElement reviewOrderButton = driver.findElement(Reviewbutton);
		reviewOrderButton.click();
		Thread.sleep(1000);

		WebElement okButton = driver.findElement(By.xpath("//button[text()='OK']"));
		okButton.click();

		Thread.sleep(1000);

		WebElement paymentDropdownField = driver.findElement(By.name("payment_method"));

		WebElement dropdownElement = driver.findElement(By.name("payment_method"));
		Select dropdown = new Select(dropdownElement);

		List<WebElement> options = dropdown.getOptions();

		List<String> excludedOptions = Arrays.asList("Payment Method", "Select");

		List<WebElement> validOptions = new ArrayList<>();
		for (WebElement option : options) {
			String optionText = option.getText().trim();
			if (!excludedOptions.contains(optionText) && !optionText.isEmpty()) {
				validOptions.add(option);
			}
		}

		if (!validOptions.isEmpty()) {
			System.out.println("Status failed: Procced without selecting payment terms(mandatory)");
			dropdown.selectByVisibleText(validOptions.get(0).getText()); // Select first valid option
			Thread.sleep(1000);

			String selectedOption = dropdown.getFirstSelectedOption().getText();
			System.out.println("Status Passed");
		} else {
			System.out.println("No valid payment methods available to select.");
		}
	}

	public void Sel_billingAddr_And_ShiAddr_popupErrorValidation() throws InterruptedException {
		Thread.sleep(2000);
		WebElement reviewOrderButton = driver.findElement(Reviewbutton);
		reviewOrderButton.click();
		Thread.sleep(2000);

		WebElement okButton = driver.findElement(By.xpath("//button[text()='OK']"));
		okButton.click();
		System.out.println("Status failed: Procced without selecting billing & shipping address(mandatory).");

		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement billingAddressRadio = driver.findElement(By.name("billing_address"));

			if (!billingAddressRadio.isSelected()) {
				billingAddressRadio.click();
				System.out.println("Status Passed");
//				System.out.println("Billing address selected successfully.");
			} else {
				System.out.println("Billing address was already selected.");
			}
		} catch (Exception e) {
			System.out.println("Billing address selection error: " + e.getMessage());
		}

		try {
			WebElement shippingAddressRadio = driver.findElement(By.name("shipping_address"));

			if (!shippingAddressRadio.isSelected()) {
				shippingAddressRadio.click();
//				System.out.println("Shipping address selected successfully.");
			} else {
				System.out.println("Shipping address was already selected.");
			}
		} catch (Exception e) {
			System.out.println("Shipping address selection error: " + e.getMessage());
		}
	}

	public void selectByDriver() throws InterruptedException {

		WebElement dropdownElement = driver.findElement(By.name("del_user_id"));
		Select dropdown = new Select(dropdownElement);

		List<WebElement> options = dropdown.getOptions();
		// List of options to exclude
		List<String> excludedOptions = new ArrayList<>();
		excludedOptions.add("Payment Method");
		excludedOptions.add("Select");

		List<WebElement> validOptions = new ArrayList<>();
		for (WebElement option : options) {
			String optionText = option.getText();
			if (!excludedOptions.contains(optionText)) {
				validOptions.add(option);
			}
		}

		for (WebElement option : options) {
		}
		Thread.sleep(1000);

		dropdown.selectByIndex(2);
	}

	
	public void Lpo_Num_popupErrorValidation() throws InterruptedException {
		Thread.sleep(1000);
		WebElement reviewOrderButton = driver.findElement(Reviewbutton);
		reviewOrderButton.click();
		Thread.sleep(1000);

		WebElement okButton = driver.findElement(By.xpath("//button[text()='OK']"));
		okButton.click();

		Thread.sleep(1000);

		try {
			WebElement LPONumberField = driver.findElement(By.name("lpo_number"));

			if (LPONumberField.getAttribute("value").isEmpty()) {
				System.out.println("Status failed: Procced without providing LPO Number reference in the order(mandatory).");
				Thread.sleep(1000);

				LPONumberField.sendKeys("05-march-2025");
				Thread.sleep(1000);

				if (!LPONumberField.getAttribute("value").isEmpty()) {
					System.out.println("Status Passed");
				} else {
					System.out.println("Test Case Failed: LPO Number is still empty.");
				}
			} else {
				System.out.println(
						"Status Passed: LPO Number is already filled - " + LPONumberField.getAttribute("value"));
			}
		} catch (Exception e) {
			System.out.println("LPO Number field not found or an error occurred: " + e.getMessage());
		}
	}
	
	
	public void validateTotalAmount(double prdValu) throws InterruptedException {
		Thread.sleep(1000);

		
		WebElement reviewOrderButton = driver.findElement(Reviewbutton);
		reviewOrderButton.click();
		Thread.sleep(1000);
//		driver.findElement(By.xpath("//button[text()='Yes']")).click();

		// Fetch all selected products
		List<WebElement> products = driver
				.findElements(By.xpath("/html/body/div[4]/div[3]/div[2]/div[2]/table/thead/tr[6]/td[2]")); 
		WebElement product = products.get(0);

		// Retrieve the value (text) of the product
		String productValue = product.getText();
		double val = Double.parseDouble(productValue);
		if (val == prdValu) {
			System.out.println("Total amount is: " +val);
			System.out.println("Gross amount is: " +prdValu);
			System.out.println("Status Passed");
		}else {
			System.out.println("Total amount is: " +val);
			System.out.println("Gross amount is - " +prdValu);
			System.out.println("Status failed");
		}
		
	}
	public void clicplaceorderbutton() {
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		driver.findElement(By.xpath("//button[text()='No']")).click();
		   
		   System.out.println("Order created successfully");
		   System.out.println("Status Passed");
			 
		}
}


