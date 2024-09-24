/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;
import utils.MD5PasswordEncoderUtils;

@WebServlet(name = "AuthenController", urlPatterns = {"/authen"})
public class AuthenController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page") == null
                ? ""
                : request.getParameter("page");
        String url;
        switch (page) {
            case "login":
                url = "authen/authen.jsp";
                break;
            case "register":
                url = "authen/authen.jsp";
                break;
            default:
                url = "authen/authen.jsp";
                break;
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");

        switch (action) {
            case "login":
                loginDoPost(request, response);
                break;
            case "register":
                register(request, response);
                break;
            default:
                throw new AssertionError();
        }

    }

    private void loginDoPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the email and password from the form
        String email = request.getParameter("usernameMail");
        String password = request.getParameter("password");

        // Use the UserDAO to authenticate the user
        UserDAO userDAO = new UserDAO();
        User user = userDAO.authenticateUser(email, MD5PasswordEncoderUtils.encodeMD5(password));

        if (user != null) {
            // User is authenticated, store the user in the session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect the user to the appropriate page
            response.sendRedirect("home");
        } else {
            // Authentication failed, display an error message
            request.setAttribute("errorMessageLogin", "Invalid email or password");
            request.getRequestDispatcher("authen/authen.jsp").forward(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy thông tin người dùng từ yêu cầu
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Mã hóa mật khẩu
        String encodedPassword = MD5PasswordEncoderUtils.encodeMD5(password);

        // Tạo đối tượng UserDAO
        UserDAO userDAO = new UserDAO();

        // Đăng ký người dùng mới
        User newUser = userDAO.registerUser(username, email, encodedPassword, null, null);

        if (newUser != null) {
            // Đăng ký thành công, điều hướng đến trang đăng nhập
            response.sendRedirect("authen?page=login");
        } else {
            // Đăng ký thất bại, hiển thị thông báo lỗi
            request.setAttribute("errorMessageRegister", "Registration failed. Please try again.");
            request.getRequestDispatcher("authen/authen.jsp").forward(request, response);
        }
    }

}
