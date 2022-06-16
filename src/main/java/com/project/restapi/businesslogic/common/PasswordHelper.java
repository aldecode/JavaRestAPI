package com.project.restapi.businesslogic.common;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHelper {
    public static String HashPassword(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean VerifyPassword(String password, String hashedPassword){
        return BCrypt.verifyer().verify(password.toCharArray(), hashedPassword.toCharArray()).verified;
    }
}
