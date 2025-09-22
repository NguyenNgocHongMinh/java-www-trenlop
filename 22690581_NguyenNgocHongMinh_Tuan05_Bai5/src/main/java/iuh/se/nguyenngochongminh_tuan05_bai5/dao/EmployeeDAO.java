package iuh.se.nguyenngochongminh_tuan05_bai5.dao;

import iuh.se.nguyenngochongminh_tuan05_bai5.model.Employee;
import iuh.se.nguyenngochongminh_tuan05_bai5.util.DBUtil;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import iuh.se.nguyenngochongminh_tuan05_bai5.model.Department;
public class EmployeeDAO {
    private DBUtil dbutil;
    private DepartmentDAO departmentDAO;
    public EmployeeDAO(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
        departmentDAO = new DepartmentDAO(dataSource);
    }
    public List<Employee> getAllEmployees() {
        List<Employee> emplist = new ArrayList<>();
        String sql = "select * from employees";
        try {
            Connection con = dbutil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getDouble("salary"));
                emplist.add(emp);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return emplist;
        }

    public Employee getByID(int id) {
        String sql = "select * from employees where id =?";
        Employee emp= new Employee();
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                emp = new Employee();
                emp.setId(id);
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getDouble("salary"));
                int departmentId = rs.getInt("department_id");
                Department department = departmentDAO.getByID(departmentId);
                emp.setDepartment(department);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return emp;
    }

    public List<Employee> getAllByDepartment(int deptId) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id=?";
        try (Connection conn = dbutil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee emp = new Employee();
                    emp.setId(rs.getInt("id"));
                    emp.setName(rs.getString("name"));
                    emp.setSalary(rs.getDouble("salary"));
                    Department department = departmentDAO.getByID(deptId);
                    emp.setDepartment(department);
                    list.add(emp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return list;
    }
    public void save(Employee emp) {
        String sql = "INSERT INTO employees(name, salary, department_id) VALUES (?,?,?)" ;
        try (Connection conn = dbutil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, emp.getName());
                ps.setDouble(2, emp.getSalary());
                ps.setInt(3, emp.getDepartment().getId());
                ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Employee emp) {
        String sql = "UPDATE employees SET name=?, salary=?, department_id=? WHERE id=?" ;
        try (Connection conn = dbutil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setInt(3, emp.getDepartment().getId());
            ps.setInt(4, emp.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = dbutil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
