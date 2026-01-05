package StepDefinition;

import PageObject.PlaceOrder;
import Utils.Setuptest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaceOrderStepDefinition {
	double prdValu =0.0d;
	public Setuptest testsetup;
    
	public PlaceOrderStepDefinition(Setuptest testSetup) {
		this.testsetup = testSetup;
	}
	
	@Given("Navigate to place order page")
	public void navigate_to_place_order_page() throws InterruptedException {
		PlaceOrder order = testsetup.pageObjectManager.getPlaceOrder();
	    order.clickgrid();
	    Thread.sleep(1000);
	    order.clickorderoption();
	    Thread.sleep(1000);
	    order.clickplaceorder();
	    Thread.sleep(2000);
	    order.clickbusinessfield();
	    Thread.sleep(10000);
	    order.validatePagination();
	    Thread.sleep(1000);
		order.selectbusinessrandomaly1();
	    Thread.sleep(2000);
//	    order.Businessfilteration();
//	    order.FOCOrderCreation();
	}

	@When("Validate if the products are selected by clicking the Review order button and valiadte the error message")
	public void validate_if_the_products_are_selected_by_clicking_the_review_order_button_and_valiadte_the_error_message() throws InterruptedException {
		PlaceOrder order = testsetup.pageObjectManager.getPlaceOrder();
		// Get the accumulated amount from the getTotalAmount method
		  prdValu = order.selectProduct();
	}

	@Then("Validate if shipping date is provided by clicking the Review orders button and validate the error message")
	public void validate_if_shipping_date_is_provided_by_clicking_the_review_orders_button_and_validate_the_error_message() throws InterruptedException {
		PlaceOrder order = testsetup.pageObjectManager.getPlaceOrder();
		Thread.sleep(2000);
		order.Past_shippdate_date();
		Thread.sleep(2000);
		order.Ship_Date_ErrorMessageValidation();
		
	}

	@Then("Validate if the Lpo number is provided by clicking the Review order button and validate the popup message")
	public void validate_if_the_lpo_number_is_provided_by_clicking_the_review_order_button_and_validate_the_popup_message() throws InterruptedException {
		PlaceOrder order = testsetup.pageObjectManager.getPlaceOrder();
		Thread.sleep(2000);
		order.Lpo_Num_popupErrorValidation();
	}

	@Then("Validate if a payment method is selected by clicking the Review order button and capture the message")
	public void validate_if_a_payment_method_is_selected_by_clicking_the_review_order_button_and_capture_the_message() throws InterruptedException {
		PlaceOrder order = testsetup.pageObjectManager.getPlaceOrder();
		order.selectPaymentModeByIndex(0);
		Thread.sleep(2000);
		order.Sel_billingAddr_And_ShiAddr_popupErrorValidation();
	}

	@Then("Validate the if a billing address by clicking the Review order button and validate the popup message")
	public void validate_the_if_a_billing_address_by_clicking_the_review_order_button_and_validate_the_popup_message() throws InterruptedException {
		PlaceOrder order = testsetup.pageObjectManager.getPlaceOrder();
//		order.driverclose();	
		order.selectByDriver();
		
		order.validateTotalAmount(prdValu);
		Thread.sleep(2000);
		order.clicplaceorderbutton();
	}
	public double getTotalAmount() {
	    return prdValu;  // Return the accumulated gross amount
	}
	
}
