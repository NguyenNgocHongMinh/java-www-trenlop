package com.se.nguyenngochongminh_shopping_thymeleaf.services;

import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Category;
import com.se.nguyenngochongminh_shopping_thymeleaf.reposities.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Category findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return repo.save(category);
    }

    public void delete(int id) {
        Category category = findById(id);
        if (category != null) {
            // Kiểm tra xem category có products không
            if (category.getProducts() != null && !category.getProducts().isEmpty()) {
                throw new RuntimeException("Không thể xóa danh mục này vì còn " +
                    category.getProducts().size() + " sản phẩm. Vui lòng xóa hoặc chuyển sản phẩm sang danh mục khác trước.");
            }
            repo.deleteById(id);
        }
    }

    public boolean canDelete(int id) {
        Category category = findById(id);
        return category != null && (category.getProducts() == null || category.getProducts().isEmpty());
    }
}
