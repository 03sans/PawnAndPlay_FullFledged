package com.pawnandplay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles requests to the home page of the Pawn and Play website.
 * 
 * URL Patterns:
 * - /home
 * - /
 * 
 * Loads the home.jsp view located inside /WEB-INF/pages/
 * to prevent direct browser access.
 * 
 * @author 
 * 23048503 Sanskriti Agrahari
 */
@WebServlet(urlPatterns = {"/home", "/"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests and forwards to home.jsp
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }
}