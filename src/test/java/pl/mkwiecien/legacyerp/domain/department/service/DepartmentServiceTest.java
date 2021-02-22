package pl.mkwiecien.legacyerp.domain.department.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;
import pl.mkwiecien.legacyerp.util.services.DataPopulationService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class DepartmentServiceTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DataPopulationService dataPopulationService;

    @Test
    public void shouldSaveAndProperlyRetrievedDepartmentData() throws Exception {
        // given :
            dataPopulationService.populateWithRandomData(Optional.of(3));
            Employee departmentManager = employeeRepository.findAll().get(0);
            Employee firstDepartmentEmployee = employeeRepository.findAll().get(1);
            Employee secondDepartmentEmployee = employeeRepository.findAll().get(2);
            Department aDepartment = departmentRepository.save(new Department(departmentManager.getId()));

        // when :
            firstDepartmentEmployee.setDepartment(aDepartment);
            secondDepartmentEmployee.setDepartment(aDepartment);
            employeeRepository.saveAll(Arrays.asList(departmentManager, firstDepartmentEmployee, secondDepartmentEmployee));

        // then :
            assertEquals(departmentManager.getId(), departmentRepository.findAll().get(0).getManagerId());
            assertTrue(departmentRepository.findAll().get(0).getEmployees().contains(firstDepartmentEmployee));
            assertTrue(departmentRepository.findAll().get(0).getEmployees().contains(secondDepartmentEmployee));
            assertEquals(3, employeeRepository.findAll().size());
            assertEquals(1, departmentRepository.findAll().size());
    }
}