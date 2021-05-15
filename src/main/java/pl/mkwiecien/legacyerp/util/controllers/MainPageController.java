package pl.mkwiecien.legacyerp.util.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mkwiecien.legacyerp.util.ports.GetResourcesDataPort;
import pl.mkwiecien.legacyerp.util.values.ResourcesData;

@Controller
@RequestMapping("/")
public class MainPageController {

    private final GetResourcesDataPort resourcesDataPort;

    public MainPageController(GetResourcesDataPort resourcesDataPort) {
        this.resourcesDataPort = resourcesDataPort;
    }

    @GetMapping
    public String getMainPage(Model model) {
        ResourcesData resourcesData = resourcesDataPort.getResources();
        model.addAttribute("ResourcesData", resourcesData);
        return "index";
    }
}
