package pl.mkwiecien.legacyerp.util.services;

import java.util.List;

import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.*;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;
import pl.mkwiecien.legacyerp.util.ports.GetResourcesDataPort;
import pl.mkwiecien.legacyerp.util.values.ResourcesData;

@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class ResourcesDataServiceTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private GetResourcesDataPort resourcesDataPort;

    @Test
    void shouldRetrieveCorrectResourcesData() throws Exception {
        // given :
        employeeRepository.saveAll(anEmployeeList());
        departmentRepository.saveAll(aDepartmentList());

        // when :
        ResourcesData resourcesData = resourcesDataPort.getResources();

        // then :
        Assertions.assertEquals(3, resourcesData.getEmployeesNumber());
        Assertions.assertEquals(2, resourcesData.getDepartmentsNumber());
    }

    private List<Employee> anEmployeeList() {
        return List.of(
                anEmployeeWith("John", "Doe", "john.doe@example.com"),
                anEmployeeWith("Marla", "Singer", "marla.singer@example.com"),
                anEmployeeWith("Diego", "Sanchez", "diego.sanchez@example.com")
        );
    }

    private List<Department> aDepartmentList() {
        return List.of(aDepartmentWithOnlyName("dep-01"), aDepartmentWithOnlyName("dep-02"));
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