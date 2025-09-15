package iuh.se.nguyenngochongminh_tuan4.servlet;

import iuh.se.nguyenngochongminh_tuan4.model.CartBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            session.removeAttribute("cart");
            req.setAttribute("message", "Thanh toán thành công!");
        } else {
            req.setAttribute("message", "Giỏ hàng trống, không thể thanh toán.");
        }
        req.getRequestDispatcher("thanhtoan.jsp").forward(req, resp);
    }
}
