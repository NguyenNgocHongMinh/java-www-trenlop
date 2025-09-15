package iuh.fit.nguyenngochongminh_tuan04_bai3.servlets;

import iuh.fit.nguyenngochongminh_tuan04_bai3.dao.ProductDAO;
import iuh.fit.nguyenngochongminh_tuan04_bai3.model.Product;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet({"/products", "/product"})
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Resource(name="jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idstr = req.getParameter("id");

        if (idstr != null) {
            try {
                int id = Integer.parseInt(idstr);
                System.out.println(id);
                Product product = productDAO.getProductById(id);
                if (product == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                    return;
                }
                req.setAttribute("product", product);
                req.getRequestDispatcher("/product-detail.jsp").forward(req, resp);
                return;
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id");
                return;
            }
        }
        List<Product> products = productDAO.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/product-list.jsp").forward(req, resp);

    }
}