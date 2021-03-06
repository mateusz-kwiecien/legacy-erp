package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeListView;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.EMPLOYEES_URI;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.anEmployeeWith;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class EmployeeListControllerTest {
    private static final String EMPTY_VALUE = "";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void shouldRetrieveListOfAllEmployees() throws Exception {
        // given :
        Employee managerWithoutDepartment = employeeRepository.save(anEmployeeWith("John", "Doe", "john.doe@example.com"));
        Employee assignedToDepartmentManager = employeeRepository.save(anEmployeeWith("Jack", "Black", "jack.black@example.com"));
        Employee assignedToDepartmentEmployee = employeeRepository.save(anEmployeeWith("Robert", "Brown", "robert.brown@example.com"));
        Employee employeeWithoutDepartment = employeeRepository.save(anEmployeeWith("Joan", "Smith", "joan.smith@example.com"));
        Department firstDepartment = departmentRepository.save(DepartmentMotherObject.aDepartmentWith("dep-01", managerWithoutDepartment.getId()));
        Department secondDepartment = departmentRepository.save(DepartmentMotherObject.aDepartmentWith("dep-02", assignedToDepartmentManager.getId()));
        assignEmployeeToDepartment(assignedToDepartmentManager, firstDepartment);
        assignEmployeeToDepartment(assignedToDepartmentEmployee, firstDepartment);

        // when :
        ResultActions result = mockMvc.perform(get(EMPLOYEES_URI));

        // then :
        result.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("employees", Matchers.hasSize(4)));
        List<EmployeeListView> retrievedEmployees = (List<EmployeeListView>) result.andReturn().getModelAndView().getModel().get("employees");
        retrievedEmployees.sort(Comparator.comparing(EmployeeListView::getId));
        assertCorrect(retrievedEmployees.get(0), managerWithoutDepartment, EMPTY_VALUE, firstDepartment.getName());
        assertCorrect(retrievedEmployees.get(1), assignedToDepartmentManager, firstDepartment.getName(), secondDepartment.getName());
        assertCorrect(retrievedEmployees.get(2), assignedToDepartmentEmployee, firstDepartment.getName(), EMPTY_VALUE);
        assertCorrect(retrievedEmployees.get(3), employeeWithoutDepartment, EMPTY_VALUE, EMPTY_VALUE);
    }

    private void assignEmployeeToDepartment(Employee employee, Department department) {
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }

    private void assertCorrect(EmployeeListView employeeListView, Employee employee,
                               String assignedDepartment, String subordinatedDepartment) {
        assertEquals(employeeListView.getId(), employee.getId());
        assertEquals(employeeListView.getFullName(), employee.getFullName());
        assertEquals(employeeListView.getEmail(), employee.getEmail());
        assertEquals(employeeListView.getAssignedDepartment(), assignedDepartment);
        assertEquals(employeeListView.getSubordinateDepartment(), subordinatedDepartment);
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