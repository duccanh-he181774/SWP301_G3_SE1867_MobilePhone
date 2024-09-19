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
            statement.setTimestamp(7, new Timestamp(System.currentTimeMillis())); 
            statement.setTimestamp(8, new Timestamp(System.currentTimeMillis())); 
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
        String sql = "DELETE FROM Product WHERE ProductID = ?";
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
        Integer categoryID = resultSet.getInt("CategoryID");
        if (resultSet.wasNull()) {
            categoryID = null;
        }
        BigDecimal price = resultSet.getBigDecimal("Price");
        int stockQuantity = resultSet.getInt("StockQuantity");
        Timestamp createdDate = resultSet.getTimestamp("CreatedDate");
        Timestamp updatedDate = resultSet.getTimestamp("UpdatedDate");
        String status = resultSet.getString("Status");

        Product product = new Product(productId, productName, productDetails, productImage, categoryID, price, stockQuantity, status);
        product.setCreatedDate(createdDate);
        product.setUpdatedDate(updatedDate);

        return product;
    }

    public List<Product> getProducts(String search, int page) throws SQLException {
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * 5;
        String sql = "SELECT * FROM Product WHERE LOWER(ProductName) LIKE ? ORDER BY ProductID OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + search.toLowerCase() + "%");
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(extractProductFromResultSet(resultSet));
                }
            }
        }
        return products;
    }

    public int countProducts(String search) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Product WHERE LOWER(ProductName) LIKE ?";
        try (Connection connection = dbContext.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + search.toLowerCase() + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        return 0;
    }
}
