package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.repository.AppUserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    private final AppUserRepository appUserRepository;

    public ProfileController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        if (principal == null || principal.getName() == null) {
            return "redirect:/login";
        }

        Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());

        if (appUser.isEmpty()) {
            return "redirect:/login";
        }

        AppUser user = appUser.get();
        List<CarModel> favoriteCars = new ArrayList<>(user.getFavoriteCars());

        model.addAttribute("user", user);
        model.addAttribute("favoriteCars", favoriteCars);

        return "profile";
    }
}
