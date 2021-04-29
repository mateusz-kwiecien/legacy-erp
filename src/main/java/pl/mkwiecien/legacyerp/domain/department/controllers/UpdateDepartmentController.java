package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;
import pl.mkwiecien.legacyerp.domain.department.ports.UpdateDepartmentPort;

@Controller
@RequestMapping("/departments")
public class UpdateDepartmentController {

    private final UpdateDepartmentPort updateDepartmentPort;

    public UpdateDepartmentController(UpdateDepartmentPort updateDepartmentPort) {
        this.updateDepartmentPort = updateDepartmentPort;
    }

    @PutMapping
    public String updateDepartment(@ModelAttribute @Validated DepartmentRequest departmentRequest, Errors errors) {
        if (errors.hasErrors() || departmentRequest.getId() == null) {
            return "departments/details";
        }
        updateDepartmentPort.update(departmentRequest);
        return "redirect:departments/" + departmentRequest.getId();
    }
}
