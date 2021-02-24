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

@Controller
@RequestMapping("/departments")
public class CreateDepartmentController {

    private final DepartmentService service;

    public CreateDepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String getNewDepartmentModel(Model model) {
        DepartmentRequest departmentRequest = new DepartmentRequest();
        model.addAttribute(departmentRequest);
        return "departments/create";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated DepartmentRequest departmentRequest, Errors errors) {
        service.crete(departmentRequest);
        return "redirect:/departments";
    }
}
