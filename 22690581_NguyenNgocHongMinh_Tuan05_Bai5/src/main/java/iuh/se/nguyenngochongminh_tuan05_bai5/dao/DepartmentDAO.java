package iuh.se.nguyenngochongminh_tuan05_bai5.dao;

import iuh.se.nguyenngochongminh_tuan05_bai5.model.Department;
import iuh.se.nguyenngochongminh_tuan05_bai5.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    private DBUtil dbutil;
    public DepartmentDAO(DataSource dataSource) {
        dbutil = new DBUtil(dataSource);
    }
    public List<Department> getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();
        String sql = "select * from departments";
        try {
            Connection con = dbutil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("name"));
                departmentList.add(department);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return departmentList;
    }

    public Department getByID(int id) {
        String sql = "select * from departments where department_id = ?";
        Department department= new Department();
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                department.setId(id);
                department.setName(rs.getString("name"));

            }else{
                department = null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return department;
    }
    public void save(Department dept) {
        String sql = "INSERT INTO departments(name) VALUES (?)";
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dept.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Department dept) {
        String sql = "UPDATE departments SET name = ? WHERE department_id = ?";
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dept.getName());
            ps.setInt(2, dept.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(int id) {
        String sql = "DELETE FROM departments WHERE department_id = ?";
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Department> searchByName(String departmentName) {
        List<Department> departmentList = new ArrayList<>();
        String sql = "SELECT * FROM departments WHERE name LIKE ?";
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + departmentName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("name"));
                departmentList.add(department);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return departmentList;
    }

}
