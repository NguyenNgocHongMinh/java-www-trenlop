package iuh.se.nguyenngochongminh_jpa.model;
import jakarta.persistence.*;
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "emp_name")
    private String empName;
    private String email;
    private int age;
    private int status;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    public Employee() {
    }

    public String getEmpId() {
        return empId;
    }

    public Employee(String empId, String empName, String email, int age, int status, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.age = age;
        this.status = status;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", status=" + status +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
