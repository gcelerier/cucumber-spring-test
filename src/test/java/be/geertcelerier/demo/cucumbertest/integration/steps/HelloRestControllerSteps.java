package be.geertcelerier.demo.cucumbertest.integration.steps;

import be.geertcelerier.demo.cucumbertest.CucumberTestApplication;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        loader = SpringBootContextLoader.class,
        classes = CucumberTestApplication.class)
@WebAppConfiguration
public class HelloRestControllerSteps {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    private ResultActions resultActions;

    @Given("^A REST client$")
    public void a_REST_client() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @When("^I perform a GET on (.*)$")
    public void i_perform_a_GET_on_hello(String url) throws Throwable {
        resultActions = mockMvc.perform(get(url));
    }

    @Then("^I should get status (\\d+)$")
    public void i_should_get_as_response_code(int responseCode) throws Throwable {
        resultActions.andExpect(status().is(responseCode));
    }

    @Then("^I should get (\".+?\") as a response$")
    public void i_should_get_as_a_response(String response) throws Throwable {
        response = response.replace("\"", "");
        resultActions.andExpect(content().string(response));
    }

}
