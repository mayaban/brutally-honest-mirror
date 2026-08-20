package com.brutallyhonestmirror.e2e.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.brutallyhonestmirror.e2e.stepdefinitions",
        plugin = {"pretty"}
)
public class RunCucumberTest {
}
