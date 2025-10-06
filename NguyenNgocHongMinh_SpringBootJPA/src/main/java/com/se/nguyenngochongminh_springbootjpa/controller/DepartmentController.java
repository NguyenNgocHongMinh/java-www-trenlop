package com.se.nguyenngochongminh_springbootjpa.controller;

import com.se.nguyenngochongminh_springbootjpa.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String listDepartment(Model model) {
        model.addAttribute("departments", service.getAll());
        return "listDepartment";
    }
}
