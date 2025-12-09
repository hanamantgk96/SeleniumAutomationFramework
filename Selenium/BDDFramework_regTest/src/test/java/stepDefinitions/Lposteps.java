package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObject.Lpopage;
import utilities.BaseClass;

public class Lposteps {

	public BaseClass setupclass;
	
	public Lposteps(BaseClass setupclass) {
		this.setupclass = setupclass;
	}
	
	@Given("User Navigate to the create LPO page")
	public void user_navigate_to_the_create_lpo_page() throws InterruptedException {
		Lpopage Lpo = setupclass.pageObjectManager.getLpopage();
		Thread.sleep(3000);
		Lpo.clickthreedot();
		Thread.sleep(2000);
		Lpo.clickprocurement();
		Thread.sleep(2000);
		Lpo.clickLpo();
	}

	@Given("Select products which is approved")
	public void select_products_which_is_approved() throws InterruptedException {
		Lpopage Lpo = setupclass.pageObjectManager.getLpopage();
		Thread.sleep(3000);
		Lpo.clickwearhousefitler();
		Thread.sleep(2000);
		Lpo.wearhousedropdown();
		Thread.sleep(2000);
		Lpo.selectwearouse();
		Thread.sleep(2000);
		Lpo.selectproducts();
	}

	@Given("Got to second page")
	public void got_to_second_page() throws InterruptedException {
		Lpopage Lpo = setupclass.pageObjectManager.getLpopage();
		Thread.sleep(2000);
		Lpo.clicknextbutton();
	}

	@Given("Got to third page and select the Delivery Hub")
	public void got_to_third_page_and_select_the_delivery_hub() throws InterruptedException {
		Lpopage Lpo = setupclass.pageObjectManager.getLpopage();
		Thread.sleep(2000);
		Lpo.clicknextbutton();
	}

	@Then("Click submit button and click confirm")
	public void click_submit_button_and_click_confirm() throws InterruptedException {
		Lpopage Lpo = setupclass.pageObjectManager.getLpopage();
		Thread.sleep(5000);
		Lpo.clicksubmittbutton();
		Thread.sleep(1000);
		Lpo.ClickConfirmButton();
		Thread.sleep(1000);
		Lpo.clickNoButton();
	}

	@Then("LPO is successfully created")
	public void lpo_is_successfully_created() throws InterruptedException {
		Lpopage Lpo = setupclass.pageObjectManager.getLpopage();
		Thread.sleep(2000);
//		Lpo.CreateLpoValidation();
		Thread.sleep(2000);
		Lpo.updateTopLpoStatus(1);
		Thread.sleep(2000);
		Lpo.changeNewStatus();
		Thread.sleep(2000);
		Lpo.changeLpoAtHub(1);
		Thread.sleep(2000);
		Lpo.Lpofulfillment();
		Thread.sleep(2000);
		Lpo.enterInvoiceDetails();
		Thread.sleep(2000);
		Lpo.VeryfyAndApprove();
		Thread.sleep(2000);
		Lpo.updateOnlyWaitingForAcceptance(1);
		Thread.sleep(2000);
		Lpo.processLpoWorkflow();
	}	

	}

