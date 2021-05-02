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
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void shouldRetrieveEmptyModelAndProperListOfPotentialManagers() throws Exception {
        // given :
        Employee manager = employeeRepository.save(anEmployeeWith("John", "Doe", "john.doe@example.com"));
        Employee employee = employeeRepository.save(anEmployeeWith("Michael", "Black", "michael.black@example.com"));
        departmentRepository.save(aDepartmentWith("dep-01", manager.getId()));
        String uri = DEPARTMENTS_URI + "/create";

        // when :
        ResultActions result = mockMvc.perform(get(uri));

        // then :
        result.andExpect(MockMvcResultMatchers.status().isOk());
        List<Employee> potentialManagers = (List<Employee>) result.andReturn().getModelAndView().getModel().get("potentialManagers");
        Assertions.assertEquals(1, potentialManagers.size());
        Assertions.assertTrue(potentialManagers.contains(employee));
    }

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

    @Test
    void shouldReturnErrorWhenDepartmentNameIsAlreadyUsed() throws Exception {
        // given :
        String existingName = "dep-01";
        departmentRepository.save(aDepartmentWithOnlyName(existingName));

        // when :
        ResultActions result = mockMvc.perform(post(DEPARTMENTS_URI)
                .param("name", existingName));

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