package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.repository.BrandRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/contact")
    public String submitContact(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String message) {
        if (name.isBlank() || email.isBlank() || message.isBlank()) {
            return "redirect:/contact?error";
        }

        return "redirect:/contact?success";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
