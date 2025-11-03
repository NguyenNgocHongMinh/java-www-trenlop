package com.se.nguyenngochongminh_shopping_thymeleaf.controller;

import com.se.nguyenngochongminh_shopping_thymeleaf.dto.CartItem;
import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Customer;
import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Order;
import com.se.nguyenngochongminh_shopping_thymeleaf.entities.OrderLine;
import com.se.nguyenngochongminh_shopping_thymeleaf.entities.Product;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.CartService;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.CustomerService;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.OrderService;
import com.se.nguyenngochongminh_shopping_thymeleaf.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CustomerService customerService,
                          CartService cartService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.cartService = cartService;
        this.productService = productService;
    }

    // Xem danh sách đơn hàng (CUSTOMER và ADMIN)
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @GetMapping("")
    public String showAllOrders(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    // Xem chi tiết đơn hàng (CUSTOMER và ADMIN)
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @GetMapping("/{id}")
    public String showOrderDetail(@PathVariable int id, Model model) {
        Order order = orderService.findById(id);
        if (order == null) {
            return "redirect:/order";
        }
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderLine line : order.getOrderLines()) {
            if (line.getPurchasePrice() != null && line.getAmount() != null) {
                BigDecimal lineTotal = line.getPurchasePrice()
                        .multiply(BigDecimal.valueOf(line.getAmount()));
                totalAmount = totalAmount.add(lineTotal);
            }
        }
        model.addAttribute("order", order);
        model.addAttribute("totalAmount", totalAmount);
        return "order/detail";
    }

    // Thanh toán từ giỏ hàng( CUSTOMER và ADMIN)
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    @PostMapping("/checkout")
    public String checkout(@RequestParam(required = false) List<Integer> selectedItems,
                          @RequestParam(required = false) Integer customerId,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        // Kiểm tra có sản phẩm được chọn không
        if (selectedItems == null || selectedItems.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng chọn ít nhất một sản phẩm để thanh toán!");
            return "redirect:/cart";
        }

        for (CartItem item : cartService.getCartItems()) {
            item.setSelected(selectedItems.contains(item.getProductId()));
        }

        List<CartItem> itemsToCheckout = cartService.getSelectedItems();

        if (itemsToCheckout.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không có sản phẩm nào được chọn!");
            return "redirect:/cart";
        }

        // Nếu chưa chọn customer, hiển thị form chọn customer
        if (customerId == null) {
            model.addAttribute("cartItems", itemsToCheckout);
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("totalAmount", cartService.getSelectedTotal());
            model.addAttribute("selectedItems", selectedItems);
            return "order/checkout";
        }

        // Tạo order mới
        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            redirectAttributes.addFlashAttribute("error", "Khách hàng không tồn tại!");
            return "redirect:/cart";
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(Calendar.getInstance());

        // Tạo OrderLines từ CartItems
        Set<OrderLine> orderLines = new HashSet<>();
        for (CartItem cartItem : itemsToCheckout) {
            Product product = productService.findById(cartItem.getProductId());
            if (product != null) {
                OrderLine orderLine = new OrderLine();
                orderLine.setProduct(product);
                orderLine.setAmount(cartItem.getQuantity());
                orderLine.setPurchasePrice(cartItem.getPrice());
                orderLine.setOrder(order);
                orderLines.add(orderLine);
            }
        }

        order.setOrderLines(orderLines);

        // Lưu order
        Order savedOrder = orderService.save(order);

        // Xóa các items đã thanh toán khỏi giỏ hàng
        cartService.removeSelectedItems();

        redirectAttributes.addFlashAttribute("success",
            "Đặt hàng thành công! Mã đơn hàng: #" + savedOrder.getId());

        return "redirect:/order/" + savedOrder.getId();
    }
}
