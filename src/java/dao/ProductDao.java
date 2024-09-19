/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Product;
import dal.DBContext;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */

public class ProductDao {

    private DBContext dbContext = new DBContext();

    // Add product to the database
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product (ProductName, ProductDetails, ProductImage, CategoryID, Price, StockQuantity, CreatedDate, UpdatedDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getProductName());
            statement.setString(2, product.getProductDetails());
            statement.setString(3, product.getProductImage());
            if (product.getCategoryID() != null) {
                statement.setInt(4, product.getCategoryID());
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            statement.setBigDecimal(5, product.getPrice());
            statement.setInt(6, product.getStockQuantity());
            statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));  // CreatedDate
            statement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));  // UpdatedDate
            statement.setString(9, product.getStatus());

            statement.executeUpdate();
        }
    }

    // Fetch all products from the database
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String productDetails = resultSet.getString("ProductDetails");
                String productImage = resultSet.getString("ProductImage");
                Integer categoryId = resultSet.getInt("CategoryID");
                BigDecimal price = resultSet.getBigDecimal("Price");
                int stockQuantity = resultSet.getInt("StockQuantity");
                Timestamp createdDate = resultSet.getTimestamp("CreatedDate");
                Timestamp updatedDate = resultSet.getTimestamp("UpdatedDate");
                String status = resultSet.getString("Status");

                Product product = new Product(productId, productName, productDetails, productImage, categoryId, price, stockQuantity, status);
                products.add(product);
            }
        }
        return products;
    }
}
