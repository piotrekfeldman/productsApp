package com.products.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private ProductsRepository productsRepository;

    public ProductController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/")
    public String getCategoryList(Model model) {

        List<Category> categoryList = productsRepository.getAll().
                stream().map(Product::getCategory).collect(Collectors.toList());
        model.addAttribute("lista", categoryList);
        model.addAttribute("product", new Product());
        return "index";
    }

    @GetMapping("/lista")
    public String getEditForm(@RequestParam(name = "kategoria") String category, Model model) {
        List<Product> productsOutput = new ArrayList<>();
        for (Product product : productsRepository.getAll()) {
            if (product.getCategory().getLinkName().equals(category)) {
                productsOutput.add(product);
            } else if (category.equals("wszystkie")) {
                productsOutput.add(product);
            }
        }

        Double sum = productsOutput.stream().map(Product::getPrice).reduce((result, next) -> result += next).get();
        model.addAttribute("total", sum);
        model.addAttribute("products", productsOutput);
        return "list";
    }

    @PostMapping("/dodaj")
    public String addProduct(Product product) {
        productsRepository.add(product);
        return "index";
    }
}