package pl.mkwiecien.legacyerp.domain.department.ports;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentListView;

import java.util.List;
import java.util.Optional;

public interface FindDepartmentPort {

    List<Department> retrieveAll();

    List<DepartmentListView> retrieveAllViews();

    Department retrieveByName(String name);

    Optional<Department> findById(Long departmentId);
}
