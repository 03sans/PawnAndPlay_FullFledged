package com.pawnandplay.controller;

import java.io.IOException;

import com.pawnandplay.util.CookiesUtil;
import com.pawnandplay.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author 23048503 Sanskriti Agrahari
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CookiesUtil.deleteCookie(resp, "role");
		SessionUtil.invalidateSession(req);
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}