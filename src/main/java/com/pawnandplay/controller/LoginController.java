package com.pawnandplay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.pawnandplay.model.UserModel;
import com.pawnandplay.service.LoginService;
import com.pawnandplay.util.SessionUtil;
import com.pawnandplay.util.CookiesUtil;
import com.pawnandplay.util.RedirectionUtil;

/** 
 * LoginController handles GET and POST requests for the login page.
 * It validates user credentials using the LoginService and sets session and cookie attributes accordingly.
 * 
 * @author 23048503 Sanskriti Agrahari
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        this.loginService = new LoginService();
    }

    /**
     * Handles GET requests and forwards the user to the login page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for user login.
     * Validates credentials, sets session and cookie attributes, and redirects accordingly.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Wrap credentials in a model
        UserModel user = new UserModel(username, password);
        Boolean loginStatus = loginService.loginUser(user);

        if (loginStatus != null && loginStatus) {
            // Set session attribute
            SessionUtil.setAttribute(req, "username", username);
            
         // Set login success message in session
            req.getSession().setAttribute("successMsg", "Login successful. Welcome back!");

            // Assign role and redirect based on user type
            if (username.equals("admin")) {
                CookiesUtil.addCookie(resp, "Role", "admin", 60 * 30); // 30-minute session
                resp.sendRedirect(req.getServletContext().getContextPath() + "/about"); // Redirect admin to /about
            } else {
                CookiesUtil.addCookie(resp, "Role", "customer", 60 * 30); // 30-minute session
                resp.sendRedirect(req.getServletContext().getContextPath() + "/home"); // Redirect customer to /home
            }
        } else {
            // Handle invalid login or server error
            handleLoginFailure(req, resp, loginStatus);
        }
    }

    /**
     * Displays an appropriate error message based on login failure reason.
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "User credential mismatch. Please try again!";
        }
        req.setAttribute("errorMsg", errorMessage);
        req.getRequestDispatcher(RedirectionUtil.loginUrl).forward(req, resp);
    }
}