package com.se.nguyenngochongminh_springbootjpa.service.impl;

import com.se.nguyenngochongminh_springbootjpa.model.Department;
import com.se.nguyenngochongminh_springbootjpa.repository.DepartmentRepository;
import com.se.nguyenngochongminh_springbootjpa.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repo;

    public DepartmentServiceImpl(DepartmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Department> getAll() {
        return repo.findAll();
    }
}
