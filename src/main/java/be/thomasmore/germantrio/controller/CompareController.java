package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.repository.BrandRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import be.thomasmore.germantrio.service.ComparisonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CompareController {

    private final CarModelRepository carModelRepository;
    private final BrandRepository brandRepository;
    private final ComparisonService comparisonService;

    public CompareController(CarModelRepository carModelRepository,
                             BrandRepository brandRepository,
                             ComparisonService comparisonService) {
        this.carModelRepository = carModelRepository;
        this.brandRepository = brandRepository;
        this.comparisonService = comparisonService;
    }

    @GetMapping("/compare")
    public String compare(@RequestParam(required = false) Long leftCarId,
                          @RequestParam(required = false) Long rightCarId,
                          Model model) {
        model.addAttribute("cars", carModelRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("leftCarId", leftCarId);
        model.addAttribute("rightCarId", rightCarId);

        CarModel leftCarValue = null;
        CarModel rightCarValue = null;

        if (leftCarId != null) {
            Optional<CarModel> leftCar = carModelRepository.findById(leftCarId);
            if (leftCar.isEmpty()) {
                return "redirect:/compare";
            }
            leftCarValue = leftCar.get();
            model.addAttribute("leftCar", leftCarValue);
            model.addAttribute("leftBrandId", leftCarValue.getBrand().getId());
        }

        if (rightCarId != null) {
            Optional<CarModel> rightCar = carModelRepository.findById(rightCarId);
            if (rightCar.isEmpty()) {
                return "redirect:/compare";
            }
            rightCarValue = rightCar.get();
            model.addAttribute("rightCar", rightCarValue);
            model.addAttribute("rightBrandId", rightCarValue.getBrand().getId());
        }

        if (leftCarValue != null && rightCarValue != null) {
            model.addAttribute("comparisonResult", comparisonService.compare(leftCarValue, rightCarValue));
        }

        return "compare";
    }
}
