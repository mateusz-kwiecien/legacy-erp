package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class CreateEmployeeController {

    private EmployeeService employeeService;

    public CreateEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/create")
    public String getNewEmployeeForm(Model model) {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        model.addAttribute(employeeRequest);
        return "employees/create";
    }

    @PostMapping
    public String createNewEmployee(@ModelAttribute @Validated EmployeeRequest employeeRequest, Errors errors) {
        if (errors.hasErrors()) {
            return "employees/create";
        }
        employeeService.create(employeeRequest);
        return "redirect:/employees";
    }
}
