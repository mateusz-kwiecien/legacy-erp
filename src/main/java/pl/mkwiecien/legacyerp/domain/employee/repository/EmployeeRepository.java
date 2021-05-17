package pl.mkwiecien.legacyerp.domain.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    @Modifying
    @Query("DELETE FROM Employee")
    void deleteAll();

    @Modifying
    @Query("DELETE FROM Employee e WHERE e.id = :employeeId")
    void deleteById(@Param("employeeId") Long employeeId);

    List<Employee> findAllByDepartmentId(Long departmentId);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department = null")
    Long countAllUnassignedEmployees();
}
