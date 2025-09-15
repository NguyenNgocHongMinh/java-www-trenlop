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
        String action = req.getParameter("action");

        if ("Cancel".equals(action)) {
            resp.sendRedirect("giohang.jsp");
            return;
        }

        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            req.setAttribute("message", "Giỏ hàng trống, không thể thanh toán.");
            req.getRequestDispatcher("thanhtoan.jsp").forward(req, resp);
            return;
        } else {
            session.removeAttribute("cart");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println("<script>alert('Đặt hàng thành công!'); window.location='products';</script>");

        }
    }
}
