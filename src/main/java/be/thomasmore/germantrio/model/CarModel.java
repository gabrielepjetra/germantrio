package be.thomasmore.germantrio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "brand_id",  nullable = false)
    @NotNull(message = "Brand is required.")
    private Brand brand;

    @NotBlank(message = "Model name is required.")
    @Size(max = 100, message = "Model name must be 100 characters or fewer.")
    private String name;

    @Size(max = 500, message = "Image URL must be 500 characters or fewer.")
    private String imageUrl;

    @NotBlank(message = "Transmission is required.")
    @Size(max = 50, message = "Transmission must be 50 characters or fewer.")
    private String transmission;

    @Size(max = 100, message = "Engine config must be 100 characters or fewer.")
    private String engineConfig;

    @NotBlank(message = "Engine type is required.")
    @Size(max = 50, message = "Engine type must be 50 characters or fewer.")
    private String engineType;

    @Min(value = 1, message = "Horsepower must be at least 1.")
    private int horsepower;

    @Min(value = 1, message = "Torque must be at least 1.")
    private int torqueNm;

    @NotBlank(message = "Drivetrain is required.")
    @Size(max = 20, message = "Drivetrain must be 20 characters or fewer.")
    private String drivetrain;

    @Min(value = 1, message = "Weight must be at least 1.")
    private int weightKg;

    @Positive(message = "0-100 time must be positive.")
    private BigDecimal zeroToHundred;

    @Positive(message = "Base price must be positive.")
    private BigDecimal basePriceEur;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CarModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngineConfig() {
        return engineConfig;
    }

    public void setEngineConfig(String engineConfig) {
        this.engineConfig = engineConfig;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getTorqueNm() {
        return torqueNm;
    }

    public void setTorqueNm(int torqueNm) {
        this.torqueNm = torqueNm;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    public BigDecimal getZeroToHundred() {
        return zeroToHundred;
    }

    public void setZeroToHundred(BigDecimal zeroToHundred) {
        this.zeroToHundred = zeroToHundred;
    }

    public BigDecimal getBasePriceEur() {
        return basePriceEur;
    }

    public void setBasePriceEur(BigDecimal basePriceEur) {
        this.basePriceEur = basePriceEur;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
