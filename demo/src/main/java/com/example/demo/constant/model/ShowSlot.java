package com.example.demo.constant.model;

import com.example.demo.constant.TimeSlot;
import java.util.LinkedList;
import java.util.Queue;

public class ShowSlot {
    private TimeSlot slot;
    private int capacity;
    private int booked;
    private Queue<String> waitlist = new LinkedList<>();

    public ShowSlot(TimeSlot slot, int capacity) {
        this.slot = slot;
        this.capacity = capacity;
        this.booked = 0;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public int getAvailableSeats() {
        return capacity - booked;
    }

    public boolean book(int count) {
        if (booked + count <= capacity) {
            booked += count;
            return true;
        }
        return false;
    }

    public void cancel(int count) {
        booked -= count;
    }

    public void addToWaitlist(String user) {
        waitlist.offer(user);
    }

    public String popFromWaitlist() {
        return waitlist.poll();
    }

    public boolean isFull() {
        return booked >= capacity;
    }

    public Queue<String> getWaitlist() {
        return waitlist;
    }
}
