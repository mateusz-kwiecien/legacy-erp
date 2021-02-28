package pl.mkwiecien.legacyerp.domain.department.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

@Service
@Transactional
public class DepartmentDAOImpl implements DepartmentDAO {

    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    public DepartmentDAOImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Modifying
    public void deleteDepartmentById(Long departmentId) {
        employeeRepository.findAllByDepartmentId(departmentId).stream()
                .peek(employee -> employee.setDepartment(null))
                .forEach(employeeRepository::save);
        departmentRepository.deleteById(departmentId);
    }
}



