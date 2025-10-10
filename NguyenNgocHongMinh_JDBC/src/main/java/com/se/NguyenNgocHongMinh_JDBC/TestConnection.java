package com.se.NguyenNgocHongMinh_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/sample";
        String user = "root";
        String password = "sapassword";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to MariaDB successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}