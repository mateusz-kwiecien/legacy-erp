package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.hamcrest.Matchers;
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

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.aDepartmentWith;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class DepartmentListControllerTest {
    private static final String DEPARTMENTS_URI = "/departments";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldRetrieveListOfAllDepartments() throws Exception {
        // given :
        departmentRepository.saveAll(existingDepartments());

        // when :
        ResultActions result = mockMvc.perform(get(DEPARTMENTS_URI));

        // then :
        result.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("departments", Matchers.hasSize(2)));
    }

    private static List<Department> existingDepartments() {
        return Arrays.asList(aDepartmentWith(1L), aDepartmentWith(2L));
    }

    @BeforeEach
    void setup() {
        cleanup();
    }

    @AfterEach
    void cleanup() {
        departmentRepository.deleteAll();
    }
}