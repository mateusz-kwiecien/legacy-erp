package pl.mkwiecien.legacyerp.domain.department.ports;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;

public interface FindDepartmentPort {

    Department retrieveByName(String name);
}
