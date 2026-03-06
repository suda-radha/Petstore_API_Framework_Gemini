package com.petstore;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepDefinitions"},
    plugin = {
        "pretty",
        "html:target/cucumber-testng-reports.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true
)
public class RunnerTestNG extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = true) // This enables parallel execution at the scenario level
    public Object[][] scenarios() {
        return super.scenarios();
    }
}