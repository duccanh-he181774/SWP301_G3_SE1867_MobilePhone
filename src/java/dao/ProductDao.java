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

    // Fetch all products not deleted
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE isDeleted = 0";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                products.add(extractProductFromResultSet(resultSet));
            }
        }
        return products;
    }

    public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Product(
                            resultSet.getInt("ProductID"),
                            resultSet.getString("ProductName"),
                            resultSet.getString("ProductDetails"),
                            resultSet.getString("ProductImage"),
                            resultSet.getInt("CategoryID"),
                            resultSet.getBigDecimal("Price"),
                            resultSet.getInt("StockQuantity"),
                            resultSet.getString("Status")
                    );
                }
            }
        }
        return null;
    }

    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET ProductName = ?, ProductDetails = ?, ProductImage = ?, CategoryID = ?, Price = ?, StockQuantity = ?, UpdatedDate = ?, Status = ? WHERE ProductID = ?";
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
            statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            statement.setString(8, product.getStatus());
            statement.setInt(9, product.getProductId());
            statement.executeUpdate();
        }
    }

    public void deleteProduct(int productId) throws SQLException {
        String sql = "UPDATE Product SET isDeleted = TRUE WHERE ProductID = ?";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("ProductID");
        String productName = resultSet.getString("ProductName");
        String productDetails = resultSet.getString("ProductDetails");
        String productImage = resultSet.getString("ProductImage");
        Integer categoryId = resultSet.getInt("CategoryID");
        if (resultSet.wasNull()) {
            categoryId = null;
        }
        BigDecimal price = resultSet.getBigDecimal("Price");
        int stockQuantity = resultSet.getInt("StockQuantity");
        Timestamp createdDate = resultSet.getTimestamp("CreatedDate");
        Timestamp updatedDate = resultSet.getTimestamp("UpdatedDate");
        String status = resultSet.getString("Status");
        boolean isDeleted = resultSet.getBoolean("isDeleted");

        Product product = new Product(productId, productName, productDetails, productImage, categoryId, price, stockQuantity, status);
        product.setCreatedDate(createdDate);
        product.setUpdatedDate(updatedDate);
        // Assuming you have a setter for isDeleted in your Product model if you want to track this in the object
        // product.setIsDeleted(isDeleted);

        return product;
    }
}
