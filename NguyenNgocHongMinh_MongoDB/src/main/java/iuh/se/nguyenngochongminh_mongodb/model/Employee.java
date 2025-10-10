package iuh.se.nguyenngochongminh_mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "employees")
public class Employee {
    @Id
    @JsonIgnore
    private String empId;
    private String empName;
    private String email;
    private int age;
    private int status;
    private double salary;
    private String deptId;
    public Employee() {
    }

    public String getEmpId() {
        return empId;
    }

    public Employee(String empId, String empName, String email, int age, int status, double salary, String deptId) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.age = age;
        this.status = status;
        this.salary = salary;
        this.deptId = deptId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
