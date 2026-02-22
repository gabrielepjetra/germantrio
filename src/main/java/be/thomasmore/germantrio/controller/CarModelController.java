package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.CarModel;
import be.thomasmore.germantrio.repository.CarModelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CarModelController {

    private final CarModelRepository carModelRepository;

    public CarModelController(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    @GetMapping("/carmodels")
    public String carModelsList(Model model) {
        Iterable<CarModel> allCars = carModelRepository.findAll();

        model.addAttribute("cars", allCars);

        return "carmodelslist";
    }

    @GetMapping("/carmodels/{id}")
    public String carModelDetail(@PathVariable long id, Model model) {
        Optional<CarModel> car = carModelRepository.findById(id);

        if (car.isPresent()) {
            model.addAttribute("car", car.get());
        }

        return "carmodeldetail";
    }
}
