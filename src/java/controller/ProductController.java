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

    // Handle GET requests for returning product list as HTML
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("viewList".equals(action)) {
            // Fetch the list of products
            List<Product> productList = null;
            try {
                productList = productDao.getAllProducts();
            } catch (Exception ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                response.getWriter().write("<p>-No data-</p>");
                return;
            }

            // Prepare HTML output
            StringBuilder htmlResponse = new StringBuilder();
            htmlResponse.append("<table border='1'>"); // Added border for better readability
            htmlResponse.append("<tr><th>ID</th><th>Name</th><th>Details</th><th>Price</th><th>Category</th></tr>");

            // Iterate over the product list and create HTML table rows
            for (Product product : productList) {
                htmlResponse.append("<tr>");
                htmlResponse.append("<td>").append(product.getProductId()).append("</td>");
                htmlResponse.append("<td>").append(product.getProductName()).append("</td>");
                htmlResponse.append("<td>").append(product.getProductDetails()).append("</td>");
                htmlResponse.append("<td>").append(product.getPrice()).append("</td>");
                htmlResponse.append("<td>").append(product.getCategoryID() == null ? "No Category" : product.getCategoryID()).append("</td>");
                htmlResponse.append("</tr>");
            }
            htmlResponse.append("</table>");

            response.setContentType("text/html; charset=UTF-8");

            response.getWriter().write(htmlResponse.toString());
        }
    }

    // Handle POST requests for adding products
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
                    response.getWriter().println("Error: Invalid Category ID.");
                    return;
                }
            }

            // Validate price
            BigDecimal price = null;
            try {
                price = new BigDecimal(request.getParameter("productPrice"));
            } catch (NumberFormatException e) {
                response.getWriter().println("Error: Invalid price format.");
                return;
            }

            // Validate stock quantity
            int stockQuantity;
            try {
                stockQuantity = Integer.parseInt(request.getParameter("productStock"));
            } catch (NumberFormatException e) {
                response.getWriter().println("Error: Invalid stock quantity.");
                return;
            }

            String status = request.getParameter("productStatus");

            // Create product object
            Product product = new Product(productName, productDescription, productImage, categoryID, price, stockQuantity, status);

            // Try adding the product
            try {
                productDao.addProduct(product);
                response.getWriter().println("Success: Product added successfully.");
            } catch (SQLException e) {
                response.getWriter().println("Error: Unable to add product. " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
