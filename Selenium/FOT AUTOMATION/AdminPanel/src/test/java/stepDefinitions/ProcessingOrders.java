package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObjects.CompilationPage;
import PageObjects.PageObjectManager;
import PageObjects.ProcessingOrdersPage;
import PageObjects.StockSummaryPage;
import context.OrderData;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.BaseTest;

public class ProcessingOrders {
	public WebDriver driver;
	public CompilationPage compilationpage;
	public PageObjectManager pageObjectManager;
	public ProcessingOrdersPage processingOrdersPage;
	private ScenarioContext scenarioContext;
	public StockSummaryPage stockSummaryPage;

	public ProcessingOrders(ScenarioContext scenarioContext) throws IOException {
		this.driver = BaseTest.getDriver();
		this.scenarioContext = scenarioContext;
		this.pageObjectManager = new PageObjectManager(driver);
		this.compilationpage = pageObjectManager.getCompilationPage();
		this.processingOrdersPage = pageObjectManager.getproOrdersPage();
		this.stockSummaryPage = pageObjectManager.getStockSummaryPage();

	}

	@Given("user creates processing order with below data")
	public void user_creates_processing_order_with_below_data(io.cucumber.datatable.DataTable dataTable)
			throws InterruptedException {

		scenarioContext.populateOrderData(dataTable);
		OrderData data = scenarioContext.getOrderData();

		processingOrdersPage.clickOrderProcessingMenu();
		processingOrdersPage.clickProcessingOrdersMenu();
		processingOrdersPage.clickCreateNewButton();

		processingOrdersPage.selectTransformationType(data.getTransformationType());

		processingOrdersPage.selectHub(data.getHub());
		System.out.println(processingOrdersPage.getSelectedHub());

		processingOrdersPage.selectSubCategory(data.getSubCategory());

		processingOrdersPage.selectProductFromGrid(data.getProduct());

		if (data.getTransFormedProduct() != null) {

			processingOrdersPage.selectTransformedProductFromGrid2(data.getTransFormedProduct());
		}

		processingOrdersPage.selectSeller(data.getSeller());
		processingOrdersPage.selectUom(data.getToUom());

		processingOrdersPage.clearAndEnterRequestedQuantity(data.getRequestedQty());

		processingOrdersPage.clickSendForProcess();
		processingOrdersPage.clickConfirmButton();

		Assert.assertEquals(processingOrdersPage.getSuccessPopupText(), "Processing Order created successfully!");

		processingOrdersPage.clickOkButton();

	}

	@Then("the processing order ID should be captured")
	public void the_processing_order_id_should_be_captured() {
		String orderId = processingOrdersPage.getProcessingOrderId();
		scenarioContext.setProcessingOrderId(orderId);
		processingOrdersPage.getProcessingOrderId();
		System.out.println("Processing Order ID creatred: " + processingOrdersPage.getProcessingOrderId());

	}

	@When("user searches the order using created order id")
	public void user_searches_the_order_using_created_order_id() {
		processingOrdersPage.clickFilterForProcessingOrderId();

		processingOrdersPage.enterProcessingOrderIdInFilter(scenarioContext.getProcessingOrderId());
		processingOrdersPage.clickApplyButton();

	}

	@Then("processing order should be created in Draft status")
	public void processing_order_should_be_created_in_Draft_status() {
		processingOrdersPage.getOrderStatus();
		System.out.println("Processing Order Status: " + processingOrdersPage.getOrderStatus());
		Assert.assertEquals(processingOrdersPage.getOrderStatus(), "Draft");
		processingOrdersPage.clickMoreButton();
		processingOrdersPage.clickEditOption();

	}

	@When("user updates the order status from Draft to Created")
	public void user_updates_the_order_status_from_draft_to_created() {
//		processingOrdersPage.clickMoreButton();
//		processingOrdersPage.clickEditOption();

		processingOrdersPage.clickUpdateButton();
		processingOrdersPage.clickConfirmButton();
		Assert.assertEquals(processingOrdersPage.getSuccessPopupText(), "Processing Order updated successfully!");
		processingOrdersPage.clickOkButton();
	}

