package com.se.NguyenNgocHongMinh_JDBC.controller;

import com.se.NguyenNgocHongMinh_JDBC.model.Employee;
import com.se.NguyenNgocHongMinh_JDBC.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Employee> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable String id) {
        return repo.findById(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee e) {
        return repo.save(e);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee e) {
        e.setEmpId(id);
        return repo.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }

    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String name) {
        return repo.findByEmpName(name);
    }

    @GetMapping("/age/{age}")
    public List<Employee> searchByAge(@PathVariable int age) {
        return repo.findByAge(age);
    }

    @GetMapping("/salary/{salary}")
    public List<Employee> searchBySalary(@PathVariable double salary) {
        return repo.findBySalary(salary);
    }
}
