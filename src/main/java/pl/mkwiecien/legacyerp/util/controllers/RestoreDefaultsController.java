package pl.mkwiecien.legacyerp.util.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.ports.DeleteDepartmentPort;
import pl.mkwiecien.legacyerp.domain.employee.ports.DeleteEmployeePort;

@Controller
@RequestMapping("/util/restore-defaults")
public class RestoreDefaultsController {

    private final DeleteDepartmentPort departmentPort;

    private final DeleteEmployeePort employeePort;

    public RestoreDefaultsController(DeleteDepartmentPort departmentPort,
                                     DeleteEmployeePort employeePort) {
        this.departmentPort = departmentPort;
        this.employeePort = employeePort;
    }

    @PostMapping
    public String restoreDefaults() {
        employeePort.deleteAllEmployees();
        departmentPort.deleteAllDepartments();
        return "redirect:/";
    }
}
