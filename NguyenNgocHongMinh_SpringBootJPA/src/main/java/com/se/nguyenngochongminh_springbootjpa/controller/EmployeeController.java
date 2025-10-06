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
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final DepartmentService serviceD;
    public EmployeeController(EmployeeService service, DepartmentService serviceD) {
        this.service = service;
        this.serviceD = serviceD;
    }

    // Trang view hiển thị danh sách
    @GetMapping("/")
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
        service.create(e);
        return "redirect:/employees/";
    }
    @GetMapping("/api")
    @ResponseBody
    public List<Employee> getAllApi() {
        return service.getAll();
    }

    @PostMapping("/api")
    @ResponseBody
    public Employee createApi(@RequestBody Employee e) {
        return service.create(e);
    }

    @GetMapping("/api/max-salary")
    @ResponseBody
    public List<Employee> getMaxSalaryApi() {
        return service.getMaxSalaryEmployees();
    }

    @GetMapping("/api/max-age")
    @ResponseBody
    public List<Employee> getMaxAgeApi() {
        return service.getMaxAgeEmployees();
    }

    @GetMapping("/api/avg-salary-dept")
    @ResponseBody
    public List<Map<String, Object>> avgSalaryByDeptApi() {
        return service.getAvgSalaryByDept().stream().map(o -> Map.of(
                "deptId", o[0],
                "count", o[1],
                "avgSalary", o[2]
        )).toList();
    }

    @GetMapping("/api/avg-age-status")
    @ResponseBody
    public List<Map<String, Object>> avgAgeByStatusApi() {
        return service.getAvgAgeByStatus().stream().map(o -> Map.of(
                "status", o[0],
                "count", o[1],
                "avgAge", o[2]
        )).toList();
    }
}