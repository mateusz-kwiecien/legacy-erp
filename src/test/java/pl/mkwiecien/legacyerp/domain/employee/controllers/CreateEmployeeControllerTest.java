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
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.*;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.EmployeeUriResolver.getNewEmployeeUri;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class CreateEmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void shouldAddNewEmployeeFromRequestAndSaveInRepository() throws Exception {
        // given :
        Employee correctEmployee = EmployeeMotherObject.aRandomEmployee();

        // when :
        ResultActions result = mockMvc.perform(post(getNewEmployeeUri())
                .param(FIRST_NAME_PARAM_NAME, correctEmployee.getFirstName())
                .param(LAST_NAME_PARAM_NAME, correctEmployee.getLastName())
                .param(EMAIL_PARAM_NAME, correctEmployee.getEmail()));

        // than :
        result.andExpect(status().is3xxRedirection());
        Assertions.assertEquals(1, employeeRepository.count());
    }

    @Test
    void shouldReturnErrorsAndCreateViewWhenRequestIsIncorrect() throws Exception {
        // given :
        String incorrectEmail = "incorrectEmail";
        String emptyParamValue = "";

        // when :
        ResultActions result = mockMvc.perform(post(getNewEmployeeUri())
                .param(FIRST_NAME_PARAM_NAME, emptyParamValue)
                .param(LAST_NAME_PARAM_NAME, emptyParamValue)
                .param(EMAIL_PARAM_NAME, incorrectEmail));

        // then :
        result.andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeErrorCount("employeeRequest", 3));
        Assertions.assertEquals(0, employeeRepository.count());
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