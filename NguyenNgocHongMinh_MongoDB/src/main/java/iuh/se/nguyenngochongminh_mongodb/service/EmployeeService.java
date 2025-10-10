package iuh.se.nguyenngochongminh_mongodb.service;

import iuh.se.nguyenngochongminh_mongodb.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(String id);
    Employee create(Employee e);
    Employee update(String id, Employee e);
    void delete(String id);
}