	@Then("order status should be Created")
	public void order_status_should_be_created() {

		Assert.assertEquals(processingOrdersPage.getOrderStatus(), "Order Created");
	}

	@When("user processes the order with below data")
	public void user_processes_the_order_with_below_data(io.cucumber.datatable.DataTable dataTable) {
		scenarioContext.populateOrderData(dataTable);

		OrderData orderData = scenarioContext.getOrderData();

		processingOrdersPage.clickMoreButton();
		processingOrdersPage.clickProcessOrderOption();
		if (orderData.getSourceSeller() != null) {
			processingOrdersPage.selectSourceSeller(orderData.getSourceSeller());
		}
		if (orderData.getFromUom() != null) {
			processingOrdersPage.selectPrimeryUom(orderData.getFromUom());
		}
		processingOrdersPage.enterProcessedQuantity(orderData.getProcessedQty());
		if (orderData.getActualYield() != null) {
			processingOrdersPage.enterActualYield(orderData.getActualYield());
			String wastageYield = processingOrdersPage.getWasteActualYieldInUnits();
			scenarioContext.setWastageYield(wastageYield);
			System.out.println("Wastage Actual Yield in Units: " + wastageYield);

		}

		processingOrdersPage.clickTransformButton();
		processingOrdersPage.clickConfirmButton();

		Assert.assertEquals(processingOrdersPage.getSuccessPopupText(), "Order is transformed successfully!");

		processingOrdersPage.clickOkButton();
		processingOrdersPage.clickFilterForProcessingOrderId();
		processingOrdersPage.pressEnterKey();

	}

	@Then("order status should be Completed")
	public void order_status_should_be_completed() {
		Assert.assertEquals(processingOrdersPage.getOrderStatus(), "Completed");
		System.out.println("Processing Order Status after processing: " + processingOrdersPage.getOrderStatus());

	}

