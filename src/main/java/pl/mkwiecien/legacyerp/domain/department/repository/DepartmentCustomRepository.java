package pl.mkwiecien.legacyerp.domain.department.repository;

import org.springframework.data.repository.query.Param;

public interface DepartmentCustomRepository {

    void deleteDepartmentById(@Param("departmentId") Long departmentId);
}
