package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DeleteDepartmentController {

    private final DepartmentService departmentService;

    public DeleteDepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @DeleteMapping("/{departmentId}")
    public String deleteEmployee(@PathVariable Long departmentId) {
        departmentService.delete(departmentId);
        return "redirect:/departments";
    }
}
