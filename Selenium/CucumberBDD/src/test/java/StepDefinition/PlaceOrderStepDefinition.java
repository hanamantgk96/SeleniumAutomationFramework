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
	
	@Given("the user navigates to the Place Order page")
	public void the_user_navigates_to_the_place_order_page() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder();
	    Order.clickgrid();
	    Thread.sleep(1000);
	    Order.clickorderoption();
	    Thread.sleep(1000);
	    Order.clickplaceorder();
	    Thread.sleep(2000);
	    Order.clickbusinessfield();
	    Thread.sleep(10000);
//	    order.Businessfilteration();
//	    order.FOCOrderCreation();
	    
	}

	@When("pagination is validated for business selection")
	public void pagination_is_validated_for_business_selection() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder();
		 Order.validatePagination();
		    Thread.sleep(1000);	    
	}

	@Then("the user selects a random business; if a product does not exist, the user selects another business")
	public void the_user_selects_a_random_business_if_a_product_does_not_exist_the_user_selects_another_business() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder();
		Order.selectbusinessrandomaly1();
	    Thread.sleep(2000);
	}

	@Then("the user selects random products from the list and enters random prices")
	public void the_user_selects_random_products_from_the_list_and_enters_random_prices() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder();
		Thread.sleep(2000);
		  prdValu = Order.selectProduct();
	}

	@Then("the user selects the payment method and delivery user")
	public void the_user_selects_the_payment_method_and_delivery_user() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder();
		Order.selectPaymentModeByIndex();
		Thread.sleep(2000);
		Order.selectByDriver();
	}

	@Then("the shipping date is validated")
	public void the_shipping_date_is_validated() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder();
		Thread.sleep(2000);
		Order.Past_shippdate_date();
		Thread.sleep(2000);
		Order.Ship_Date_ErrorMessageValidation();
	   
	}

	@Then("the user selects the billing address and shipping address")
	public void the_user_selects_the_billing_address_and_shipping_address() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder();
		Thread.sleep(2000);
		Order.Sel_billingAddr_And_ShiAddr_popupErrorValidation();
	   
	}

	@Then("the LPO number is validated")
	public void the_lpo_number_is_validated() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder(); 
		Thread.sleep(2000);
		Order.Lpo_Num_popupErrorValidation();
	}
	
	
	@Then("Click the Review button and validate the total Amount of the Order")
	public void click_the_review_button_and_validate_the_total_amount_of_the_order() throws InterruptedException {
		PlaceOrder Order = testsetup.pageObjectManager.getPlaceOrder();
		Order.validateTotalAmount(prdValu);
		Thread.sleep(2000);
		Order.clickplaceorderbutton();
	}
	public double getTotalAmount() {
	    return prdValu;  
	}
	   
	}	
	

