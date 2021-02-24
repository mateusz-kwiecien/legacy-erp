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
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class CreateDepartmentControllerTest {

    private static final String DEPARTMENTS_URI = "/departments";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void shouldCreateNewDepartmentFromRequestAndSaveInRepository() throws Exception {
        // given :
        Long managerId = 1L;

        // when :
        ResultActions result = mockMvc.perform(post(DEPARTMENTS_URI)
                .param("managerId", managerId.toString()));

        // then :
        result.andExpect(status().is3xxRedirection());
        Assertions.assertEquals(1, departmentRepository.count());
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