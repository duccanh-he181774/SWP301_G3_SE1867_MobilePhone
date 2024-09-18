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

    private DBContext dbContext = new DBContext(); // Manages database connections

    // SQL query for inserting a product
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product (ProductName, ProductDetails, ProductImage, CategoryID, Price, StockQuantity, CreatedDate, UpdatedDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // SQL query for fetching all products
    private static final String SELECT_ALL_PRODUCTS = "SELECT ProductID, ProductName, ProductDetails, ProductImage, CategoryID, Price, StockQuantity, CreatedDate, UpdatedDate, Status FROM Product";

    // Method to add a product to the database
    public void addProduct(Product product) throws SQLException, Exception {
        try (Connection connection = dbContext.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDetails());
            preparedStatement.setString(3, product.getProductImage());

            if (product.getCategoryID() != null) {
                preparedStatement.setInt(4, product.getCategoryID());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER); // Handle null CategoryID
            }

            preparedStatement.setBigDecimal(5, product.getPrice());
            preparedStatement.setInt(6, product.getStockQuantity());
            preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // CreatedDate
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // UpdatedDate
            preparedStatement.setString(9, product.getStatus());

            // Execute the insert statement
            preparedStatement.executeUpdate();
        }
    }

    // Method to retrieve all products from the database
    public List<Product> getAllProducts() throws SQLException, Exception {
        List<Product> productList = new ArrayList<>();

        try (Connection connection = dbContext.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS); ResultSet resultSet = preparedStatement.executeQuery()) {

            // Process the ResultSet and create Product objects
            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                String productDetails = resultSet.getString("ProductDetails");
                String productImage = resultSet.getString("ProductImage");
                Integer categoryId = resultSet.getInt("CategoryID");
                if (resultSet.wasNull()) {
                    categoryId = null; // Handle null categoryID
                }
                BigDecimal price = resultSet.getBigDecimal("Price");
                int stockQuantity = resultSet.getInt("StockQuantity");
                Timestamp createdDate = resultSet.getTimestamp("CreatedDate");
                Timestamp updatedDate = resultSet.getTimestamp("UpdatedDate");
                String status = resultSet.getString("Status");

                // Create a Product object and add it to the list
                Product product = new Product(productId, productName, productDetails, productImage, categoryId, price, stockQuantity, status);
                productList.add(product);
            }
        }

        return productList;
    }
}
