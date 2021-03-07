package pl.mkwiecien.legacyerp.domain.employee;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import static pl.mkwiecien.legacyerp.domain.employee.entity.Employee.Builder.builder;

public class EmployeeMotherObject {
    public static final String EMPLOYEE_ID_PARAM_NAME = "id";
    public static final String EMPLOYEE_FIRST_NAME_PARAM_NAME = "firstName";
    public static final String EMPLOYEE_LAST_NAME_PARAM_NAME = "lastName";
    public static final String EMPLOYEE_EMAIL_PARAM_NAME = "email";
    public static final String EMPLOYEE_DEPARTMENT_PARAM_NAME = "department";
    public static final String EMPLOYEE_DEPARTMENT_NAME_PARAM_NAME = "name";
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

    public static Employee anEmployeeWith(Long id, String firstName, String lastName, String email, Department department) {
        return builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .department(department)
                .build();
    }
}
