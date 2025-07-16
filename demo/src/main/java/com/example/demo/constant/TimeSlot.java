package com.example.demo.constant;

import java.time.LocalTime;

public enum TimeSlot {
    NINE_AM(LocalTime.of(9, 0), LocalTime.of(10, 0)),
    TEN_AM(LocalTime.of(10, 0), LocalTime.of(11, 0)),
    ELEVEN_AM(LocalTime.of(11, 0), LocalTime.of(12, 0)),
    TWELVE_PM(LocalTime.of(12, 0), LocalTime.of(13, 0)),
    ONE_PM(LocalTime.of(13, 0), LocalTime.of(14, 0)),
    TWO_PM(LocalTime.of(14, 0), LocalTime.of(15, 0)),
    THREE_PM(LocalTime.of(15, 0), LocalTime.of(16, 0)),
    FOUR_PM(LocalTime.of(16, 0), LocalTime.of(17, 0)),
    FIVE_PM(LocalTime.of(17, 0), LocalTime.of(18, 0)),
    SIX_PM(LocalTime.of(18, 0), LocalTime.of(19, 0)),
    SEVEN_PM(LocalTime.of(19, 0), LocalTime.of(20, 0)),
    EIGHT_PM(LocalTime.of(20, 0), LocalTime.of(21, 0));

    private final LocalTime start;
    private final LocalTime end;

    TimeSlot(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String toRangeString() {
        return start + "-" + end;
    }
}