	@When("user navigates to stock summary page")
	public void user_navigates_to_stock_summary_page() {
		processingOrdersPage.clickMainMenu();
		stockSummaryPage.clickStockMenu();
		stockSummaryPage.clickStockSummaryMenu();

	}

//	@Then("verify the stock deduction for the variant used in the transformation")
//	public void verify_the_stock_dedution_for_the_variant_which_was_used_in_transformation() {
//		OrderData orderData = scenarioContext.getOrderData();
//
//		stockSummaryPage.clickProductNameFilterIcon();
//		stockSummaryPage.enterProductNameInFilterInput(orderData.getProduct());
//		processingOrdersPage.clickApplyButton();
//
//		stockSummaryPage.clickUomFilterIcon();
//		if (orderData.getFromUom() != null) {
//			stockSummaryPage.enterUomInFilterInput(orderData.getFromUom());
//		} else {
//			stockSummaryPage.enterUomInFilterInput(orderData.getToUom());
//		}
//
//		processingOrdersPage.pressEnterKey();
//		stockSummaryPage.selectproduct(orderData.getProduct());
//		if (orderData.getSourceSeller() != null) {
//			stockSummaryPage.selectSeller(orderData.getSourceSeller());
//		} else {
//			stockSummaryPage.selectSeller(orderData.getSeller());
//		}
//	
//		stockSummaryPage.selectBatchDetails("Batch Details");
//		stockSummaryPage.clickReferenceFilterIcon();
//		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
//		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
//		processingOrdersPage.pressEnterKey();
//		List<String> actions = stockSummaryPage.getAllPerformedActions();
//
//		for (String action : actions) {
//			System.out.println("Performed Action: " + action);
//			Assert.assertTrue(action.equalsIgnoreCase("Stock-transform-Dispatched"),
//					"Unexpected performed action: " + action);
//		}
//		String processedQty = orderData.getProcessedQty();
//		System.out.println("Processed Quantity for Stock Verification: " + processedQty);
//
//		double actualTotalQty = stockSummaryPage.getQuatity();
//		double expectedQty = Double.parseDouble(processedQty);
//
//		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
//		driver.navigate().refresh();
//		
//	}
	@Then("search the product and UOM for the variant used in the transformation")
	public void search_the_product_and_uom_for_the_variant_used_in_the_transformation() {
		OrderData orderData = scenarioContext.getOrderData();

		stockSummaryPage.clickProductNameFilterIcon();
		stockSummaryPage.enterProductNameInFilterInput(orderData.getProduct());
		processingOrdersPage.clickApplyButton();

		stockSummaryPage.clickUomFilterIcon();
		if (orderData.getFromUom() != null) {
			stockSummaryPage.enterUomInFilterInput(orderData.getFromUom());
		} else {
			stockSummaryPage.enterUomInFilterInput(orderData.getToUom());
		}

		processingOrdersPage.pressEnterKey();
		stockSummaryPage.selectproduct(orderData.getProduct());
	    
	}
	@Then("select the seller from which the variant was picked for transformation")
	public void select_the_seller_from_which_the_variant_was_picked_for_transformation() {
		OrderData orderData = scenarioContext.getOrderData();
		if (orderData.getSourceSeller() != null) {
			stockSummaryPage.selectSeller(orderData.getSourceSeller());
		} else {
			stockSummaryPage.selectSeller(orderData.getSeller());
		}
	}
	@Then("select the batch details and verify the deducted quantity and the performed action {string}")
	public void select_the_batch_details_and_verify_the_deducted_quantity_and_the_performed_action(String string) {
		OrderData orderData = scenarioContext.getOrderData();
		stockSummaryPage.selectBatchDetails("Batch Details");
		stockSummaryPage.clickReferenceFilterIcon();
		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
		processingOrdersPage.pressEnterKey();
		List<String> actions = stockSummaryPage.getAllPerformedActions();

		for (String action : actions) {
			System.out.println("Performed Action: " + action);
			Assert.assertTrue(action.equalsIgnoreCase(string),
					"Unexpected performed action: " + action);
		}
		String processedQty = orderData.getProcessedQty();
		System.out.println("Processed Quantity for Stock Verification: " + processedQty);

		double actualTotalQty = stockSummaryPage.getQuatity();
		double expectedQty = Double.parseDouble(processedQty);

		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
		driver.navigate().refresh();
	}
	@Then("search the product and UOM for the processed variant")
	public void search_the_product_and_uom_for_the_processed_variant() {
		OrderData orderData = scenarioContext.getOrderData();

		stockSummaryPage.clickProductNameFilterIcon();
		if (orderData.getTransFormedProduct() != null) {
			stockSummaryPage.enterProductNameInFilterInput(orderData.getTransFormedProduct());
		} else {
			stockSummaryPage.enterProductNameInFilterInput(orderData.getProduct());
		}

		processingOrdersPage.clickApplyButton();

		stockSummaryPage.clickUomFilterIcon();
//		if (scenarioContext.getChangedUom() != null) {
//			stockSummaryPage.enterUomInFilterInput(scenarioContext.getChangedUom());
//		}

	    if (orderData.getToUom() != null) {
			stockSummaryPage.enterUomInFilterInput(orderData.getToUom());
		} else {
			stockSummaryPage.enterUomInFilterInput(orderData.getFromUom());
			
		}

		processingOrdersPage.pressEnterKey();
		if (orderData.getTransFormedProduct() != null) {
			stockSummaryPage.selectproduct(orderData.getTransFormedProduct());
		} else {
			stockSummaryPage.selectproduct(orderData.getProduct());
		}
	}
	@Then("select the seller to which the processed variant is credited")
	public void select_the_seller_to_which_the_processed_variant_is_credited() {
		OrderData orderData = scenarioContext.getOrderData();
		stockSummaryPage.selectSeller(orderData.getSeller());
	}
	@Then("select the seller used while converting the order from Draft to Created.")
	public void select_the_seller_used_while_converting_the_order_from_draft_to_created() {
	    stockSummaryPage.selectSeller(scenarioContext.getChangedSeller());
	}
	@Then("select the batch details and verify the added quantity and the performed action {string}")
	public void select_the_batch_details_and_verify_the_added_quantity_and_the_performed_action(String string) {
		OrderData orderData = scenarioContext.getOrderData();
		stockSummaryPage.selectBatchDetails("Batch Details");
		stockSummaryPage.clickReferenceFilterIcon();
		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
		processingOrdersPage.pressEnterKey();
		List<String> actions = stockSummaryPage.getAllPerformedActions();

		for (String action : actions) {
			System.out.println("Performed Action: " + action);
			Assert.assertTrue(action.equalsIgnoreCase(string), "Unexpected performed action: " + action);
		} 
		String ActualQtyStockIn = orderData.getActualYield()!=null?orderData.getActualYield():orderData.getRequestedQty();
		

		double actualTotalQty = stockSummaryPage.getQuatity();
		double expectedQty = Double.parseDouble(ActualQtyStockIn);

		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
}
	@Then("select the batch details and verify the added quantity for wastage and the performed action {string}")
	public void select_the_batch_details_and_verify_the_added_quantity_for_wastage_and_the_performed_action(String string) {
		stockSummaryPage.selectBatchDetails("Batch Details");
		stockSummaryPage.clickReferenceFilterIcon();
		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
		processingOrdersPage.pressEnterKey();
		List<String> actions = stockSummaryPage.getAllPerformedActions();

		for (String action : actions) {
			System.out.println("Performed Action: " + action);
			Assert.assertTrue(action.equalsIgnoreCase(string), "Unexpected performed action: " + action);
		} 
		String ActualQtyStockIn = scenarioContext.getWastageYield();
		System.out.println("Wastage Quantity for Stock Verification: " + ActualQtyStockIn);
		

		double actualTotalQty = stockSummaryPage.getQuatity();
		double expectedQty = Double.parseDouble(ActualQtyStockIn);

		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
}
	    
	
	


//	@Then("verify the stock entry for the processed variant")
//	public void verify_the_stock_entry_for_the_processed_variant() {
//		OrderData orderData = scenarioContext.getOrderData();
//
//		stockSummaryPage.clickProductNameFilterIcon();
//		if (orderData.getTransFormedProduct() != null) {
//			stockSummaryPage.enterProductNameInFilterInput(orderData.getTransFormedProduct());
//		} else {
//			stockSummaryPage.enterProductNameInFilterInput(orderData.getProduct());
//		}
//
//		processingOrdersPage.clickApplyButton();
//
//		stockSummaryPage.clickUomFilterIcon();
//		if (scenarioContext.getChangedUom() != null) {
//			stockSummaryPage.enterUomInFilterInput(scenarioContext.getChangedUom());
//		}
//
//		else if (orderData.getToUom() != null) {
//			stockSummaryPage.enterUomInFilterInput(orderData.getToUom());
//		} else {
//			stockSummaryPage.enterUomInFilterInput(orderData.getFromUom());
//			;
//		}
//
//		processingOrdersPage.pressEnterKey();
//		if (orderData.getTransFormedProduct() != null) {
//			stockSummaryPage.selectproduct(orderData.getTransFormedProduct());
//		} else {
//			stockSummaryPage.selectproduct(orderData.getProduct());
//		}
//		stockSummaryPage.selectSeller(orderData.getSeller());
//		stockSummaryPage.selectBatchDetails("Batch Details");
//		stockSummaryPage.clickReferenceFilterIcon();
//		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
//		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
//		processingOrdersPage.pressEnterKey();
//		List<String> actions = stockSummaryPage.getAllPerformedActions();
//
//		for (String action : actions) {
//			System.out.println("Performed Action: " + action);
//			Assert.assertTrue(action.equalsIgnoreCase("Stock Entry"), "Unexpected performed action: " + action);
//		}
//		String requestedQty = orderData.getRequestedQty();
//		System.out.println("Processed Quantity for Stock Verification: " + requestedQty);
//
//		double actualTotalQty = stockSummaryPage.getQuatity();
//		double expectedQty = Double.parseDouble(requestedQty);
//
//		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
//
//	}
	@Then("search the product and select the updated UOM used while converting the order from Draft to Created.")
	public void search_the_product_and_select_the_updated_uom_used_while_converting_the_order_from_draft_to_created() {
		OrderData orderData = scenarioContext.getOrderData();

		stockSummaryPage.clickProductNameFilterIcon();
		if (orderData.getTransFormedProduct() != null) {
			stockSummaryPage.enterProductNameInFilterInput(orderData.getTransFormedProduct());
		} else {
			stockSummaryPage.enterProductNameInFilterInput(orderData.getProduct());
		}

		processingOrdersPage.clickApplyButton();

		stockSummaryPage.clickUomFilterIcon();
		stockSummaryPage.enterUomInFilterInput(scenarioContext.getChangedUom());
		processingOrdersPage.pressEnterKey();
		if (orderData.getTransFormedProduct() != null) {
			stockSummaryPage.selectproduct(orderData.getTransFormedProduct());
		} else {
			stockSummaryPage.selectproduct(orderData.getProduct());
		}
	    
	}

