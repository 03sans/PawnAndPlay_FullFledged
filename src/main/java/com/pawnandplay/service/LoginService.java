package com.pawnandplay.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.pawnandplay.config.DbConfig;
import com.pawnandplay.model.UserModel;
import com.pawnandplay.util.PasswordUtil;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * LoginService handles user authentication and user data retrieval from the database.
 */
public class LoginService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    /**
     * Constructor attempts to establish a database connection.
     */
    public LoginService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    /**
     * Validates user credentials.
     * @param userModel User input credentials
     * @return true if login is successful, false if credentials mismatch, null if DB error
     */
    public Boolean loginUser(UserModel userModel) {
        if (isConnectionError) {
            return null; // Database connection issue
        }

        String query = "SELECT Username, Password FROM User WHERE Username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, userModel.getUsername());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return validatePassword(result, userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return false; // No user found or invalid credentials
    }

    /**
     * Retrieves complete user details by username.
     * @param username Username to fetch data for
     * @return Populated UserModel object, or null if not found
     */
    public UserModel getUserDetails(String username) {
        UserModel user = null;
        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE Username = ?")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setUserId(rs.getString("UserID"));
                user.setFirstName(rs.getString("Firstname"));
                user.setLastName(rs.getString("Lastname"));
                user.setUsername(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setNumber(rs.getString("Phone"));
                user.setPassword(rs.getString("Password"));

                // Handle profile image
                String image = rs.getString("image");
                if (image == null || image.isEmpty()) {
                    image = "resources/images/default.jpg"; // Set to default if none
                }
                user.setImage(image);

                // Handle date of birth conversion
                String dobString = rs.getString("dob");
                if (dobString != null) {
                    try {
                        user.setDob(LocalDate.parse(dobString));
                    } catch (DateTimeParseException e) {
                        user.setDob(null); // If format is invalid
                    }
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Retrieves the role of a user from the database.
     * @param username Username to lookup
     * @return Role as a String (e.g., "admin", "customer"), or null if error
     */
    public String getUserRole(String username) {
        if (isConnectionError) {
            return null;
        }

        String query = "SELECT Role FROM User WHERE Username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return result.getString("Role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Validates user-entered password against the decrypted password from DB.
     * @param result ResultSet containing user data
     * @param userModel User input model
     * @return true if passwords match, false otherwise
     */
    private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
        String dbUsername = result.getString("Username");
        String dbPassword = result.getString("Password");

        String decryptedPassword = PasswordUtil.decrypt(dbPassword, dbUsername);

        return dbUsername.equals(userModel.getUsername())
                && decryptedPassword != null
                && decryptedPassword.equals(userModel.getPassword());
    }
}