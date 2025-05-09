package com.pawnandplay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 
 * @author 23048503 Sanskriti Agrahari
 *Handles user logout by invalidating the session and clearing role cookies.
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests to perform logout operations:
     * - Invalidates the session (if it exists)
     * - Removes the 'Role' cookie from the client
     * - Redirects the user to the login page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Invalidate the user session if it exists
        if (req.getSession(false) != null) {
            req.getSession().invalidate();
        }

        // Retrieve all cookies and remove the 'Role' cookie if present
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Role".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath(req.getContextPath());
                    cookie.setMaxAge(0); // Set expiration to remove it
                    resp.addCookie(cookie); // Add modified cookie to response
                }
            }
        }

        // Redirect the user to the login page after logout
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    /**
     * Delegates POST logout requests to GET logic.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}