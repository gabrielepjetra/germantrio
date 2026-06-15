package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.Notification;
import be.thomasmore.germantrio.repository.NotificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class NotificationController {

    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/notifications")
    public String notifications(Principal principal, Model model) {
        if (principal == null || principal.getName() == null) {
            return "redirect:/login";
        }

        String email = principal.getName();
        List<Notification> notifications = notificationRepository.findByRecipientEmailOrderByCreatedAtDesc(email);

        model.addAttribute("notifications", notifications);
        model.addAttribute("unreadNotificationCount", notificationRepository.countByRecipientEmailAndReadFalse(email));

        return "notifications";
    }
}
