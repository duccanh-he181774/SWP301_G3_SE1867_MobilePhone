package dao;

import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

public class ProductDAO extends DBContext {

    public ProductDAO() {
        super();
    }

    private Product getFromResultSet(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("ProductID"),
                rs.getString("ProductName"),
                rs.getString("ProductDetails"),
                rs.getString("ProductImage"),
                rs.getInt("CategoryID"),
                rs.getDouble("Price"),
                rs.getInt("StockQuantity"),
                rs.getDate("CreatedDate"),
                rs.getDate("UpdatedDate"),
                rs.getString("Status"));
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(getFromResultSet(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getFromResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(getFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getProductsByName(String name) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE ProductName LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(getFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getProducts(int offset, int noOfRecords) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product ORDER BY ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getNoOfRecords() {
        int noOfRecords = 0;
        String query = "SELECT COUNT(*) AS total FROM Product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                noOfRecords = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }

    public List<Product> getFeaturedProducts(int limit) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT TOP (?) * FROM Product ORDER BY CreatedDate DESC";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(getFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        dao.getAllProducts().stream().forEach(item -> {
            System.out.println(item);
        });
    }
}