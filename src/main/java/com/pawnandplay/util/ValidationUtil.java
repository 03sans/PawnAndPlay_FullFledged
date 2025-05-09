package com.pawnandplay.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
import jakarta.servlet.http.Part;

public class ValidationUtil {

    // --------------------- Generic String & Input Validations ---------------------

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isAlphabetic(String value) {
        return value != null && value.matches("^[a-zA-Z]+$");
    }

    public static boolean isAlphanumericStartingWithLetter(String value) {
        return value != null && value.matches("^[a-zA-Z][a-zA-Z0-9]*$");
    }

    // --------------------- Email, Phone, and Password Validations ---------------------

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPhoneNumber(String number) {
        return number != null && number.matches("^98\\d{8}$");
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }

    public static boolean doPasswordsMatch(String password, String retypePassword) {
        return password != null && password.equals(retypePassword);
    }

    // --------------------- File and Date Validations ---------------------

    public static boolean isValidImageExtension(Part imagePart) {
        if (imagePart == null || isNullOrEmpty(imagePart.getSubmittedFileName())) {
            return false;
        }
        String fileName = imagePart.getSubmittedFileName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
               fileName.endsWith(".png") || fileName.endsWith(".gif");
    }

    public static boolean isAgeAtLeast16(LocalDate dob) {
        if (dob == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        return Period.between(dob, today).getYears() >= 16;
    }

    // --------------------- GamesModel Validations ---------------------

    public static boolean isValidGameID(String gameIDText) {
        if (isNullOrEmpty(gameIDText)) {
            return false;
        }
        try {
            int gameID = Integer.parseInt(gameIDText);
            return gameID > 0 && gameID < 10000;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidGamename(String gamename) {
        return gamename != null && gamename.matches("^[a-zA-Z0-9\\s]{2,50}$");
    }

    public static boolean isValidLevel(String level) {
        return level != null && level.matches("^(Beginner|Intermediate|Expert)$");
    }

    public static boolean isValidGenre(String genre) {
        return genre != null && genre.matches("^(Strategy|Party|Family|Cooperative|Adventure|Fantasy|Sci-Fi|Historical)$");
    }

    public static boolean isValidAge(String ageText) {
        if (isNullOrEmpty(ageText)) {
            return false;
        }
        try {
            int age = Integer.parseInt(ageText);
            return age >= 4 && age <= 99;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidPrice(String priceText) {
        if (isNullOrEmpty(priceText)) {
            return false;
        }
        try {
            double price = Double.parseDouble(priceText);
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidStock(String stockText) {
        if (isNullOrEmpty(stockText)) {
            return false;
        }
        try {
            int stock = Integer.parseInt(stockText);
            return stock >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidBrand(String brand) {
        return brand != null && brand.matches("^[a-zA-Z0-9\\s]{2,50}$");
    }
}