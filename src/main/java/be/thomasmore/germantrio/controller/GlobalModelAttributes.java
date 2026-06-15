package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.repository.NotificationRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    private final NotificationRepository notificationRepository;

    public GlobalModelAttributes(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @ModelAttribute
    public void addNavbarNotificationData(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("unreadNotificationCount", 0);
            model.addAttribute("hasUnreadNotifications", false);
            return;
        }

        String email = authentication.getName();
        long unreadNotificationCount = notificationRepository.countByRecipientEmailAndReadFalse(email);

        model.addAttribute("unreadNotificationCount", unreadNotificationCount);
        model.addAttribute("hasUnreadNotifications", unreadNotificationCount > 0);
    }
}
