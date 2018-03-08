package be.geertcelerier.demo.cucumbertest.integration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:cucumber/person.feature" },
        glue = {"be.geertcelerier.demo.cucumbertest.integration.steps.person", "cucumber.api.spring"}
)
public class PersonRestControllerCucumberTest {
}
