package com.pawnandplay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Invalidate session
        if (req.getSession(false) != null) {
            req.getSession().invalidate();
            System.out.println("[Logout] Session found and invalidated.");
        } else {
            System.out.println("[Logout] No session found.");
        }

        // Remove 'Role' cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            System.out.println("[Logout] Total cookies: " + cookies.length);
            for (Cookie cookie : cookies) {
                System.out.println("[Logout] Cookie name: " + cookie.getName() + ", value: " + cookie.getValue());
                if ("Role".equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath(req.getContextPath());
                    cookie.setMaxAge(0); // Delete immediately
                    resp.addCookie(cookie);
                    System.out.println("[Logout] Role cookie removed successfully.");
                }
            }
        } else {
            System.out.println("[Logout] No cookies found.");
        }

        // Redirect to login page
        System.out.println("[Logout] Redirecting to login page.");
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}