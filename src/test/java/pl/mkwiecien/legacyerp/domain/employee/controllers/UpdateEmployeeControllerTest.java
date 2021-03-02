package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class UpdateEmployeeControllerTest {

    private static final String EMPLOYEE_UPDATE_URI = "/employee/update/";
    private static final String EMPLOYEE_DETAILS_URI = "/employee/details?id=";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void shouldRetrieveEmployeeModel() throws Exception {
        // given :
        Employee employee = employeeRepository.save(anEmployee());
        String employeeDetailsUri = EMPLOYEE_DETAILS_URI + employee.getId();

        // when :
        ResultActions result = mockMvc.perform(get(employeeDetailsUri));

        // then :
        result.andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("employeeRequest", hasProperty(EMPLOYEE_ID_PARAM_NAME, is(employee.getId()))))
                .andExpect(model().attribute("employeeRequest", hasProperty(EMPLOYEE_FIRST_NAME_PARAM_NAME, is(employee.getFirstName()))))
                .andExpect(model().attribute("employeeRequest", hasProperty(EMPLOYEE_LAST_NAME_PARAM_NAME, is(employee.getLastName()))))
                .andExpect(model().attribute("employeeRequest", hasProperty(EMPLOYEE_EMAIL_PARAM_NAME, is(employee.getEmail()))));
    }

    @Test
    void shouldUpdateGivenEmployee() throws Exception {
        // given :
        Employee employee = employeeRepository.save(anEmployee());
        String employeeUpdateUri = EMPLOYEE_UPDATE_URI + employee.getId();
        String updatedFirstName = "updatedFirstName";
        String updatedLastName = "updatedLastName";
        String updatedEmail = "updatedEmail@example.com";

        // when :
        ResultActions result = mockMvc.perform(put(employeeUpdateUri)
                .param(EMPLOYEE_FIRST_NAME_PARAM_NAME, updatedFirstName)
                .param(EMPLOYEE_LAST_NAME_PARAM_NAME, updatedLastName)
                .param(EMPLOYEE_EMAIL_PARAM_NAME, updatedEmail));

        // then :
        result.andExpect(status().is3xxRedirection());
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        assertEquals(updatedFirstName, updatedEmployee.getFirstName());
        assertEquals(updatedLastName, updatedEmployee.getLastName());
        assertEquals(updatedEmail, updatedEmployee.getEmail());
    }

    @Test
    void shouldReturnErrorsAndCreateViewWhenRequestIsIncorrect() throws Exception {
        // given :
        Employee existingEmployee = employeeRepository.save(anEmployee());
        String employeeUpdateUri = EMPLOYEE_UPDATE_URI + existingEmployee.getId();
        String incorrectEmail = "incorrectEmail";
        String emptyParamValue = "";

        // when :
        ResultActions result = mockMvc.perform(put(employeeUpdateUri)
                .param(EMPLOYEE_FIRST_NAME_PARAM_NAME, emptyParamValue)
                .param(EMPLOYEE_LAST_NAME_PARAM_NAME, emptyParamValue)
                .param(EMPLOYEE_EMAIL_PARAM_NAME, incorrectEmail));

        // then :
        result.andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeErrorCount("employeeRequest", 3));
        Employee givenEmployee = employeeRepository.findById(existingEmployee.getId()).get();
        assertEquals(givenEmployee.getFirstName(), existingEmployee.getFirstName());
        assertEquals(givenEmployee.getLastName(), existingEmployee.getLastName());
        assertEquals(givenEmployee.getEmail(), existingEmployee.getEmail());
    }
}