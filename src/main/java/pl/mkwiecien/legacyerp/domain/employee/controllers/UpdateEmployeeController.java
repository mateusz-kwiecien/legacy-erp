package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class UpdateEmployeeController {

    private EmployeeService employeeService;

    public UpdateEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/details")
    public String retrieveEmployeesDetails(Model model, @RequestParam(value = "id", required = false) Long employeeId) {
        if (employeeId != null) {
            Employee employee = employeeService.findById(employeeId)
                    .orElseThrow(IllegalArgumentException::new);
            model.addAttribute("employeeRequest", EmployeeRequest.from(employee));
        }
        return "employee/details";
    }

    @PutMapping("/update/{employeeId}")
    public String updateGivenEmployee(@ModelAttribute @Validated EmployeeRequest employeeRequest, Errors errors,
                                      @PathVariable Long employeeId) {
        if (errors.hasErrors()) {
            return "employee/details";
        }
        employeeService.update(employeeId, employeeRequest);
        return "redirect:/employee/list";
    }
}
