package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.mkwiecien.legacyerp.application.ApplicationTestConfiguration;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentListView;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.*;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.anEmployeeWith;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationTestConfiguration.class})
class GetDepartmentListControllerTest {
    private static final String FIRST_DEPARTMENT_NAME = "dep-01";
    private static final String SECOND_DEPARTMENT_NAME = "dep-02";
    private static final String MANAGER_FIRST_NAME = "John";
    private static final String MANAGER_LAST_NAME = "Doe";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldRetrieveListOfAllDepartments() throws Exception {
        // given :
        Employee manager = employeeRepository.save(firstDepartmentManager());
        Department firstDepartment = departmentRepository.save(aDepartmentWith(FIRST_DEPARTMENT_NAME, manager.getId()));
        departmentRepository.save(aDepartmentWithOnlyName(SECOND_DEPARTMENT_NAME));

        saveEmployeeAndAssignTo(anEmployeeWith("Jack", "Black", "jack.black@example.com"), firstDepartment);
        saveEmployeeAndAssignTo(anEmployeeWith("Kevin", "Brown", "kevin.brown@example.com"), firstDepartment);

        // when :
        ResultActions result = mockMvc.perform(get(DEPARTMENTS_URI));

        // then :
        result.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        PageImpl<DepartmentListView> page = (PageImpl<DepartmentListView>) result.andReturn().getModelAndView().getModel().get("departmentsPage");
        Assertions.assertTrue(page.isLast());
        Assertions.assertEquals(2, page.getTotalElements());

        List<DepartmentListView> retrievedDepartments = page.getContent();
        Assertions.assertEquals(retrievedDepartments.get(0).getName(), FIRST_DEPARTMENT_NAME);
        Assertions.assertEquals(retrievedDepartments.get(0).getManager(), MANAGER_FIRST_NAME + " " + MANAGER_LAST_NAME);
        Assertions.assertEquals(retrievedDepartments.get(0).getEmployees(), 2);
        Assertions.assertEquals(retrievedDepartments.get(1).getName(), SECOND_DEPARTMENT_NAME);
        Assertions.assertEquals(retrievedDepartments.get(1).getManager(), "");
        Assertions.assertEquals(retrievedDepartments.get(1).getEmployees(), 0);
    }

    private static Employee firstDepartmentManager() {
        return anEmployeeWith(MANAGER_FIRST_NAME, MANAGER_LAST_NAME, "john.doe@example.com");
    }

    private void saveEmployeeAndAssignTo(Employee employee, Department department) {
        Employee given = employeeRepository.save(employee);
        employee.setDepartment(department);
        employeeRepository.save(given);
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