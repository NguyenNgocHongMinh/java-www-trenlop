package com.se.NguyenNgocHongMinh_JDBC.repository;

import com.se.NguyenNgocHongMinh_JDBC.model.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {
}
