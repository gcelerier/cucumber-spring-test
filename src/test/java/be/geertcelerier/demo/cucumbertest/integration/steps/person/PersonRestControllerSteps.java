package be.geertcelerier.demo.cucumbertest.integration.steps.person;

import be.geertcelerier.demo.cucumbertest.CucumberTestApplication;
import be.geertcelerier.demo.cucumbertest.entity.PersonBuilder;
import be.geertcelerier.demo.cucumbertest.repository.PersonRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Before;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        loader = SpringBootContextLoader.class,
        classes = CucumberTestApplication.class)
@WebAppConfiguration
public class PersonRestControllerSteps {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private PersonRepository personRepository;

    private ResultActions resultActions;

    @Before
    public void setUp() {

    }

    @Given("^A REST client$")
    public void a_REST_client() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }


    @When("^I retrieve a list of persons$")
    public void i_retrieve_a_list_of_persons() throws Throwable {
        resultActions = mockMvc.perform(get("/persons"));
    }

    @Then("^I should get a list of six persons as a response$")
    public void i_should_get_a_list_of_six_persons_as_a_response() throws Throwable {
        resultActions.andExpect(jsonPath("$", hasSize(6)));
    }

    @When("^I search for person with ID (\\d+)$")
    public void i_search_for_person_with_ID(int id) throws Throwable {
        resultActions = mockMvc.perform(get("/persons/" + id));
    }

    @Then("^I should get status (\\d+)$")
    public void i_should_get_as_response_code(int responseCode) throws Throwable {
        resultActions.andExpect(status().is(responseCode));
    }

    @Given("^A list of six persons on the database$")
    public void a_list_of_six_persons_on_the_database() {
        personRepository.save(new PersonBuilder().id(1).firstName("Geert").lastName("Celerier").build());
        personRepository.save(new PersonBuilder().id(2).firstName("Tristan").lastName("Fily").build());
        personRepository.save(new PersonBuilder().id(3).firstName("Jan").lastName("Verbeeck").build());
        personRepository.save(new PersonBuilder().id(4).firstName("Gard").lastName("Skauge").build());
        personRepository.save(new PersonBuilder().id(5).firstName("Vincent").lastName("Letellier").build());
        personRepository.save(new PersonBuilder().id(6).firstName("Stephen").lastName("Ranson").build());
    }

    @Then("^I should get a person with first name (.*) and last name (.*)$")
    public void i_should_get_a_person_with_first_name_and_last_name(String firstName, String lastName) throws Throwable {
        resultActions.andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)));
    }
}
