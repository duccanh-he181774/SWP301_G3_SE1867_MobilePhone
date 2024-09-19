/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import com.google.gson.Gson;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.UserWithRole;

/**
 *
 * @author LENOVO
 */
public class manageUser extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet manageUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet manageUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String userIdStr = request.getParameter("userId");

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if ("view".equals(action) && userIdStr != null) {
                int userId = Integer.parseInt(userIdStr);
                UserWithRole user = userDAO.getUserById(userId);
                if (user != null) {
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    out.print(json);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
                }
            } else {
                List<UserWithRole> usersWithRoles = userDAO.getUsersWithRoles();
                Gson gson = new Gson();
                String json = gson.toJson(usersWithRoles);
                out.print(json);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (userIdStr != null) {
                int userId = Integer.parseInt(userIdStr);
                boolean success = userDAO.deleteUser(userId);
                if (success) {
                    out.print("{\"status\":\"success\"}");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required.");
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdStr = request.getParameter("userID");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String registrationDateStr = request.getParameter("registrationDate");
        String address = request.getParameter("address");
        String roleName = request.getParameter("roleName");

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (userIdStr != null && userName != null && email != null && registrationDateStr != null && address != null && roleName != null) {
                int userId = Integer.parseInt(userIdStr);

                // Sử dụng SimpleDateFormat để phân tích chuỗi ngày tháng
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy, h:mm:ss a");
                Date date = sdf.parse(registrationDateStr);
                Timestamp registrationDate = new Timestamp(date.getTime());

                UserWithRole user = new UserWithRole(userId, userName, email, registrationDate, address, roleName);
                boolean success = userDAO.updateUser(user);
                if (success) {
                    out.print("{\"status\":\"success\"}");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters.");
            }
            out.flush();
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format.");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
