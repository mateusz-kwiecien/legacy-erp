package pl.mkwiecien.legacyerp.domain.employee.service;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createNew(EmployeeRequest request) {
        Employee newEmployee = createFrom(request);
        return employeeRepository.save(newEmployee);
    }

    public List<Employee> retrieveAll() {
        return employeeRepository.findAll();
    }

    private Employee createFrom(EmployeeRequest request) {
        return new Employee(request.getFirstName(), request.getLastName(), request.getEmail());
    }
}
