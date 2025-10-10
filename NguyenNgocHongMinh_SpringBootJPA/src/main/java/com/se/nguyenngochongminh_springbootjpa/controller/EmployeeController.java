package com.se.nguyenngochongminh_springbootjpa.controller;
import com.se.nguyenngochongminh_springbootjpa.model.Employee;
import com.se.nguyenngochongminh_springbootjpa.service.DepartmentService;
import com.se.nguyenngochongminh_springbootjpa.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService service;
    private final DepartmentService serviceD;
    public EmployeeController(EmployeeService service, DepartmentService serviceD) {
        this.service = service;
        this.serviceD = serviceD;
    }

    // Trang view hiển thị danh sách
    @GetMapping("")
    public String listEmployees(Model model) {
        model.addAttribute("employees", service.getAll());
        return "index";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", serviceD.getAll());
        return "add-employee";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee e) {
        if (e.getEmpId() != null && !e.getEmpId().isEmpty()) {
            service.update(e.getEmpId(), e);
        } else {
            service.create(e);
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Employee emp = service.getById(id);
        model.addAttribute("employee", emp);
        model.addAttribute("departments", serviceD.getAll());
        return "add-employee"; // dùng lại form thêm
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        service.delete(id);
        return "redirect:/";
    }
    @GetMapping("/search")
    public String searchEmployees(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) Integer age,
                                  Model model) {
        model.addAttribute("employees", service.search(name, age));
        return "index";
    }

    @GetMapping("/max-age")
    public String showOldestEmployees(Model model) {
        model.addAttribute("employees", service.getOldestEmployees());
        return "index";
    }


}