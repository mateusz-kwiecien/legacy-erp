package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class UpdateEmployeeController {

    private EmployeeService employeeService;

    public UpdateEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public String showEmployeeDetails(@PathVariable Long employeeId, Model model) {
        Optional<Employee> employee = employeeService.findById(employeeId);
        employee.ifPresent(e -> model.addAttribute("employee", e));
        return "employee/details";
    }

    @PutMapping("/update/{employeeId}")
    public String updateGivenEmployee(@PathVariable Long employeeId, EmployeeRequest request) {
        employeeService.update(employeeId, request);
        return "redirect:/employee/list";
    }
}
