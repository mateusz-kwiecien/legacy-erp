package pl.mkwiecien.legacyerp.domain.department.repository;

import org.springframework.data.repository.query.Param;

public interface DepartmentDAO {

    void deleteDepartmentById(@Param("departmentId") Long departmentId);
}
