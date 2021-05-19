package pl.mkwiecien.legacyerp.util;

import static pl.mkwiecien.legacyerp.domain.department.DepartmentMotherObject.aDepartmentWith;
import static pl.mkwiecien.legacyerp.domain.employee.EmployeeMotherObject.anEmployeeWith;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.repository.DepartmentRepository;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.repository.EmployeeRepository;

public class UtilDataPopulationService {

    public static void populateWithDefaultData(EmployeeRepository employeeRepository,
                                               DepartmentRepository departmentRepository) {
        Employee manager = employeeRepository.save(anEmployeeWith("John", "Doe", "john.doe@example.com"));
        employeeRepository.save(anEmployeeWith("Marla", "Singer", "marla.singer@example.com"));
        Employee assignedEmployee = employeeRepository.save(anEmployeeWith("Diego", "Sanchez", "diego.sanchez@example.com"));

        Department department = departmentRepository.save(aDepartmentWith("dep-01", manager.getId()));
        assignedEmployee.setDepartment(department);
        employeeRepository.save(assignedEmployee);
    }
}
