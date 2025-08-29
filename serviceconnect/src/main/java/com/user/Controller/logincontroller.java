// File: logincontoller.java (or rename to UserSession.java for clarity)
package com.user.Controller;

public class logincontroller {
    private static String currentUserEmail;

    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
        System.out.println(currentUserEmail);
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }
}