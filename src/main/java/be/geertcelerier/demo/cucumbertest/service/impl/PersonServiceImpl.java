package be.geertcelerier.demo.cucumbertest.service.impl;

import be.geertcelerier.demo.cucumbertest.entity.Person;
import be.geertcelerier.demo.cucumbertest.repository.PersonRepository;
import be.geertcelerier.demo.cucumbertest.service.PersonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        CollectionUtils.addAll(personList, personRepository.findAll().iterator());
        return personList;
    }

    @Override
    public Person getById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }
}
