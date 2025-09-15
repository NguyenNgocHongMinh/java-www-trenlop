package iuh.fit.nguyenngochongminh_tuan04_bai3.model;
import java.util.ArrayList;
import java.util.List;
public class CartBean {
    private List<CartItemBean> items;

    public CartBean() {
        items = new ArrayList<>();
    }

    public List<CartItemBean> getItems() { return items; }

    public void addProduct(Product p, int quantity) {
        if (p == null) return;
        for (CartItemBean item : items) {
            if (item.getProduct().getId()== p.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItemBean(p, quantity));
    }

    public void removeProduct(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public void updateQuantity(int productId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == productId) {
                if (quantity > 0)
                    item.setQuantity(quantity);
                else
                    removeProduct(productId);
                return;
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for (CartItemBean item : items)
            total += item.getSubtotal();
        return total;
    }

    public void clear() {
        items.clear();
    }

}
