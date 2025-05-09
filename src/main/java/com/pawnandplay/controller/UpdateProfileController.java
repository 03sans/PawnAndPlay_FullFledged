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

/**
 * 
 * @author 23048503 Sanskriti Agrahari
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/updateprofile" })
public class UpdateProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UpdateProfileService editprofileService;

    @Override
    public void init() throws ServletException {
        // Initialize the service used to handle profile update logic
        this.editprofileService = new UpdateProfileService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Load the update profile JSP page
        request.getRequestDispatcher("/WEB-INF/pages/updateprofile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve the current username from session
            String currentUsername = (String) SessionUtil.getAttribute(req, "username");

            // Get the new password or use the existing one if the field is left blank
            String password = req.getParameter("Password");
            String finalPassword;

            if (password != null && !password.isEmpty()) {
                finalPassword = PasswordUtil.encrypt(currentUsername, password);
            } else {
                // Do not re-encrypt, just reuse the already encrypted password
                finalPassword = editprofileService.getExistingPassword(currentUsername);
            }

            // Extract user input and build updated UserModel
            try {
                UserModel userModel = extractUserModel(req, finalPassword);
                Boolean isUpdated = editprofileService.updateUser(userModel, currentUsername);

                if (isUpdated == null) {
                    handleError(req, resp, "Our service is under maintenance. Please try again later.");
                } else if (!isUpdated) {
                    handleError(req, resp, "Could not update your profile. Please try again later!");
                } else {
                    // Update session if username was changed
                    SessionUtil.setAttribute(req, "username", userModel.getUsername());
                    resp.sendRedirect(req.getContextPath() + "/profile");
                }

            } catch (IllegalArgumentException e) {
                // Input validation failed
                handleError(req, resp, e.getMessage());
            }

        } catch (Exception e) {
            // Catch-all for unexpected errors
            handleError(req, resp, "An unexpected error occurred. Please try again later!");
            e.printStackTrace();
        }
    }

    /**
     * Extracts user input fields from the request and constructs a UserModel.
     * Validates required fields and date format.
     */
    private UserModel extractUserModel(HttpServletRequest req, String finalPassword) throws Exception {
        String firstName = req.getParameter("Firstname");
        String lastName = req.getParameter("Lastname");
        String username = req.getParameter("Username");
        String email = req.getParameter("Email");
        String number = req.getParameter("Phone");

        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }

        String dobParam = req.getParameter("dob");
        LocalDate dob = null;

        if (dobParam != null && !dobParam.isEmpty()) {
            try {
                dob = LocalDate.parse(dobParam);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid date format. Expected YYYY-MM-DD.");
            }
        }

        String imageUrl = ""; // Optional: Handle image uploading here

        return new UserModel(firstName, lastName, username, email, number, dob, finalPassword, imageUrl);
    }

    /**
     * Handles error forwarding by setting request attributes and redirecting back to the form.
     */
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.setAttribute("firstName", req.getParameter("Firstname"));
        req.setAttribute("lastName", req.getParameter("Lastname"));
        req.setAttribute("username", req.getParameter("Username"));
        req.setAttribute("email", req.getParameter("Email"));
        req.setAttribute("number", req.getParameter("Phone"));
        req.setAttribute("dob", req.getParameter("dob"));

        req.getRequestDispatcher("/WEB-INF/pages/updateprofile.jsp").forward(req, resp);
    }
}