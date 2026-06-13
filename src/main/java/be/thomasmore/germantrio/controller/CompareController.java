package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.repository.CarModelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompareController {

    private final CarModelRepository carModelRepository;

    public CompareController(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @GetMapping("/compare")
    public String compare(@RequestParam(required = false) Long leftCarId, Model model) {
        if (leftCarId != null) {
            return carModelRepository.findById(leftCarId)
                    .map(car -> {
                        model.addAttribute("leftCar", car);
                        return "compare";
                    })
                    .orElse("redirect:/compare");
        }

        return "compare";
    }
}
