package pl.mkwiecien.legacyerp.domain.employee.controllers;

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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.EMPLOYEES_URI;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.anEmployee;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class DeleteEmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void shouldDeleteEmployeeWithGivenId() throws Exception {
        // given :
        Employee employee = anEmployee();
        Long id = employeeRepository.save(employee).getId();

        // when :
        String deleteUri = EMPLOYEES_URI + "/" + id;
        ResultActions result = mockMvc.perform(delete(deleteUri));

        // then :
        result.andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        assertTrue(employeeRepository.findById(id).isEmpty());
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