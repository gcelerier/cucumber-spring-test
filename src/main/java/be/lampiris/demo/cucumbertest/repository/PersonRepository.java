package be.lampiris.demo.cucumbertest.repository;

import be.lampiris.demo.cucumbertest.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
