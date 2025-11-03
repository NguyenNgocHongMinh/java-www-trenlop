package com.se.nguyenngochongminh_shopping_thymeleaf.services;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Order;
import com.se.nguyenngochongminh_shopping_thymeleaf.reposities.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> getAll() {
        return repo.findAll();
    }

    public Order findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Order save(Order order) {
        return repo.save(order);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
