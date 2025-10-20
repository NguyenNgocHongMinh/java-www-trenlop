package com.se.nguyenngochongminh_shopping_thymeleaf.reposities;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}