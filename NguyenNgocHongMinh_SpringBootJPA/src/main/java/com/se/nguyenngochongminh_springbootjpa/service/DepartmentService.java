package com.se.nguyenngochongminh_springbootjpa.service;

import com.se.nguyenngochongminh_springbootjpa.model.Department;
import com.se.nguyenngochongminh_springbootjpa.model.Employee;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();
}
