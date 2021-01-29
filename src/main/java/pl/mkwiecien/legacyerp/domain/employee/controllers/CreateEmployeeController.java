package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/create")
    public String getNewEmployeeForm(Model model) {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        model.addAttribute(employeeRequest);
        return "employee/create";
    }

    @PostMapping("/new")
    public String createNewEmployee(@ModelAttribute EmployeeRequest employeeRequest, Model model) {
        employeeService.createNew(employeeRequest);
        return "redirect:/employee/list";
    }
}
