package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.OrderCompilation;
import utilities.BaseClass;

public class OrderCompilationSteps {
	
public BaseClass setupclass;
	
	public OrderCompilationSteps(BaseClass setupclass) {
		this.setupclass = setupclass;
	}
		
	@Given("User navigates to the Order Compilation page")
	public void user_navigates_to_the_order_compilation_page() throws InterruptedException {
		OrderCompilation Order = setupclass.pageObjectManager.getOrderCompilation();
		Thread.sleep(2000);
		Order.clickgrid();
		Thread.sleep(1000);
		Order.clickorderProcessing();
		Thread.sleep(2000);
		Order.orderCompilation();
		Thread.sleep(1000);
	}
	
	@And("Search the order Id")
	public void Search_the_order_Id() {
	
	}
	@Then("Clicks more button and Reserve Batch")
	public void clicks_more_button_and_Reserve_batch() {

	}

	@When("the user reserves the stock")
	public void the_user_reserves_the_stock() throws InterruptedException {
		OrderCompilation Order = setupclass.pageObjectManager.getOrderCompilation();
		Thread.sleep(2000);
		Order.stockReserving();
	}
	
	@And("Check details and Submit it")
	public void check_details_and_submit_It() {
	
	}

	@Then("the stock is reserved successfully")
	public void the_stock_is_reserved_successfully() throws InterruptedException{
		OrderCompilation Order = setupclass.pageObjectManager.getOrderCompilation();
		Thread.sleep(2000);
		Order.stockReserve();
	}


}
