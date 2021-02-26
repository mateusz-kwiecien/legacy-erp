package pl.mkwiecien.legacyerp.domain.employee.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;
import pl.mkwiecien.legacyerp.util.services.DataPopulationService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.aDepartmentWith;

@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class EmployeeServiceTest {

    private static final Long DEPARTMENT_MANAGER_ID = 1L;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DataPopulationService dataPopulationService;

    @Test
    void shouldRetrieveAllEmployeesFromGivenDepartment() throws Exception {
        // given :
        dataPopulationService.populateWithRandomData(Optional.of(5));
        Department department = departmentRepository.save(aDepartmentWith(DEPARTMENT_MANAGER_ID));
        setDepartmentForEmployees(2, department);

        // then :
        assertEquals(5, employeeRepository.findAll().size());
        assertEquals(2, employeeRepository.findAllByDepartmentId(department.getId()).size());

    }

    private void setDepartmentForEmployees(long numberOfEmployees, Department department) {
        employeeRepository.findAll().stream()
                .limit(numberOfEmployees)
                .peek(e -> e.setDepartment(department))
                .forEach(employeeRepository::save);
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