package be.thomasmore.germantrio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name must be 50 characters or fewer.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name must be 50 characters or fewer.")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
    private String username;

    private LocalDate birthDate;

    @Column(unique = true)
    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter a valid email address.")
    @Size(max = 100, message = "Email must be 100 characters or fewer.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 3, max = 255, message = "Password must be at least 3 characters.")
    private String password;
    private String profilePhotoUrl;
    private LocalDateTime createdAt;
    private boolean admin = false;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean banned = false;
    private LocalDateTime mutedUntil;
    private String muteReason;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_cars",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_model_id"))
    private List<CarModel> favoriteCars = new ArrayList<>();

    public AppUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public LocalDateTime getMutedUntil() {
        return mutedUntil;
    }

    public void setMutedUntil(LocalDateTime mutedUntil) {
        this.mutedUntil = mutedUntil;
    }

    public String getMuteReason() {
        return muteReason;
    }

    public void setMuteReason(String muteReason) {
        this.muteReason = muteReason;
    }

    public List<CarModel> getFavoriteCars() {
        return favoriteCars;
    }

    public void setFavoriteCars(List<CarModel> favoriteCars) {
        this.favoriteCars = favoriteCars;
    }
}
