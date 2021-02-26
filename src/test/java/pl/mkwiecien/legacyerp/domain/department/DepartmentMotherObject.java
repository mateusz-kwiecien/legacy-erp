package pl.mkwiecien.legacyerp.domain.department;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;

public class DepartmentMotherObject {
    public static final String DEPARTMENTS_URI = "/departments";
    public static final String DEPARTMENT_NAME = "departmentName";

    public static Department aDepartmentWith(Long managerId) {
        return aDepartmentWith(DEPARTMENT_NAME, managerId);
    }

    public static Department aDepartmentWith(String name, Long managerId) {
        return Department.Builder.builder()
                .name(name)
                .managerId(managerId)
                .build();
    }
}
