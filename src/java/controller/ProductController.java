package controller;

import dao.ProductDao;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet("/MobilePhone/manage-product")
public class ProductController extends HttpServlet {

    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listProducts(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "new":
                    showNewForm(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Product> products = productDao.getAllProducts();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String jsonProducts = convertToJson(products);
            response.getWriter().write(jsonProducts);
        } catch (IOException e) {
            // Xử lý ngoại lệ
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error converting products to JSON\"}");
        }
    }

    private String convertToJson(List<Product> products) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (i > 0) {
                json.append(",");
            }
            json.append("{");
            json.append("\"productID\":").append(product.getProductID()).append(",");
            json.append("\"productName\":\"").append(escapeJsonString(product.getProductName())).append("\",");
            json.append("\"productDetails\":\"").append(escapeJsonString(product.getProductDetails())).append("\",");
            json.append("\"productImage\":\"").append(escapeJsonString(product.getProductImage())).append("\",");
            json.append("\"categoryID\":").append(product.getCategoryID()).append(",");
            json.append("\"price\":").append(product.getPrice()).append(",");
            json.append("\"stockQuantity\":").append(product.getStockQuantity()).append(",");
            json.append("\"status\":\"").append(escapeJsonString(product.getStatus())).append("\"");
            json.append("}");
        }
        json.append("]");
        return json.toString();
    }

    private String escapeJsonString(String input) {
        if (input == null) {
            return "null";
        }
        StringBuilder escaped = new StringBuilder("\"");
        for (char c : input.toCharArray()) {
            switch (c) {
                case '"':
                    escaped.append("\\\"");
                    break;
                case '\\':
                    escaped.append("\\\\");
                    break;
                case '\b':
                    escaped.append("\\b");
                    break;
                case '\f':
                    escaped.append("\\f");
                    break;
                case '\n':
                    escaped.append("\\n");
                    break;
                case '\r':
                    escaped.append("\\r");
                    break;
                case '\t':
                    escaped.append("\\t");
                    break;
                default:
                    escaped.append(c);
            }
        }
        escaped.append("\"");
        return escaped.toString();
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/product-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.getProductById(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/product-form.jsp").forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String name = request.getParameter("name");
            String productDetails = request.getParameter("description");
            String productImage = request.getParameter("imageUrl");
            int categoryID = Integer.parseInt(request.getParameter("categoryId"));
            double price = Double.parseDouble(request.getParameter("price"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            String status = request.getParameter("status");

            // Validate input
            if (name == null || name.trim().isEmpty() || productDetails == null || productDetails.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"success\": false, \"message\": \"Name and description are required\"}");
                return;
            }

            if (price <= 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"success\": false, \"message\": \"Price must be a positive number\"}");
                return;
            }

            if (stockQuantity < 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"success\": false, \"message\": \"Stock quantity must be a non-negative integer\"}");
                return;
            }

            if (!status.equals("in stock") && !status.equals("out of stock")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"success\": false, \"message\": \"Invalid status\"}");
                return;
            }

            // Create the Product object
            Product newProduct = new Product(name, productDetails, productImage, categoryID, price, stockQuantity, status);

            // Save the product
            productDao.saveProduct(newProduct);

            // Return success response
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"success\": true, \"message\": \"Product added successfully\"}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid number format for categoryId, price, or stockQuantity: " + e.getMessage() + "\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"Error saving product to database: " + e.getMessage() + "\"}");
            e.printStackTrace();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"Unexpected error occurred: " + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String productDetails = request.getParameter("productDetails");
        String productImage = request.getParameter("productImage");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        double price = Double.parseDouble(request.getParameter("price"));
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        String status = request.getParameter("status");

        Product updatedProduct = new Product(id, name, productDetails, productImage, categoryID, price, stockQuantity, status);
        productDao.updateProduct(updatedProduct);

        response.sendRedirect("product?action=list");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        productDao.deleteProduct(id);
        response.sendRedirect("product?action=list");
    }
}
