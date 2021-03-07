package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DetachEmployeeController {

    private DepartmentService departmentService;

    public DetachEmployeeController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PutMapping("/detach/{employeeId}")
    public String detachEmployeeFromDepartment(Model model, @PathVariable Long employeeId) {
        departmentService.detachEmployee(employeeId);
        String id = (String) model.getAttribute("departmentId");
        return "redirect:departments/details/" + id;
    }
}
