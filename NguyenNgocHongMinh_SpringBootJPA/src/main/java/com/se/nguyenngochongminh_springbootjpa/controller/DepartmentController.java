package com.se.nguyenngochongminh_springbootjpa.controller;

import com.se.nguyenngochongminh_springbootjpa.model.Department;
import com.se.nguyenngochongminh_springbootjpa.model.Employee;
import com.se.nguyenngochongminh_springbootjpa.service.DepartmentService;
import com.se.nguyenngochongminh_springbootjpa.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService service;
    private final EmployeeService employeeService;
    public DepartmentController(DepartmentService service, EmployeeService employeeService) {
        this.service = service;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String listDepartments(Model model) {
        List<Department> departments = service.getAll();
        Map<String, Double> avgSalaryMap = employeeService.getAvgSalaryByDept();

        model.addAttribute("departments", departments);
        model.addAttribute("avgSalaryMap", avgSalaryMap);
        return "listDepartment";
    }



    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        return "add-department";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute("department") Department d) {
        if (d.getDeptId() == null || d.getDeptId().isEmpty()) {
            service.create(d); // trong service sẽ tự sinh ID Dxxx
        } else {
            service.update(d.getDeptId(), d);
        }
        return "redirect:/department/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        model.addAttribute("department", service.getById(id));
        return "add-department";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") String id) {
        service.delete(id);
        return "redirect:/department/";
    }
    // xem nhân viên theo phòng ban
    @GetMapping("/{deptId}/employees")
    public String listEmployeesByDept(@PathVariable("deptId") String deptId, Model model) {
        List<Employee> employees = employeeService.getByDepartment(deptId);
        model.addAttribute("employees", employees);
        model.addAttribute("deptId", deptId);
        return "employees-by-department";
    }


}
