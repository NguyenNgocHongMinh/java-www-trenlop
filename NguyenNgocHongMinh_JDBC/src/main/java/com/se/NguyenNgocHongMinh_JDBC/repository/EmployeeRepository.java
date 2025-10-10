package com.se.NguyenNgocHongMinh_JDBC.repository;

import com.se.NguyenNgocHongMinh_JDBC.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    List<Employee> findByEmpName(String name);
    List<Employee> findByAge(int age);
    List<Employee> findBySalary(double salary);
    List<Employee> findByDeptId(String deptId);
}
