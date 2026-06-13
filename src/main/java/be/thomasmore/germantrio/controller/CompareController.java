package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.repository.BrandRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CompareController {

    private final CarModelRepository carModelRepository;
    private final BrandRepository brandRepository;

    public CompareController(CarModelRepository carModelRepository, BrandRepository brandRepository) {
        this.carModelRepository = carModelRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping("/compare")
    public String compare(@RequestParam(required = false) Long leftCarId,
                          @RequestParam(required = false) Long rightCarId,
                          Model model) {
        model.addAttribute("cars", carModelRepository.findAll());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("leftCarId", leftCarId);
        model.addAttribute("rightCarId", rightCarId);

        if (leftCarId != null) {
            Optional<CarModel> leftCar = carModelRepository.findById(leftCarId);
            if (leftCar.isEmpty()) {
                return "redirect:/compare";
            }
            model.addAttribute("leftCar", leftCar.get());
            model.addAttribute("leftBrandId", leftCar.get().getBrand().getId());
        }

        if (rightCarId != null) {
            Optional<CarModel> rightCar = carModelRepository.findById(rightCarId);
            if (rightCar.isEmpty()) {
                return "redirect:/compare";
            }
            model.addAttribute("rightCar", rightCar.get());
            model.addAttribute("rightBrandId", rightCar.get().getBrand().getId());
        }

        return "compare";
    }
}
