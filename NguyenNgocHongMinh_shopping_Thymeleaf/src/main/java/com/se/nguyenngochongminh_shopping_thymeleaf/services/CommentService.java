package com.se.nguyenngochongminh_shopping_thymeleaf.services;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Comment;
import com.se.nguyenngochongminh_shopping_thymeleaf.reposities.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repo;
    public CommentService(CommentRepository repo) { this.repo = repo; }

    public List<Comment> findByProductId(int productId) { return repo.findByProductId(productId); }
    public Comment save(Comment c) { return repo.save(c); }
    public void delete(int id) { repo.deleteById(id); }
}
