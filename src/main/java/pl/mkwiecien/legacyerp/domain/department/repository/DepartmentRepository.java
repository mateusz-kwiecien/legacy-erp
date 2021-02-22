package pl.mkwiecien.legacyerp.domain.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
