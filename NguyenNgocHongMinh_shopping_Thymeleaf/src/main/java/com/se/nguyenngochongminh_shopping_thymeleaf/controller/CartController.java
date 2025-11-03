package com.se.nguyenngochongminh_shopping_thymeleaf.controller;

import com.se.nguyenngochongminh_shopping_thymeleaf.dto.CartItem;
import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Product;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.CartService;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    // Thêm sản phẩm vào giỏ hàng (CUSTOMER và ADMIN)
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Integer productId,
                            @RequestParam(defaultValue = "1") Integer quantity,
                            RedirectAttributes redirectAttributes) {
        Product product = productService.findById(productId);
        if (product != null) {
            cartService.addToCart(product, quantity);
            redirectAttributes.addFlashAttribute("success",
                "Đã thêm " + quantity + " sản phẩm '" + product.getName() + "' vào giỏ hàng!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Sản phẩm không tồn tại!");
        }
        return "redirect:/product/" + productId;
    }

    // Xem giỏ hàng (CUSTOMER và ADMIN)
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @GetMapping("")
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", cartService.getTotalAmount());
        model.addAttribute("selectedTotal", cartService.getSelectedTotal());
        return "cart/view";
    }

    // Xóa item khỏi giỏ
    @GetMapping("/remove/{productId}")
    public String removeItem(@PathVariable Integer productId) {
        cartService.removeItem(productId);
        return "redirect:/cart";
    }

    // Cập nhật số lượng
    @PostMapping("/update/{productId}")
    public String updateQuantity(@PathVariable Integer productId,
                                  @RequestParam Integer quantity) {
        cartService.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }

    // Cập nhật trạng thái selected (checkbox)
    @PostMapping("/select")
    public String updateSelection(@RequestParam(required = false) List<Integer> selectedItems) {
        for (CartItem item : cartService.getCartItems()) {
            item.setSelected(false);
        }
        // Set selected = true cho các items được chọn
        if (selectedItems != null) {
            for (Integer productId : selectedItems) {
                cartService.updateSelected(productId, true);
            }
        }

        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}

