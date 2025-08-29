package com.user.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationStore {

    public static List<Notification> seekerNotifications = new ArrayList<>();
    public static List<Notification> providerNotifications = new ArrayList<>();

    public static void addSeekerNotification(String providerName) {
        seekerNotifications.add(new Notification("New provider added: " + providerName));
    }

    public static void addProviderNotification(String seekerName, String serviceName) {
        providerNotifications.add(new Notification(
            "New booking request from " + seekerName + " for " + serviceName));
    }
}