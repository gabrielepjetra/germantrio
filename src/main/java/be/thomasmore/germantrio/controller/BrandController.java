package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.Brand;
import be.thomasmore.germantrio.repository.BrandRepository;
import be.thomasmore.germantrio.repository.CarModelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class BrandController {

    private final BrandRepository brandRepository;
    private final CarModelRepository carModelRepository;

    public BrandController(BrandRepository brandRepository, CarModelRepository carModelRepository) {
        this.brandRepository = brandRepository;
        this.carModelRepository = carModelRepository;
    }

    @GetMapping("/brands")
    public String brands(Model model) {
        Iterable<Brand> allBrands = brandRepository.findAll();

        model.addAttribute("brands", allBrands);

        return "brands";
    }

    @GetMapping("/brands/{id}")
    public String brandDetail(@PathVariable long id, Model model) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            model.addAttribute("brand", brand.get());
            model.addAttribute("cars", carModelRepository.findByBrandId(id));
        }
        return "branddetail";
    }
}
