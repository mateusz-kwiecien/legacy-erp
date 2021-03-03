package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;
import pl.mkwiecien.legacyerp.domain.department.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class UpdateDepartmentController {

    private DepartmentService departmentService;

    public UpdateDepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/details")
    public String getNewDepartmentModel(Model model, @RequestParam(value = "id", required = false) Long departmentId) {
        if (departmentId != null) {
            Department department = departmentService.findById(departmentId)
                    .orElseThrow(IllegalArgumentException::new);
            DepartmentRequest departmentRequest = DepartmentRequest.from(department);
            model.addAttribute(departmentRequest);
        }
        return "departments/details";
    }
}
