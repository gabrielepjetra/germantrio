package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.Brand;
import be.thomasmore.germantrio.repository.BrandRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class BrandController {

    private final BrandRepository brandRepository;
    private final CarModelRepository carModelRepository;

    public BrandController(BrandRepository brandRepository, CarModelRepository carModelRepository) {
        this.brandRepository = brandRepository;
        this.carModelRepository = carModelRepository;
    }

    @GetMapping("/brands/{id}")
    public String brandDetail(@PathVariable long id,
                              @RequestParam(required = false) String engineType,
                              @RequestParam(required = false) String drivetrain,
                              @RequestParam(required = false) String transmission,
                              @RequestParam(required = false) String minHorsepower,
                              @RequestParam(required = false) String maxBasePrice,
                              @RequestParam(required = false) String maxZeroToHundred,
                              Model model) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            String normalizedEngineType = normalize(engineType);
            String normalizedDrivetrain = normalize(drivetrain);
            String normalizedTransmission = normalize(transmission);
            Integer parsedMinHorsepower = parseInteger(minHorsepower);
            BigDecimal parsedMaxBasePrice = parseBigDecimal(maxBasePrice);
            BigDecimal parsedMaxZeroToHundred = parseBigDecimal(maxZeroToHundred);

            model.addAttribute("brand", brand.get());
            model.addAttribute("cars", carModelRepository.findByBrandIdWithFilters(
                    id,
                    normalizedEngineType,
                    normalizedDrivetrain,
                    normalizedTransmission,
                    parsedMinHorsepower,
                    parsedMaxBasePrice,
                    parsedMaxZeroToHundred));
            model.addAttribute("engineTypes", carModelRepository.findDistinctEngineTypesByBrandId(id));
            model.addAttribute("transmissions", carModelRepository.findDistinctTransmissionsByBrandId(id));
            model.addAttribute("selectedEngineType", normalizedEngineType);
            model.addAttribute("selectedDrivetrain", normalizedDrivetrain);
            model.addAttribute("selectedTransmission", normalizedTransmission);
            model.addAttribute("minHorsepower", minHorsepower);
            model.addAttribute("maxBasePrice", maxBasePrice);
            model.addAttribute("maxZeroToHundred", maxZeroToHundred);
            return "branddetail";
        }
        return "redirect:/error?message=Brand%20not%20found";
    }

    private String normalize(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }

    private Integer parseInteger(String value) {
        try {
            return normalize(value) == null ? null : Integer.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private BigDecimal parseBigDecimal(String value) {
        try {
            return normalize(value) == null ? null : new BigDecimal(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
