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

import java.net.URI;
import java.net.URISyntaxException;
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

        return "redirect:" + getLocalRedirectTarget(request.getHeader("Referer"), request);
    }

    private String getLocalRedirectTarget(String referer, HttpServletRequest request) {
        if (referer == null || referer.isBlank()) {
            return "/profile";
        }

        try {
            URI refererUri = new URI(referer);

            String requestScheme = request.getScheme();
            String requestHost = request.getServerName();
            int requestPort = request.getServerPort();

            boolean sameApplication = requestScheme.equalsIgnoreCase(refererUri.getScheme())
                    && requestHost.equalsIgnoreCase(refererUri.getHost())
                    && requestPort == getEffectivePort(refererUri);

            if (!sameApplication || refererUri.getPath() == null || refererUri.getPath().isBlank()) {
                return "/profile";
            }

            return refererUri.getPath()
                    + (refererUri.getQuery() == null ? "" : "?" + refererUri.getQuery());
        } catch (URISyntaxException | IllegalArgumentException e) {
            return "/profile";
        }
    }

    private int getEffectivePort(URI uri) {
        if (uri.getPort() != -1) {
            return uri.getPort();
        }

        return "https".equalsIgnoreCase(uri.getScheme()) ? 443 : 80;
    }
}
