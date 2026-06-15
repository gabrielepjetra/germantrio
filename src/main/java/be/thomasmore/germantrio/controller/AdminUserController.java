package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.model.BlacklistedEmail;
import be.thomasmore.germantrio.repository.AppUserRepository;
import be.thomasmore.germantrio.repository.BlacklistedEmailRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class AdminUserController {

    private final AppUserRepository appUserRepository;
    private final BlacklistedEmailRepository blacklistedEmailRepository;

    public AdminUserController(AppUserRepository appUserRepository,
                               BlacklistedEmailRepository blacklistedEmailRepository) {
        this.appUserRepository = appUserRepository;
        this.blacklistedEmailRepository = blacklistedEmailRepository;
    }

    @GetMapping("/admin/users")
    public String users(Model model) {
        model.addAttribute("users", appUserRepository.findAllByOrderByCreatedAtDesc());
        return "admin-users";
    }

    @PostMapping("/admin/users/{id}/ban")
    public String banUser(@PathVariable long id, Principal principal) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isEmpty()) {
            return "redirect:/admin/users?error";
        }

        AppUser userToBan = user.get();
        if (principal != null && userToBan.getEmail().equalsIgnoreCase(principal.getName())) {
            return "redirect:/admin/users?selfBanError";
        }

        if (userToBan.isAdmin()) {
            return "redirect:/admin/users?adminBanError";
        }

        String normalizedEmail = userToBan.getEmail().trim().toLowerCase();
        if (!blacklistedEmailRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            BlacklistedEmail blacklistedEmail = new BlacklistedEmail();
            blacklistedEmail.setEmail(normalizedEmail);
            blacklistedEmail.setReason("Banned by admin");
            blacklistedEmail.setCreatedAt(LocalDateTime.now());
            blacklistedEmailRepository.save(blacklistedEmail);
        }

        userToBan.setBanned(true);
        appUserRepository.save(userToBan);

        return "redirect:/admin/users?banned";
    }

    @PostMapping("/admin/users/{id}/mute")
    public String muteUser(@PathVariable long id,
                           @RequestParam(required = false)
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime mutedUntil,
                           @RequestParam(required = false) String reason,
                           Principal principal) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isEmpty() || mutedUntil == null) {
            return "redirect:/admin/users?error";
        }

        AppUser userToMute = user.get();
        if (principal != null && userToMute.getEmail().equalsIgnoreCase(principal.getName())) {
            return "redirect:/admin/users?selfMuteError";
        }

        if (userToMute.isAdmin()) {
            return "redirect:/admin/users?adminMuteError";
        }

        userToMute.setMutedUntil(mutedUntil);
        userToMute.setMuteReason(reason == null || reason.isBlank() ? null : reason.trim());
        appUserRepository.save(userToMute);

        return "redirect:/admin/users?muted";
    }

    @PostMapping("/admin/users/{id}/unmute")
    public String unmuteUser(@PathVariable long id) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isEmpty()) {
            return "redirect:/admin/users?error";
        }

        AppUser userToUnmute = user.get();
        userToUnmute.setMutedUntil(null);
        userToUnmute.setMuteReason(null);
        appUserRepository.save(userToUnmute);

        return "redirect:/admin/users?unmuted";
    }
}
