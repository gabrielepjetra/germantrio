package be.thomasmore.germantrio.controller;

import be.thomasmore.germantrio.model.Brand;
import be.thomasmore.germantrio.repository.BrandRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandController {

    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping("/brands")
    public String brands(Model model) {
        Iterable<Brand> allBrands = brandRepository.findAll();

        model.addAttribute("brands", allBrands);

        return "brands";
    }
}
