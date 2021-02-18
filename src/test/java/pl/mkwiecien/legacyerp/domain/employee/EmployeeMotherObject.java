package pl.mkwiecien.legacyerp.domain.employee;

import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

public class EmployeeMotherObject {
    public static final String ID_PARAM_NAME = "id";
    public static final String FIRST_NAME_PARAM_NAME = "firstName";
    public static final String LAST_NAME_PARAM_NAME = "lastName";
    public static final String EMAIL_PARAM_NAME = "email";

    public static Employee anEmployee() {
        return new Employee("firstName", "lastName", "employee@example.com");
    }
}
