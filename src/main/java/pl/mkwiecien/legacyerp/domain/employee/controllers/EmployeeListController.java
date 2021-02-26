package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeListController {

    private EmployeeService employeeService;

    public EmployeeListController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String retrieveAll(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employee/list";
    }
}
