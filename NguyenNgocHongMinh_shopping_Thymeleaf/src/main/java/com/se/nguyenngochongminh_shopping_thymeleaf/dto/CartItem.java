package com.se.nguyenngochongminh_shopping_thymeleaf.dto;

import java.math.BigDecimal;

public class CartItem {
    private Integer productId;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private boolean selected; // Để chọn sản phẩm khi thanh toán

    public CartItem() {
    }

    public CartItem(Integer productId, String productName, BigDecimal price, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.selected = false;
    }

    // Tính tổng tiền cho item này
    public BigDecimal getTotal() {
        return price.multiply(new BigDecimal(quantity));
    }

    // Getters and Setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

