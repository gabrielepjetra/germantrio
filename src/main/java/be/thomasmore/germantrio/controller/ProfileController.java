package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.repository.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ProfileController {

    private final AppUserRepository appUserRepository;

    public ProfileController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        if (principal == null || principal.getName() == null) {
            return "redirect:/login";
        }

        Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());

        if (appUser.isEmpty()) {
            return "redirect:/login";
        }

        model.addAttribute("user", appUser.get());

        return "profile";
    }
}
