package com.example.demo.constant.model;

import com.example.demo.constant.Genre;
import com.example.demo.constant.TimeSlot;
import java.util.HashMap;
import java.util.Map;

public class LiveShow {
    private String showName;
    private Genre genre;
    private Map<TimeSlot, ShowSlot> slots = new HashMap<>();

    public LiveShow(String showName, Genre genre) {
        this.showName = showName;
        this.genre = genre;
    }

    public String getShowName() {
        return showName;
    }

    public Genre getGenre() {
        return genre;
    }

    public Map<TimeSlot, ShowSlot> getSlots() {
        return slots;
    }

    public void addSlot(TimeSlot slot, int capacity) {
        slots.putIfAbsent(slot, new ShowSlot(slot, capacity));
    }
}
