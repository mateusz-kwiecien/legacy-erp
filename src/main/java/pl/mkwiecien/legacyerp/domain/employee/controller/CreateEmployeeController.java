package pl.mkwiecien.legacyerp.domain.employee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class CreateEmployeeController {

    private EmployeeService employeeService;

    public CreateEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/new")
    public String createNewEmployee(@ModelAttribute EmployeeRequest request, Model model) {
        employeeService.createNew(request);
        return "employee/list";
    }
}
