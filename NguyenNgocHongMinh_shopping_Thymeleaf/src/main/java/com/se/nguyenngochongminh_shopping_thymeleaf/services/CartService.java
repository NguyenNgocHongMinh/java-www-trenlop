package com.se.nguyenngochongminh_shopping_thymeleaf.services;

import com.se.nguyenngochongminh_shopping_thymeleaf.dto.CartItem;
import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope // Mỗi session sẽ có một instance riêng
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();

    // Thêm sản phẩm vào giỏ hàng
    public void addToCart(Product product, Integer quantity) {
        if (product == null || quantity == null || quantity <= 0) {
            return;
        }

        // Kiểm tra xem sản phẩm đã có trong giỏ chưa
        Optional<CartItem> existingItem = cartItems.stream()
                .filter(item -> item.getProductId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Nếu đã có, tăng số lượng
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            // Nếu chưa có, thêm mới
            CartItem newItem = new CartItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    quantity
            );
            cartItems.add(newItem);
        }
    }

    // Lấy tất cả items trong giỏ
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Xóa item khỏi giỏ
    public void removeItem(Integer productId) {
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }

    // Cập nhật số lượng
    public void updateQuantity(Integer productId, Integer quantity) {
        if (quantity <= 0) {
            removeItem(productId);
            return;
        }

        cartItems.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
    }

    // Cập nhật trạng thái selected
    public void updateSelected(Integer productId, boolean selected) {
        cartItems.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst()
                .ifPresent(item -> item.setSelected(selected));
    }

    // Lấy danh sách items được chọn
    public List<CartItem> getSelectedItems() {
        return cartItems.stream()
                .filter(CartItem::isSelected)
                .toList();
    }

    // Xóa các items đã được chọn (sau khi thanh toán)
    public void removeSelectedItems() {
        cartItems.removeIf(CartItem::isSelected);
    }

    // Tính tổng tiền của các items được chọn
    public BigDecimal getSelectedTotal() {
        return cartItems.stream()
                .filter(CartItem::isSelected)
                .map(CartItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Tính tổng tiền toàn bộ giỏ hàng
    public BigDecimal getTotalAmount() {
        return cartItems.stream()
                .map(CartItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Đếm số lượng items trong giỏ
    public int getItemCount() {
        return cartItems.size();
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart() {
        cartItems.clear();
    }
}

