package com.pawnandplay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.pawnandplay.model.UserModel;
import com.pawnandplay.service.LoginService;
import com.pawnandplay.util.SessionUtil;
import com.pawnandplay.util.CookiesUtil;
import com.pawnandplay.util.RedirectionUtil;

/** 
 * @author 23048503 Sanskriti Agrahari
 * */

@WebServlet(urlPatterns = {"/login"})
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        UserModel user = new UserModel(username, password);
        Boolean loginStatus = loginService.loginUser(user);
        
        if (loginStatus != null && loginStatus) {
			SessionUtil.setAttribute(req, "username", username); 
			if (username.equals("admin")) {
				CookiesUtil.addCookie(resp, "Role", "admin", 5 * 30);
				resp.sendRedirect(req.getServletContext().getContextPath() +"/home"); // Redirect to /home
			} else {
				CookiesUtil.addCookie(resp, "Role", "customer", 5 * 30);
				System.out.println("Login successfull");
				resp.sendRedirect(req.getServletContext().getContextPath() + "/home"); // Redirect to /home
			}
		} else {
			handleLoginFailure(req, resp, loginStatus);
		}
		
	}
		
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