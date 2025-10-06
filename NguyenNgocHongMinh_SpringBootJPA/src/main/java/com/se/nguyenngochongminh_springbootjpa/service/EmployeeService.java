package com.se.nguyenngochongminh_springbootjpa.service;
import com.se.nguyenngochongminh_springbootjpa.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(String id);
    Employee create(Employee e);
    Employee update(String id, Employee e);
    void delete(String id);

    List<Employee> getMaxSalaryEmployees();
    List<Employee> getMaxAgeEmployees();
    List<Object[]> getAvgSalaryByDept();
    List<Object[]> getAvgAgeByStatus();
}