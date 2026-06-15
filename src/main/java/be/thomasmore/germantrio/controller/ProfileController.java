package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.model.Notification;
import be.thomasmore.germantrio.repository.AppUserRepository;
import be.thomasmore.germantrio.repository.NotificationRepository;
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
    private final NotificationRepository notificationRepository;

    public ProfileController(AppUserRepository appUserRepository,
                             NotificationRepository notificationRepository) {
        this.appUserRepository = appUserRepository;
        this.notificationRepository = notificationRepository;
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
        List<CarModel> favoriteCarsPreview = favoriteCars.stream()
                .limit(2)
                .toList();
        List<Notification> notifications = notificationRepository.findByRecipientEmailOrderByCreatedAtDesc(user.getEmail());
        List<Notification> notificationsPreview = notifications.stream()
                .limit(2)
                .toList();
        long unreadNotificationCount = notificationRepository.countByRecipientEmailAndReadFalse(user.getEmail());

        model.addAttribute("user", user);
        model.addAttribute("favoriteCars", favoriteCars);
        model.addAttribute("favoriteCarsPreview", favoriteCarsPreview);
        model.addAttribute("favoriteCarsCount", favoriteCars.size());
        model.addAttribute("notifications", notifications);
        model.addAttribute("notificationsPreview", notificationsPreview);
        model.addAttribute("notificationsCount", notifications.size());
        model.addAttribute("unreadNotificationCount", unreadNotificationCount);

        return "profile";
    }
}
