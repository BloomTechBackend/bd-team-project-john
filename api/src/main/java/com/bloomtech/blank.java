package com.bloomtech;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class blank {
    public static void main(String[] args) {
        System.out.println("This is it");
        System.out.println(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).with(TemporalAdjusters.lastDayOfMonth()).toString().
                compareTo("2024-06-14T03:33:43.976Z")<0);
    }
}
