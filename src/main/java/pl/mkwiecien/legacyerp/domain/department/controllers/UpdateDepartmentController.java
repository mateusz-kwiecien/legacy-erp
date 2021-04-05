package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.mkwiecien.legacyerp.domain.department.entity.Department;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentRequest;
import pl.mkwiecien.legacyerp.domain.department.ports.FindDepartmentPort;
import pl.mkwiecien.legacyerp.domain.department.ports.UpdateDepartmentPort;

@Controller
@RequestMapping("/departments")
public class UpdateDepartmentController {

    private final FindDepartmentPort findDepartmentPort;

    private final UpdateDepartmentPort updateDepartmentPort;

    public UpdateDepartmentController(FindDepartmentPort findDepartmentPort, UpdateDepartmentPort updateDepartmentPort) {
        this.findDepartmentPort = findDepartmentPort;
        this.updateDepartmentPort = updateDepartmentPort;
    }

    @GetMapping("{departmentId}/details")
    public String getNewDepartmentModel(Model model, @PathVariable Long departmentId) {
        Department department = findDepartmentPort.findById(departmentId)
                .orElseThrow(IllegalArgumentException::new);
        DepartmentRequest departmentRequest = DepartmentRequest.from(department);
        model.addAttribute(departmentRequest);
        return "departments/details";
    }

    @PutMapping("{departmentId}")
    public String updateGivenDepartment(@ModelAttribute @Validated DepartmentRequest request, Errors errors,
                                        @PathVariable Long departmentId) {
        if (errors.hasErrors()) {
            return "departments/" + departmentId + "/details";
        }
        updateDepartmentPort.update(request);
        return "redirect:/departments";
    }
}
