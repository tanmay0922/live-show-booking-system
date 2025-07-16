package com.example.demo.service;

import com.example.demo.constant.Genre;
import com.example.demo.constant.TimeSlot;
import com.example.demo.constant.model.LiveShow;
import com.example.demo.repository.LiveShowRepository;

import java.util.Map;

public class LiveShowService {
    private final LiveShowRepository showRepo;

    public LiveShowService(LiveShowRepository showRepo) {
        this.showRepo = showRepo;
    }

    public String registerShow(String showName, Genre genre) {
        if (showRepo.exists(showName)) {
            return "Show already exists!";
        }
        LiveShow show = new LiveShow(showName, genre);
        showRepo.save(show);
        return showName + " show is registered !!";
    }

    public String onboardSlots(String showName, Map<TimeSlot, Integer> slotCapacities) {
        LiveShow show = showRepo.findByName(showName);
        if (show == null) {
            return "Show not found!";
        }

        for (Map.Entry<TimeSlot, Integer> entry : slotCapacities.entrySet()) {
            show.addSlot(entry.getKey(), entry.getValue());
        }

        return "Done!";
    }
}
