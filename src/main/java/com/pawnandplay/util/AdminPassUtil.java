package com.pawnandplay.util;

public class AdminPassUtil {
	public static void main(String[] args) {
        String username = "admin";
        String password = "Pass123!";

        String encrypted = PasswordUtil.encrypt(username, password);
        System.out.println("Encrypted password: " + encrypted);
    }
}
