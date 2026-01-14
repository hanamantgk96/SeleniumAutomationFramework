package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import PageObjects.LoginPage;
import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import utils.BaseTest;

public class Hooks {
//	private WebDriver driver;
	private WebDriver driver;
	private ScenarioContext scenarioContext;

	public Hooks(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}
	@Before
    public void beforeScenario() {
		scenarioContext.clear();
	}
    @After
    public void tearDown() {
        BaseTest.quitDriver();
    }
}
