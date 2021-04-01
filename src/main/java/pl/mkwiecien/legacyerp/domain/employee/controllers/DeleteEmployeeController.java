package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class DeleteEmployeeController {

    private EmployeeService employeeService;

    public DeleteEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @DeleteMapping("{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employees";
    }
}
