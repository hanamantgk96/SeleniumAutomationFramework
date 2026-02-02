package runner;

import org.apache.commons.io.filefilter.TrueFileFilter;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features = "@target/failed_scenarios.txt",
				glue = "stepDefinitions",
				plugin = {"pretty","html:target/cucumber-report-failed.html"},
				monochrome = true
		)

public class FailedTeseCaseRunner extends AbstractTestNGCucumberTests {
	

}
