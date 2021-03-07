package pl.mkwiecien.legacyerp.domain.department.service;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentDAO;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static pl.mkwiecien.legacyerp.domain.department.entity.Department.Builder.builder;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    private final DepartmentDAO departmentDAO;

    private final EmployeeService employeeService;

    public DepartmentService(DepartmentRepository repository, DepartmentDAO departmentDAO, EmployeeService employeeService) {
        this.repository = repository;
        this.departmentDAO = departmentDAO;
        this.employeeService = employeeService;
    }

    public Optional<Department> findById(Long departmentId) {
        return repository.findById(departmentId);
    }

    public List<Department> retrieveAll() {
        return repository.findAll();
    }

    public Department create(DepartmentRequest request) {
        return repository.save(from(request));
    }

    public void delete(Long id) {
        departmentDAO.deleteDepartmentById(id);
    }

    public void detachEmployee(Long employeeId) {
        employeeService.detachEmployee(employeeId);
    }

    private Department from(DepartmentRequest request) {
        return builder()
                .id(request.getId())
                .name(request.getName())
                .managerId(request.getManagerId())
                .employees(Collections.emptySet())
                .build();
    }
}
