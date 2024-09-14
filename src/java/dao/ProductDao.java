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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDao {
    private DBContext dbContext = new DBContext();

    public boolean addProduct(Product product) throws Exception {
        String sql = "INSERT INTO Product (ProductName, ProductDetails, ProductImage, CategoryID, Price, StockQuantity, CreatedDate, UpdatedDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getProductDetails());
            pstmt.setString(3, product.getProductImage());
            pstmt.setInt(4, product.getCategoryID());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setInt(6, product.getStockQuantity());
            pstmt.setDate(7, new java.sql.Date(product.getCreatedDate().getTime()));
            pstmt.setDate(8, new java.sql.Date(product.getUpdatedDate().getTime()));
            pstmt.setString(9, product.getStatus());
            pstmt.setInt(10, product.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() throws Exception {
        String sql = "SELECT * FROM Product";
        List<Product> products = new ArrayList<>();
        try (Connection conn = dbContext.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("ProductID"));
                product.setName(rs.getString("ProductName"));
                product.setProductDetails(rs.getString("ProductDetails"));
                product.setProductImage(rs.getString("ProductImage"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setPrice(rs.getDouble("Price"));
                product.setStockQuantity(rs.getInt("StockQuantity"));
                product.setCreatedDate(rs.getDate("CreatedDate"));
                product.setUpdatedDate(rs.getDate("UpdatedDate"));
                product.setStatus(rs.getString("Status"));
                products.add(product);
            }
        }
        return products;
    }
}
