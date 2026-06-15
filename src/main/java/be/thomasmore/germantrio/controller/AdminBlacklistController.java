package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.BlacklistedEmail;
import be.thomasmore.germantrio.repository.BlacklistedEmailRepository;
import jakarta.validation.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class AdminBlacklistController {

    private final BlacklistedEmailRepository blacklistedEmailRepository;
    private final Validator validator;

    public AdminBlacklistController(BlacklistedEmailRepository blacklistedEmailRepository,
                                    Validator validator) {
        this.blacklistedEmailRepository = blacklistedEmailRepository;
        this.validator = validator;
    }

    @GetMapping("/admin/blacklist")
    public String blacklist(Model model) {
        model.addAttribute("blacklistedEmails", blacklistedEmailRepository.findAllByOrderByCreatedAtDesc());
        return "admin-blacklist";
    }

    @PostMapping("/admin/blacklist")
    public String addBlacklistedEmail(@RequestParam String email,
                                      @RequestParam(required = false) String reason) {
        String normalizedEmail = email == null ? "" : email.trim().toLowerCase();

        BlacklistedEmail blacklistedEmail = new BlacklistedEmail();
        blacklistedEmail.setEmail(normalizedEmail);
        blacklistedEmail.setReason(reason == null || reason.isBlank() ? null : reason.trim());
        blacklistedEmail.setCreatedAt(LocalDateTime.now());

        if (!validator.validate(blacklistedEmail).isEmpty()
                || blacklistedEmailRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            return "redirect:/admin/blacklist?error";
        }

        blacklistedEmailRepository.save(blacklistedEmail);

        return "redirect:/admin/blacklist?success";
    }

    @PostMapping("/admin/blacklist/{id}/delete")
    public String deleteBlacklistedEmail(@PathVariable long id) {
        if (blacklistedEmailRepository.existsById(id)) {
            blacklistedEmailRepository.deleteById(id);
        }

        return "redirect:/admin/blacklist?removed";
    }
}
