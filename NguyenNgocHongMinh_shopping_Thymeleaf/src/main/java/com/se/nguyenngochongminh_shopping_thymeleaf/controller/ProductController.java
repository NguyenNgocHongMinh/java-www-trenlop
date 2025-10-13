package com.se.nguyenngochongminh_shopping_thymeleaf.controller;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Product;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "product/list";
    }
    @GetMapping("/{id}")
    public String showProduct(@PathVariable int id, Model model) {
    Product product=productService.findById(id);
        model.addAttribute("product", product);
        return "product/productdetail";
    }
}