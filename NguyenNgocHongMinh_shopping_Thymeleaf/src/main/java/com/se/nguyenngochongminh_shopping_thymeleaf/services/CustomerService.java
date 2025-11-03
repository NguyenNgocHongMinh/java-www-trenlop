package com.se.nguyenngochongminh_shopping_thymeleaf.services;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Customer;
import com.se.nguyenngochongminh_shopping_thymeleaf.reposities.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> findAll() {
        return repo.findAll();
    }

    public Customer findById(Integer id) {
        return repo.findById(id).orElse(null);
    }
}

