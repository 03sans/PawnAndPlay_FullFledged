package com.pawnandplay.controller;

import java.time.LocalDate;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import com.pawnandplay.model.UserModel;
import com.pawnandplay.service.RegisterService;
import com.pawnandplay.util.PasswordUtil;
import com.pawnandplay.util.RedirectionUtil;
import com.pawnandplay.util.ValidationUtil;

/**
 * @author 23048503 Sanskriti Agrahari
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/registration"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
				 maxFileSize = 1024 * 1024 * 10,
				 maxRequestSize = 1024 * 1024 * 50)
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService registerService;
	private RedirectionUtil redirectionUtil;

	@Override
	public void init() throws ServletException {
		this.registerService = new RegisterService();
		this.redirectionUtil = new RedirectionUtil();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String validationMessage = validateRegistrationForm(req);
		if (validationMessage != null) {
				handleError(req,resp,validationMessage);
				return;
		}
			
			UserModel userModel = extractUserModel(req, resp);
			Boolean isAdded = registerService.addUser(userModel);

			if (isAdded == null) {
				req.setAttribute("error", "Our service is under maintainance. Please try again later");
			} else if (!isAdded) {
				handleError(req, resp, "Could not register your account. Please try again later!");
			} else {
				req.setAttribute("success", "Your account was created!");
			}
		} catch (Exception e) {
			handleError(req, resp, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}
	
	private String validateRegistrationForm(HttpServletRequest req) {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("userName");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		String dobStr = req.getParameter("dob");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("confirmpassword");

		// Check for null or empty fields first
		if (ValidationUtil.isNullOrEmpty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(number))
			return "Phone number is required.";
		if (ValidationUtil.isNullOrEmpty(dobStr))
			return "Date of birth is required.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(retypePassword))
			return "Please retype the password.";


		// Validate fields
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(number))
			return "Phone number must be 10 digits and start with 98.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";
		
		return null;
	}
	
	private UserModel extractUserModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String username = req.getParameter("userName");
		String email = req.getParameter("email");
		String number = req.getParameter("number");
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		String password = req.getParameter("password");
		
		password = PasswordUtil.encrypt(username, password);
		
		if (password == null) {
			redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please correct your password & retype-password!",
					RedirectionUtil.registerUrl);
			}

		return new UserModel(firstName, lastName, username, email, number, dob, password);
	}
	
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("firstName", req.getParameter("firstName"));
		req.setAttribute("lastName", req.getParameter("lastName"));
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("number", req.getParameter("phoneNumber"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}

}