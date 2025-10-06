package com.se.nguyenngochongminh_springbootjpa.repository;
import com.se.nguyenngochongminh_springbootjpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByEmpNameContainingIgnoreCase(String name);
    List<Employee> findByStatus(int status);

    // Nhân viên lương cao nhất
    @Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e2.salary) FROM Employee e2)")
    List<Employee> findMaxSalaryEmployees();

    // Nhân viên lớn tuổi nhất
    @Query("SELECT e FROM Employee e WHERE e.age = (SELECT MAX(e2.age) FROM Employee e2)")
    List<Employee> findMaxAgeEmployees();

    // Lương TB theo phòng ban
    @Query("SELECT e.department.deptId AS deptId, COUNT(e) AS count, AVG(e.salary) AS avgSalary FROM Employee e GROUP BY e.department.deptId")
    List<Object[]> getAvgSalaryByDept();

    // Tuổi TB theo status
    @Query("SELECT e.status AS status, COUNT(e) AS count, AVG(e.age) AS avgAge FROM Employee e GROUP BY e.status")
    List<Object[]> getAvgAgeByStatus();
}