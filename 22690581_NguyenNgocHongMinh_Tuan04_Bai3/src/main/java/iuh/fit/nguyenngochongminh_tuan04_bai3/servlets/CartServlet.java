package iuh.fit.nguyenngochongminh_tuan04_bai3.servlets;

import iuh.fit.nguyenngochongminh_tuan04_bai3.model.CartBean;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import iuh.fit.nguyenngochongminh_tuan04_bai3.dao.ProductDAO;
import iuh.fit.nguyenngochongminh_tuan04_bai3.model.Product;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Resource(name="jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                int quantity = 1;
                String quantityStr = req.getParameter("quantity");
                if (quantityStr != null && !quantityStr.isBlank()) {
                    try {
                        quantity = Integer.parseInt(quantityStr);
                    } catch (NumberFormatException e) {
                        quantity = 1;
                    }
                }

                Product p = productDAO.getProductById(id);
                if (p != null) {
                    cart.addProduct(p, quantity);
                }
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("productId"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                cart.updateQuantity(id, quantity);
            } else if ("remove".equals(action)) {
                int id = Integer.parseInt(req.getParameter("productId"));
                cart.removeProduct(id);
            } else if ("clear".equals(action)) {
                cart.clear();
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}