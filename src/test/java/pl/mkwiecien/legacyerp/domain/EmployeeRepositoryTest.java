package pl.mkwiecien.legacyerp.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository repository;

    @Test
    void databaseTest() {
        //given :
        Employee testEmployee = new Employee("firstname", "lastname", "email@example.com");

        //when :
        Employee savedEmployee = repository.save(testEmployee);

        //than :
        assertTrue(repository.findById(savedEmployee.getId()).isPresent());
    }

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        setup();
    }
}