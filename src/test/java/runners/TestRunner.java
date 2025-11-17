package runners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "Steps",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports/cucumber.json",  // JSON report
                "junit:target/cucumber-reports/cucumber.xml", // JUnit XML report (TestNG compatible)
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", // Allure plugin for Cucumber 7.x
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

}