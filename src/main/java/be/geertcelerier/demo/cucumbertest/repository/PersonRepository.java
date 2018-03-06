package be.geertcelerier.demo.cucumbertest.repository;

import be.geertcelerier.demo.cucumbertest.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
