package com.se.nguyenngochongminh_springbootjpa.service;
import com.se.nguyenngochongminh_springbootjpa.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(String id);
    Employee create(Employee e);
    Employee update(String id, Employee e);
    void delete(String id);
    List<Employee> getByDepartment(String deptId);
    List<Employee> search(String name, Integer age);
    List<Employee> getOldestEmployees();
    Map<String, Double> getAvgSalaryByDept();
}