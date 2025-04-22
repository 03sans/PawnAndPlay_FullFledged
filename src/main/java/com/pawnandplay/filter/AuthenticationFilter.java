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
	private static final String REGISTER = "/registration";
	private static final String HOME = "/home";
	private static final String ROOT = "/";
	private static final String ABOUT = "/about";

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

		if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(".css") || uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(".ttf") || uri.endsWith(".png") || uri.endsWith(".jpeg")) {
			chain.doFilter(request, response);
			return;
		}
		
		// Get the session and check if user is logged in
		boolean isLoggedIn = SessionUtil.getAttribute(req, "username") != null;
		String userRole = CookiesUtil.getCookie(req, "Role") != null ? CookiesUtil.getCookie(req, "Role").getValue()
				: null;
		System.out.println(1);

		if ("admin".equals(userRole)) {
			// Admin is logged in
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath() + HOME);
				System.out.println(2);
			} else if (uri.endsWith(HOME) || uri.endsWith(ROOT)) {
				chain.doFilter(request, response);
			} else {
				System.out.println(3);
				res.sendRedirect(req.getContextPath() + HOME);
			}
		} else if ("customer".equals(userRole)) {
			System.out.println(4);
			// User is logged in
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath() + HOME);
				System.out.println(5);
			} else if (uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUT)) {
				System.out.println(6);
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + HOME);
			}
		} else {
			// Not logged in
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER) || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
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