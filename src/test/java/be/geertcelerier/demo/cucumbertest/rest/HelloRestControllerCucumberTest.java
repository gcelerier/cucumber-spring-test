package be.geertcelerier.demo.cucumbertest.rest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:cucumber/simple.feature" },
        glue = {"be.geertcelerier.demo.cucumbertest.rest.steps"}
)
public class HelloRestControllerCucumberTest {
}
