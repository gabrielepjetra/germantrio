package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.repository.BrandRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BrandRepository brandRepository;

    public HomeController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping({"/","/index"})
    public String index(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
