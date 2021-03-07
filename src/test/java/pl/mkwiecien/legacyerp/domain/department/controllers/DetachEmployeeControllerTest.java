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
import pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;
import pl.mkwiecien.legacyerp.util.services.DataPopulationService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class DetachEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataPopulationService dataPopulationService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldDetachEmployeeFromGivenDepartment() throws Exception {
        // given :
        Employee employee = dataPopulationService.populateWithRandomData(Optional.of(1)).get(0);
        Department department = departmentRepository.save(DepartmentMotherObject.aDepartment());
        employee.setDepartment(department);
        employeeRepository.save(employee);
        String detachEmployeeUri = DepartmentMotherObject.DEPARTMENTS_URI + "/detach/" + employee.getId();

        // when :
        ResultActions result = mockMvc.perform(put(detachEmployeeUri));

        // then :
        result.andExpect(status().is3xxRedirection());
        Assertions.assertNull(employeeRepository.findById(employee.getId()).get().getDepartment());
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