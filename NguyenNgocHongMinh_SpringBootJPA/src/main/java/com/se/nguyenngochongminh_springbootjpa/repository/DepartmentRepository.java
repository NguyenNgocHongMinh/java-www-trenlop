package com.se.nguyenngochongminh_springbootjpa.repository;
import com.se.nguyenngochongminh_springbootjpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
}