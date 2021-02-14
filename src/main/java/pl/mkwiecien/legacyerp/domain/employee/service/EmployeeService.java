package pl.mkwiecien.legacyerp.domain.employee.service;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.ports.CreateEmployeePort;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements CreateEmployeePort {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(EmployeeRequest employeeRequest) {
        return employeeRepository.save(createFrom(employeeRequest));
    }

    @Override
    public List<Employee> createAll(List<EmployeeRequest> employeeRequests) {
        return employeeRepository.saveAll(createFrom(employeeRequests));
    }

    public Optional<Employee> findById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee update(Long employeeId, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);
        return employeeRepository.save(updateFrom(employee, request));
    }

    public List<Employee> retrieveAll() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    private Employee createFrom(EmployeeRequest request) {
        return new Employee(request.getFirstName(), request.getLastName(), request.getEmail());
    }

    private List<Employee> createFrom(List<EmployeeRequest> employeeRequests) {
        return employeeRequests.stream()
                .map(this::createFrom)
                .collect(Collectors.toList());
    }

    private Employee updateFrom(Employee employee, EmployeeRequest request) {
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        return employee;
    }
}
