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
public class FavoritesPageController {

    private final AppUserRepository appUserRepository;

    public FavoritesPageController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping("/favorites")
    public String favorites(Principal principal, Model model) {
        if (principal == null || principal.getName() == null) {
            return "redirect:/login";
        }

        Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());

        if (appUser.isEmpty()) {
            return "redirect:/login";
        }

        List<CarModel> favoriteCars = new ArrayList<>(appUser.get().getFavoriteCars());
        model.addAttribute("favoriteCars", favoriteCars);

        return "favorites";
    }
}