	@Given("user creates transformation order with below data")
	public void user_creates_transformation_order_with_below_data(io.cucumber.datatable.DataTable dataTable)
			throws InterruptedException {
		scenarioContext.populateOrderData(dataTable);
		OrderData data = scenarioContext.getOrderData();

		processingOrdersPage.clickOrderProcessingMenu();
		processingOrdersPage.clickProcessingOrdersMenu();
		processingOrdersPage.clickCreateNewButton();
		System.out.println("Transformation Type: " + data.getTransformationType());

		processingOrdersPage.selectTransformationType(data.getTransformationType());

		processingOrdersPage.selectHub(data.getHub());
		processingOrdersPage.selectSubCategory(data.getSubCategory());
		processingOrdersPage.selectProductFromGrid(data.getProduct());
		Thread.sleep(5000);

	}

	@Given("user enters processing product details")
	public void user_enters_processing_product_details(io.cucumber.datatable.DataTable dataTable) {
		scenarioContext.populateOrderData(dataTable);
		OrderData data = scenarioContext.getOrderData();
		System.out.println("Transformed Product: " + data.getTransFormedProduct());
		processingOrdersPage.selectTransformedProductFromGrid1(data.getTransFormedProduct());
		processingOrdersPage.selectSeller(data.getSeller());
		processingOrdersPage.selectUom(data.getToUom());
		System.out.println("Requested Quantity: " + data.getRequestedQty());
		processingOrdersPage.clearAndEnterRequestedQuantity(data.getRequestedQty());
		processingOrdersPage.enterMinimumYield(data.getMinYield());
		processingOrdersPage.enterMaxixmumYield(data.getMaxYield());

	}

