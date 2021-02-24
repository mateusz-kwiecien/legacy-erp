package pl.mkwiecien.legacyerp.domain.department.service;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> retrieveAll() {
        return repository.findAll();
    }

    public Department crete(DepartmentRequest request) {
        return repository.save(from(request));
    }

    private Department from(DepartmentRequest request) {
        return new Department(request.getManagerId());
    }
}
