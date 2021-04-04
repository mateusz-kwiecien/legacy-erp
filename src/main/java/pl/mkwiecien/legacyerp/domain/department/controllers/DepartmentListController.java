package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.ports.FindDepartmentPort;

@Controller
@RequestMapping("/departments")
public class DepartmentListController {

    private final FindDepartmentPort findDepartmentPort;

    public DepartmentListController(FindDepartmentPort findDepartmentPort) {
        this.findDepartmentPort = findDepartmentPort;
    }

    @GetMapping
    public String retrieveAll(Model model) {
        model.addAttribute("departments", findDepartmentPort.retrieveAllViews());
        return "departments/list";
    }
}
