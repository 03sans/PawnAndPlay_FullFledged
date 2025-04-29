package com.pawnandplay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.pawnandplay.model.UserModel;
import com.pawnandplay.service.LoginService;

/**
 * @author 23048503 Sanskriti Agrahari
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfileController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String username = (String) request.getSession().getAttribute("username"); 

        if (username != null) {
            LoginService loginService = new LoginService();
            UserModel user = loginService.getUserDetails(username);
            if (user != null) {
                request.setAttribute("user", user); 
                System.out.println("User fetched: " + user.getUsername());
            } else {
                request.setAttribute("error", "User not found!");
                System.out.println("Error: User not found for username: " + username);
            }
        } else {
            request.setAttribute("error", "No user logged in!");
            System.out.println("Error: No user logged in!");
        }

        // Forward the request to profile.jsp for rendering the profile page
        request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}