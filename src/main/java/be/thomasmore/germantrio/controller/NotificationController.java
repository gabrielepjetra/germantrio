package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.Notification;
import be.thomasmore.germantrio.repository.NotificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        long unreadNotificationCount = notificationRepository.countByRecipientEmailAndReadFalse(email);

        Set<Long> unreadNotificationIds = new HashSet<>();
        for (Notification notification : notifications) {
            if (!notification.isRead()) {
                unreadNotificationIds.add(notification.getId());
            }
        }

        model.addAttribute("notifications", notifications);
        model.addAttribute("unreadNotificationCount", unreadNotificationCount);
        model.addAttribute("unreadNotificationIds", unreadNotificationIds);

        for (Notification notification : notifications) {
            if (unreadNotificationIds.contains(notification.getId())) {
                notification.setRead(true);
            }
        }

        if (!unreadNotificationIds.isEmpty()) {
            notificationRepository.saveAll(notifications);
        }

        return "notifications";
    }
}
