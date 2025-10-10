package com.se.nguyenngochongminh_springbootjpa.service.impl;

import com.se.nguyenngochongminh_springbootjpa.model.Employee;
import com.se.nguyenngochongminh_springbootjpa.repository.EmployeeRepository;
import com.se.nguyenngochongminh_springbootjpa.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> getAll() { return repo.findAll(); }

    @Override
    public Employee getById(String id) { return repo.findById(id).orElse(null); }

    @Override
    public Employee create(Employee e) {
        if (e.getEmpId() == null || e.getEmpId().isEmpty()) {
            e.setEmpId(generateNextId());
        }
        return repo.save(e); }
    public String generateNextId() {
        String maxId = repo.findMaxEmpId(); // E005 chẳng hạn
        int nextNumber = 1;
        if (maxId != null) {
            nextNumber = Integer.parseInt(maxId.substring(1)) + 1;
        }
        return String.format("E%03d", nextNumber);
    }
    @Override
    public Employee update(String id, Employee e) {
        e.setEmpId(id);
        return repo.save(e);
    }

    @Override
    public void delete(String id) { repo.deleteById(id); }

    @Override
    public List<Employee> getByDepartment(String deptId) {
        return repo.findByDepartment_DeptId(deptId);
    }

    @Override
    public List<Employee> search(String name, Integer age) {
        if (name != null && !name.isEmpty()) {
            return repo.findByEmpNameContainingIgnoreCase(name);
        } else if (age != null) {
            return repo.findByAge(age);
        } else {
            return repo.findAll();
        }
    }

    @Override
    public List<Employee> getOldestEmployees() {
        return repo.findMaxAgeEmployees();
    }

    @Override
    public Map<String, Double> getAvgSalaryByDept() {
        List<Object[]> result = repo.getAvgSalaryByDept();
        Map<String, Double> avgMap = new HashMap<>();

        for (Object[] row : result) {
            String deptId = (String) row[0];
            Double avgSalary = (Double) row[1];
            avgMap.put(deptId, avgSalary);
        }

        return avgMap;
    }

}