package pl.mkwiecien.legacyerp.domain.employee.ports;

import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeListView;

import java.util.List;
import java.util.Optional;

public interface FindEmployeePort {

    Optional<Employee> findById(Long employeeId);

    List<Employee> findAll();

    List<Employee> findAllByDepartmentId(Long departmentId);

    List<EmployeeListView> findAllAndMapToView();

    List<Employee> findAllPotentialManagers();
}
