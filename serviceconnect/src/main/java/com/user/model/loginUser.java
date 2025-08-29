package com.user.model;

public class loginUser {
    public static String userName;
    public static String userEmail;

    public static void setLoginUser(String name, String email) {
        loginUser.userName = name;
        loginUser.userEmail = email;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    
}
