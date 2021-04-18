package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;
import pl.mkwiecien.legacyerp.domain.department.service.DepartmentService;
import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class CreateDepartmentController {

    private final DepartmentService departmentService;

    private final EmployeeService employeeService;

    public CreateDepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping("/create")
    public String getNewDepartmentModel(Model model) {
        DepartmentRequest departmentRequest = new DepartmentRequest();
        List<Employee> potentialManagers = employeeService.findAllPotentialManagers();
        model.addAttribute("potentialManagers", potentialManagers);
        model.addAttribute(departmentRequest);
        return "departments/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated DepartmentRequest departmentRequest, Errors errors) {
        if (errors.hasErrors()) {
            return "departments/create";
        }
        departmentService.create(departmentRequest);
        return "redirect:/departments";
    }
}
