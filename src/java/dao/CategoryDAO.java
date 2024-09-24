package dal;

import model.ProductCategory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DBContext {

    public List<ProductCategory> getAllCategories() {
        List<ProductCategory> list = new ArrayList<>();
        String query = "SELECT * FROM ProductCategory";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                list.add(new ProductCategory(
                    rs.getInt("CategoryID"),
                    rs.getString("CategoryName"),
                    rs.getString("Description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ProductCategory getCategoryById(int categoryId) {
        String query = "SELECT * FROM ProductCategory WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ProductCategory(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCategory(ProductCategory category) {
        String query = "INSERT INTO ProductCategory (CategoryName, Description) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCategory(ProductCategory category) {
        String query = "UPDATE ProductCategory SET CategoryName = ?, Description = ? WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getCategoryID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCategory(int categoryId) {
        String query = "DELETE FROM ProductCategory WHERE CategoryID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}