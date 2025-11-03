package com.se.nguyenngochongminh_shopping_thymeleaf.controller;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Category;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Xem danh sách categories - chỉ ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public String showAllCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    // Hiển thị form thêm category - chỉ ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    // Lưu category (thêm mới hoặc cập nhật) - chỉ ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage",
            category.getId() == null ? "Thêm danh mục thành công!" : "Cập nhật danh mục thành công!");
        return "redirect:/category";
    }

    // Hiển thị form sửa category - chỉ ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return "redirect:/category";
        }
        model.addAttribute("category", category);
        return "category/form";
    }

    // Xóa category - chỉ ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa danh mục thành công!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/category";
    }
}

