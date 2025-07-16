package com.example.demo;

import com.example.demo.constant.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Initialize repositories
            LiveShowRepository showRepo = new LiveShowRepository();
            BookingRepository bookingRepo = new BookingRepository();

            // Initialize services
            LiveShowService showService = new LiveShowService(showRepo);
            BookingService bookingService = new BookingService(showRepo, bookingRepo);
            SearchService searchService = new SearchService(showRepo);

            System.out.println("Live Show Booking System Ready!");

            while (true) {
                System.out.print("\n> ");
                String input = sc.nextLine().trim();

                try {
                    if (input.startsWith("registerShow:")) {
                        String[] parts = input.split("->");
                        String showName = parts[0].split(":")[1].trim();
                        Genre genre = Genre.valueOf(parts[1].trim().toUpperCase());
                        System.out.println(showService.registerShow(showName, genre));

                    } else if (input.startsWith("onboardShowSlots:")) {
                        String[] parts = input.split(":", 2)[1].trim().split(" ", 2);
                        if (parts.length < 2) {
                            System.out.println("Invalid command format for onboardShowSlots");
                            continue;
                        }

                        String showName = parts[0];
                        String[] slots = parts[1].split(",");

                        Map<TimeSlot, Integer> slotMap = new HashMap<>();
                        for (String slotEntry : slots) {
                            slotEntry = slotEntry.trim();
                            System.out.println("[DEBUG] Slot Entry: '" + slotEntry + "'");

                            int lastSpaceIdx = slotEntry.lastIndexOf(" ");
                            if (lastSpaceIdx == -1) {
                                System.out.println("[DEBUG] Skipping invalid slotEntry (no space found): " + slotEntry);
                                continue;
                            }

                            String timeRange = slotEntry.substring(0, lastSpaceIdx).trim();
                            String[] times = timeRange.split("-");
                            if (times.length != 2) {
                                System.out.println("[DEBUG] Invalid time range split: " + timeRange);
                                continue;
                            }

                            String startTime = times[0].trim();
                            String endTime = times[1].trim();
                            System.out.println("[DEBUG] Start: " + startTime + ", End: " + endTime);

                            int capacity;
                            try {
                                capacity = Integer.parseInt(slotEntry.substring(lastSpaceIdx + 1).trim());
                                System.out.println("[DEBUG] Capacity: " + capacity);
                            } catch (NumberFormatException e) {
                                System.out.println("[DEBUG] Invalid capacity: " + slotEntry);
                                continue;
                            }

                            TimeSlot ts = getTimeSlotFromTime(startTime);
                            if (ts == null) {
                                System.out.println("[DEBUG] Could not match startTime to TimeSlot: " + startTime);
                                continue;
                            }

                            slotMap.put(ts, capacity);
                        }

                        System.out.println(showService.onboardSlots(showName, slotMap));

                    } else if (input.startsWith("showAvailByGenre:")) {
                        String genreStr = input.split(":")[1].trim().toUpperCase();
                        Genre genre = Genre.valueOf(genreStr);
                        List<String> shows = searchService.showAvailByGenre(genre);
                        for (String s : shows) System.out.println(s);

                    } else if (input.startsWith("bookTicket:")) {
    int startIdx = input.indexOf("(");
    int endIdx = input.lastIndexOf(")");
    if (startIdx == -1 || endIdx == -1 || startIdx >= endIdx) {
        System.out.println("Invalid booking format.");
        continue;
    }

    String argsStr = input.substring(startIdx + 1, endIdx);
    String[] argsArr = Arrays.stream(argsStr.split(","))
        .map(String::trim)
        .toArray(String[]::new);

    if (argsArr.length != 4) {
        System.out.println("Invalid booking format.");
        continue;
    }

    String user = argsArr[0];
    String showName = argsArr[1];
    String timeStr = argsArr[2];
    TimeSlot slot = getTimeSlotFromHour(timeStr);
    if (slot == null) {
        System.out.println("Invalid slot time.");
        continue;
    }

    int count;
    try {
        count = Integer.parseInt(argsArr[3]);
    } catch (NumberFormatException e) {
        System.out.println("Invalid number of tickets.");
        continue;
    }

    String result = bookingService.bookTicket(user, showName, slot, count);
    System.out.println(result);


                    } else if (input.startsWith("cancelBookingId:")) {
                        int id = Integer.parseInt(input.split(":")[1].trim());
                        System.out.println(bookingService.cancelBooking(id));

                    } else if (input.equalsIgnoreCase("exit")) {
                        System.out.println("Goodbye!");
                        break;
                    } else {
                        System.out.println("Unknown command.");
                    }

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    private static TimeSlot getTimeSlotFromTime(String timeStr) {
        try {
            LocalTime inputTime = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern("H:mm"));
            for (TimeSlot ts : TimeSlot.values()) {
                if (ts.getStart().equals(inputTime)) {
                    return ts;
                }
            }
        } catch (Exception e) {
            System.out.println("[DEBUG] Failed to parse time: " + timeStr);
        }
        return null;
    }

    private static TimeSlot getTimeSlotFromHour(String hourStr) {
    try {
        LocalTime inputTime = LocalTime.parse(hourStr.trim());
        for (TimeSlot ts : TimeSlot.values()) {
            if (ts.getStart().equals(inputTime)) {
                return ts;
            }
        }
    } catch (Exception e) {
        System.out.println("[DEBUG] Invalid time format: " + hourStr);
    }
    return null;
}

}
