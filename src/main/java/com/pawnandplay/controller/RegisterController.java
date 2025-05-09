package com.pawnandplay.controller;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.pawnandplay.model.UserModel;
import com.pawnandplay.service.RegisterService;
import com.pawnandplay.util.PasswordUtil;
import com.pawnandplay.util.ValidationUtil;
import com.pawnandplay.util.ImageUtil;

/**
* @author 23048503 Sanskriti Agrahari
*/

@WebServlet(asyncSupported = true, urlPatterns = { "/registration" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ImageUtil imageUtil = new ImageUtil();
    private final RegisterService registerService = new RegisterService();

    // Handles GET request to show registration form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(request, response);
    }

    // Handles POST request for user registration
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Validate input fields
            String validationMessage = validateRegistrationForm(req);
            if (validationMessage != null) {
                handleError(req, resp, validationMessage);
                return;
            }

            // Extract and construct user model
            UserModel userModel = extractUserModel(req, resp);
            Boolean isAdded = registerService.addUser(userModel);

            // Handle registration result
            if (isAdded == null) {
                handleError(req, resp, "Our server is under maintenance. Please try again later!");
            } else if (isAdded) {
                try {
                    // Attempt to upload the image
                    if (image(req)) {
                        handleSuccess(req, resp, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");
                    } else {
                        handleError(req, resp, "Could not upload the image. Please try again later!");
                    }
                } catch (IOException | ServletException e) {
                    handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
                    e.printStackTrace(); // Log the exception for debugging
                }
            } else {
                handleError(req, resp, "Could not register your account. Please try again later!");
            }
        } catch (Exception e) {
            handleError(req, resp, "An unexpected error occurred. Please try again later!");
            e.printStackTrace(); // Log unexpected exceptions
        }
    }

    // Validates form inputs and returns a message if validation fails, or null if all is valid
    private String validateRegistrationForm(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("userName");
        String email = req.getParameter("email");
        String number = req.getParameter("number");
        String dobStr = req.getParameter("dob");
        String password = req.getParameter("password");
        String retypePassword = req.getParameter("confirmpassword");

        // Check for required fields
        if (ValidationUtil.isNullOrEmpty(firstName)) return "First name is required.";
        if (ValidationUtil.isNullOrEmpty(lastName)) return "Last name is required.";
        if (ValidationUtil.isNullOrEmpty(username)) return "Username is required.";
        if (ValidationUtil.isNullOrEmpty(email)) return "Email is required.";
        if (ValidationUtil.isNullOrEmpty(number)) return "Phone number is required.";
        if (ValidationUtil.isNullOrEmpty(dobStr)) return "Date of birth is required.";
        if (ValidationUtil.isNullOrEmpty(password)) return "Password is required.";
        if (ValidationUtil.isNullOrEmpty(retypePassword)) return "Please retype the password.";

        // Validate date format
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobStr);
        } catch (Exception e) {
            return "Invalid date format. Please use YYYY-MM-DD.";
        }

        // Format and rule validations
        if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
            return "Username must start with a letter and contain only alphanumeric characters.";
        if (!ValidationUtil.isValidEmail(email))
            return "Invalid email format.";
        if (!ValidationUtil.isValidPhoneNumber(number))
            return "Phone number must be 10 digits and start with 98.";
        if (!ValidationUtil.isValidPassword(password))
            return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 special character.";
        if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
            return "Passwords do not match.";
        if (!ValidationUtil.isAgeAtLeast16(dob))
            return "You must be at least 16 years old to register.";

        // Validate image
        try {
            Part image = req.getPart("image");
            if (!ValidationUtil.isValidImageExtension(image))
                return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
        } catch (IOException | ServletException e) {
            return "Error handling image file. Please ensure the file is valid.";
        }

        return null;
    }

    // Extracts form data and constructs the UserModel
    private UserModel extractUserModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("userName");
        String email = req.getParameter("email");
        String number = req.getParameter("number");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        String password = req.getParameter("password");

        // Encrypt password
        password = PasswordUtil.encrypt(username, password);

        // Process uploaded image
        Part image = req.getPart("image");
        String imageUrl = imageUtil.getImageNameFromPart(image);

        return new UserModel(firstName, lastName, username, email, number, dob, password, imageUrl);
    }

    // Handles image upload and returns true if successful
    private boolean image(HttpServletRequest req) throws IOException, ServletException {
        Part image = req.getPart("image");
        return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "User");
    }

    // Handles successful registration
    private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
            throws IOException {
        req.getSession().setAttribute("success", message); // Set success message for the next page
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    // Handles registration errors and repopulates the form
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("error", message);

        // Refill the form fields
        req.setAttribute("firstName", req.getParameter("firstName"));
        req.setAttribute("lastName", req.getParameter("lastName"));
        req.setAttribute("username", req.getParameter("userName"));
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("number", req.getParameter("number"));
        req.setAttribute("dob", req.getParameter("dob"));

        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
    }
}