	@Given("user enters wastage product details")
	public void user_enters_wastage_product_details(io.cucumber.datatable.DataTable dataTable) {
		scenarioContext.populateOrderData(dataTable);
		OrderData data = scenarioContext.getOrderData();
		processingOrdersPage.clickAddWastageButton();
		System.out.println("Wastage Product: " + data.getWastageProduct());

		processingOrdersPage.selectWastageProduct();
		processingOrdersPage.enterWastageMinYield(data.getMinYield());
		processingOrdersPage.enterWastageMaxYield(data.getMaxYield());

	}

	@When("user sends the order for processing")
	public void user_sends_the_order_for_processing() {
		processingOrdersPage.clickSendForProcess();
		processingOrdersPage.clickConfirmButton();

		Assert.assertEquals(processingOrdersPage.getSuccessPopupText(), "Processing Order created successfully!");

		processingOrdersPage.clickOkButton();

	}
	@Then("search the wastage product and UOM used in the transformation")
	public void search_the_wastage_product_and_uom_used_in_the_transformation() {
		driver.navigate().refresh();
		OrderData data = scenarioContext.getOrderData();
		stockSummaryPage.clickProductNameFilterIcon();
		stockSummaryPage.enterProductNameInFilterInput(data.getWastageProduct());
		processingOrdersPage.clickApplyButton();
		stockSummaryPage.clickUomFilterIcon();
		stockSummaryPage.enterUomInFilterInput(data.getToUom());
		processingOrdersPage.pressEnterKey();
		stockSummaryPage.selectproduct(data.getWastageProduct());
	   
	}
	@Then("select the seller {string} to which the wastage product is credited")
	public void select_the_seller_to_which_the_wastage_product_is_credited(String string) {
		stockSummaryPage.selectSeller(string);
	}

//	@Then("verify the stock entry for the wastage product for {string} seller")
//	public void verify_the_stock_entry_for_the_wastage_product_for_seller(String string) {
//		driver.navigate().refresh();
//
//		OrderData data = scenarioContext.getOrderData();
//		stockSummaryPage.clickProductNameFilterIcon();
//		stockSummaryPage.enterProductNameInFilterInput(data.getWastageProduct());
//		processingOrdersPage.clickApplyButton();
//		stockSummaryPage.clickUomFilterIcon();
//		stockSummaryPage.enterUomInFilterInput(data.getToUom());
//		processingOrdersPage.pressEnterKey();
//		stockSummaryPage.selectproduct(data.getWastageProduct());
//		stockSummaryPage.selectSeller(string);
//		stockSummaryPage.selectBatchDetails("Batch Details");
//		stockSummaryPage.clickReferenceFilterIcon();
//		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
//		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
//		processingOrdersPage.pressEnterKey();
//		List<String> actions = stockSummaryPage.getAllPerformedActions();
//
//		for (String action : actions) {
//			System.out.println("Performed Action: " + action);
//			Assert.assertTrue(action.equalsIgnoreCase("Stock Entry"), "Unexpected performed action: " + action);
//		}
//		String wastageQty = scenarioContext.getWastageYield();
//		System.out.println("Processed Quantity for Stock Verification: " + wastageQty);
//
//		double actualTotalQty = stockSummaryPage.getQuatity();
//		double expectedQty = Double.parseDouble(wastageQty);
//
//		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
//
//	}

