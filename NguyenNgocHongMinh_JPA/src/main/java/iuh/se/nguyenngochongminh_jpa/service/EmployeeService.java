package iuh.se.nguyenngochongminh_jpa.service;

import iuh.se.nguyenngochongminh_jpa.model.Employee;

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
