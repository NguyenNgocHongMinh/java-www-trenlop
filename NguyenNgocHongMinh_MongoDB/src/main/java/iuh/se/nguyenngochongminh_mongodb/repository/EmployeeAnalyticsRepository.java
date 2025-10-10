package iuh.se.nguyenngochongminh_mongodb.repository;

import iuh.se.nguyenngochongminh_mongodb.DTO.AvgAgeByStatusDTO;
import iuh.se.nguyenngochongminh_mongodb.DTO.AvgSalaryByDeptIdDTO;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface EmployeeAnalyticsRepository {
    List<AvgSalaryByDeptIdDTO> getCountAndAvgSalaryByDept();
    List<AvgAgeByStatusDTO> getCountAndAvgAgeByStatus();
    List<Document> findAllMaxSalaryEmployees();
    List<Document> findAllMaxAgeEmployees();
}
