package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeListView;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeListController {

    private EmployeeService employeeService;

    public EmployeeListController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String retrieveAll(Model model) {
        List<EmployeeListView> employees = employeeService.findAllAndMapToView();
        model.addAttribute("employees", employees);
        return "employees/list";
    }
}
