package pl.mkwiecien.legacyerp.domain.department.controllers;

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
import org.springframework.web.util.NestedServletException;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.*;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.anEmployeeWith;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class CreateDepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldCreateNewDepartmentFromRequestAndSaveInRepository() throws Exception {
        // given :
        Long managerId = 1L;

        // when :
        ResultActions result = mockMvc.perform(post(DEPARTMENTS_URI)
                .param("managerId", managerId.toString())
                .param("name", DEFAULT_DEPARTMENT_NAME));

        // then :
        result.andExpect(status().is3xxRedirection());
        Assertions.assertEquals(1, departmentRepository.count());
    }

    @Test
    void shouldReturnErrorWhenManagerIsAssignmentToOtherDepartment() throws Exception {
        // given :
        Employee manager = employeeRepository.save(anEmployeeWith("John", "Doe", "john.doe@example.com"));
        departmentRepository.save(aDepartmentWith("dep-01", manager.getId()));

        // when :
        ResultActions result = mockMvc.perform(post(DEPARTMENTS_URI)
                .param("managerId", manager.getId().toString())
                .param("name", DEFAULT_DEPARTMENT_NAME));

        // then :
        result.andExpect(MockMvcResultMatchers.view().name("error"));
    }

    @BeforeEach
    void setup() {
        cleanup();
    }

    @AfterEach
    void cleanup() {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();
    }
}