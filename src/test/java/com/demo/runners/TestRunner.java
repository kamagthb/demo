package com.demo.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        glue = "com/demo/step_definitions",
        features = "src/test/resources/features",

        tags = "@smoke",
        dryRun = false
)
public class TestRunner {
}


