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

    @Override
    public Department getById(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Department create(Department d) {
        if (d.getDeptId() == null || d.getDeptId().isEmpty()) {
            d.setDeptId(generateNextId());
        }
        return repo.save(d);
    }
    public String generateNextId() {
        String maxId = repo.findMaxDeptId();
        int nextNumber = 1;
        if (maxId != null) {
            nextNumber = Integer.parseInt(maxId.substring(1)) + 1;
        }
        return String.format("D%03d", nextNumber);
    }
    @Override
    public Department update(String id, Department d) {
        d.setDeptId(id);
        return repo.save(d);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }
}
