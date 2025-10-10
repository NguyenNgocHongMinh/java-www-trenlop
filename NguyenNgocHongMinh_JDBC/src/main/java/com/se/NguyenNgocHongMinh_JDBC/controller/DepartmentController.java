package com.se.NguyenNgocHongMinh_JDBC.controller;

import com.se.NguyenNgocHongMinh_JDBC.model.Department;
import com.se.NguyenNgocHongMinh_JDBC.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentRepository repo;

    public DepartmentController(DepartmentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Department> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Department> getById(@PathVariable String id) {
        return repo.findById(id);
    }

    @PostMapping
    public Department create(@RequestBody Department d) {
        return repo.save(d);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable String id, @RequestBody Department d) {
        d.setDeptId(id);
        return repo.save(d);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}