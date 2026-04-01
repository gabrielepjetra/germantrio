package be.thomasmore.germantrio.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "brand_id",  nullable = false)
    private Brand brand;

    private String name;
    private String imageUrl;
    private String transmission;
    private String engineConfig;
    private String engineType;
    private int horsepower;
    private int torqueNm;
    private String drivetrain;
    private int weightKg;
    private BigDecimal zeroToHundred;
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
