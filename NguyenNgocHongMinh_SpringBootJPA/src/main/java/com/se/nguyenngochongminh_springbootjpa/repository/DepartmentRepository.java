package com.se.nguyenngochongminh_springbootjpa.repository;

import com.se.nguyenngochongminh_springbootjpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    @Query("SELECT MAX(d.deptId) FROM Department d")
    String findMaxDeptId();

}
