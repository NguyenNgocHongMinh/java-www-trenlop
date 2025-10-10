package iuh.se.nguyenngochongminh_mongodb.controller;

import iuh.se.nguyenngochongminh_mongodb.DTO.AvgAgeByStatusDTO;
import iuh.se.nguyenngochongminh_mongodb.DTO.AvgSalaryByDeptIdDTO;
import iuh.se.nguyenngochongminh_mongodb.model.Employee;
import iuh.se.nguyenngochongminh_mongodb.repository.EmployeeAnalyticsRepository;
import iuh.se.nguyenngochongminh_mongodb.service.EmployeeService;

import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeAnalyticsRepository analytics;

    public EmployeeController(EmployeeService service, EmployeeAnalyticsRepository analytics) {
        this.service = service;
        this.analytics = analytics;
    }

    @GetMapping
    public List<Employee> getAll() { return service.getAll(); }

    @PostMapping
    public Employee create(@RequestBody Employee e) { return service.create(e); }

    @GetMapping("/max-salary")
    public List<Document> maxSalary() { return analytics.findAllMaxSalaryEmployees(); }

    @GetMapping("/max-age")
    public List<Document> maxAge() { return analytics.findAllMaxAgeEmployees(); }

    @GetMapping("/avg-salary-dept")
    public List<AvgSalaryByDeptIdDTO> avgSalaryDept() { return analytics.getCountAndAvgSalaryByDept(); }

    @GetMapping("/avg-age-status")
    public List<AvgAgeByStatusDTO> avgAgeStatus() { return analytics.getCountAndAvgAgeByStatus(); }
}
