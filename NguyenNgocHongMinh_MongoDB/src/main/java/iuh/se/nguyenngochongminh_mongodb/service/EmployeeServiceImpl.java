package iuh.se.nguyenngochongminh_mongodb.service;

import iuh.se.nguyenngochongminh_mongodb.model.Employee;
import iuh.se.nguyenngochongminh_mongodb.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public Employee create(Employee e) { return repo.save(e); }

    @Override
    public Employee update(String id, Employee e) {
        e.setEmpId(id);
        return repo.save(e);
    }

    @Override
    public void delete(String id) { repo.deleteById(id); }
}
