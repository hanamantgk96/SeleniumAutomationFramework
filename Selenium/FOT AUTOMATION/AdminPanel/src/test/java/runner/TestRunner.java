package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features = "src/test/resources/features",
		glue={"stepDefinitions"},
		plugin = {"pretty", "html:target/cucumber-report.html"},
	    monochrome = true,
	    tags = "@productTransformation3",
	    dryRun = false
		)

public class TestRunner extends AbstractTestNGCucumberTests {
	
}
