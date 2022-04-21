package com.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty:target/cucumber/cucumber.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/cucumber/cucumber.json",
                "com.utils.MyTestListener"
        }

        , features = {"src/test/resources/features"}
        , glue = {"com.stepDefinitions"}
        //,dryRun = true
        , monochrome = true
        , snippets = SnippetType.CAMELCASE
        , tags = "@movieAPI"
)
public class TestRunner {

}