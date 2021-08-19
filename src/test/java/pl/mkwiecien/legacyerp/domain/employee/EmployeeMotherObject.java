package pl.mkwiecien.legacyerp.domain.employee;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import java.util.ArrayList;
import java.util.List;

import static pl.mkwiecien.legacyerp.domain.employee.entity.Employee.Builder.builder;

public class EmployeeMotherObject {

    public static final String EMPLOYEES_URI = "/employees";
    public static final String EMPLOYEE_ID_PARAM_NAME = "id";
    public static final String EMPLOYEE_FIRST_NAME_PARAM_NAME = "firstName";
    public static final String EMPLOYEE_LAST_NAME_PARAM_NAME = "lastName";
    public static final String EMPLOYEE_EMAIL_PARAM_NAME = "email";
    public static final String EMPLOYEE_DEPARTMENT_NAME_PARAM = "departmentName";
    public static final String EMPLOYEE_REQUEST_PARAM_NAME = "employeeRequest";

    public static Employee anEmployee() {
        return builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("employee@example.com")
                .build();
    }

    public static Employee anEmployeeWith(String firstName, String lastName, String email) {
        return anEmployeeWith(null, firstName, lastName, email, null);
    }

    public static Employee anEmployeeWith(Long id, String firstName, String lastName, String email) {
        return anEmployeeWith(id, firstName, lastName, email, null);
    }

    public static Employee anEmployeeWith(String firstName, String lastName, String email, Department department) {
        return anEmployeeWith(null, firstName, lastName, email, department);
    }

    public static Employee anEmployeeWith(Long id, String firstName, String lastName, String email, Department department) {
        return builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .department(department)
                .build();
    }

    public static List<Employee> anEmployeeFakeList(int range) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= range; i++) {
            employees.add(anEmployeeWith("firstName-" + i, "lastName-" + i, "email-" + i));
        }
        return employees;
    }
}
