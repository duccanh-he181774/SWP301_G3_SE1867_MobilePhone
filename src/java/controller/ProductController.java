package controller;

import dao.ProductDao;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */

@WebServlet("/MobilePhone/manage-product")
public class ProductController extends HttpServlet {

    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("viewList".equals(action)) {
            try {
                List<Product> productList = productDao.getAllProducts();

                StringBuilder htmlResponse = new StringBuilder();
                htmlResponse.append("<table>");
                for (Product product : productList) {
                    htmlResponse.append("<tr>");
                    htmlResponse.append("<td>").append(product.getProductId()).append("</td>");
                    htmlResponse.append("<td>").append(product.getProductName()).append("</td>");
                    htmlResponse.append("<td>").append(product.getProductDetails()).append("</td>");
                    htmlResponse.append("<td>").append("<img src=\"" + product.getProductImage() + "\" width=\"200\">").append("</td>");
                    htmlResponse.append("<td>").append(product.getPrice()).append("</td>");
                    htmlResponse.append("<td>").append(product.getCategoryID() == null ? "No Category" : product.getCategoryID()).append("</td>");
                    htmlResponse.append("<td>").append(product.getStockQuantity()).append("</td>");
                    htmlResponse.append("<td>").append(product.getStatus()).append("</td>");
                    htmlResponse.append("</tr>");
                }
                htmlResponse.append("</table>");

                response.setContentType("text/html");
                response.getWriter().write(htmlResponse.toString());

            } catch (SQLException e) {
                response.getWriter().println("Error fetching product list: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Retrieve form parameters
            String productName = request.getParameter("productName");
            String productDescription = request.getParameter("productDescription");
            String productImage = request.getParameter("imageUrl");

            // CategoryID handling
            Integer categoryID = null;
            String categoryIDParam = request.getParameter("categoryId");
            if (categoryIDParam != null && !categoryIDParam.isEmpty()) {
                try {
                    categoryID = Integer.parseInt(categoryIDParam);
                } catch (NumberFormatException e) {
                    response.getWriter().println("Invalid Category ID.");
                    return;
                }
            }

            // Validate price
            BigDecimal price;
            try {
                price = new BigDecimal(request.getParameter("productPrice"));
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid price format.");
                return;
            }

            int stockQuantity = Integer.parseInt(request.getParameter("productStock"));
            String status = request.getParameter("productStatus");

            // Create product object and save it
            Product product = new Product(productName, productDescription, productImage, categoryID, price, stockQuantity, status);
            try {
                productDao.addProduct(product);
                response.getWriter().println("Product added successfully.");
            } catch (SQLException e) {
                response.getWriter().println("Error adding product: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
