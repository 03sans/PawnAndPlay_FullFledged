package com.pawnandplay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.pawnandplay.model.UserModel;
import com.pawnandplay.service.LoginService;
import com.pawnandplay.util.CookiesUtil;
import com.pawnandplay.util.RedirectionUtil;
import com.pawnandplay.util.SessionUtil;


/**
 * @author 23048503 Sanskriti Agrahari
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;
	
	@Override
	public void init() throws ServletException {
		this.loginService = new LoginService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserModel partnerModel = new UserModel(username, password);
		
		Boolean loginStatus = loginService.loginUser(partnerModel);

		if (loginStatus != null && loginStatus) {
			SessionUtil.setAttribute(req, "username", username); 
			if (username.equals("admin")) {
				CookiesUtil.addCookie(resp, "role", "admin", 5 * 30);
				resp.sendRedirect(req.getServletContext().getContextPath() +"/login"); // Redirect to /home
			} else {
				CookiesUtil.addCookie(resp, "role", "partner", 5 * 30);
				System.out.println("Login successfull");
				resp.sendRedirect(req.getServletContext().getContextPath() + "/home"); // Redirect to /home
			}
		} else {
			handleLoginFailure(req, resp, loginStatus);
		}
		
	}
	
	
	/**
	 * Handles login failures by setting attributes and forwarding to the login
	 * page.
	 *
	 * @param req         HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		req.setAttribute("error", errorMessage);
		req.getRequestDispatcher(RedirectionUtil.loginUrl).forward(req, resp);
	}

}
