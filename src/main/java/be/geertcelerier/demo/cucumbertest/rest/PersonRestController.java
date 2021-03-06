package be.geertcelerier.demo.cucumbertest.rest;

import be.geertcelerier.demo.cucumbertest.entity.Person;
import be.geertcelerier.demo.cucumbertest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/persons/{id}")
    public @ResponseBody Person getPersonById(@PathVariable Integer id) {
        return personService.getById(id);
    }
}
