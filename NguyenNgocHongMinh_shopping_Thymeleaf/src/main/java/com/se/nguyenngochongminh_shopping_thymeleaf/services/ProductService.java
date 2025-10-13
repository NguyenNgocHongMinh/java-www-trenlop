package com.se.nguyenngochongminh_shopping_thymeleaf.services;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Product;
import com.se.nguyenngochongminh_shopping_thymeleaf.reposities.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }
    public List<Product> getAll(){
        return repo.findAll();
    }
    public Product findById(Integer id){
        return repo.findById(id).orElse(null);
    }
}
