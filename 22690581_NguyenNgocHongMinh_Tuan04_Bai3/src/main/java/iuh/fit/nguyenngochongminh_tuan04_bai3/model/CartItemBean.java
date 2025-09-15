package iuh.fit.nguyenngochongminh_tuan04_bai3.model;

import java.io.Serializable;

public class CartItemBean implements Serializable {
    private Product product;
    private int quantity;

    public CartItemBean() {}

    public CartItemBean(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
