package controller;

import com.google.gson.Gson;
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
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("viewList".equals(action)) {
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }

            try {
                List<Product> productList = productDao.getProducts(search, page);
                int totalProducts = productDao.countProducts(search);
                int totalPages = (int) Math.ceil((double) totalProducts / 5);

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

                // Add pagination links
                htmlResponse.append("<div class='pagination'>");
                for (int i = 1; i <= totalPages; i++) {
                    htmlResponse.append(String.format("<a href='#' class='page-link' data-page='%d'>%d</a> ", i, i));
                }
                htmlResponse.append("</div>");

                response.setContentType("text/html");
                response.getWriter().write(htmlResponse.toString());

            } catch (SQLException e) {
                response.getWriter().println("Error fetching product list: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ("getProduct".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            try {
                Product product = productDao.getProductById(productId);
                String json = gson.toJson(product);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error retrieving product: " + e.getMessage());
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
            BigDecimal price = new BigDecimal(request.getParameter("productPrice"));
            Integer categoryId = request.getParameter("categoryId").isEmpty() ? null : Integer.parseInt(request.getParameter("categoryId"));
            int stockQuantity = Integer.parseInt(request.getParameter("productStock"));
            String status = request.getParameter("productStatus");

            // Create new product object
            Product newProduct = new Product(productName, productDescription, productImage, categoryId, price, stockQuantity, status);

            try {
                productDao.addProduct(newProduct);
                response.getWriter().println("Product added successfully.");
            } catch (SQLException e) {
                response.getWriter().println("Error adding product: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ("update".equals(action)) {
            // Get parameters from request
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");
            String productDescription = request.getParameter("productDescription");
            String productImage = request.getParameter("imageUrl");
            BigDecimal price = new BigDecimal(request.getParameter("productPrice"));
            Integer categoryId = request.getParameter("categoryId").isEmpty() ? null : Integer.parseInt(request.getParameter("categoryId"));
            int stockQuantity = Integer.parseInt(request.getParameter("productStock"));
            String status = request.getParameter("productStatus");

            // Create updated product object
            Product updatedProduct = new Product(productId, productName, productDescription, productImage, categoryId, price, stockQuantity, status);

            try {
                productDao.updateProduct(updatedProduct);
                response.getWriter().write("Product updated successfully");
            } catch (SQLException e) {
                response.getWriter().write("Error updating product: " + e.getMessage());
            }
        }

        if ("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            try {
                productDao.deleteProduct(productId);
                response.getWriter().write("Product deleted successfully");
            } catch (SQLException e) {
                response.getWriter().write("Error deleting product: " + e.getMessage());
            }
        }
    }
}
