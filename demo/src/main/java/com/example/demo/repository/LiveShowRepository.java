package com.example.demo.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.constant.model.LiveShow;

public class LiveShowRepository {
    private Map<String, LiveShow> showMap = new HashMap<>();

    public void save(LiveShow show) {
        showMap.put(show.getShowName(), show);
    }

    public LiveShow findByName(String showName) {
        return showMap.get(showName);
    }

    public Collection<LiveShow> findAll() {
        return showMap.values();
    }

    public boolean exists(String showName) {
        return showMap.containsKey(showName);
    }
}
