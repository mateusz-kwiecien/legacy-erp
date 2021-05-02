package pl.mkwiecien.legacyerp.domain.department.controllers;

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
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.*;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.anEmployeeWith;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class UpdateDepartmentControllerTest {
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String NEW_DEPARTMENT_NAME = "newDepartmentName";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldUpdateDepartmentNameAndManager() throws Exception {
        // given :
        Employee manager = employeeRepository.save(anEmployeeWith("John", "Doe", "john.doe@example.com"));
        Department aDepartment = departmentRepository.save(aDepartmentWithOnlyName(DEPARTMENT_NAME));

        // when :
        ResultActions result = mockMvc.perform(put(DEPARTMENTS_URI)
                .param("id", aDepartment.getId().toString())
                .param("managerId", manager.getId().toString())
                .param("name", NEW_DEPARTMENT_NAME));

        // then :
        result.andExpect(status().is3xxRedirection());
        Department updated = departmentRepository.findById(aDepartment.getId()).get();
        assertEquals(manager.getId(), updated.getManagerId());
        assertEquals(NEW_DEPARTMENT_NAME, updated.getName());
    }

    @Test
    void shouldReturnErrorWhenManagerIsAssignmentToOtherDepartment() throws Exception {
        // given :
        Employee manager = employeeRepository.save(anEmployeeWith("John", "Doe", "john.doe@example.com"));
        departmentRepository.save(aDepartmentWith(DEPARTMENT_NAME, manager.getId()));
        Department newDepartment = departmentRepository.save(aDepartmentWithOnlyName(NEW_DEPARTMENT_NAME));

        // when :
        ResultActions result = mockMvc.perform(put(DEPARTMENTS_URI)
                .param("id", newDepartment.getId().toString())
                .param("managerId", manager.getId().toString())
                .param("name", newDepartment.getName()));

        // then :
        result.andExpect(MockMvcResultMatchers.view().name("error"));
    }

    @Test
    void shouldReturnErrorDepartmentNameIsAlreadyUsed() throws Exception {
        // given :
        String existingName = "existingName";
        departmentRepository.save(aDepartmentWithOnlyName(existingName));
        Department newDepartment = departmentRepository.save(aDepartmentWithOnlyName(NEW_DEPARTMENT_NAME));

        // when :
        ResultActions result = mockMvc.perform(put(DEPARTMENTS_URI)
                .param("id", newDepartment.getId().toString())
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