package iuh.se.nguyenngochongminh_jpa.model;
import jakarta.persistence.*;
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "dept_id")
    private String deptId;
    @Column(name = "dept_name")
    private String deptName;

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

    public Department(String deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }
    public Department() {}

    @Override
    public String toString() {
        return "Department{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
