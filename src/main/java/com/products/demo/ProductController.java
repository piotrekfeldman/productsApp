package com.products.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private ProductsRepository productsRepository;
    private boolean productToAdd = false;

    public ProductController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("/")
    public String getCategoryList(Model model) {


        model.addAttribute("product", new Product());
        model.addAttribute("success", productToAdd);
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
    public String addProduct(Product product, Model model) {

        productToAdd = productsRepository.add(product);
        if (productToAdd) {
            return "redirect:success";
        } else {
            return "redirect:failure.html";
        }
    }

    @GetMapping("/success")
    public String successPage(Product product, Model model) {
        Product lastProduct = productsRepository.getAll().stream().reduce((first, second) -> second)
                .orElse(null);
        model.addAttribute("last", lastProduct);
        return "success";
    }
}