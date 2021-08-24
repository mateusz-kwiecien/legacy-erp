package pl.mkwiecien.legacyerp.domain.department.ports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentListView;

import java.util.List;
import java.util.Optional;

public interface FindDepartmentPort {

    List<Department> retrieveAll();

    Page<DepartmentListView> retrieveAllViews(Pageable pageable);

    Department retrieveByName(String name);

    Optional<Department> findById(Long departmentId);

    List<String> retrieveAllNames();

    Long countAllDepartments();
}