	@When("at draft status user changes the uom to {string}")
	public void at_draft_status_user_changes_the_uom_to(String string) {
//		String chanedUom = string;
		scenarioContext.setChangedUom(string);
		System.out.println("Changed UOM: " + scenarioContext.getChangedUom());
		processingOrdersPage.selectChangedUom(scenarioContext.getChangedUom());

	}

	@When("at draft status user changes the seller to {string}")
	public void at_draft_status_user_changes_the_seller_to(String string) {
		scenarioContext.setChangedSeller(string);
		System.out.println("Changed Seller: " + scenarioContext.getChangedSeller());
		processingOrdersPage.selectChangedSeller(scenarioContext.getChangedSeller());
	}

	@When("User selects the UOM as {string}")
	public void user_selects_the_uom_as(String string) {
		processingOrdersPage.selectUom(string);
	}

//	@Then("verify the stock deduction for the changed seller variant used in the transformation")
//	public void verify_the_stock_deduction_for_the_changed_seller_variant_used_in_the_transformation() {
//		OrderData orderData = scenarioContext.getOrderData();
//		stockSummaryPage.clickProductNameFilterIcon();
//		stockSummaryPage.enterProductNameInFilterInput(orderData.getProduct());
//		processingOrdersPage.clickApplyButton();
//		stockSummaryPage.clickUomFilterIcon();
//		stockSummaryPage.enterUomInFilterInput(orderData.getFromUom());
//		processingOrdersPage.pressEnterKey();
//		stockSummaryPage.selectproduct(orderData.getProduct());
//		stockSummaryPage.selectSeller(scenarioContext.getChangedSeller());
//		stockSummaryPage.selectBatchDetails("Batch Details");
//		stockSummaryPage.clickReferenceFilterIcon();
//		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
//		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
//		processingOrdersPage.pressEnterKey();
//		List<String> actions = stockSummaryPage.getAllPerformedActions();
//
//		for (String action : actions) {
//			System.out.println("Performed Action: " + action);
//			Assert.assertTrue(action.equalsIgnoreCase("Stock-transform-Dispatched"),
//					"Unexpected performed action: " + action);
//		}
//		String processedQty = orderData.getProcessedQty();
//		System.out.println("Processed Quantity for Stock Verification: " + processedQty);
//
//		double actualTotalQty = stockSummaryPage.getQuatity();
//		double expectedQty = Double.parseDouble(processedQty);
//
//		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
//		driver.navigate().refresh();
//
//	}

//	@Then("verify the stock entry for changed sellers processed variant")
//	public void verify_the_stock_entry_for_changed_sellers_processed_variant() {
//		OrderData orderData = scenarioContext.getOrderData();
//		stockSummaryPage.clickProductNameFilterIcon();
//		if (orderData.getTransFormedProduct() != null) {
//			stockSummaryPage.enterProductNameInFilterInput(orderData.getTransFormedProduct());
//		} else {
//			stockSummaryPage.enterProductNameInFilterInput(orderData.getProduct());
//		}
//		processingOrdersPage.clickApplyButton();
//		stockSummaryPage.clickUomFilterIcon();
//		stockSummaryPage.enterUomInFilterInput(orderData.getToUom());
//		processingOrdersPage.pressEnterKey();
//		if (orderData.getTransFormedProduct() != null) {
//			stockSummaryPage.selectproduct(orderData.getTransFormedProduct());
//		} else {
//			stockSummaryPage.selectproduct(orderData.getProduct());
//		}
//		stockSummaryPage.selectSeller(scenarioContext.getChangedSeller());
//		stockSummaryPage.selectBatchDetails("Batch Details");
//		stockSummaryPage.clickReferenceFilterIcon();
//		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
//		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
//		processingOrdersPage.pressEnterKey();
//		List<String> actions = stockSummaryPage.getAllPerformedActions();
//
//		for (String action : actions) {
//			System.out.println("Performed Action: " + action);
//			Assert.assertTrue(action.equalsIgnoreCase("Stock Entry"), "Unexpected performed action: " + action);
//		}
//		String requestedQty = orderData.getRequestedQty();
//		System.out.println("Processed Quantity for Stock Verification: " + requestedQty);
//
//		double actualTotalQty = stockSummaryPage.getQuatity();
//		double expectedQty = Double.parseDouble(requestedQty);
//
//		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
//	}

