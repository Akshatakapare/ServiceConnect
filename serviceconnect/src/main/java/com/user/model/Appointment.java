package com.user.model;


public class Appointment {
    private String title;
    private String dateTime;

    public Appointment(String title, String dateTime) {
        this.title = title;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDateTime() {
        return dateTime;
    }
}
 
