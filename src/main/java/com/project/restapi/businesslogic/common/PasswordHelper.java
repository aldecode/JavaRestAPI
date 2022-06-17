package com.project.restapi.businesslogic.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHelper {
    public static String HashPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean VerifyPassword(String password, String hashedPassword){
        return new BCryptPasswordEncoder().matches(password, hashedPassword);
    }
}
