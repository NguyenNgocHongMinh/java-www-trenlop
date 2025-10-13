package com.se.nguyenngochongminh_shopping_thymeleaf.reposities;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
