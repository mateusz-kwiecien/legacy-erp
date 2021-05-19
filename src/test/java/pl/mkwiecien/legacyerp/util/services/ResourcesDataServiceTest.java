package pl.mkwiecien.legacyerp.util.services;

import static pl.mkwiecien.legacyerp.util.UtilDataPopulationService.populateWithDefaultData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
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
        populateWithDefaultData(employeeRepository, departmentRepository);

        // when :
        ResourcesData resourcesData = resourcesDataPort.getResources();

        // then :
        Assertions.assertEquals(3, resourcesData.getEmployeesNumber());
        Assertions.assertEquals(2, resourcesData.getUnassignedEmployeesNumber());
        Assertions.assertEquals(1, resourcesData.getDepartmentsNumber());
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