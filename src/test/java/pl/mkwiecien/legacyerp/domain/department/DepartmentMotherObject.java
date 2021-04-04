package pl.mkwiecien.legacyerp.domain.department;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import java.util.Collections;
import java.util.Set;

public class DepartmentMotherObject {
    public static final String DEPARTMENTS_PARAMETER_NAME = "departments";
    public static final String DEPARTMENTS_URI = "/departments";
    public static final String DEFAULT_DEPARTMENT_NAME = "departmentName";
    public static final Long DEFAULT_DEPARTMENT_MANAGER_ID = 1L;
    public static final String DEPARTMENT_ID_PARAM_NAME = "id";
    public static final String DEPARTMENT_NAME_PARAM_NAME = "name";
    public static final String DEPARTMENT_MANAGER_ID_PARAM_NAME = "managerId";
    public static final String DEPARTMENT_EMPLOYEES_PARAM_NAME = "employees";

    public static Department aDepartment() {
        return aDepartmentWith(DEFAULT_DEPARTMENT_NAME, DEFAULT_DEPARTMENT_MANAGER_ID);
    }

    public static Department aDepartmentWithOnlyName(String name) {
        return aDepartmentWith(name, null);
    }

    public static Department aDepartmentWith(Long managerId) {
        return aDepartmentWith(DEFAULT_DEPARTMENT_NAME, managerId);
    }

    public static Department aDepartmentWith(String name, Long managerId) {
        return aDepartmentWith(name, managerId, Collections.emptySet());
    }

    public static Department aDepartmentWith(String name, Long managerId, Set<Employee> employees) {
        return Department.Builder.builder()
                .name(name)
                .managerId(managerId)
                .employees(employees)
                .build();
    }
}
