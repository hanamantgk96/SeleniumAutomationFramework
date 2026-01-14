package StepDefinition;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObject.CreateLpo;
import PageObject.LoginSnoc;
import Utils.Setuptest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Create_LpoStepDefinition {
	
	LocalDate fromDate;
    LocalDate toDate;
	
//	static WebDriver driver;
	public Setuptest testsetup;

	public Create_LpoStepDefinition(Setuptest testSetup) {
		this.testsetup = testSetup;
	}
	
	@Given("User navigates to Create LPO page")
	public void User_navigates_Create_LPO_page() throws InterruptedException {
		CreateLpo Lpo = testsetup.pageObjectManager.getCreateLpo();
		Thread.sleep(2000);
		Lpo.clickthreedot();
		Thread.sleep(2000);
		Lpo.clickprocurement();
		Thread.sleep(2000);
		Lpo.clickLpo();
		Thread.sleep(2000);
	    Lpo.clickwearhousefitler();
	}
	
	@When("User applies Delivery Hub filter {string}")
	public void user_applies_delivery_hub_filter(String hubValue) throws InterruptedException {
		CreateLpo Lpo = testsetup.pageObjectManager.getCreateLpo();
	    Thread.sleep(1000);
	    Lpo.selectDeliveryHub(hubValue);
	    Thread.sleep(1000);
	    Lpo.clickApplyFilters();
	    Thread.sleep(1000);
		Lpo.selectproducts();
		Thread.sleep(1000);
		Lpo.clicknextbutton();
		Lpo.clicknextbutton();
		Thread.sleep(2000);
	}

/*	@Then("Only orders from Delivery Hub {string} should be displayed")
	public void only_orders_from_delivery_hub_should_be_displayed(String expectedHub) {
		CreateLpo Lpo = testsetup.pageObjectManager.getCreateLpo();
		
//	    Lpo.validateDeliveryHubInGenerateLPO(expectedHub);
//	    Lpo.validateDeliveryHubResults(hub);
		
		String actualHub = Lpo.validateDeliveryHubInGenerateLPO();

//	    Assert.assertEquals(
//	        actualHub,
//	        expectedHub,
//	        "Delivery Hub mismatch on Generate LPO page!");	 
		
		 //  Print values for debugging / reports
	    System.out.println("Expected Delivery Hub : " + expectedHub);
	    System.out.println("Actual Delivery Hub   : " + actualHub);

	    // Optional: normalize before compare (recommended)
	    Assert.assertEquals(
	        actualHub.trim().replaceAll("\\s+", " "),
	        expectedHub.trim().replaceAll("\\s+", " "),
	        "Delivery Hub mismatch on Generate LPO page!"
	    );
	}*/
	
	@Then("Only orders from Delivery Hub {string} should be displayed")
	public void only_orders_from_delivery_hub_should_be_displayed(String expectedHub) {

	    CreateLpo lpo = testsetup.pageObjectManager.getCreateLpo();

	    // Step 1: Validate Delivery Hub on Create LPO page
	    String actualHub = lpo.getSelectedDeliveryHub();

	    String expectedNormalized = expectedHub.trim().replaceAll("\\s+", " ");
	    String actualNormalized   = actualHub.trim().replaceAll("\\s+", " ");

	    System.out.println("Expected Delivery Hub : " + expectedNormalized);
	    System.out.println("Actual Delivery Hub   : " + actualNormalized);

	    Assert.assertEquals(
	        actualNormalized,
	        expectedNormalized,
	        "Delivery Hub mismatch on Create LPO page");
	        System.out.println("delivery hub matched"
	    );

	    // Step 2: Product availability check
	    if (!lpo.areProductsAvailable()) {
	        System.out.println(
	            "INFO: No records to display for Delivery Hub: " + actualNormalized
	        );
	        return; //  STOP HERE – DO NOT CLICK NEXT
	    }

	    // Step 3: Navigate forward only if products exist

	    lpo.clickNextSafely(); // Review page
	    lpo.clickNextSafely(); // Generate LPO page

	    // Step 4 (Optional but recommended): Re-validate hub
	    String hubOnGeneratePage = lpo.getSelectedDeliveryHub();
	    Assert.assertEquals(
	        hubOnGeneratePage.trim().replaceAll("\\s+", " "),
	        expectedNormalized,
	        "Delivery Hub mismatch on Generate LPO page"
	    );

	    System.out.println("PASSED – Delivery Hub validated successfully");
	}

	
	@Then("Only products of selected seller should be displayed after applying filters")
	public void validate_products_after_applying_filters() throws InterruptedException {

	    CreateLpo Lpo = testsetup.pageObjectManager.getCreateLpo();
	    
//	    Lpo.clickwearhousefitler();

	    String selectedSeller = Lpo.selectRandomSellerAndApplyFilter();

	    Lpo.validateLpoResultsForSeller(selectedSeller);
	}
	
	@Then("only FOC orders should be displayed with zero order margin")
	public void validate_only_foc_orders() {

	    CreateLpo lpo = testsetup.pageObjectManager.getCreateLpo();

	    lpo.applyOnlyFocFilter();

	    boolean isValid = lpo.validateFocOrdersMarginZero();

	    if (isValid) {
	        System.out.println("PASSED - FOC Orders validation successful");
	    } else {
	        System.out.println("FAILED - Non-zero margin found in FOC Orders");
	    }

	    Assert.assertTrue(
	        isValid,
	        "FOC Orders validation failed: Order Margin is not zero"
	    );
	}
	
	//existing seller
	
	@Given("User navigates to Create LPO page1")
	public void User_navigates_Create_LPO_page1() throws InterruptedException {
		CreateLpo Lpo = testsetup.pageObjectManager.getCreateLpo();
		Thread.sleep(2000);
		Lpo.clickthreedot();
		Thread.sleep(2000);
		Lpo.clickprocurement();
		Thread.sleep(2000);
		Lpo.clickLpo();
	}
	
	@Then("User filters LPOs by seller name from table")
	public void user_filters_lpos_by_seller_name_from_table() throws InterruptedException {

	    CreateLpo lpo = testsetup.pageObjectManager.getCreateLpo();

	    // Step 1: Fetch seller name dynamically
	    String sellerName = lpo.fetchRandomSellerNameFromTable();

	    // Step 2: Search using seller filter
	    lpo.searchBySellerName(sellerName);
	    Thread.sleep(2000);
//	    lpo.clickwearhousefitler();

	    // Step 3: Validate results
	    lpo.validateSellerFilterResults(sellerName);
	}

}
