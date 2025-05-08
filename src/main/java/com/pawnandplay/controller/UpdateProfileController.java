package com.pawnandplay.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.pawnandplay.model.UserModel;
import com.pawnandplay.service.UpdateProfileService;
import com.pawnandplay.util.PasswordUtil;
import com.pawnandplay.util.SessionUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/updateprofile" })
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UpdateProfileService editprofileService;

	@Override
	public void init() throws ServletException {
		this.editprofileService = new UpdateProfileService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/updateprofile.jsp").forward(request, response);
		System.out.println("UpdateProfileController doGet() called");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    System.out.println("UpdateProfileController doPost() triggered");

	    try {
	        // Retrieve current username from session
	        String currentUsername = SessionUtil.getAttribute(req, "username").toString();
	        System.out.println("updateprofile doPost username = " + currentUsername);

	        String password = req.getParameter("password");
	        String confirmPassword = req.getParameter("confirmpassword");
	        String finalPassword;

	        if ((password != null && !password.isEmpty()) || (confirmPassword != null && !confirmPassword.isEmpty())) {
	            if (!password.equals(confirmPassword)) {
	                handleError(req, resp, "Passwords do not match. Please re-enter your password.");
	                return;
	            }
	            finalPassword = PasswordUtil.encrypt(currentUsername, password);
	        } else {
	            finalPassword = editprofileService.getExistingPassword(currentUsername);
	            if (finalPassword == null) {
	                handleError(req, resp, "Unable to load existing password.");
	                return;
	            }
	        }
	        try {
	            UserModel userModel = extractUserModel(req, finalPassword);
	            Boolean isUpdated = editprofileService.updateUser(userModel, currentUsername);
	            System.out.println("isUpdated = " + isUpdated);

	            if (isUpdated == null) {
	                handleError(req, resp, "Our service is under maintenance. Please try again later.");
	            } else if (!isUpdated) {
	                handleError(req, resp, "Could not update your profile. Please try again later!");
	            } else {
	                SessionUtil.setAttribute(req, "username", userModel.getUsername());
	                resp.sendRedirect(req.getContextPath() + "/profile.jsp");
	            }
	        } catch (IllegalArgumentException e) {
	            handleError(req, resp, e.getMessage()); // Show specific validation error
	            return;
	        }

	    } catch (Exception e) {
	        handleError(req, resp, "An unexpected error occurred. Please try again later!");
	        e.printStackTrace();
	    }
	}

	private UserModel extractUserModel(HttpServletRequest req, String finalPassword) throws Exception {
	    String firstName = req.getParameter("firstName");
	    String lastName = req.getParameter("lastName");
	    String username = req.getParameter("userName");
	    String email = req.getParameter("email");
	    String number = req.getParameter("number");
	    
	    if (firstName == null || firstName.trim().isEmpty()) {
	        throw new IllegalArgumentException("First name cannot be empty.");
	    }

	    // Get dob, but do not throw an error if it is missing
	    String dobParam = req.getParameter("dob");
	    LocalDate dob = null;
	    if (dobParam != null && !dobParam.isEmpty()) {
	        try {
	            dob = LocalDate.parse(dobParam); // Try parsing the date if provided
	        } catch (Exception e) {
	            throw new IllegalArgumentException("Invalid date format. Expected YYYY-MM-DD.");
	        }
	    }

	    String imageUrl = ""; // Handle image upload if required

	    return new UserModel(firstName, lastName, username, email, number, dob, finalPassword, imageUrl);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("firstName", req.getParameter("firstName"));
		req.setAttribute("lastName", req.getParameter("lastName"));
		req.setAttribute("username", req.getParameter("userName"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("number", req.getParameter("number"));
		req.setAttribute("dob", req.getParameter("dob"));
		req.getRequestDispatcher("/WEB-INF/pages/updateprofile.jsp").forward(req, resp);
	}
}