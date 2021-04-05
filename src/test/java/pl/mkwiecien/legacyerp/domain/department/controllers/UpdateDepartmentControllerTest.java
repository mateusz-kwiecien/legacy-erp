package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;
import pl.mkwiecien.legacyerp.util.services.DataPopulationService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class UpdateDepartmentControllerTest {
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String NEW_DEPARTMENT_NAME = "newDepartmentName";
    private static final String NEW_DEPARTMENT_MANAGER_ID = "7";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DataPopulationService dataPopulationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldRetrieveDepartmentModel() throws Exception {
        // given :
        dataPopulationService.populateWithRandomData(Optional.of(3));
        List<Employee> employees = employeeRepository.findAll();
        Employee departmentManager = employees.get(0);
        Department aDepartment = departmentRepository.save(aDepartmentWith(DEPARTMENT_NAME, departmentManager.getId()));
        Employee firstDepartmentEmployee = Employee.Builder.builder().from(employees.get(1)).department(aDepartment).build();
        Employee secondDepartmentEmployee = Employee.Builder.builder().from(employees.get(2)).department(aDepartment).build();
        aDepartment.setEmployees(Set.of(firstDepartmentEmployee, secondDepartmentEmployee));
        departmentRepository.save(aDepartment);
        String departmentUri = DEPARTMENTS_URI + "/" + aDepartment.getId() + "/details";

        // when :
        ResultActions result = mockMvc.perform(get(departmentUri));

        // then :
        result.andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("departmentRequest", hasProperty(DEPARTMENT_ID_PARAM_NAME, is(aDepartment.getId()))))
                .andExpect(model().attribute("departmentRequest", hasProperty(DEPARTMENT_NAME_PARAM_NAME, is(aDepartment.getName()))))
                .andExpect(model().attribute("departmentRequest", hasProperty(DEPARTMENT_MANAGER_ID_PARAM_NAME, is(aDepartment.getManagerId()))))
                .andExpect(model().attribute("departmentRequest", hasProperty(DEPARTMENT_EMPLOYEES_PARAM_NAME, hasItem(firstDepartmentEmployee))))
                .andExpect(model().attribute("departmentRequest", hasProperty(DEPARTMENT_EMPLOYEES_PARAM_NAME, hasItem(secondDepartmentEmployee))));
    }

    @Test
    void shouldUpdateGivenDepartment() throws Exception {
        // given :
        Department aDepartment = departmentRepository.save(aDepartmentWithOnlyName(DEPARTMENT_NAME));
        String departmentUri = DEPARTMENTS_URI + "/" + aDepartment.getId();

        // when :
        ResultActions result = mockMvc.perform(put(departmentUri)
                .param(DEPARTMENT_ID_PARAM_NAME, aDepartment.getId().toString())
                .param(DEPARTMENT_NAME_PARAM_NAME, NEW_DEPARTMENT_NAME)
                .param(DEPARTMENT_MANAGER_ID_PARAM_NAME, NEW_DEPARTMENT_MANAGER_ID));

        // then :
        result.andExpect(status().is3xxRedirection());
        Department updated = departmentRepository.findById(aDepartment.getId()).get();
        assertEquals(NEW_DEPARTMENT_NAME, updated.getName());
        assertEquals(NEW_DEPARTMENT_MANAGER_ID, updated.getManagerId().toString());
    }

    private Department createDepartmentWithEmployees(List<Employee> employees) {
        Department aDepartment = departmentRepository.save(aDepartmentWith(DEPARTMENT_NAME, employees.get(0).getId()));
        aDepartment.setEmployees(Set.of(employees.get(1), employees.get(2)));
        return departmentRepository.save(aDepartment);
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