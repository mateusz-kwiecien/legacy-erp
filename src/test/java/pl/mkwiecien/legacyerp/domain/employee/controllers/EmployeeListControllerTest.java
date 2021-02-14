package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class EmployeeListControllerTest {
    private static final String FIRST_EMPLOYEE_FIRST_NAME = "John";
    private static final String FIRST_EMPLOYEE_LAST_NAME = "Testing";
    private static final String FIRST_EMPLOYEE_EMAIL = "john.testing@example.com";
    private static final String SECOND_EMPLOYEE_FIRST_NAME = "Jason";
    private static final String SECOND_EMPLOYEE_LAST_NAME = "Unit";
    private static final String SECOND_EMPLOYEE_EMAIL = "jason.unit@example.com";
    private static final String EMPLOYEE_LIST_URI = "/employee/list";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void shouldRetrieveListOfAllEmployees() throws Exception {
        // given :
        employeeRepository.saveAll(anEmployeesList());

        // when :
        ResultActions result = mockMvc.perform(get(EMPLOYEE_LIST_URI));

        // then :
        result.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("employees", Matchers.hasSize(2)));
    }

    @BeforeEach
    void setup() {
        cleanup();
    }

    @AfterEach
    void cleanup() {
        employeeRepository.deleteAll();
    }

    private List<Employee> anEmployeesList() {
        Employee first = new Employee(FIRST_EMPLOYEE_FIRST_NAME, FIRST_EMPLOYEE_LAST_NAME, FIRST_EMPLOYEE_EMAIL);
        Employee second = new Employee(SECOND_EMPLOYEE_FIRST_NAME, SECOND_EMPLOYEE_LAST_NAME, SECOND_EMPLOYEE_EMAIL);
        return Arrays.asList(first, second);
    }

}