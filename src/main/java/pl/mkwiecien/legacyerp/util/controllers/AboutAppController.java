package pl.mkwiecien.legacyerp.util.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutAppController {

    @GetMapping
    public String getMainPage() {
        return "about";
    }
}
