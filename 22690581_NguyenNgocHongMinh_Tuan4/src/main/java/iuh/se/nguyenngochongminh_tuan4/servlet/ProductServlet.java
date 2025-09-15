package iuh.se.nguyenngochongminh_tuan4.servlet;

import iuh.se.nguyenngochongminh_tuan4.dao.ProductDAO;
import iuh.se.nguyenngochongminh_tuan4.model.Product;
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

    @Resource(name="jdbc/bookdb")
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
            int id = Integer.parseInt(idstr);
            Product p = productDAO.getProductById(id);
            if (p != null) {
                req.setAttribute("product", p);
                req.getRequestDispatcher("/chitietsach.jsp").forward(req, resp);
                return;
            }
        }
        List<Product> products = productDAO.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/danhsach.jsp").forward(req, resp);

    }
}