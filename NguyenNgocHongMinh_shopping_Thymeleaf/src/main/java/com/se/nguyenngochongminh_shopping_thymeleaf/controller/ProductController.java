package com.se.nguyenngochongminh_shopping_thymeleaf.controller;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Product;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.CategoryService;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
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
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());  // lấy danh mục
        return "product/form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/product";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/product";
    }
}