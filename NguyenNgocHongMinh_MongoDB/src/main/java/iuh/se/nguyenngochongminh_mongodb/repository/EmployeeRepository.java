package iuh.se.nguyenngochongminh_mongodb.repository;

import iuh.se.nguyenngochongminh_mongodb.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findByEmpId(String empId);
    List<Employee> findByEmpNameContainingIgnoreCase(String empName);
    List<Employee> findByDeptId(String deptId);
    List<Employee> findByStatus(int status);
}
