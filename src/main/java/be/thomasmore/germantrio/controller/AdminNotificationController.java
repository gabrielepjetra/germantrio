package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.model.Notification;
import be.thomasmore.germantrio.repository.AppUserRepository;
import be.thomasmore.germantrio.repository.NotificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminNotificationController {

    private static final int MAX_MESSAGE_LENGTH = 500;

    private final AppUserRepository appUserRepository;
    private final NotificationRepository notificationRepository;

    public AdminNotificationController(AppUserRepository appUserRepository,
                                       NotificationRepository notificationRepository) {
        this.appUserRepository = appUserRepository;
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/admin/notifications")
    public String notifications(Model model) {
        model.addAttribute("recentGlobalNotifications", getRecentGlobalNotifications());
        return "admin-notifications";
    }

    @PostMapping("/admin/notifications")
    public String sendGlobalNotification(@RequestParam String message) {
        if (message == null || message.isBlank() || message.trim().length() > MAX_MESSAGE_LENGTH) {
            return "redirect:/admin/notifications?error";
        }

        String trimmedMessage = message.trim();
        LocalDateTime createdAt = LocalDateTime.now();
        List<Notification> notifications = new ArrayList<>();

        for (AppUser user : appUserRepository.findAll()) {
            Notification notification = new Notification();
            notification.setMessage(trimmedMessage);
            notification.setRecipient(user);
            notification.setCreatedAt(createdAt);
            notification.setRead(false);
            notification.setRelatedCarModel(null);
            notifications.add(notification);
        }

        notificationRepository.saveAll(notifications);
        return "redirect:/admin/notifications?success";
    }

    private List<Notification> getRecentGlobalNotifications() {
        Map<String, Notification> uniqueNotifications = new LinkedHashMap<>();

        for (Notification notification : notificationRepository.findTop50ByRelatedCarModelIsNullOrderByCreatedAtDesc()) {
            String key = notification.getCreatedAt() + "|" + notification.getMessage();
            uniqueNotifications.putIfAbsent(key, notification);
        }

        return uniqueNotifications.values().stream()
                .limit(5)
                .toList();
    }
}
