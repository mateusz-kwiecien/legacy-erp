package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.service.DepartmentService;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentListController {

    private final DepartmentService service;

    public DepartmentListController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String retrieveAll(Model model) {
        List<Department> departments = service.retrieveAll();
        model.addAttribute("departments", departments);
        return "departments/list";
    }
}
