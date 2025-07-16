package com.example.demo.service;

import com.example.demo.constant.TimeSlot;
import com.example.demo.constant.model.Booking;
import com.example.demo.constant.model.LiveShow;
import com.example.demo.constant.model.ShowSlot;
import com.example.demo.repository.*;

public class BookingService {
    private final LiveShowRepository showRepo;
    private final BookingRepository bookingRepo;

    public BookingService(LiveShowRepository showRepo, BookingRepository bookingRepo) {
        this.showRepo = showRepo;
        this.bookingRepo = bookingRepo;
    }

    public String bookTicket(String user, String showName, TimeSlot slot, int count) {
        LiveShow show = showRepo.findByName(showName);
        if (show == null) return "Show not found";

        ShowSlot showSlot = show.getSlots().get(slot);
        if (showSlot == null) return "Slot not available";

        // Check user has booking in same slot
        for (Booking booking : bookingRepo.findByUser(user)) {
            if (booking.getSlot() == slot) {
                return "User already has booking in this time slot";
            }
        }

        if (showSlot.getAvailableSeats() >= count) {
            showSlot.book(count);
            Booking booking = new Booking(user, showName, slot, count);
            bookingRepo.save(booking);
            return "Booked. Booking id: " + booking.getBookingId();
        } else {
            showSlot.addToWaitlist(user);
            return "Slot full. Added to waitlist.";
        }
    }

    public String cancelBooking(int bookingId) {
        Booking booking = bookingRepo.findById(bookingId);
        if (booking == null) return "Booking not found";

        LiveShow show = showRepo.findByName(booking.getShowName());
        if (show == null) return "Show not found";

        ShowSlot slot = show.getSlots().get(booking.getSlot());
        if (slot == null) return "Slot not found";

        // Cancel and update
        slot.cancel(booking.getCount());
        bookingRepo.delete(bookingId);

        // Move next waitlisted user (if any)
        String nextUser = slot.popFromWaitlist();
        if (nextUser != null) {
            bookTicket(nextUser, booking.getShowName(), booking.getSlot(), booking.getCount());
        }

        return "Booking Canceled";
    }
}
