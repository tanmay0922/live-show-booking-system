package com.example.demo.constant.model;

import com.example.demo.constant.TimeSlot;

public class Booking {
    private static int idCounter = 1000;
    private final int bookingId;
    private final String user;
    private final String showName;
    private final TimeSlot slot;
    private final int count;

    public Booking(String user, String showName, TimeSlot slot, int count) {
        this.user = user;
        this.showName = showName;
        this.slot = slot;
        this.count = count;
        this.bookingId = idCounter++;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getUser() {
        return user;
    }

    public String getShowName() {
        return showName;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public int getCount() {
        return count;
    }
}
