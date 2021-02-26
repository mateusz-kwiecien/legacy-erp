package pl.mkwiecien.legacyerp.domain.employee.ports;

import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface FindEmployeePort {

    Optional<Employee> findById(Long employeeId);

    List<Employee> findAll();

    List<Employee> findAllByDepartmentId(Long departmentId);
}
