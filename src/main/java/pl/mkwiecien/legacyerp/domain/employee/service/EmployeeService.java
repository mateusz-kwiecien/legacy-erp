package pl.mkwiecien.legacyerp.domain.employee.service;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.ports.FindDepartmentPort;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee.Builder;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.ports.CreateEmployeePort;
import pl.mkwiecien.legacyerp.domain.employee.ports.FindEmployeePort;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements CreateEmployeePort, FindEmployeePort {

    private final EmployeeRepository employeeRepository;

    private final FindDepartmentPort findDepartmentPort;

    public EmployeeService(EmployeeRepository employeeRepository, FindDepartmentPort findDepartmentPort) {
        this.employeeRepository = employeeRepository;
        this.findDepartmentPort = findDepartmentPort;
    }

    @Override
    public Employee create(EmployeeRequest employeeRequest) {
        return employeeRepository.save(createFrom(employeeRequest));
    }

    @Override
    public List<Employee> createAll(List<EmployeeRequest> employeeRequests) {
        return employeeRepository.saveAll(createFrom(employeeRequests));
    }

    @Override
    public Optional<Employee> findById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAllByDepartmentId(Long departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }

    public Employee update(Long employeeId, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);
        return employeeRepository.save(updateFrom(employee, request));
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    private Employee createFrom(EmployeeRequest request) {
        return Builder.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .department(retrieveFrom(request))
                .build();
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
        employee.setDepartment(retrieveFrom(request));
        return employee;
    }

    private Department retrieveFrom(EmployeeRequest request) {
        return request.getDepartmentName() != null
                ? findDepartmentPort.retrieveByName(request.getDepartmentName())
                : null;
    }
}
