package pl.mkwiecien.legacyerp.domain.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;

import java.util.Optional;

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Modifying
    @Query("DELETE FROM Department")
    void deleteAll();

    Optional<Department> findByName(String name);
}
