package com.se.nguyenngochongminh_springbootjpa.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departments")

public class Department {
    @Id
    @Column(name = "dept_id")
    private String deptId;

    @Column(name = "dept_name")
    private String deptName;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    public Department() {
    }

    public Department(String deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}