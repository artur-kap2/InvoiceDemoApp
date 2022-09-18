package com.example.invoicedemo.controller;

import com.example.invoicedemo.model.Product;
import com.example.invoicedemo.service.ProductService;
import com.example.invoicedemo.service.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public String viewProductsList(Model model) {
        model.addAttribute("products", productService.getProductsList());

        return "products";
    }

    @GetMapping("/products/new")
    public String viewCreateProduct(Model model) {
        model.addAttribute("product", new Product());

        return "product_form";
    }

    @GetMapping("/products/edit/{id}")
    public String viewModifyProduct(@PathVariable("id") final Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") final Long id, Model model) {

        if (productService.isUsed(id)) {
            model.addAttribute("error", String.format("Product is in use"));
        } else {
            Product product = productService.getProduct(id);
            productService.deleteProduct(id);
        }
        model.addAttribute("products", productService.getProductsList());
        return "products";
    }

    @PostMapping("/products/save")
    public String saveProduct(@Valid Product product, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "product_form";
        }
        model.addAttribute("product", product);
        Result result = productService.saveProduct(product);
        if (result.isOk()) {
            return "redirect:/products";
        } else {
            model.addAttribute("error", String.format(result.getMessage()));
            return "product_form";
        }
    }

}
