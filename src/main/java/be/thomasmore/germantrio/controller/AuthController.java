package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.AppUser;
import be.thomasmore.germantrio.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class AuthController {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (!password.equals(repeatedPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match.");
            return "register";
        }

        if (appUserRepository.existsByEmail(email)) {
            model.addAttribute("errorMessage", "Email is already in use.");
            return "register";
        }

        if (appUserRepository.existsByUsername(username)) {
            model.addAttribute("errorMessage", "Username is already in use.");
            return "register";
        }

        AppUser appUser = new AppUser();
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setUsername(username);
        appUser.setBirthDate(birthDate);
        appUser.setEmail(email);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setProfilePhotoUrl(null);
        appUser.setCreatedAt(LocalDateTime.now());
        appUser.setAdmin(false);

        appUserRepository.save(appUser);

        return "redirect:/login?registered";
    }
}
