package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.repository.AppUserRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class FavoriteController {

    private final AppUserRepository appUserRepository;
    private final CarModelRepository carModelRepository;

    public FavoriteController(AppUserRepository appUserRepository, CarModelRepository carModelRepository) {
        this.appUserRepository = appUserRepository;
        this.carModelRepository = carModelRepository;
    }

    @Transactional
    @PostMapping("/favorites/{carId}/toggle")
    public String toggleFavorite(@PathVariable long carId,
                                 Principal principal,
                                 HttpServletRequest request) {
        if (principal == null || principal.getName() == null) {
            return "redirect:/login";
        }

        Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());
        Optional<CarModel> carModel = carModelRepository.findById(carId);

        if (appUser.isEmpty() || carModel.isEmpty()) {
            return "redirect:/";
        }

        AppUser user = appUser.get();
        CarModel car = carModel.get();

        boolean removed = user.getFavoriteCars()
                .removeIf(favoriteCar -> favoriteCar.getId() == car.getId());

        if (!removed) {
            user.getFavoriteCars().add(car);
        }

        appUserRepository.save(user);

        String referer = request.getHeader("Referer");

        if (referer == null || referer.isBlank()) {
            return "redirect:/profile";
        }

        return "redirect:" + referer;
    }
}
