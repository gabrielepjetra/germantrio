package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.repository.AppUserRepository;
import be.thomasmore.germantrio.repository.BlacklistedEmailRepository;
import be.thomasmore.germantrio.service.StorageService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Controller
public class AuthController {

    private final AppUserRepository appUserRepository;
    private final BlacklistedEmailRepository blacklistedEmailRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;
    private final StorageService storageService;

    public AuthController(AppUserRepository appUserRepository,
                          BlacklistedEmailRepository blacklistedEmailRepository,
                          PasswordEncoder passwordEncoder,
                          Validator validator,
                          StorageService storageService) {
        this.appUserRepository = appUserRepository;
        this.blacklistedEmailRepository = blacklistedEmailRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
        this.storageService = storageService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String username,
                               @RequestParam LocalDate birthDate,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String repeatedPassword,
                               @RequestParam MultipartFile profilePhoto,
                               Model model) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(firstName == null ? null : firstName.trim());
        appUser.setLastName(lastName == null ? null : lastName.trim());
        appUser.setUsername(username == null ? null : username.trim());
        appUser.setBirthDate(birthDate);
        appUser.setEmail(email == null ? null : email.trim());
        appUser.setPassword(password);
        appUser.setProfilePhotoUrl(null);
        appUser.setCreatedAt(LocalDateTime.now());
        appUser.setAdmin(false);
        appUser.setBanned(false);

        Set<ConstraintViolation<AppUser>> validationErrors = validator.validate(appUser);
        if (!validationErrors.isEmpty()) {
            model.addAttribute("errorMessage", firstValidationMessage(validationErrors));
            return "register";
        }

        if (!password.equals(repeatedPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match.");
            return "register";
        }

        String normalizedEmail = email.trim();
        String normalizedUsername = username.trim();

        if (blacklistedEmailRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            model.addAttribute("errorMessage", "This email cannot be used to register.");
            return "register";
        }

        if (appUserRepository.existsByEmail(normalizedEmail)) {
            model.addAttribute("errorMessage", "Email is already in use.");
            return "register";
        }

        if (appUserRepository.existsByUsername(normalizedUsername)) {
            model.addAttribute("errorMessage", "Username is already in use.");
            return "register";
        }

        try {
            appUser.setProfilePhotoUrl(storageService.uploadProfilePhoto(profilePhoto));
        } catch (IllegalArgumentException | IllegalStateException exception) {
            model.addAttribute("errorMessage", exception.getMessage());
            return "register";
        }

        appUser.setPassword(passwordEncoder.encode(password));
        appUserRepository.save(appUser);

        return "redirect:/login?registered";
    }

    private String firstValidationMessage(Set<? extends ConstraintViolation<?>> violations) {
        return violations.iterator().next().getMessage();
    }
}
