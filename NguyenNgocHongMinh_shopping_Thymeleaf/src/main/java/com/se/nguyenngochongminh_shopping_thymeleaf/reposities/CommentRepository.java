package com.se.nguyenngochongminh_shopping_thymeleaf.reposities;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Integer> {
    List<Comment> findByProductId(int productId);
}
