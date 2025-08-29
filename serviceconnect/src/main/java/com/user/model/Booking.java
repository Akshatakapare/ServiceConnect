package com.user.model;

import java.time.LocalDateTime;

public class Booking {
    private String seekerName;
    private String providerName;
    private String serviceName;
    private LocalDateTime dateTime;
    private String status;  // pending / accepted / rejected

    // NEW FIELDS
    private String seekerAddress;
    private String note;
    private String time;
    private String duration;

    // OLD CONSTRUCTOR + new optional info
    public Booking(String seekerName, String providerName, String serviceName,
                   LocalDateTime dateTime) {
        this.seekerName = seekerName;
        this.providerName = providerName;
        this.serviceName = serviceName;
        this.dateTime = dateTime;
        this.status = "pending";
    }

    // NEW CONSTRUCTOR (if you want to include all details at creation time)
    public Booking(String seekerName, String providerName, String serviceName,
                   LocalDateTime dateTime, String seekerAddress, String note,
                   String time, String duration) {
        this(seekerName, providerName, serviceName, dateTime);
        this.seekerAddress = seekerAddress;
        this.note = note;
        this.time = time;
        this.duration = duration;
    }

    // getters
    public String getSeekerName() { return seekerName; }
    public String getProviderName() { return providerName; }
    public String getServiceName() { return serviceName; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getStatus() { return status; }

    // new getters
    public String getSeekerAddress() { return seekerAddress; }
    public String getNote() { return note; }
    public String getTime() { return time; }
    public String getDuration() { return duration; }

    public void setStatus(String status) { this.status = status; }
}