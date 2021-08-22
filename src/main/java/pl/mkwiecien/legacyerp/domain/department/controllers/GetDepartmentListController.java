package pl.mkwiecien.legacyerp.domain.department.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.domain.department.entity.DepartmentListView;
import pl.mkwiecien.legacyerp.domain.department.ports.FindDepartmentPort;

@Controller
@RequestMapping("/departments")
public class GetDepartmentListController {

    private final FindDepartmentPort findDepartmentPort;

    public GetDepartmentListController(FindDepartmentPort findDepartmentPort) {
        this.findDepartmentPort = findDepartmentPort;
    }

    @GetMapping
    public String retrieveAll(Model model, Pageable pageable) {
        Page<DepartmentListView> departmentsPage = findDepartmentPort.retrieveAllViews(pageable);
        model.addAttribute("departmentsPage", departmentsPage);
        return "departments/list";
    }
}
