package be.geertcelerier.demo.cucumbertest.repository;

import be.geertcelerier.demo.cucumbertest.entity.Person;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class })
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testEmptyTable() {
        List<Person> personList = (List<Person>) personRepository.findAll();
        assertTrue(CollectionUtils.isEmpty(personList));
    }

    @Test
    @DatabaseSetup("/data/person-oneperson.xml")
    @DatabaseTearDown(value = "/data/person-oneperson.xml", type = DatabaseOperation.DELETE_ALL)
    public void testOneRecord() {
        List<Person> personList = (List<Person>) personRepository.findAll();
        assertTrue(CollectionUtils.isNotEmpty(personList));
        assertEquals(1, personList.size());
    }

    @Test
    @DatabaseSetup("/data/person-multiplepersons.xml")
    @DatabaseTearDown(value = "/data/person-multiplepersons.xml", type = DatabaseOperation.DELETE_ALL)
    public void testMultipleRecord() {
        List<Person> personList = (List<Person>) personRepository.findAll();
        assertTrue(CollectionUtils.isNotEmpty(personList));
        assertEquals(11, personList.size());
    }

}