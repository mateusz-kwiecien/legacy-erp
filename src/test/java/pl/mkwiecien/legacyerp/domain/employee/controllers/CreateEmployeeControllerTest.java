package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class CreateEmployeeControllerTest {
    private static final String NEW_EMPLOYEE_URI = "/employee/new";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "example@example.com";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void shouldAddNewEmployeeFromRequestAndSaveInRepository() throws Exception {
        // when :
        ResultActions result = mockMvc.perform(post(NEW_EMPLOYEE_URI)
                .param("firstName", FIRST_NAME)
                .param("lastName", LAST_NAME)
                .param("email", EMAIL));

        // than :
        result.andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        Assertions.assertEquals(1, employeeRepository.count());
    }

    @BeforeEach
    void setup() {
        cleanup();
    }

    @AfterEach
    void cleanup() {
        employeeRepository.deleteAll();
    }
}