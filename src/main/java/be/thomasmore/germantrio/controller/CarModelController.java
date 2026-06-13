package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.repository.AppUserRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CarModelController {

    private final CarModelRepository carModelRepository;
    private final AppUserRepository appUserRepository;

    public CarModelController(CarModelRepository carModelRepository, AppUserRepository appUserRepository) {
        this.carModelRepository = carModelRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping("/carmodels/{id}")
    public String carModelDetail(@PathVariable long id, Model model, Principal principal) {
        Optional<CarModel> car = carModelRepository.findById(id);
        if (car.isPresent()) {
            CarModel currentCar = car.get();
            boolean isAuthenticatedUser = principal != null && principal.getName() != null;
            boolean isFavorite = false;

            if (isAuthenticatedUser) {
                Optional<AppUser> appUser = appUserRepository.findByEmail(principal.getName());
                isFavorite = appUser
                        .map(user -> user.getFavoriteCars().stream()
                                .anyMatch(favoriteCar -> favoriteCar.getId() == currentCar.getId()))
                        .orElse(false);
            }

            model.addAttribute("car", currentCar);
            model.addAttribute("brandId", currentCar.getBrand().getId());
            model.addAttribute("isFavorite", isFavorite);
            model.addAttribute("isAuthenticatedUser", isAuthenticatedUser);
            return "carmodeldetail";
        }
        return "redirect:/error?message=Car%20model%20not%20found";
    }
}
