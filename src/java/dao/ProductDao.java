/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ADMIN
 */
import model.Product;
import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private DBContext dbContext = new DBContext();

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO Product (ProductName, ProductDetails, ProductImage, CategoryID, Price, StockQuantity, CreatedDate, UpdatedDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM Product";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM Product WHERE ProductID = ?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE Product SET ProductName = ?, ProductDetails = ?, ProductImage = ?, CategoryID = ?, Price = ?, StockQuantity = ?, UpdatedDate = ?, Status = ? WHERE ProductID = ?";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM Product WHERE ProductID = ?";

    protected Connection getConnection() throws Exception {
        return dbContext.getConnection();
    }

    public void saveProduct(Product product) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getProductDetails());
                preparedStatement.setString(3, product.getProductImage());
                preparedStatement.setInt(4, product.getCategoryID());
                preparedStatement.setDouble(5, product.getPrice());
                preparedStatement.setInt(6, product.getStockQuantity());
                preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(9, product.getStatus());
                preparedStatement.executeUpdate();
            }

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new SQLException("Error rolling back transaction: " + ex.getMessage(), ex);
                }
            }
            throw new SQLException("Error saving product: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS); ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (Exception e) {
            throw new SQLException("Error getting all products", e);
        }
        return products;
    }

    public Product getProductById(int productID) throws SQLException {
        Product product = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, productID);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    product = extractProductFromResultSet(rs);
                }
            }
        } catch (Exception e) {
            throw new SQLException("Error getting product by ID", e);
        }
        return product;
    }

    public void updateProduct(Product product) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getProductDetails());
                preparedStatement.setString(3, product.getProductImage());
                preparedStatement.setInt(4, product.getCategoryID());
                preparedStatement.setDouble(5, product.getPrice());
                preparedStatement.setInt(6, product.getStockQuantity());
                preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(8, product.getStatus());
                preparedStatement.setInt(9, product.getProductID());
                preparedStatement.executeUpdate();
            }

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new SQLException("Error rolling back transaction", ex);
                }
            }
            throw new SQLException("Error updating product", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
        }
    }

    public void deleteProduct(int productID) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
                preparedStatement.setInt(1, productID);
                preparedStatement.executeUpdate();
            }

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new SQLException("Error rolling back transaction", ex);
                }
            }
            throw new SQLException("Error deleting product", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    // Log error
                }
            }
        }
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("ProductID"),
                rs.getString("ProductName"),
                rs.getString("ProductDetails"),
                rs.getString("ProductImage"),
                rs.getInt("CategoryID"),
                rs.getDouble("Price"),
                rs.getInt("StockQuantity"),
                rs.getString("Status")
        );
    }
}
