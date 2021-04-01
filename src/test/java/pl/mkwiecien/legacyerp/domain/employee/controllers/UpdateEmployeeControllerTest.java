package pl.mkwiecien.legacyerp.domain.employee.controllers;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.aDepartmentWith;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.aDepartmentWithOnlyName;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class UpdateEmployeeControllerTest {
    private static final String EMPLOYEE_DETAILS_URI = "/employees/details?id=";
    private static final String DEPARTMENT_NAME = "department-01";
    private static final String EMPLOYEE_UPDATED_FIRST_NAME = "updatedFirstName";
    private static final String EMPLOYEE_UPDATED_LAST_NAME = "updatedLastName";
    private static final String EMPLOYEE_UPDATED_EMAIL = "updatedEmail@example.com";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldRetrieveEmployeeModel() throws Exception {
        // given :
        Employee employee = employeeRepository.save(anEmployee());
        Department department = departmentRepository.save(aDepartmentWith(DEPARTMENT_NAME, employee.getId()));
        employeeRepository.save(Employee.Builder.builder().from(employee).department(department).build());
        String employeeDetailsUri = EMPLOYEE_DETAILS_URI + employee.getId();

        // when :
        ResultActions result = mockMvc.perform(get(employeeDetailsUri));

        // then :
        result.andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute(EMPLOYEE_REQUEST_PARAM_NAME, hasProperty(EMPLOYEE_ID_PARAM_NAME, is(employee.getId()))))
                .andExpect(model().attribute(EMPLOYEE_REQUEST_PARAM_NAME, hasProperty(EMPLOYEE_FIRST_NAME_PARAM_NAME, is(employee.getFirstName()))))
                .andExpect(model().attribute(EMPLOYEE_REQUEST_PARAM_NAME, hasProperty(EMPLOYEE_LAST_NAME_PARAM_NAME, is(employee.getLastName()))))
                .andExpect(model().attribute(EMPLOYEE_REQUEST_PARAM_NAME, hasProperty(EMPLOYEE_EMAIL_PARAM_NAME, is(employee.getEmail()))))
                .andExpect(model().attribute(EMPLOYEE_REQUEST_PARAM_NAME, hasProperty(EMPLOYEE_DEPARTMENT_NAME_PARAM, is(department.getName()))));
    }

    @Test
    void shouldUpdateGivenEmployee() throws Exception {
        // given :
        Employee employee = employeeRepository.save(anEmployee());
        String employeeUpdateUri = EMPLOYEES_URI + "/" + employee.getId();
        Department department = departmentRepository.save(aDepartmentWithOnlyName(DEPARTMENT_NAME));

        // when :
        ResultActions result = mockMvc.perform(put(employeeUpdateUri)
                .param(EMPLOYEE_FIRST_NAME_PARAM_NAME, EMPLOYEE_UPDATED_FIRST_NAME)
                .param(EMPLOYEE_LAST_NAME_PARAM_NAME, EMPLOYEE_UPDATED_LAST_NAME)
                .param(EMPLOYEE_EMAIL_PARAM_NAME, EMPLOYEE_UPDATED_EMAIL)
                .param(EMPLOYEE_DEPARTMENT_NAME_PARAM, DEPARTMENT_NAME));

        // then :
        result.andExpect(status().is3xxRedirection());
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        Department updatedDepartment = departmentRepository.findByName(DEPARTMENT_NAME).get();
        assertEquals(EMPLOYEE_UPDATED_FIRST_NAME, updatedEmployee.getFirstName());
        assertEquals(EMPLOYEE_UPDATED_LAST_NAME, updatedEmployee.getLastName());
        assertEquals(EMPLOYEE_UPDATED_EMAIL, updatedEmployee.getEmail());
        assertEquals(DEPARTMENT_NAME, updatedEmployee.getDepartment().getName());
        assertThat(updatedDepartment.getEmployees(), contains(updatedEmployee));
    }

    @Test
    void shouldReturnErrorsAndCreateViewWhenRequestIsIncorrect() throws Exception {
        // given :
        Employee existingEmployee = employeeRepository.save(anEmployee());
        String employeeUpdateUri = EMPLOYEES_URI + "/" + existingEmployee.getId();
        String incorrectEmail = "incorrectEmail";
        String emptyParamValue = "";

        // when :
        ResultActions result = mockMvc.perform(put(employeeUpdateUri)
                .param(EMPLOYEE_FIRST_NAME_PARAM_NAME, emptyParamValue)
                .param(EMPLOYEE_LAST_NAME_PARAM_NAME, emptyParamValue)
                .param(EMPLOYEE_EMAIL_PARAM_NAME, incorrectEmail));

        // then :
        result.andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeErrorCount(EMPLOYEE_REQUEST_PARAM_NAME, 3));
        Employee givenEmployee = employeeRepository.findById(existingEmployee.getId()).get();
        assertEquals(givenEmployee.getFirstName(), existingEmployee.getFirstName());
        assertEquals(givenEmployee.getLastName(), existingEmployee.getLastName());
        assertEquals(givenEmployee.getEmail(), existingEmployee.getEmail());
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