package pl.mkwiecien.legacyerp.util.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mkwiecien.legacyerp.util.services.DataPopulationService;

import java.util.Optional;

@Controller
@RequestMapping("/util/populate")
public class DataPopulationController {

    private final DataPopulationService populationService;

    public DataPopulationController(DataPopulationService populationService) {
        this.populationService = populationService;
    }

    @PostMapping("/employees")
    public String populateApplicationWithEmployees(ModelAndView modelAndView) {
        populationService.populateWithRandomData(Optional.of(20));
        return "redirect:/";
    }
}
