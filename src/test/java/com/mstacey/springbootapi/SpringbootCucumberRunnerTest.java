package com.mstacey.springbootapi;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    plugin = "pretty",
    features = "src/test/resources",
    glue = {"com.mstacey.springbootapi.steps"}
)
public class SpringbootCucumberRunnerTest extends AbstractTestNGCucumberTests {
}
