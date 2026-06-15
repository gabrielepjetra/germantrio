package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.Brand;
import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.repository.BrandRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Controller
public class AdminCarModelController {

    private final CarModelRepository carModelRepository;
    private final BrandRepository brandRepository;
    private final Validator validator;

    public AdminCarModelController(CarModelRepository carModelRepository,
                                   BrandRepository brandRepository,
                                   Validator validator) {
        this.carModelRepository = carModelRepository;
        this.brandRepository = brandRepository;
        this.validator = validator;
    }

    @GetMapping("/admin/carmodels")
    public String carModels(Model model) {
        model.addAttribute("cars", carModelRepository.findAll());
        return "admin-carmodels";
    }

    @GetMapping("/admin/carmodels/new")
    public String newCarModel(Model model) {
        model.addAttribute("carModel", new CarModel());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("isEdit", false);
        return "admin-carmodel-form";
    }

    @PostMapping("/admin/carmodels/new")
    public String createCarModel(@RequestParam(required = false) Long brandId,
                                 @RequestParam String name,
                                 @RequestParam String imageUrl,
                                 @RequestParam String transmission,
                                 @RequestParam String engineConfig,
                                 @RequestParam String engineType,
                                 @RequestParam int horsepower,
                                 @RequestParam int torqueNm,
                                 @RequestParam String drivetrain,
                                 @RequestParam int weightKg,
                                 @RequestParam BigDecimal zeroToHundred,
                                 @RequestParam BigDecimal basePriceEur,
                                 Model model) {
        Optional<Brand> brand = brandId == null ? Optional.empty() : brandRepository.findById(brandId);

        CarModel carModel = new CarModel();
        copyFormValues(carModel, brand.orElse(null), name, imageUrl, transmission, engineConfig, engineType,
                horsepower, torqueNm, drivetrain, weightKg, zeroToHundred, basePriceEur);
        if (hasValidationErrors(carModel, model, false)) {
            return "admin-carmodel-form";
        }

        carModel.setCreatedAt(LocalDateTime.now());
        carModel.setUpdatedAt(LocalDateTime.now());
        carModelRepository.save(carModel);

        return "redirect:/admin/carmodels";
    }

    @GetMapping("/admin/carmodels/{id}/edit")
    public String editCarModel(@PathVariable long id, Model model) {
        Optional<CarModel> carModel = carModelRepository.findById(id);

        if (carModel.isEmpty()) {
            return "redirect:/admin/carmodels";
        }

        model.addAttribute("carModel", carModel.get());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("isEdit", true);
        return "admin-carmodel-form";
    }

    @PostMapping("/admin/carmodels/{id}/edit")
    public String updateCarModel(@PathVariable long id,
                                 @RequestParam(required = false) Long brandId,
                                 @RequestParam String name,
                                 @RequestParam String imageUrl,
                                 @RequestParam String transmission,
                                 @RequestParam String engineConfig,
                                 @RequestParam String engineType,
                                 @RequestParam int horsepower,
                                 @RequestParam int torqueNm,
                                 @RequestParam String drivetrain,
                                 @RequestParam int weightKg,
                                 @RequestParam BigDecimal zeroToHundred,
                                 @RequestParam BigDecimal basePriceEur,
                                 Model model) {
        Optional<CarModel> carModel = carModelRepository.findById(id);
        Optional<Brand> brand = brandId == null ? Optional.empty() : brandRepository.findById(brandId);

        if (carModel.isEmpty()) {
            return "redirect:/admin/carmodels";
        }

        copyFormValues(carModel.get(), brand.orElse(null), name, imageUrl, transmission, engineConfig, engineType,
                horsepower, torqueNm, drivetrain, weightKg, zeroToHundred, basePriceEur);
        if (hasValidationErrors(carModel.get(), model, true)) {
            return "admin-carmodel-form";
        }

        carModel.get().setUpdatedAt(LocalDateTime.now());
        carModelRepository.save(carModel.get());

        return "redirect:/admin/carmodels";
    }

    private void copyFormValues(CarModel carModel, Brand brand, String name, String imageUrl, String transmission,
                                String engineConfig, String engineType, int horsepower, int torqueNm,
                                String drivetrain, int weightKg, BigDecimal zeroToHundred, BigDecimal basePriceEur) {
        carModel.setBrand(brand);
        carModel.setName(name.trim());
        carModel.setImageUrl(imageUrl);
        carModel.setTransmission(transmission);
        carModel.setEngineConfig(engineConfig);
        carModel.setEngineType(engineType);
        carModel.setHorsepower(horsepower);
        carModel.setTorqueNm(torqueNm);
        carModel.setDrivetrain(drivetrain);
        carModel.setWeightKg(weightKg);
        carModel.setZeroToHundred(zeroToHundred);
        carModel.setBasePriceEur(basePriceEur);
    }

    private boolean hasValidationErrors(CarModel carModel, Model model, boolean isEdit) {
        Set<ConstraintViolation<CarModel>> violations = validator.validate(carModel);
        if (violations.isEmpty()) {
            return false;
        }

        model.addAttribute("carModel", carModel);
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("error", firstValidationMessage(violations));
        return true;
    }

    private String firstValidationMessage(Set<? extends ConstraintViolation<?>> violations) {
        return violations.iterator().next().getMessage();
    }
}
