package com.se.nguyenngochongminh_shopping_thymeleaf.controller;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Comment;
import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Product;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.CommentService;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final ProductService productService;

    public CommentController(CommentService commentService, ProductService productService) {
        this.commentService = commentService;
        this.productService = productService;
    }

    @PostMapping("/add/{productId}")
    public String addComment(@PathVariable int productId, @RequestParam String text) {
        Product product = productService.findById(productId);
        Comment c = new Comment();
        c.setText(text);
        c.setProduct(product);
        commentService.save(c);
        return "redirect:/product/" + productId;
    }

    @GetMapping("/delete/{id}/{productId}")
    public String deleteComment(@PathVariable int id, @PathVariable int productId) {
        commentService.delete(id);
        return "redirect:/product/" + productId;
    }
}
