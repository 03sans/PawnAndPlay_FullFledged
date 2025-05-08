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

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class AuthenticationFilter implements Filter {

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
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Cast the request and response to HttpServletRequest and HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get the requested URI
		String uri = req.getRequestURI();

		if  (uri.endsWith(".css") || uri.endsWith("/logout") ||
				 uri.endsWith(".ttf") || uri.endsWith(".png") || 
				uri.endsWith(".jpeg")||uri.endsWith(".jpg")) {
			chain.doFilter(request, response);
			return;
		}
	
		// Get the session and check if user is logged in
		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
		String userRole = CookiesUtil.getCookie(req, "Role") != null ? CookiesUtil.getCookie(req, "Role").getValue()
				: null;
		System.out.println(isLoggedIn);
		System.out.println("here");
		if (isLoggedIn && "admin".equals(userRole)) {
		    System.out.println("Admin logged in");
		    if (uri.endsWith(LOGIN) || uri.endsWith(REGISTRATION)) {
		        System.out.println("Redirecting to dashboard due to login or registration");
		        res.sendRedirect(req.getContextPath() + DASHBOARD);
		    } else if (uri.endsWith(DASHBOARD) || uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUT) || uri.endsWith(CONTACT)
		            || uri.endsWith(PRODUCTS)|| uri.endsWith(OPERATIONS)) {
		        System.out.println("Allowing access to the requested URI");
		        chain.doFilter(request, response);
		    } else {
		        System.out.println("Redirecting to dashboard from fallback condition");
		        res.sendRedirect(req.getContextPath() + DASHBOARD);
		    }
		} else if (isLoggedIn && "customer".equals(userRole)) {
		    System.out.println("Customer logged in");
		    if (uri.endsWith(LOGIN) || uri.endsWith(REGISTRATION)) {
		        res.sendRedirect(req.getContextPath() + HOME);
		    } else if (uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUT) || uri.endsWith(CONTACT) || 
		            uri.endsWith(PROFILE) || uri.endsWith(UPDATEPROFILE) || uri.endsWith(PRODUCTS)) {
		        chain.doFilter(request, response);
		    } else {
		        res.sendRedirect(req.getContextPath() + HOME);
		    }
		} else {
		    System.out.println("Not logged in");
		    if (uri.endsWith(LOGIN) || uri.endsWith(REGISTRATION) || uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUT) || uri.endsWith(CONTACT)) {
		        chain.doFilter(request, response);
		    } else {
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