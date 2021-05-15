package pl.mkwiecien.legacyerp.util.services;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.department.ports.FindDepartmentPort;
import pl.mkwiecien.legacyerp.domain.employee.ports.FindEmployeePort;
import pl.mkwiecien.legacyerp.util.ports.GetResourcesDataPort;
import pl.mkwiecien.legacyerp.util.values.ResourcesData;

@Service
public class ResourcesDataService implements GetResourcesDataPort {

    private final FindDepartmentPort departmentPort;

    private final FindEmployeePort employeePort;

    public ResourcesDataService(FindDepartmentPort departmentPort, FindEmployeePort employeePort) {
        this.departmentPort = departmentPort;
        this.employeePort = employeePort;
    }

    @Override
    public ResourcesData getResources() {
        Long departmentsNumber = departmentPort.countAllDepartments();
        Long employeesNumber = employeePort.countAllEmployees();
        return new ResourcesData(employeesNumber, departmentsNumber);
    }
}
