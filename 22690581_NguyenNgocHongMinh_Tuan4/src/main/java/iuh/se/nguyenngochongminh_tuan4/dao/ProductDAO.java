package iuh.se.nguyenngochongminh_tuan4.dao;

import iuh.se.nguyenngochongminh_tuan4.model.Product;
import iuh.se.nguyenngochongminh_tuan4.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = dbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String img = rs.getString("imgURL");
                String description = rs.getString("description");
                Product p = new Product(id, title, author, price, quantity, img, description);
                list.add(p);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM books WHERE ID=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int product_id = rs.getInt("id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String img = rs.getString("imgURL");
                    String description = rs.getString("description");
                    Product p = new Product(product_id, title, author, price, quantity, img, description);
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
