package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.mkwiecien.legacyerp.domain.department.ports.FindDepartmentPort;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class UpdateEmployeeController {

    private final EmployeeService employeeService;

    private final FindDepartmentPort findDepartmentPort;

    public UpdateEmployeeController(EmployeeService employeeService, FindDepartmentPort findDepartmentPort) {
        this.employeeService = employeeService;
        this.findDepartmentPort = findDepartmentPort;
    }

    @GetMapping("/details")
    public String retrieveEmployeesDetails(Model model, @RequestParam(value = "id", required = false) Long employeeId) {
        model.addAttribute("employeeRequest", retrieveFrom(employeeId));
        List<String> departments = findDepartmentPort.retrieveAllNames();
        model.addAttribute("departments", departments);
        return "employees/details";
    }

    @PutMapping("{employeeId}")
    public String updateGivenEmployee(@ModelAttribute @Validated EmployeeRequest employeeRequest, Errors errors,
                                      @PathVariable Long employeeId) {
        if (errors.hasErrors()) {
            return "employees/details";
        }
        employeeService.update(employeeId, employeeRequest);
        return "redirect:/employees";
    }

    private EmployeeRequest retrieveFrom(Long employeeId) {
        if (employeeId == null) {
            return new EmployeeRequest();
        }
        Employee employee = employeeService.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);
        EmployeeRequest employeeRequest = EmployeeRequest.from(employee);
        return employeeRequest;
    }
}
