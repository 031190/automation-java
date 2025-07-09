package cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/cucumber/features",
        glue = "cucumber.stepDefinitions",
        plugin = "pretty"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
