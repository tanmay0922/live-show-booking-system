package com.example.demo.repository;

import java.util.*;

import com.example.demo.constant.model.Booking;

public class BookingRepository {
    private Map<Integer, Booking> bookingMap = new HashMap<>();
    private Map<String, List<Booking>> userBookings = new HashMap<>();

    public void save(Booking booking) {
        bookingMap.put(booking.getBookingId(), booking);
        userBookings.computeIfAbsent(booking.getUser(), k -> new ArrayList<>()).add(booking);
    }

    public Booking findById(int bookingId) {
        return bookingMap.get(bookingId);
    }

    public void delete(int bookingId) {
        Booking booking = bookingMap.remove(bookingId);
        if (booking != null) {
            List<Booking> userBookingList = userBookings.get(booking.getUser());
            if (userBookingList != null) {
                userBookingList.removeIf(b -> b.getBookingId() == bookingId);
            }
        }
    }

    public List<Booking> findByUser(String user) {
        return userBookings.getOrDefault(user, new ArrayList<>());
    }
}
