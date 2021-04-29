package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.ports.FindDepartmentPort;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.ports.FindEmployeePort;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class GetDepartmentController {

    private final FindDepartmentPort findDepartmentPort;

    private final FindEmployeePort findEmployeePort;

    public GetDepartmentController(FindDepartmentPort findDepartmentPort, FindEmployeePort findEmployeePort) {
        this.findDepartmentPort = findDepartmentPort;
        this.findEmployeePort = findEmployeePort;
    }

    @GetMapping("{departmentId}")
    public String getDepartmentDetails(Model model, @PathVariable Long departmentId) {
        Department department = findDepartmentPort.findById(departmentId).orElse(new Department());
        model.addAttribute("departmentRequest", findDepartmentPort.findById(departmentId)
                .orElse(new Department()));
        List<Employee> potentialManagers = findEmployeePort.findAllPotentialManagers();
        if (department.getManagerId() != null) {
            findEmployeePort.findById(department.getManagerId())
                    .map(potentialManagers::add);
        }
        model.addAttribute("potentialManagers", potentialManagers);
        return "departments/details";
    }
}
