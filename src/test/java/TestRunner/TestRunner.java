package TestRunner;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//
//@CucumberOptions(feratures = "", glue = {"StepDefinitions"}, plugin = {"pretty", "html:target/cucmber-reports.html" }, monochrome = true, tags = "@sanity") 
//@RunWith(Cucumber.class)


@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"StepDefinitions", "Hooks"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
