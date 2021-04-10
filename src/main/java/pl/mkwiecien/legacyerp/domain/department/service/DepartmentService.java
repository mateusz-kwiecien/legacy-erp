package pl.mkwiecien.legacyerp.domain.department.service;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentListView;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;
import pl.mkwiecien.legacyerp.domain.department.ports.CreateDepartmentPort;
import pl.mkwiecien.legacyerp.domain.department.ports.FindDepartmentPort;
import pl.mkwiecien.legacyerp.domain.department.ports.UpdateDepartmentPort;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentDAO;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static pl.mkwiecien.legacyerp.domain.department.entity.Department.Builder.builder;

@Service
public class DepartmentService implements CreateDepartmentPort, FindDepartmentPort, UpdateDepartmentPort {

    private final DepartmentRepository departmentRepository;

    private final DepartmentDAO departmentDAO;

    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository repository, DepartmentDAO departmentDAO,
                             EmployeeRepository employeeRepository) {
        this.departmentRepository = repository;
        this.departmentDAO = departmentDAO;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Department> findById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public List<Department> retrieveAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<DepartmentListView> retrieveAllViews() {
        List<Employee> employees = employeeRepository.findAll();
        return departmentRepository.findAll().stream()
                .map(department -> toView(department, employees))
                .collect(Collectors.toList());
    }

    @Override
    public Department retrieveByName(String name) {
        return departmentRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void update(DepartmentRequest request) {
        assertCorrectManagerAssignment(request);
        Department department = departmentRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        departmentRepository.save(updateFrom(department, request));
    }

    @Override
    public Department create(DepartmentRequest request) {
        assertCorrectManagerAssignment(request);
        return departmentRepository.save(from(request));
    }

    public void delete(Long id) {
        departmentDAO.deleteDepartmentById(id);
    }

    public void detachEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);
        employee.setDepartment(null);
        employeeRepository.save(employee);
    }

    private void assertCorrectManagerAssignment(DepartmentRequest request) {
        if (departmentRepository.findByManagerId(request.getManagerId()).isPresent()) {
            throw new IllegalArgumentException("Employee can be manager in only one department.");
        }
    }

    private Department from(DepartmentRequest request) {
        return builder()
                .id(request.getId())
                .name(request.getName())
                .managerId(request.getManagerId())
                .employees(Collections.emptySet())
                .build();
    }

    private Department updateFrom(Department department, DepartmentRequest request) {
        department.setName(request.getName());
        department.setManagerId(request.getManagerId());
        return department;
    }

    private DepartmentListView toView(Department department, List<Employee> employees) {
        String managersName = department.getManagerId() != null
                ? retrieveManagersName(department.getManagerId(), employees)
                : "";
        Long numberOfEmployees = (long) department.getEmployees().size();

        return new DepartmentListView(department.getId(), department.getName(), managersName, numberOfEmployees);
    }

    private String retrieveManagersName(Long managerId, List<Employee> employees) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(managerId))
                .findFirst()
                .map(Employee::getFullName).orElse("");
    }
}
