package com.se.nguyenngochongminh_springbootjpa.service;

import com.se.nguyenngochongminh_springbootjpa.model.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
    Department getById(String id);
    Department create(Department d);
    Department update(String id, Department d);
    void delete(String id);
}
