package pl.mkwiecien.legacyerp.domain.employee.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeListView;
import pl.mkwiecien.legacyerp.domain.employee.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class GetEmployeeListController {

    private EmployeeService employeeService;

    public GetEmployeeListController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String retrieveWithPageable(Model model, Pageable pageable) {
        Page<EmployeeListView> employeesPage = employeeService.findAllAndMapToView(pageable);
        model.addAttribute("employeesPage", employeesPage);
        return "employees/list";
    }
}
