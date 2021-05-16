package pl.mkwiecien.legacyerp.util.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.util.ports.GetResourcesDataPort;
import pl.mkwiecien.legacyerp.util.values.ResourcesData;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class MainPageControllerTest {
    private static final String RESOURCES_DATA_ATTRIBUTE_NAME = "ResourcesData";
    private static final String EMPLOYEES_NUMBER_PARAMETER_NAME = "employeesNumber";
    private static final String UNASSIGNED_EMPLOYEES_NUMBER_PARAMETER_NAME = "unassignedEmployeesNumber";
    private static final String MANAGERS_NUMBER_PARAMETER_NAME = "managersNumber";
    private static final String DEPARTMENTS_NUMBER_PARAMETER_NAME = "departmentsNumber";
    private static final Long EMPLOYEES_NUMBER = 15L;
    private static final Long UNASSIGNED_EMPLOYEES_NUMBER = 3L;
    private static final Long MANAGERS_NUMBER = 5L;
    private static final Long DEPARTMENTS_NUMBER = 5L;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GetResourcesDataPort resourcesDataPort;

    @Test
    void shouldPopulateApplicationWithRandomEmployees() throws Exception {
        // given :
        Mockito.when(resourcesDataPort.getResources())
                .thenReturn(mockedData());
        String mainPageUri = "/";

        // when :
        ResultActions result = mockMvc.perform(get(mainPageUri));

        // then :
        result.andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute(RESOURCES_DATA_ATTRIBUTE_NAME,
                        hasProperty(EMPLOYEES_NUMBER_PARAMETER_NAME, is(EMPLOYEES_NUMBER))))
                .andExpect(model().attribute(RESOURCES_DATA_ATTRIBUTE_NAME,
                        hasProperty(UNASSIGNED_EMPLOYEES_NUMBER_PARAMETER_NAME, is(UNASSIGNED_EMPLOYEES_NUMBER))))
                .andExpect(model().attribute(RESOURCES_DATA_ATTRIBUTE_NAME,
                        hasProperty(DEPARTMENTS_NUMBER_PARAMETER_NAME, is(DEPARTMENTS_NUMBER))));
    }

    private static ResourcesData mockedData() {
        return new ResourcesData(EMPLOYEES_NUMBER, UNASSIGNED_EMPLOYEES_NUMBER, DEPARTMENTS_NUMBER);
    }
}