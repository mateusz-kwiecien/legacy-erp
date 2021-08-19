package pl.mkwiecien.legacyerp.domain.employee.ports;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeListView;

import java.util.List;
import java.util.Optional;

public interface FindEmployeePort {

    Optional<Employee> findById(Long employeeId);

    List<Employee> findAll();

    Page<EmployeeListView> findAllAndMapToView(Pageable pageable);

    List<Employee> findAllByDepartmentId(Long departmentId);

    List<EmployeeListView> findAllAndMapToView();

    List<Employee> findAllPotentialManagers();

    Long countAllEmployees();

    Long countAllUnassignedEmployees();
}
