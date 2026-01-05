package StepDefinition;

import PageObject.UserCreation;
import Utils.Setuptest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserCreationStepDefinition {
	
	Setuptest testsetup;
	public UserCreationStepDefinition(Setuptest testSetup) {
		this.testsetup = testSetup;
	}
	
	@Given("The user clicks on the Administration grid")
	public void the_user_clicks_on_the_administration_grid() throws InterruptedException {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
		Thread.sleep(2000);
		user.clickgrid();
		Thread.sleep(1000);
		user.clickAdministrationoption();
		
	}

	@Given("Clicks on the {string} option")
	public void clicks_on_the_option(String string) throws InterruptedException {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
		Thread.sleep(2000);
		user.clickCreateOrganisation();
	}

	@Then("The user selects an organization type from the dropdown")
	public void the_user_selects_an_organization_type_from_the_dropdown() throws InterruptedException {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
		user.orgName();
	}

	@Then("Selects {string} as the organization type")
	public void selects_as_the_organization_type(String string) throws InterruptedException {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
		user.orgTypebusiness();
		
		
	}

	@Then("Fills in all mandatory fields for Organization Details")
	public void fills_in_all_mandatory_fields_for_organization_details() throws InterruptedException {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
		user.firstName();
		user.mobileNo();
		Thread.sleep(2000);
		user.Email();
	}

	@Then("Fills in all mandatory fields for Organization Admin Details")
	public void fills_in_all_mandatory_fields_for_organization_admin_details() throws InterruptedException {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
		Thread.sleep(2000);
		user.UserNametype();
		user.Password();
		user.confirmPassword();
	}

	@Then("Fills in all necessary address details")
	public void fills_in_all_necessary_address_details() throws InterruptedException {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
		user.Address();
		user.Location();
		user.Country();
		user.State();
		user.Zone();
		user.City();
	}

	@Then("Enables all communication channels")
	public void enables_all_communication_channels() throws InterruptedException {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
		user.paymentTerms();
	}

	@Then("Uploads all mandatory documents")
	public void uploads_all_mandatory_documents() {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
	}

	@Then("Provides additional required information")
	public void provides_additional_required_information() {
		UserCreation user = testsetup.pageObjectManager.getUserCreation();
	}
	
	
	}


