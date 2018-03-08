package be.geertcelerier.demo.cucumbertest.rest;

import be.geertcelerier.demo.cucumbertest.entity.Person;
import be.geertcelerier.demo.cucumbertest.entity.PersonBuilder;
import be.geertcelerier.demo.cucumbertest.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllPersons() throws Exception {
        when(personRepository.findAll()).thenReturn(createPersonList());

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Geert")))
                .andExpect(jsonPath("$[0].lastName", is("Célérier")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Tristan")))
                .andExpect(jsonPath("$[1].lastName", is("Fily")))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].firstName", is("Jan")))
                .andExpect(jsonPath("$[2].lastName", is("Verbeeck")));
    }

    private List<Person> createPersonList() {
        List<Person> personList = new ArrayList<>();
        personList.add(createPerson(1, "Geert", "Célérier"));
        personList.add(createPerson(2, "Tristan", "Fily"));
        personList.add(createPerson(3, "Jan", "Verbeeck"));
        return personList;
    }

    private Person createPerson(Integer id, String firstName, String lastName) {
        return new PersonBuilder().id(id).firstName(firstName).lastName(lastName).build();
    }
}
