/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ADMIN
 */

import dal.DBContext;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private DBContext dbContext = new DBContext();

    public List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getInt("id"), 
                    rs.getString("name"), 
                    rs.getDouble("price"), 
                    rs.getString("description"),
                    rs.getString("imageUrl"),
                    rs.getInt("categoryId"),
                    rs.getInt("stock"),
                    rs.getDate("dateAdded"),
                    rs.getDate("dateUpdated"),
                    rs.getString("status")
                ));
            }
        }
        return products;
    }

    public Product getProductById(int id) throws Exception {
        Product product = null;
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("imageUrl"),
                        rs.getInt("categoryId"),
                        rs.getInt("stock"),
                        rs.getDate("dateAdded"),
                        rs.getDate("dateUpdated"),
                        rs.getString("status")
                    );
                }
            }
        }
        return product;
    }

    public void saveProduct(Product product) throws Exception {
        String sql = "INSERT INTO products (name, description, imageUrl, categoryId, price, stock, dateAdded, dateUpdated, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setString(3, product.getImageUrl());
            pstmt.setInt(4, product.getCategoryId());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setInt(6, product.getStock());
            pstmt.setDate(7, new java.sql.Date(product.getDateAdded().getTime()));
            pstmt.setDate(8, new java.sql.Date(product.getDateUpdated().getTime()));
            pstmt.setString(9, product.getStatus());
            pstmt.executeUpdate();
        }
    }

    public void updateProduct(Product product) throws Exception {
        String sql = "UPDATE products SET name=?, description=?, imageUrl=?, categoryId=?, price=?, stock=?, dateUpdated=?, status=? WHERE id=?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setString(3, product.getImageUrl());
            pstmt.setInt(4, product.getCategoryId());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setInt(6, product.getStock());
            pstmt.setDate(7, new java.sql.Date(product.getDateUpdated().getTime()));
            pstmt.setString(8, product.getStatus());
            pstmt.setInt(9, product.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws Exception {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

