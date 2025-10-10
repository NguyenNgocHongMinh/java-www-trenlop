package com.se.nguyenngochongminh_springbootjpa.repository;
import com.se.nguyenngochongminh_springbootjpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByEmpNameContainingIgnoreCase(String name);
    List<Employee> findByAge(int age);
    @Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e2.salary) FROM Employee e2)")
    List<Employee> findMaxSalaryEmployees();

    @Query("SELECT e FROM Employee e WHERE e.age = (SELECT MAX(e2.age) FROM Employee e2)")
    List<Employee> findMaxAgeEmployees();

        @Query("SELECT e.department.deptId AS deptId, COALESCE(AVG(e.salary), 0) AS avgSalary " +
                "FROM Employee e " +
                "GROUP BY e.department.deptId")
        List<Object[]> getAvgSalaryByDept();



    List<Employee> findByDepartment_DeptId(String deptId);
    @Query("SELECT MAX(e.empId) FROM Employee e")
    String findMaxEmpId();

}