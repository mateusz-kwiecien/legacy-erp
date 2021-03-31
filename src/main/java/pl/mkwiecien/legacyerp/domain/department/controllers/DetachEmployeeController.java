package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/departments")
public class DetachEmployeeController {

    private DepartmentService departmentService;

    public DetachEmployeeController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PutMapping("{departmentId}/detach/{employeeId}")
    public String detachEmployeeFromDepartment(@PathVariable String departmentId, @PathVariable Long employeeId, HttpServletRequest request) {
        departmentService.detachEmployee(employeeId);
        String referer = request.getHeader("Referer");
        return  referer != null
                ? "redirect:" + referer
                : "redirect:/departments/" + departmentId + "/details";
    }
}
