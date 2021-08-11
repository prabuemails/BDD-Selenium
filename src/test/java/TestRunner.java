import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/java/Features/"
            ,glue = {"stepDefinition"}
            ,plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
            ,tags = "@Google"
    )
    public class TestRunner {

    }
