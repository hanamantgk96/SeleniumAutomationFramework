package stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObjects.CompilationPage;
import PageObjects.PageObjectManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.BaseTest;


public class CompilationStepDef {
	public WebDriver driver;
	public PageObjectManager pageObjectManager;
	public CompilationPage compilationPage;
	public CompilationStepDef() throws IOException {
        this.driver = BaseTest.getDriver(); // or however you retrieve your driver
		this.pageObjectManager= new PageObjectManager(driver);
		this.compilationPage = pageObjectManager.getCompilationPage();
	}
	
	@Given("The user should navigate to the order compilation page")
	public void the_user_should_navigate_to_the_order_compilation_page() throws InterruptedException {
	  compilationPage.clickOrdProcessing();
	  
	  compilationPage.clickOrdCompilation();
	  
	}

	@Then("the {string} page should be displayed")
	public void the_page_should_be_displayed(String pageName) {
	    Assert.assertTrue(compilationPage.isPageDisplayed(), pageName + " page is not displayed");
	}

	@Then("the page should contain columns")
	public void the_page_should_contain_columns(DataTable dataTable) {
		List<String> expectedColumns = dataTable.asList();
//		List<String> actualColumns =  compilationPage.getColumns();
		System.out.println("Expected Columns: " + expectedColumns);
		//System.out.println("Actual Columns: " + actualColumns);
		System.out.println("Actual columns"+ compilationPage.getColumns());
		Assert.assertEquals(expectedColumns,compilationPage.getColumns(), "The actual columns do not match the expected columns");
		
	    
	}

	}


