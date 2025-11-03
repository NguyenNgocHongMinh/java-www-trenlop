package com.se.nguyenngochongminh_shopping_thymeleaf.reposities;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}

