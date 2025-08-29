package com.user.model;

import java.util.ArrayList;
import java.util.List;

public class BookingStore {

    public static List<Booking> upcomingBookings = new ArrayList<>();

    public static void acceptBooking(Booking booking) {
        booking.setStatus("accepted");
        upcomingBookings.add(booking);
    }

    public static void rejectBooking(Booking booking) {
        booking.setStatus("rejected");
    }
}