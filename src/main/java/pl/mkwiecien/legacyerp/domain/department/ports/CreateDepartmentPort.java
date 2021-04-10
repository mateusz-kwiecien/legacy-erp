package pl.mkwiecien.legacyerp.domain.department.ports;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;

public interface CreateDepartmentPort {

    Department create(DepartmentRequest request);
}