	@When("at draft status user changes the requested quantity to {string}")
	public void at_draft_status_user_changes_the_requested_quantity_to(String string) {
		scenarioContext.setChangedRequestedQty(string);
		System.out.println("Changed Requested Quantity: " + scenarioContext.getChangedRequestedQty());
		processingOrdersPage.clearAndEnterChangedRequestedQuantity(scenarioContext.getChangedRequestedQty());

	}

//	@Then("verify the stock entry should happen for the changed requested quantity")
//	public void verify_the_stock_entry_should_happen_for_the_changed_requested_quantity() {
//		OrderData orderData = scenarioContext.getOrderData();
//
//		stockSummaryPage.clickProductNameFilterIcon();
//		if (orderData.getTransFormedProduct() != null) {
//			stockSummaryPage.enterProductNameInFilterInput(orderData.getTransFormedProduct());
//		} else {
//			stockSummaryPage.enterProductNameInFilterInput(orderData.getProduct());
//		}
//
//		processingOrdersPage.clickApplyButton();
//
//		stockSummaryPage.clickUomFilterIcon();
//		if (scenarioContext.getChangedUom() != null) {
//			stockSummaryPage.enterUomInFilterInput(scenarioContext.getChangedUom());
//		}
//
//		else if (orderData.getToUom() != null) {
//			stockSummaryPage.enterUomInFilterInput(orderData.getToUom());
//		} else {
//			stockSummaryPage.enterUomInFilterInput(orderData.getFromUom());
//			;
//		}
//
//		processingOrdersPage.pressEnterKey();
//		if (orderData.getTransFormedProduct() != null) {
//			stockSummaryPage.selectproduct(orderData.getTransFormedProduct());
//		} else {
//			stockSummaryPage.selectproduct(orderData.getProduct());
//		}
//		stockSummaryPage.selectSeller(orderData.getSeller());
//		stockSummaryPage.selectBatchDetails("Batch Details");
//		stockSummaryPage.clickReferenceFilterIcon();
//		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
//		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
//		processingOrdersPage.pressEnterKey();
//		List<String> actions = stockSummaryPage.getAllPerformedActions();
//
//		for (String action : actions) {
//			System.out.println("Performed Action: " + action);
//			Assert.assertTrue(action.equalsIgnoreCase("Stock Entry"), "Unexpected performed action: " + action);
//		}
//		String changedRequestedQty = scenarioContext.getChangedRequestedQty();
//		System.out.println("Processed Quantity for Stock Verification: " + changedRequestedQty);
//
//		double actualTotalQty = stockSummaryPage.getQuatity();
//		double expectedQty = Double.parseDouble(changedRequestedQty);
//
//		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
//	}
	@Then("select the batch details and verify that stock entry is created for the updated requested quantity with the action {string}.")
	public void select_the_batch_details_and_verify_that_stock_entry_is_created_for_the_updated_requested_quantity_with_the_action(String string) {
		stockSummaryPage.selectBatchDetails("Batch Details");
		stockSummaryPage.clickReferenceFilterIcon();
		System.out.println("Processing Order ID for Stock Verification: " + scenarioContext.getProcessingOrderId());
		stockSummaryPage.enterReferenceInFilterInput(scenarioContext.getProcessingOrderId());
		processingOrdersPage.pressEnterKey();
		List<String> actions = stockSummaryPage.getAllPerformedActions();

		for (String action : actions) {
			System.out.println("Performed Action: " + action);
			Assert.assertTrue(action.equalsIgnoreCase("Stock Entry"), "Unexpected performed action: " + action);
		}
		String changedRequestedQty = scenarioContext.getChangedRequestedQty();
		System.out.println("Processed Quantity for Stock Verification: " + changedRequestedQty);

		double actualTotalQty = stockSummaryPage.getQuatity();
		double expectedQty = Double.parseDouble(changedRequestedQty);

		Assert.assertEquals(actualTotalQty, expectedQty, "Total quantity mismatch after transformation");
	
	}

