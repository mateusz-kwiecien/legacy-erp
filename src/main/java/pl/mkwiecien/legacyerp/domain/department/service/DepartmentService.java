package pl.mkwiecien.legacyerp.domain.department.service;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentDAO;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;

import java.util.Collections;
import java.util.List;

import static pl.mkwiecien.legacyerp.domain.department.entity.Department.Builder.builder;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    private final DepartmentDAO departmentDAO;

    public DepartmentService(DepartmentRepository repository, DepartmentDAO departmentDAO) {
        this.repository = repository;
        this.departmentDAO = departmentDAO;
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

    private Department from(DepartmentRequest request) {
        return builder()
                .id(request.getId())
                .name(request.getName())
                .managerId(request.getManagerId())
                .employees(Collections.emptySet())
                .build();
    }
}
