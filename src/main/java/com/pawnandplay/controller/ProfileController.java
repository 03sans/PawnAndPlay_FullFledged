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
 * 
 * @author 23048503 Sanskriti Agrahari
 * Handles the profile page by retrieving and displaying the user's details.
 */


@WebServlet(asyncSupported = true, urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfileController() {
        super();
    }

    /**
     * Handles GET requests to load the user profile page.
     * Retrieves user information from the session and fetches user details
     * using the LoginService. Forwards data to profile.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        
        // Get the currently logged-in username from the session
        String username = (String) request.getSession().getAttribute("username"); 

        if (username != null) {
            // Retrieve user details using the login service
            LoginService loginService = new LoginService();
            UserModel user = loginService.getUserDetails(username);

            if (user != null) {
                // Set user object as a request attribute to be displayed in the JSP
                request.setAttribute("user", user); 
            } else {
                // Set error message if user not found
                request.setAttribute("error", "User not found!");
            }
        } else {
            // Set error message if no user is logged in
            request.setAttribute("error", "No user logged in!");
        }

        // Forward the request to the profile view JSP
        request.getRequestDispatcher("WEB-INF/pages/profile.jsp").forward(request, response);
    }

    /**
     * Handles POST requests by delegating to doGet.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}