	@Given("User enters the following details and select the select which has only one UOM for the selected product")
	public void user_enters_the_following_details_and_select_the_select_which_has_only_one_uom_for_the_selected_product(
			io.cucumber.datatable.DataTable dataTable) {
		scenarioContext.populateOrderData(dataTable);
		OrderData data = scenarioContext.getOrderData();

		processingOrdersPage.clickOrderProcessingMenu();
		processingOrdersPage.clickProcessingOrdersMenu();
		processingOrdersPage.clickCreateNewButton();

		processingOrdersPage.selectTransformationType(data.getTransformationType());

		processingOrdersPage.selectHub(data.getHub());
		System.out.println(processingOrdersPage.getSelectedHub());

		processingOrdersPage.selectSubCategory(data.getSubCategory());

		processingOrdersPage.selectProductFromGrid(data.getProduct());
		processingOrdersPage.selectSeller(data.getSeller());
		processingOrdersPage.selectUom(data.getToUom());

		processingOrdersPage.clearAndEnterRequestedQuantity(data.getRequestedQty());
	}

	@Then("click on send for process button")
	public void click_on_send_for_process_button() {
		processingOrdersPage.clickSendForProcess();
	}

	@Then("system should display error message {string}")
	public void system_should_display_error_message(String string) {
		Assert.assertEquals(processingOrdersPage.getSuccessPopupText(), string);

	}

}
