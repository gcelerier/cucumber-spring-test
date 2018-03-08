package be.geertcelerier.demo.cucumbertest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CucumberTestApplicationTest {

    @Test
    public void testMain() {
        CucumberTestApplication.main(new String []{});
    }
}
