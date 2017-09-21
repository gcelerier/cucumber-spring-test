package be.lampiris.demo.cucumbertest.rest;

import be.lampiris.demo.cucumbertest.entity.Person;
import be.lampiris.demo.cucumbertest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonRestController {

    private PersonService personService;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/persons")
    public @ResponseBody List<Person> getAllPersons() {
        return personService.getAll();
    }
}
