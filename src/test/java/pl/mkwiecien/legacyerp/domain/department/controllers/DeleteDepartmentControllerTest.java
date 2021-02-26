package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import pl.mkwiecien.legacyerp.util.services.DataPopulationService;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.DEPARTMENTS_URI;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.aDepartmentWith;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class DeleteDepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DataPopulationService dataPopulationService;

    // TODO implement department deleting logic
    void shouldDeleteDepartmentAndRemoveDepartmentIdFromGivenEmployees() throws Exception {
        // given :
        dataPopulationService.populateWithRandomData(Optional.of(3));
        Employee departmentManager = employeeRepository.findAll().get(0);
        Employee firstDepartmentEmployee = employeeRepository.findAll().get(1);
        Employee secondDepartmentEmployee = employeeRepository.findAll().get(2);
        Department aDepartment = departmentRepository.save(aDepartmentWith(departmentManager.getId()));
        firstDepartmentEmployee.setDepartment(aDepartment);
        secondDepartmentEmployee.setDepartment(aDepartment);
        employeeRepository.saveAll(Arrays.asList(departmentManager, firstDepartmentEmployee, secondDepartmentEmployee));
        String deleteUri = DEPARTMENTS_URI + "/" + aDepartment.getId();

        // when :
        ResultActions result = mockMvc.perform(delete(deleteUri));

        // then :
        result.andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        assertEquals(3, employeeRepository.findAll().size());
        assertEquals(0, departmentRepository.findAll().size());
        assertThat(employeeRepository.findAll(), everyItem(hasProperty("department", is(null))));
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