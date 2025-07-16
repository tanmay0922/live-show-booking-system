package com.example.demo.service;

import com.example.demo.constant.Genre;
import com.example.demo.constant.model.LiveShow;
import com.example.demo.constant.model.ShowSlot;
import com.example.demo.repository.LiveShowRepository;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private final LiveShowRepository showRepo;

    public SearchService(LiveShowRepository showRepo) {
        this.showRepo = showRepo;
    }

    public List<String> showAvailByGenre(Genre genre) {
        List<String> results = new ArrayList<>();

        for (LiveShow show : showRepo.findAll()) {
            if (show.getGenre() == genre) {
                for (var entry : show.getSlots().entrySet()) {
                    ShowSlot slot = entry.getValue();
                    if (slot.getAvailableSeats() > 0) {
                        results.add(show.getShowName() + ": (" + entry.getKey().toRangeString() + ") " + slot.getAvailableSeats());
                    }
                }
            }
        }

        return results;
    }
}
