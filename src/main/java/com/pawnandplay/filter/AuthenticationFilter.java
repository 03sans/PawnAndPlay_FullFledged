package com.pawnandplay.filter;

import java.io.IOException;

import com.pawnandplay.util.SessionUtil;
import com.pawnandplay.util.CookiesUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter to manage user authentication and access control based on role.
 * Prevents unauthorized access to protected pages.
 * 
 * @author 23048503 Sanskriti Agrahari
 */
@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {

    // Define paths
    private static final String LOGIN = "/login";
    private static final String REGISTRATION = "/registration";
    private static final String HOME = "/home";
    private static final String ROOT = "/";
    private static final String ABOUT = "/about";
    private static final String CONTACT = "/contact";
    private static final String DASHBOARD = "/dashboard";
    private static final String PROFILE = "/profile";
    private static final String UPDATEPROFILE = "/updateprofile";
    private static final String LOGOUT = "/logout";
    private static final String PRODUCTS = "/products";
    private static final String OPERATIONS = "/operations";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if required
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Cast generic request/response to HTTP-specific versions
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Retrieve the requested URI
        String uri = req.getRequestURI();

        // Allow static resources (e.g., images, styles) and logout to bypass filter
        if (uri.endsWith(".css") || uri.endsWith(".ttf") || uri.endsWith(".png") ||
            uri.endsWith(".jpeg") || uri.endsWith(".jpg") || uri.endsWith(LOGOUT)) {
            chain.doFilter(request, response);
            return;
        }

        // Check if user is logged in
        boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;

        // Get user role from cookie (if any)
        String userRole = CookiesUtil.getCookie(req, "Role") != null
                ? CookiesUtil.getCookie(req, "Role").getValue()
                : null;

        // Logic for admin users
        if (isLoggedIn && "admin".equals(userRole)) {
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTRATION)) {
                // Redirect admin away from login/registration if already logged in
                res.sendRedirect(req.getContextPath() + DASHBOARD);
            } else if (uri.endsWith(DASHBOARD) || uri.endsWith(HOME) || uri.endsWith(ROOT) ||
                       uri.endsWith(ABOUT) || uri.endsWith(CONTACT) || uri.endsWith(PRODUCTS) || 
                       uri.endsWith(OPERATIONS)) {
                // Allow access to admin-specific or public pages
                chain.doFilter(request, response);
            } else {
                // Redirect unknown pages to dashboard
                res.sendRedirect(req.getContextPath() + DASHBOARD);
            }
        }

        // Logic for customer users
        else if (isLoggedIn && "customer".equals(userRole)) {
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTRATION)) {
                // Redirect customer away from login/registration if already logged in
                res.sendRedirect(req.getContextPath() + HOME);
            } else if (uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUT) ||
                       uri.endsWith(CONTACT) || uri.endsWith(PROFILE) || 
                       uri.endsWith(UPDATEPROFILE) || uri.endsWith(PRODUCTS)) {
                // Allow access to customer-specific or public pages
                chain.doFilter(request, response);
            } else {
                // Redirect unknown pages to home
                res.sendRedirect(req.getContextPath() + HOME);
            }
        }

        // Logic for unauthenticated users
        else {
            if (uri.endsWith(LOGIN) || uri.endsWith(REGISTRATION) || uri.endsWith(HOME) ||
                uri.endsWith(ROOT) || uri.endsWith(ABOUT) || uri.endsWith(CONTACT)) {
                // Allow access to public pages
                chain.doFilter(request, response);
            } else {
                // Redirect unauthenticated users to login
		        res.sendRedirect(req.getContextPath() + LOGIN);
		    }
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}
}