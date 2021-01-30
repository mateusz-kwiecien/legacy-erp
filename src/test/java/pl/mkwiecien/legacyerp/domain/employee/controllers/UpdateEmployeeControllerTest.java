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
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.EmployeeUriResolver.getEmployeeUpdateUriFor;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.EmployeeUriResolver.getEmployeeUriFor;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.aRandomEmployee;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class UpdateEmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void shouldRetrieveEmployeeModel() throws Exception {
        // given :
        Employee employee = employeeRepository.save(aRandomEmployee());
        String employeeDetailsUri = getEmployeeUriFor(employee.getId());

        // when :
        ResultActions result = mockMvc.perform(get(employeeDetailsUri));

        // then :
        result.andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("employee", hasProperty("firstName", is(employee.getFirstName()))))
                .andExpect(model().attribute("employee", hasProperty("lastName", is(employee.getLastName()))))
                .andExpect(model().attribute("employee", hasProperty("email", is(employee.getEmail()))));
    }

    @Test
    void shouldUpdateGivenEmployee() throws Exception {
        // given :
        Employee employee = employeeRepository.save(aRandomEmployee());
        String employeeUpdateUri = getEmployeeUpdateUriFor(employee.getId());
        String updatedFirstName = "updatedFirstName";
        String updatedLastName = "updatedFirstName";
        String updatedEmail = "updatedFirstName";

        // when :
        ResultActions result = mockMvc.perform(put(employeeUpdateUri)
                .param("firstName", updatedFirstName)
                .param("lastName", updatedLastName)
                .param("email", updatedEmail));

        // then :
        result.andExpect(status().is3xxRedirection());
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        assertEquals(updatedEmployee.getFirstName(), updatedFirstName);
        assertEquals(updatedEmployee.getLastName(), updatedLastName);
        assertEquals(updatedEmployee.getEmail(), updatedEmail);
    }
}