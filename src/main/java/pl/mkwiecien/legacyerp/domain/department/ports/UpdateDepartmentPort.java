package pl.mkwiecien.legacyerp.domain.department.ports;

import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;

public interface UpdateDepartmentPort {

    void update(DepartmentRequest departmentRequest);
}
