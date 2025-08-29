package com.user.model;

import java.time.LocalDateTime;

public class Notification {
    private String message;
    private LocalDateTime dateTime;
    private String status; // For provider notifications: "pending", "accepted", "rejected"

    public Notification(String message) {
        this.message = message;
        this.dateTime = LocalDateTime.now();
        this.status = "pending";
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}