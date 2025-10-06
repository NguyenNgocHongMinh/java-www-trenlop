package com.se.nguyenngochongminh_springbootjpa.controller;
import com.se.nguyenngochongminh_springbootjpa.model.Employee;
import com.se.nguyenngochongminh_springbootjpa.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() { return service.getAll(); }

    @PostMapping
    public Employee create(@RequestBody Employee e) { return service.create(e); }

    @GetMapping("/max-salary")
    public List<Employee> getMaxSalary() { return service.getMaxSalaryEmployees(); }

    @GetMapping("/max-age")
    public List<Employee> getMaxAge() { return service.getMaxAgeEmployees(); }

    @GetMapping("/avg-salary-dept")
    public List<Map<String, Object>> avgSalaryByDept() {
        return service.getAvgSalaryByDept().stream().map(o -> Map.of(
                "deptId", o[0],
                "count", o[1],
                "avgSalary", o[2]
        )).toList();
    }

    @GetMapping("/avg-age-status")
    public List<Map<String, Object>> avgAgeByStatus() {
        return service.getAvgAgeByStatus().stream().map(o -> Map.of(
                "status", o[0],
                "count", o[1],
                "avgAge", o[2]
        )).toList();
    }
}