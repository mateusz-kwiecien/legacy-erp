package pl.mkwiecien.legacyerp.domain.employee.ports;

import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;

import java.util.List;

public interface CreateEmployeePort {

    Employee create(EmployeeRequest employeeRequest);

    List<Employee> createAll(List<EmployeeRequest> employeeRequests);
}
